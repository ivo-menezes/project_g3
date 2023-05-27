package org.switch2022.project.model.typology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TypologyFactoryImplTest {

    @Test
    @DisplayName("Ensure factory creates Typology successfully")
    void ensureFactoryCreatesTypologySuccessfully() {
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyFactoryImpl factory = new TypologyFactoryImpl();

        //Act
        TypologyDDD typology = factory.createTypology(typologyID, typologyDesignation);

        //Assert
        assertInstanceOf(TypologyDDD.class, typology);
    }

    @Test
    @DisplayName("Ensure factory fails to create Typology with null TypologyDesignation")
    void ensureFactoryFailsToCreateTypologyWithNullTypologyDesignation() {
        //Arrange
        TypologyID typologyID = mock(TypologyID.class);
        TypologyDesignation typologyDesignation = null;
        TypologyFactoryImpl factory = new TypologyFactoryImpl();
        String expectedMessage = "Typology designation must not be null";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> factory.createTypology(typologyID, typologyDesignation));
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }
}

