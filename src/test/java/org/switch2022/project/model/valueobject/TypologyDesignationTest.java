package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TypologyDesignationTest {

    @Test
    @DisplayName("Ensure typology designation is correctly created")
    void ensureTypologyDesignationIsCreated() {
        //Arrange
        String expectedDesignation = "Fixed cost";

        //Act
        TypologyDesignation designation = new TypologyDesignation(expectedDesignation);

        //Assert
        assertTrue(designation instanceof TypologyDesignation);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("Ensure exception is thrown when designation is null, blank or empty")
    void ensureExceptionIsThrownWhenDesignationIsNullBlankOrEmpty(String designation){

        //Arrange
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypologyDesignation(designation));
        String expectedMessage = "Typology designation must not be null";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectDoesNotEqualNull(){
        //Arrange
        TypologyDesignation designation = new TypologyDesignation("Fixed cost");

        //Act
        boolean result = designation.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void ensureObjectEqualsSameObject(){
        //Arrange
        TypologyDesignation designation = new TypologyDesignation("Fixed cost");

        //Act
        boolean result = designation.equals(designation);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same designation")
    void ensureObjectEqualsObjectWithSameDesignation(){
        //Arrange
        TypologyDesignation designation1 = new TypologyDesignation("Fixed cost");
        TypologyDesignation designation2 = new TypologyDesignation("Fixed cost");

        //Act
        boolean result = designation1.equals(designation2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different designation")
    void ensureObjectDoesNotEqualObjectWithDifferentDesignation(){
        //Arrange
        TypologyDesignation designation1 = new TypologyDesignation("Fixed cost");
        TypologyDesignation designation2 = new TypologyDesignation("Time and materials");

        //Act
        boolean result = designation1.equals(designation2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode(){
        //Arrange
        TypologyDesignation designation1 = new TypologyDesignation("Fixed cost");
        TypologyDesignation designation2 = new TypologyDesignation("Fixed cost");

        //Act
        int hashCode1 = designation1.hashCode();
        int hashCode2 = designation2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode(){
        //Arrange
        TypologyDesignation designation1 = new TypologyDesignation("Fixed cost");
        TypologyDesignation designation2 = new TypologyDesignation("Time and materials");

        //Act
        int hashCode1 = designation1.hashCode();
        int hashCode2 = designation2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

}