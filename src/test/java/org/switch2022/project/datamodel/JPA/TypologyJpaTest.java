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


    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectNotEqualNull(){
        //Arrange
        String typologyDesignation = "Fixed cost";
        TypologyJpa typologyJpa = new TypologyJpa(typologyDesignation);

        //Act
        boolean result = typologyJpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void objectEqualsSameObject(){
        //Arrange
        String typologyDesignation = "Fixed cost";
        TypologyJpa typologyJpa = new TypologyJpa(typologyDesignation);

        //Act
        boolean result = typologyJpa.equals(typologyJpa);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same TypologyDesignation")
    void objectEqualsSameTypologyDesignation(){
        //Arrange
        String typologyDesignation = "Fixed cost";
        TypologyJpa typologyJpa1 = new TypologyJpa(typologyDesignation);
        TypologyJpa typologyJpa2 = new TypologyJpa(typologyDesignation);

        //Act
        boolean result = typologyJpa1.equals(typologyJpa2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different TypologyDesignation")
    void objectNotEqualToObjectWithDifferentTypologyDesignation(){
        //Arrange
        String typologyDesignation1 = "Fixed cost";
        String typologyDesignation2 = "Time and materials";
        TypologyJpa typologyJpa1 = new TypologyJpa(typologyDesignation1);
        TypologyJpa typologyJpa2 = new TypologyJpa(typologyDesignation2);

        //Act
        boolean result = typologyJpa1.equals(typologyJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode(){
        //Arrange
        String typologyDesignation = "Fixed cost";
        TypologyJpa typologyJpa1 = new TypologyJpa(typologyDesignation);
        TypologyJpa typologyJpa2 = new TypologyJpa(typologyDesignation);

        //Act
        int hashCode1 = typologyJpa1.hashCode();
        int hashCode2 = typologyJpa2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode(){
        //Arrange
        String typologyDesignation1 = "Fixed cost";
        String typologyDesignation2 = "Time and materials";
        TypologyJpa typologyJpa1 = new TypologyJpa(typologyDesignation1);
        TypologyJpa typologyJpa2 = new TypologyJpa(typologyDesignation2);

        //Act
        int hashCode1 = typologyJpa1.hashCode();
        int hashCode2 = typologyJpa2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}

