package org.switch2022.project.model.typology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.TypologyDesignation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TypologyDDDTest {

    @Test
    @DisplayName("Ensure Typology is successfully created")
    void ensureTypologyIsSuccessfullyCreated(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);

        //Act
        TypologyDDD typology = new TypologyDDD(typologyDesignation);

        //Assert
        assertInstanceOf(TypologyDDD.class, typology);
    }

    @Test
    @DisplayName("Ensure exception is thrown when TypologyDesignation is null")
    void ensureExceptionIsThrownWhenNullTypologyDesignation(){
        //Arrange
        TypologyDesignation typologyDesignation = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TypologyDDD(typologyDesignation));
        String expectedMessage = "Typology designation must not be null";

        //Act
        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Ensure typology Id is returned")
    void ensureTypologyIdIsReturned(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = new TypologyDDD(typologyDesignation);
        TypologyDesignation expectedId = typologyDesignation;

        //Act
        TypologyDesignation resultId = typology.identity();

        //Assert
        assertEquals(expectedId, resultId);
    }

    @Test
    @DisplayName("Ensure typology designation is returned")
    void ensureTypologyDesignationIsReturned(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = new TypologyDDD(typologyDesignation);
        TypologyDesignation expectedDesignation = typologyDesignation;

        //Act
        TypologyDesignation resultDesignation = typology.identity();

        //Assert
        assertEquals(expectedDesignation, resultDesignation);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectNotEqualNull(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = new TypologyDDD(typologyDesignation);

        //Act
        boolean result = typology.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void objectEqualsSameObject(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology = new TypologyDDD(typologyDesignation);

        //Act
        boolean result = typology.equals(typology);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same TypologyDesignation")
    void objectEqualsSameTypologyDesignation(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology1 = new TypologyDDD(typologyDesignation);
        TypologyDDD typology2 = new TypologyDDD(typologyDesignation);

        //Act
        boolean result = typology1.equals(typology2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different TypologyDesignation")
    void objectNotEqualToObjectWithDifferentTypologyDesignation(){
        //Arrange
        TypologyDesignation typologyDesignation1 = mock(TypologyDesignation.class);
        TypologyDesignation typologyDesignation2 = mock(TypologyDesignation.class);
        TypologyDDD typology1 = new TypologyDDD(typologyDesignation1);
        TypologyDDD typology2 = new TypologyDDD(typologyDesignation2);

        //Act
        boolean result = typology1.equals(typology2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode(){
        //Arrange
        TypologyDesignation typologyDesignation = mock(TypologyDesignation.class);
        TypologyDDD typology1 = new TypologyDDD(typologyDesignation);
        TypologyDDD typology2 = new TypologyDDD(typologyDesignation);

        //Act
        int hashCode1 = typology1.hashCode();
        int hashCode2 = typology2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode(){
        //Arrange
        TypologyDesignation typologyDesignation1 = mock(TypologyDesignation.class);
        TypologyDesignation typologyDesignation2 = mock(TypologyDesignation.class);
        TypologyDDD typology1 = new TypologyDDD(typologyDesignation1);
        TypologyDDD typology2 = new TypologyDDD(typologyDesignation2);

        //Act
        int hashCode1 = typology1.hashCode();
        int hashCode2 = typology2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure TypologyDDD is converted to String")
    void ensureTypologyIsConvertedToString(){
        //Arrange
        String expectedString = "Fixed cost";
        TypologyDesignation typologyDesignation1 = mock(TypologyDesignation.class);
        when(typologyDesignation1.toString()).thenReturn(expectedString);
        TypologyDDD typology1 = new TypologyDDD(typologyDesignation1);

        //Act
        String actualString = typology1.toString();

        //Assert
        assertEquals(expectedString, actualString);
    }
}