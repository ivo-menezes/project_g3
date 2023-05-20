package org.switch2022.project.model.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CostumerFactoryImplTest {

    @DisplayName("assert that creating Customer succeeds")
    @Test
    void createCustomerSucceeds() {
        //Arrange
        CustomerNIF customerNIFDouble = mock(CustomerNIF.class);
        CustomerDesignation customerDesignationDouble = mock(CustomerDesignation.class);

        CostumerFactoryImpl factory = new CostumerFactoryImpl();

        //Act
        CustomerDDD customer = factory.createCustomer(customerNIFDouble,customerDesignationDouble);

        // assert
        assertInstanceOf(CustomerDDD.class, customer);
    }

    @DisplayName("assert that trying to create customer with null customerNIF throws Exception")
    @Test
    void createCustomerNullCustomerNIFThrowsException() {
        //Arrange
        CustomerNIF customerNIF = null;
        CustomerDesignation customerDesignationDouble = mock(CustomerDesignation.class);

        CostumerFactoryImpl factory = new CostumerFactoryImpl();

        String expectedMessage = "customerNIF, cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCustomer(customerNIF,customerDesignationDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create customer with null customerDesignation throws Exception")
    @Test
    void createCustomerNullCustomerDesignationThrowsException() {
        //Arrange
        CustomerNIF customerNIFDouble = mock(CustomerNIF.class);
        CustomerDesignation customerDesignationDouble = null;

        CostumerFactoryImpl factory = new CostumerFactoryImpl();

        String expectedMessage = "customerDesignation cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCustomer(customerNIFDouble,customerDesignationDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}