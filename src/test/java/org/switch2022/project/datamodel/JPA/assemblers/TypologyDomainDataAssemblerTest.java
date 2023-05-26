package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.model.valueobject.TypologyDesignation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyDomainDataAssemblerTest {

    @Test
    @DisplayName("Ensure Typology is correctly converted to TypologyJpa")
    void ensureTypologyIsConvertedToTypologyJpa(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = mock(TypologyDDD.class);

        when(typology.getTypologyDesignation()).thenReturn(typologyDesignation);
        when(typologyDesignation.toString()).thenReturn("Fixed cost");

        TypologyDomainDataAssembler assembler = new TypologyDomainDataAssembler();

        //Act
        TypologyJpa returnedTypologyJpa = assembler.toData(typology);

        //Assert
        assertInstanceOf(TypologyJpa.class, returnedTypologyJpa);
    }

    @Test
    @DisplayName("Ensure TypologyJpa is correctly converted to Typology")
    void ensureTypologyJpaIsConvertedToTypology(){
        //Arrange
        String designation = "Fixed cost";
        TypologyJpa typologyJpa = mock(TypologyJpa.class);

        when(typologyJpa.getTypologyDesignation()).thenReturn(designation);

        TypologyDomainDataAssembler assembler = new TypologyDomainDataAssembler();

        //Act
        TypologyDDD returnedTypology = assembler.toDomain(typologyJpa);

        //Assert
        assertInstanceOf(TypologyDDD.class,returnedTypology);
    }
}
