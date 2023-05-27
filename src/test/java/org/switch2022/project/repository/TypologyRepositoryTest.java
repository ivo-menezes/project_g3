package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.datamodel.JPA.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.repository.JPA.TypologyJpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyRepositoryTest {

    @Test
    @DisplayName("Ensure Typology is successfully saved")
    void ensureTypologyIsSaved(){
        //Arrange
        TypologyJpaRepository jpaRepository = mock(TypologyJpaRepository.class);
        TypologyDomainDataAssembler assembler = mock(TypologyDomainDataAssembler.class);
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyDDD savedTypology = mock(TypologyDDD.class);
        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        TypologyJpa savedTypologyJpa = mock(TypologyJpa.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        when(typology.getTypologyDesignation()).thenReturn(typologyDesignation);
        when(assembler.toData(typology)).thenReturn(typologyJpa);
        when(jpaRepository.save(typologyJpa)).thenReturn(savedTypologyJpa);
        when(assembler.toDomain(savedTypologyJpa)).thenReturn(savedTypology);

        TypologyRepository repository = new TypologyRepository(jpaRepository, assembler);

        //Act
        TypologyDDD expectedTypology = repository.save(typology);

        //Assert
        assertEquals(expectedTypology, savedTypology);
    }

    @Test
    @DisplayName("Ensure exception is thrown while saving already existing typology")
    void ensureExceptionIsThrownWhileSavingAlreadyExistingTypology(){
        //Arrange
        TypologyJpaRepository jpaRepository = mock(TypologyJpaRepository.class);
        TypologyDomainDataAssembler assembler = mock(TypologyDomainDataAssembler.class);
        TypologyDDD typology = mock(TypologyDDD.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        String designation = "Fixed cost";

        when(typology.getTypologyDesignation()).thenReturn(typologyDesignation);
        when(typologyDesignation.toString()).thenReturn(designation);
        when(jpaRepository.existsByTypologyDesignation(designation)).thenReturn(true);

        TypologyRepository repository = new TypologyRepository(jpaRepository, assembler);
        String expectedMessage = "Typology already exists";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> repository.save(typology));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    @DisplayName("Ensure TypologyRepository returns true when TypologyJpa exists in JpaRepository")
    void ensureRepositoryReturnsTrueWhenTypologyExistsInPersistence(){
        //Arrange
        TypologyJpaRepository jpaRepository = mock(TypologyJpaRepository.class);
        TypologyDomainDataAssembler assembler = mock(TypologyDomainDataAssembler.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        String designation = "Fixed cost";
        when(typologyDesignation.toString()).thenReturn(designation);
        when(jpaRepository.existsByTypologyDesignation(designation)).thenReturn(true);

        TypologyRepository repository = new TypologyRepository(jpaRepository, assembler);

        //Act
        boolean result = repository.containsTypologyDesignation(typologyDesignation);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure TypologyRepository returns false when TypologyJpa does not exist in JpaRepository")
    void ensureRepositoryReturnsFalseWhenTypologyDoesNotExistInPersistence(){
        //Arrange
        TypologyJpaRepository jpaRepository = mock(TypologyJpaRepository.class);
        TypologyDomainDataAssembler assembler = mock(TypologyDomainDataAssembler.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        String designation = "Fixed cost";
        when(typologyDesignation.toString()).thenReturn(designation);
        when(jpaRepository.existsByTypologyDesignation(designation)).thenReturn(false);

        TypologyRepository repository = new TypologyRepository(jpaRepository, assembler);

        //Act
        boolean result = repository.containsTypologyDesignation(typologyDesignation);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure typology is retrieved given a TypologyDesignation, when it exists in the persistence")
    void ensureTypologyIsRetrieved (){
        TypologyJpaRepository jpaRepository = mock(TypologyJpaRepository.class);
        TypologyDomainDataAssembler assembler = mock(TypologyDomainDataAssembler.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        TypologyDDD expectedTypology = mock(TypologyDDD.class);
        Optional<TypologyDDD> oTypology = Optional.of(expectedTypology);

        TypologyJpa typologyJpa = mock(TypologyJpa.class);
        Optional<TypologyJpa> oTypologyJpa = Optional.of(typologyJpa);

        String designation = "Fixed cost";

        when(typologyDesignation.toString()).thenReturn(designation);
        when(jpaRepository.getByTypologyDesignation(designation)).thenReturn(oTypologyJpa);
        when(assembler.toDomain(typologyJpa)).thenReturn(expectedTypology);

        TypologyRepository repository = new TypologyRepository(jpaRepository, assembler);

        //Act
        Optional<TypologyDDD> result = repository.getByDesignation(typologyDesignation);

        //Assert
        assertEquals(oTypology, result);

    }

}

