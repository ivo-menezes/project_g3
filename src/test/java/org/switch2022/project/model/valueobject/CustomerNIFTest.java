package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNIFTest {
    @DisplayName("creating CustomerNIF with null value should throw Exception")
    @Test
    void createCustomerNIFWithNullThrowsException() {
        //Arrange
        String customerNIF = null;
        String expectedMessage = "customerNIF cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating CustomerNIF with empty value should throw Exception")
    @Test
    void createCustomerNIFWithEmptyThrowsException() {
        //Arrange
        String customerNIF = "";
        String expectedMessage = "customerNIF cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating CustomerNIF with blank value should throw Exception")
    @Test
    void createCustomerNIFWithBlankThrowsException() {
        //Arrange
        String customerNIF = "      ";
        String expectedMessage = "customerNIF cannot be null/blank/empty";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating CustomerNIF with more than nine digits for the country Spain should throw Exception ")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_FirstCaseSpain() {
        //Arrange
        String customerNIF = "9876543278";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating CustomerNIF with less than nine digits for the country Spain should throw Exception")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_SecondCaseSpain() {
        //Arrange
        String customerNIF = "987654";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating CustomerNIF with more than nine digits for the country Portugal should throw Exception ")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_FirstCasePortugal() {
        //Arrange
        String customerNIF = "9876543278";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("creating CustomerNIF with more than nine digits for the country Portugal should throw Exception ")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_SecondCasePortugal() {
        //Arrange
        String customerNIF = "98765433";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("creating CustomerNIF with more than nine digits for other countries should throw Exception ")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_FirstCaseOtherCountries() {
        //Arrange
        String customerNIF = "9876543278";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("creating CustomerNIF with more than nine digits for other countries should throw Exception ")
    @Test
    void createCustomerNIFWithNIFInvalidThrowsException_SecondCaseOtherCountries() {
        //Arrange
        String customerNIF = "98765432";
        String expectedMessage = "customerNIF is not valid";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CustomerNIF(customerNIF);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}