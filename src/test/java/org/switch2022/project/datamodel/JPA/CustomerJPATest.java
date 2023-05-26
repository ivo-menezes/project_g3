package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJPATest {
    @Test
    @DisplayName("CustomerJPA constructor throws exception with null costumerNIF")
    void nullCustomerNIFThrowsException() {
        //Arrange
        String customerDesignation = "Test Silva";
        String expectedMessage = "customerNIF, cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new CustomerJPA(null,
                            customerDesignation);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("CustomerJPA constructor throws exception with null customerDesignation")
    void nullCustomerDesignationThrowsException() {
        //Arrange
        String customerNIF = "306945000";
        String expectedMessage = "customerDesignation cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new CustomerJPA(customerNIF,
                            null);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Test for a successful creation of CustomerDDD")
    public void checkIfTheCostumerJPAIsSuccessfulCreated(){
        //Arrange
        String customerNIF = "306756777";
        String customerDesignation = "Test Silva";

        //Act
        CustomerJPA customer = new CustomerJPA(customerNIF,customerDesignation);

        //Assert
        assertInstanceOf(CustomerJPA.class, customer);
    }

}