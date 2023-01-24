package org.switch2022.project.controller;


import org.switch2022.project.model.CustomerList;

public class AddCustomerController {

    private CustomerList newCustomerList;

    /***
     * This controller will be used to create the customer list.
     * The constructor doesn't receive anything, since it will be only pulled for the
     */

    public AddCustomerController (CustomerList newCustomerList) {
        this.newCustomerList = newCustomerList;
    }

    public boolean createCustomer(String designation) {
        boolean result = true;
        newCustomerList = new CustomerList();
        if (!newCustomerList.validateCustomer(designation)) {
            result = false;
        } else {
            newCustomerList.createCustomer(designation);
        }
        return result;
    }

}
