package org.switch2022.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class CustomerListTest {

    @Test
    @DisplayName("Ensure that customer is added")
    void addCustomer() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();

        assertTrue(customerList.addCustomer(newCustomer));
        assertNotEquals(false,customerList.addCustomer(anotherCustomer));
    }

    @Test
    @DisplayName("Ensure that customer is not successful added")
    void addCustomerIsNotSuccessful(){
        Customer newCustomer = null;
        CustomerList customerList = new CustomerList();

        assertFalse(customerList.addCustomer(newCustomer));
    }

    @Test
    @DisplayName("Ensure that customer does not exist in the list")
    void validateCustomerIsNewToList() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = "ISEP";

        assertTrue(customerList.validateCustomer(designation));
    }

    @Test
    @DisplayName("Ensure that can't add existing customer")
    void validateCustomerAlreadyExists() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = "CustomerName_another";

        assertFalse(customerList.validateCustomer(designation));
    }
    @Test
    @DisplayName("Ensure that customer can't be null")
    void validateCustomerIsNull() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = null;

        assertFalse(customerList.validateCustomer(designation));
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
    @DisplayName("Ensure that can't create new customer with null designation")
    void createNewCustomerWithNullDesignation() {
        Customer newCustomer = new Customer("CustomerName");
        Customer anotherCustomer = new Customer("CustomerName_another");
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(newCustomer);
        customerList.addCustomer(anotherCustomer);
        String designation = null;

        assertFalse(customerList.createCustomer(designation));
    }



    /*@Test
    @DisplayName("Ensure that there is an error if add two customers witch same name")
    void checkIfThereIsAnError(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Customer newCustomer = new Customer("CustomerName");
            Customer anotherCustomer = new Customer("CustomerName_another");
            CustomerList customerList = new CustomerList();
            customerList.addCustomer(newCustomer);
            customerList.addCustomer(anotherCustomer);
            String designation = "CustomerName";
            customerList.createCustomer(designation);
        });
        Assertions.assertEquals("Designation already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("Ensure that there is an error if add customer with null name")
    void checkIfThereIsAnErrorCustomerIsNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Customer newCustomer = new Customer("CustomerName");
            Customer anotherCustomer = new Customer("CustomerName_another");
            CustomerList customerList = new CustomerList();
            customerList.addCustomer(newCustomer);
            customerList.addCustomer(anotherCustomer);
            String designation = null;
            customerList.createCustomer(designation);
        });
        Assertions.assertEquals("Designation cannot be null.", exception.getMessage());
    }*/






}