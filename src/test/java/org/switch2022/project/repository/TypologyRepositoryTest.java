package org.switch2022.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.datamodel.JPA.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.repository.JPA.TypologyJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class TypologyRepositoryTest {

    @MockBean
    TypologyJpaRepository typologyJpaRepository;

    @MockBean
    TypologyDomainDataAssembler typologyDomainDataAssembler;

    @InjectMocks
    TypologyRepository typologyRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Ensure Typology is successfully saved")
    void ensureTypologyIsSaved(){
        //Arrange
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyDDD savedTypology = mock(TypologyDDD.class);
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        TypologyJpa savedTypologyJpa = mock(TypologyJpa.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        when(typology.getTypologyDesignation()).thenReturn(typologyDesignation);
        when(typologyDomainDataAssembler.toData(typology)).thenReturn(typologyJpa);
        when(typologyJpaRepository.save(typologyJpa)).thenReturn(savedTypologyJpa);
        when(typologyDomainDataAssembler.toDomain(savedTypologyJpa)).thenReturn(savedTypology);

        //Act
        TypologyDDD expectedTypology = typologyRepository.save(typology);

        //Assert
        assertEquals(expectedTypology, savedTypology);
    }

    @Test
    @DisplayName("Ensure exception is thrown while saving already existing typology")
    void ensureExceptionIsThrownWhileSavingAlreadyExistingTypology(){
        //Arrange
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        String designation = "Fixed cost";

        when(typology.getTypologyDesignation()).thenReturn(typologyDesignation);
        when(typologyDesignation.toString()).thenReturn(designation);
        when(typologyJpaRepository.existsByTypologyDesignation(designation)).thenReturn(true);

        String expectedMessage = "Typology already exists";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> typologyRepository.save(typology));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Ensure TypologyRepository returns true when TypologyJpa exists in JpaRepository")
    void ensureRepositoryReturnsTrueWhenTypologyExistsInPersistence(){
        //Arrange

        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        String designation = "Fixed cost";
        when(typologyDesignation.toString()).thenReturn(designation);
        when(typologyJpaRepository.existsByTypologyDesignation(designation)).thenReturn(true);

        //Act
        boolean result = typologyRepository.containsTypologyDesignation(typologyDesignation);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure TypologyRepository returns false when TypologyJpa does not exist in JpaRepository")
    void ensureRepositoryReturnsFalseWhenTypologyDoesNotExistInPersistence(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        String designation = "Fixed cost";
        when(typologyDesignation.toString()).thenReturn(designation);
        when(typologyJpaRepository.existsByTypologyDesignation(designation)).thenReturn(false);

        //Act
        boolean result = typologyRepository.containsTypologyDesignation(typologyDesignation);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure typology is retrieved given a TypologyDesignation, when it exists in the persistence")
    void ensureTypologyIsRetrieved (){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        TypologyDDD expectedTypology = mock(TypologyDDD.class);
        Optional<TypologyDDD> oTypology = Optional.of(expectedTypology);

        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Optional<TypologyJpa> oTypologyJpa = Optional.of(typologyJpa);

        String designation = "Fixed cost";

        when(typologyDesignation.toString()).thenReturn(designation);
        when(typologyJpaRepository.getByTypologyDesignation(designation)).thenReturn(oTypologyJpa);
        when(typologyDomainDataAssembler.toDomain(typologyJpa)).thenReturn(expectedTypology);

        //Act
        Optional<TypologyDDD> result = typologyRepository.getByDesignation(typologyDesignation);

        //Assert
        assertEquals(oTypology, result);
    }

    @DisplayName("Ensure that getAll method was successfully returned.")
    @Test
    void getAllTypologiesSuccess() {

        //Arrange
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyJpa typologyJPA = new TypologyJpa("test");

        List<TypologyJpa> listJPA = new ArrayList<>();
        listJPA.add(typologyJPA);

        ArrayList<TypologyDDD> expected = new ArrayList<>();
        expected.add(typology);

        when(typologyJpaRepository.findAll()).thenReturn(listJPA);

        when(typologyDomainDataAssembler.toDomain(any())).thenReturn(typology);

        //Act
        ArrayList<TypologyDDD> result = typologyRepository.getAll();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("Should return true when typologyID exists")
    void returnsTrueWhenTypologyIDExists(){
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        when(typologyID.getId()).thenReturn(1000L);
        when(typologyJpaRepository.existsById(typologyID.getId())).thenReturn(true);

        //Act
        boolean result = typologyJpaRepository.existsById(typologyID.getId());

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when typologyID does not exist")
    void returnsFalseWhenTypologyIDDoesNotExist(){
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        when(typologyID.getId()).thenReturn(1000L);
        when(typologyJpaRepository.existsById(typologyID.getId())).thenReturn(false);

        //Act
        boolean result = typologyJpaRepository.existsById(typologyID.getId());

        //Assert
        assertFalse(result);
    }
}

