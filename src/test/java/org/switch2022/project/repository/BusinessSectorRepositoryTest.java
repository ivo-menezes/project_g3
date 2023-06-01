package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.datamodel.JPA.BusinessSectorJPA;
import org.switch2022.project.datamodel.JPA.assemblers.BusinessSectorDomainDataAssembler;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.repository.JPA.BusinessSectorRepositoryJPA;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class BusinessSectorRepositoryTest {

    @MockBean
    BusinessSectorRepositoryJPA businessSectorRepositoryJPA;

    @MockBean
    BusinessSectorDomainDataAssembler businessSectorDomainDataAssembler;

    @InjectMocks
    BusinessSectorRepository businessSectorRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Ensure that business Sector was successfully saved.")
    @Test
    void saveBusinessSectorSuccess() {

        //Arrange
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        BusinessSectorDDD businessSector = mock(BusinessSectorDDD.class);
        when(businessSector.getBusinessSectorDesignation()).thenReturn(businessSectorDesignation);

        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA(businessSectorDesignation.toString());
        BusinessSectorJPA savedBusinessSectorJPA = new BusinessSectorJPA(businessSectorDesignation.toString());

        when(businessSectorRepositoryJPA.existsByBusinessSectorDesignation
                (businessSector.getBusinessSectorDesignation().toString())).thenReturn(false);
        when(businessSectorDomainDataAssembler.toData(businessSector)).thenReturn(businessSectorJPA);
        when(businessSectorRepositoryJPA.save(businessSectorJPA)).thenReturn(savedBusinessSectorJPA);
        when(businessSectorDomainDataAssembler.toDomain(savedBusinessSectorJPA)).thenReturn(businessSector);

        //Act
        BusinessSectorDDD result = businessSectorRepository.save(businessSector);

        //Assert
        assertEquals(businessSector,result);
    }

    @DisplayName("Ensure that the same business sector cannot saved - throws exception")
    @Test
    void saveTwoBusinessSectorFails() {

        //Arrange
        BusinessSectorDesignation businessSectorDesignation = mock(BusinessSectorDesignation.class);
        when(businessSectorDesignation.toString()).thenReturn("Fixed cost");
        BusinessSectorDDD businessSector = mock(BusinessSectorDDD.class);
        when(businessSector.getBusinessSectorDesignation()).thenReturn(businessSectorDesignation);

        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA(businessSectorDesignation.toString());
        BusinessSectorJPA savedBusinessSectorJPA = new BusinessSectorJPA(businessSectorDesignation.toString());

        when(businessSectorRepositoryJPA.existsByBusinessSectorDesignation
                (businessSector.getBusinessSectorDesignation().toString())).thenReturn(true);
        when(businessSectorDomainDataAssembler.toData(businessSector)).thenReturn(businessSectorJPA);
        when(businessSectorRepositoryJPA.save(businessSectorJPA)).thenReturn(savedBusinessSectorJPA);
        when(businessSectorDomainDataAssembler.toDomain(savedBusinessSectorJPA)).thenReturn(businessSector);

        String expectedMessage = "There is a business sector with this designation";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            businessSectorRepository.save(businessSector);
        });
       String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage,resultMessage);
    }

    @DisplayName("Ensure that getAll method was successfully returned.")
    @Test
    void getAllBusinessSectorSuccess() {

        //Arrange
        BusinessSectorDDD businessSector = mock(BusinessSectorDDD.class);
        BusinessSectorJPA businessSectorJPA = new BusinessSectorJPA("test");

        List<BusinessSectorJPA> listJPA = new ArrayList<>();
        listJPA.add(businessSectorJPA);

        ArrayList<BusinessSectorDDD> expected = new ArrayList<>();
        expected.add(businessSector);

        when(businessSectorRepositoryJPA.findAll()).thenReturn(listJPA);

        when(businessSectorDomainDataAssembler.toDomain(any())).thenReturn(businessSector);

        //Act
        ArrayList<BusinessSectorDDD> result = businessSectorRepository.getAll();

        //Assert
        assertEquals(expected,result);
    }
}