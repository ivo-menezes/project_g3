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

        TypologyJpa expectedTypologyJpa = new TypologyJpa("Fixed cost");

        //Act
        TypologyJpa actualTypologyJpa = assembler.toData(typology);

        //Assert
        assertEquals(expectedTypologyJpa, actualTypologyJpa);
    }

    @Test
    @DisplayName("Ensure TypologyJpa is correctly converted to Typology")
    void ensureTypologyJpaIsConvertedToTypology(){
        //Arrange
        String designation = "Fixed cost";
        TypologyJpa typologyJpa = mock(TypologyJpa.class);

        when(typologyJpa.getTypologyDesignation()).thenReturn(designation);

        TypologyDomainDataAssembler assembler = new TypologyDomainDataAssembler();

        TypologyDDD expectedTypology = new TypologyDDD(new TypologyDesignation(designation));

        //Act
        TypologyDDD actualTypology = assembler.toDomain(typologyJpa);

        //Assert
        assertEquals(expectedTypology, actualTypology);
    }

}
