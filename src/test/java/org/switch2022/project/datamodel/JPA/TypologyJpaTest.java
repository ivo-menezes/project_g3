package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TypologyJpaTest {

    @Test
    @DisplayName("Ensure TypologyJpa is successfully created")
    void ensureTypologyJpaIsSuccessfullyCreated() {
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

    @Test
    @DisplayName("Ensure typology designation is returned")
    void ensureTypologyDesignationIsReturned() {
        //Arrange
        String expectedDesignation = "Fixed cost";
        TypologyJpa typologyJpa = new TypologyJpa(expectedDesignation);

        //Act
        String resultDesignation = typologyJpa.getTypologyDesignation();

        //Assert
        assertEquals(expectedDesignation, resultDesignation);
    }

}

