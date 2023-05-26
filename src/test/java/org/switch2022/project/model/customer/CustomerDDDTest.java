package org.switch2022.project.model.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerDDDTest {
    @Test
    @DisplayName("Customer constructor throws exception with null costumerNIF")
    void nullCustomerNIFThrowsException() {
        //Arrange
        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);
        String expectedMessage = "customerNIF, cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new CustomerDDD(null,null,
                            customerDesignation);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

        @Test
        @DisplayName("Customer constructor throws exception with null customerDesignation")
        void nullCustomerDesignationThrowsException() {
            //Arrange
            CustomerNIF customerNIF = mock(CustomerNIF.class);
            String expectedMessage = "customerDesignation cannot be null";

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class, () -> {
                        new CustomerDDD(null,customerNIF,
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
    public void checkIfTheCostumerDDDIsSuccessfulCreated(){
        //Arrange
        CustomerID customerIDDouble = mock(CustomerID.class);
        CustomerNIF customerNIF = mock(CustomerNIF.class);
        CustomerDesignation customerDesignation = mock(CustomerDesignation.class);

        //Act
        CustomerDDD customer = new CustomerDDD(customerIDDouble,customerNIF,customerDesignation);

        //Assert
        assertInstanceOf(CustomerDDD.class, customer);
    }
}