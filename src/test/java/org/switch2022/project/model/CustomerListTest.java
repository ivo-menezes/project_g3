package org.switch2022.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.customer.Customer;

import static org.junit.jupiter.api.Assertions.*;



class CustomerListTest {

    @Test
    @DisplayName("Ensure that customer is added")
    void addCustomer() {
        //arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();

        //act
        boolean result = customerList.addCustomer(newCustomer);
        boolean anotherResult = customerList.addCustomer(anotherCustomer);

        //assert
        assertTrue(result);
        assertNotEquals(false,anotherResult);
    }


   @Test
    @DisplayName("Ensure that can't add existing customer")
    void validateCustomerAlreadyExists() {
       //arrange
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName");
        CustomerList customerList = new CustomerList();
        //act
        customerList.addCustomer(newCustomer);
        boolean result = customerList.addCustomer(anotherCustomer);
        //String designation = "CustomerName_another";

        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure that can create new customer")
    void createNewCustomer() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = "ISEP";

        assertTrue(customerList.createCustomer(designation));
    }

    @Test
    @DisplayName("Ensure that can't create existing customer")
    void createNewCustomerAlreadyExists() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = "CustomerName_another";

        assertFalse(customerList.createCustomer(designation));
    }

}