package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessSectorDesignationTest {
    @DisplayName("creating businessSectorDesignation with null value should throw Exception")
    @Test
    void createBusinessSectorDesignationWithNullThrowsException() {
        //Arrange
        String businessSectorDesignation = null;
        String expectedMessage = "businessSectorDesignation cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSectorDesignation(businessSectorDesignation);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating businessSectorDesignation with blank value value should throw Exception")
    @Test
    void createBusinessSectorDesignationWithBlankThrowsException() {
        //Arrange
        String businessSectorDesignation = "       ";
        String expectedMessage = "businessSectorDesignation cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSectorDesignation(businessSectorDesignation);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating businessSectorDesignation with empty value value should throw Exception")
    @Test
    void createBusinessSectorDesignationWithEmptyThrowsException() {
        //Arrange
        String businessSectorDesignation = "";
        String expectedMessage = "businessSectorDesignation cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new BusinessSectorDesignation(businessSectorDesignation);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectDoesNotEqualNull() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = new BusinessSectorDesignation("IT");

        //Act
        boolean result = businessSectorDesignation.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void ensureObjectEqualsSameObject() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = new BusinessSectorDesignation("IT");

        //Act
        boolean result = businessSectorDesignation.equals(businessSectorDesignation);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same designation")
    void ensureObjectEqualsObjectWithSameDesignation() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation1 = new BusinessSectorDesignation("IT");
        BusinessSectorDesignation businessSectorDesignation2 = new BusinessSectorDesignation("IT");

        //Act
        boolean result = businessSectorDesignation1.equals(businessSectorDesignation2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different designation")
    void ensureObjectDoesNotEqualObjectWithDifferentDesignation() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation1 = new BusinessSectorDesignation("IT");
        BusinessSectorDesignation businessSectorDesignation2 = new BusinessSectorDesignation("Healthcare");

        //Act
        boolean result = businessSectorDesignation1.equals(businessSectorDesignation2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation = new BusinessSectorDesignation("IT");
        String fakeBusiness = "Fake business";

        //Act
        boolean result = businessSectorDesignation.equals(fakeBusiness);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation1 = new BusinessSectorDesignation("IT");
        BusinessSectorDesignation businessSectorDesignation2 = new BusinessSectorDesignation("IT");

        //Act
        int hashCode1 = businessSectorDesignation1.hashCode();
        int hashCode2 = businessSectorDesignation2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        BusinessSectorDesignation businessSectorDesignation1 = new BusinessSectorDesignation("IT");
        BusinessSectorDesignation businessSectorDesignation2 = new BusinessSectorDesignation("Healthcare");

        //Act
        int hashCode1 = businessSectorDesignation1.hashCode();
        int hashCode2 = businessSectorDesignation2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @DisplayName("toString returns the Business Sector Designation String ")
    @Test
    public void toStringShouldReturnTheBusinessSectorDesignationString(){

        // Arrange
        BusinessSectorDesignation businessSectorDesignation = new BusinessSectorDesignation("IT");

        // Act & Assert
        assertEquals(businessSectorDesignation.toString(), "IT");
    }
}