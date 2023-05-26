package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TypologyJpaTest {

    @Test
    @DisplayName("Ensure TypologyJpa is successfully created")
    void ensureTypologyJpaIsSuccessfullyCreated(){
        //Arrange
        String typologyDesignation = "Fixed cost";

        //Act
        TypologyJpa typologyJpa = new TypologyJpa(typologyDesignation);

        //Assert
        assertInstanceOf(TypologyJpa.class, typologyJpa);
    }

    @Test
    @DisplayName("Ensure TypologyJpa is correctly created by no arguments constructor")
    void ensureTypologyJpaIsCreatedByDefaultConstructor() {
        // Arrange and Act
        TypologyJpa typologyJpa = new TypologyJpa();

        // Assert
        assertInstanceOf(TypologyJpa.class, typologyJpa);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("Ensure exception is thrown when designation is null, blank or empty")
    void ensureExceptionIsThrownWhenDesignationIsNullBlankOrEmpty(String typologyDesignation){
        //Arrange
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypologyJpa(typologyDesignation));
        String expectedMessage = "Typology designation must not be null";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Ensure typology designation is returned")
    void ensureTypologyDesignationIsReturned(){
        //Arrange
        String expectedDesignation = "Fixed cost";
        TypologyJpa typologyJpa = new TypologyJpa(expectedDesignation);

        //Act
        String resultDesignation = typologyJpa.getTypologyDesignation();

        //Assert
        assertEquals(expectedDesignation, resultDesignation);
    }

}

