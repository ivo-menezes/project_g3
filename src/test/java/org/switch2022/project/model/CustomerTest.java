package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomerTest {

    @Test
    @DisplayName("Ensure that the designation is the same")
    void checkTheCustomerDesignationIsTheSame(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = newCustomer;

        //Assert
        Assertions.assertSame(sameCustomer, newCustomer);
        assertEquals(newCustomer,sameCustomer);
    }


    @Test
    @DisplayName("Testing the hashcode")
    void checkTheHashCode(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = new Customer("CustomerName");

        //Assert
        assertEquals(sameCustomer.hashCode(), newCustomer.hashCode());
        assertNotEquals(0, sameCustomer.hashCode());
    }

    @Test
    @DisplayName("Ensure that the designation is corrected created")
    void checkTheBusinessDesignationIsCorrectlyCreated(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = new Customer("CustomerName");

        //Assert
        assertEquals(sameCustomer, newCustomer);
        Assertions.assertNotNull(sameCustomer);
    }

    /***
     * This test will ensure the Customer object created isn't the same as a null Object.
     */
    @Test
    @DisplayName("Ensure the customer doesn't equal Null")
    void ensureTheObjectDoesNotEqualsNull(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = null;
        boolean expected = false;

        //Act
        boolean result = newCustomer.equals(anotherCustomer);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure the customer doesn't equal to other customer")
    void ensureTheCustomerDoesNotEqualsAnotherCustomer(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerNameOther");
        boolean expected = false;

        //Act
        boolean result = newCustomer.equals(anotherCustomer);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if the get method doesn't return empty")
    void checkTheGetMethod(){
        //Arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName");

        //Act
        String customer = newCustomer.getDesignation();
        String anothercustomer = anotherCustomer.getDesignation();

        //Assert
        assertEquals(customer, anothercustomer);
        assertNotEquals("", customer);
    }

    @Test
    @DisplayName("Ensure that there is an error if add a customer with null name")
    void checkIfThereIsAnError(){
        //Arrange
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Customer customer = new Customer (null);
        });
        //Act
        String expected = "Designation can not be null!";

        //Assert
        Assertions.assertEquals(expected, exception.getMessage());
    }

}