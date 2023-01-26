package org.switch2022.project.controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.CustomerList;

import static org.junit.jupiter.api.Assertions.*;

class AddCustomerControllerTest {
    @Test
    @DisplayName("Ensure that customer controller is successfully created")
    void createdSuccessfully(){
        CustomerList newList = new CustomerList();
        AddCustomerController controller = new AddCustomerController(newList);
        boolean resultedList = controller.createCustomer("Name");

        assertTrue(resultedList);
        //assertNotEquals(false, resultedList);
    }

    @Test
    @DisplayName("Ensure that customer is successfully created")
    void createCustomer() {
        String designation = "Designation";
        CustomerList newList = new CustomerList();
        AddCustomerController controller = new AddCustomerController(newList);

        assertTrue(controller.createCustomer(designation));
    }
    @Test
    @DisplayName("Ensure that customer is successfully created")
    void checkIfItCreates(){
        String designation = "Designation";
        String anotherDesignation = "Designation";
        CustomerList newList = new CustomerList();
        AddCustomerController controller = new AddCustomerController(newList);
        controller.createCustomer(designation);

        boolean result = controller.createCustomer(anotherDesignation);

        assertFalse(result);
        //assertNotEquals(false, controller.createCustomer(designation));
    }

}