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

}