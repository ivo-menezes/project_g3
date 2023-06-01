package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.BusinessSectorDTO;
import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.businessSector.IBusinessSectorFactory;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class BusinessSectorServiceTest {

    @MockBean
    IBusinessSectorFactory businessSectorFactory;

    @MockBean
    IBusinessSectorRepository businessSectorRepository;

    @Autowired
    BusinessSectorService businessSectorService;

    @DisplayName("assert that creating a businessSectorService with null businessSectorFactory throws Exception")
    @Test
    void createBusinessSectorNullFactoryThrowsException() {
        //Arrange
        IBusinessSectorFactory factory = null;
        IBusinessSectorRepository businessSectorRepositoryDouble = mock(IBusinessSectorRepository.class);
        String expectedMessage = "businessSectorFactory must not be null.";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSectorService(factory, businessSectorRepositoryDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a businessSectorService with null businessSectorRepository throws Exception")
    @Test
    void createBusinessSectorNullRepositoryThrowsException() {
        //Arrange
        IBusinessSectorFactory factoryDouble = mock(IBusinessSectorFactory.class);
        IBusinessSectorRepository businessSectorRepositoryDouble = null;
        String expectedMessage = "businessSectorRepository must not be null.";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSectorService(factoryDouble, businessSectorRepositoryDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a business sector succeeds")
    @Test
    void createBusinessSectorSuccess() {
        //Arrange

        BusinessSectorDTO businessSector = mock(BusinessSectorDTO.class);
        BusinessSectorDDD businessSectorDDD =  mock(BusinessSectorDDD.class);

        IBusinessSectorFactory factory = mock(IBusinessSectorFactory.class);
        when(factory.createBusinessSector(any(),any())).thenReturn(businessSectorDDD);

        IBusinessSectorRepository repository = mock(IBusinessSectorRepository.class);
        when(repository.save(businessSectorDDD)).thenReturn(businessSectorDDD);

        BusinessSectorService service = new BusinessSectorService(factory,repository);

        //Act
        BusinessSectorDTO result = service.createBusinessSector(businessSector);

        //Assert
        assertEquals(businessSector,result);
    }

    @DisplayName("Ensure that getAll method was successfully returned.")
    @Test
    void getAllBusinessSectorsSuccess() {

        //Arrange
        BusinessSectorID id = new BusinessSectorID(Long.valueOf(1));
        BusinessSectorDesignation designation = new BusinessSectorDesignation("Test");
        BusinessSectorDTO businessSector = new BusinessSectorDTO();
        businessSector.businessSectorID = id;
        businessSector.businessSectorDesignation = designation;

        BusinessSectorDDD businessSectorDDD = mock(BusinessSectorDDD.class);

        ArrayList<BusinessSectorDDD> listDDD = new ArrayList<>();
        listDDD.add(businessSectorDDD);

        ArrayList<BusinessSectorDTO> expected = new ArrayList<>();
        expected.add(businessSector);

        when(businessSectorRepository.getAll()).thenReturn(listDDD);
        when(businessSectorDDD.identity()).thenReturn(id);
        when(businessSectorDDD.getBusinessSectorDesignation()).thenReturn(designation);

        //Act
        ArrayList<BusinessSectorDTO> result = businessSectorService.getAll();

        //Assert
        assertEquals(expected, result);
    }
}