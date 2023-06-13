package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDesignationTest {
    @DisplayName("creating customerDesignation with null value should throw Exception")
    @Test
    void createCustomerDesignationWithNullThrowsException() {
        // Arrange
        String customerDesignation = null;
        String expectedMessage = "customerDesignation cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new CustomerDesignation(customerDesignation);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating customerDesignation with blank value should throw Exception")
    @Test
    void createCustomerDesignationWithBlankThrowsException() {
        // Arrange
        String customerDesignation = "       ";
        String expectedMessage = "customerDesignation cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new CustomerDesignation(customerDesignation);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("creating customerDesignation with empty value should throw Exception")
    @Test
    void createCustomerDesignationWithEmptyThrowsException() {
        // Arrange
        String customerDesignation = "";
        String expectedMessage = "customerDesignation cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new CustomerDesignation(customerDesignation);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectDoesNotEqualNull() {
        //Arrange
        CustomerDesignation customerDesignation = new CustomerDesignation("TheBoss");

        //Act
        boolean result = customerDesignation.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void ensureObjectEqualsSameObject() {
        //Arrange
        CustomerDesignation customerDesignation = new CustomerDesignation("TheBoss");

        //Act
        boolean result = customerDesignation.equals(customerDesignation);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same designation")
    void ensureObjectEqualsObjectWithSameDesignation() {
        //Arrange
        CustomerDesignation customerDesignation1 = new CustomerDesignation("TheBoss");
        CustomerDesignation customerDesignation2 = new CustomerDesignation("TheBoss");

        //Act
        boolean result = customerDesignation1.equals(customerDesignation2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different designation")
    void ensureObjectDoesNotEqualObjectWithDifferentDesignation() {
        //Arrange
        CustomerDesignation customerDesignation1 = new CustomerDesignation("TheBoss");
        CustomerDesignation customerDesignation2 = new CustomerDesignation("TheMaster");

        //Act
        boolean result = customerDesignation1.equals(customerDesignation2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        CustomerDesignation customerDesignation = new CustomerDesignation("TheBoss");
        String fakeBoss = "FakeBoss";

        //Act
        boolean result = customerDesignation.equals(fakeBoss);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        CustomerDesignation customerDesignation1 = new CustomerDesignation("TheBoss");
        CustomerDesignation customerDesignation2 = new CustomerDesignation("TheBoss");

        //Act
        int hashCode1 = customerDesignation1.hashCode();
        int hashCode2 = customerDesignation2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        CustomerDesignation customerDesignation1 = new CustomerDesignation("TheBoss");
        CustomerDesignation customerDesignation2 = new CustomerDesignation("TheMaster");

        //Act
        int hashCode1 = customerDesignation1.hashCode();
        int hashCode2 = customerDesignation2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @DisplayName("toString returns the Customer Designation String ")
    @Test
    public void toStringShouldReturnTheCustomerDesignationString(){

        // Arrange
        CustomerDesignation customerDesignation = new CustomerDesignation("TheBoss");

        // Act & Assert
        assertEquals(customerDesignation.toString(), "TheBoss");
    }
}