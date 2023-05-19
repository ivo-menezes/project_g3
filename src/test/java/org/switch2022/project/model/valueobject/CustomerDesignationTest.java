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
}