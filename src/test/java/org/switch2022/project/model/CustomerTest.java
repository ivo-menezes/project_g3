package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomerTest {

    @Test
    @DisplayName("Ensure that the designation is the same")
    void checkTheCustomerDesignationIsTheSame(){

        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = newCustomer;

        Assertions.assertSame(sameCustomer, newCustomer);
        assertTrue(newCustomer.equals(sameCustomer));
        assertNotEquals(false, newCustomer.equals(sameCustomer));
    }


    @Test
    @DisplayName("Testing the hashcode")
    void checkTheHashCode(){
        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = new Customer("CustomerName");

        assertEquals(sameCustomer.hashCode(), newCustomer.hashCode());
        assertNotEquals(0, sameCustomer.hashCode());
    }

    @Test
    @DisplayName("Ensure that the designation is corrected created")
    void checkTheBusinessDesignationIsCorrectlyCreated(){
        Customer newCustomer = new Customer("CustomerName");
        Customer sameCustomer = new Customer("CustomerName");

        assertEquals(sameCustomer, newCustomer);
        Assertions.assertNotNull(sameCustomer);
    }

    /***
     * This test will ensure the Customer object created isn't the same as a null Object.
     */
    @Test
    @DisplayName("Ensure the customer doesn't equal Null")
    void ensureTheObjectDoesNotEqualsNull(){
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = null;
        boolean expected = false;

        boolean result = newCustomer.equals(anotherCustomer);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Ensure the customer doesn't equal other Types of Object")
    void ensureTheObjectDoesNotEqualsOtherTypes(){
        Customer newCustomer = new Customer("CustomerName");
        Profile firstProfile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", firstProfile);
        boolean expected = false;

        boolean result = newCustomer.equals(account);

        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Ensure the customer doesn't equal to other customer")
    void ensureTheCustomerDoesNotEqualsAnotherCustomer(){
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerNameOther");
        boolean expected = false;

        boolean result = newCustomer.equals(anotherCustomer);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check if the get method doesn't return empty")
    void checkTheGetMethod(){
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName");

        assertEquals(anotherCustomer.getDesignation(), newCustomer.getDesignation());
        assertNotEquals("", newCustomer.getDesignation());
    }

}