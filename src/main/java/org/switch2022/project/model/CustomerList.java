package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

    private List <Customer> customerList;

    /***
     * Constructor to initialise a new list of customers.
     */

    public CustomerList() {customerList = new ArrayList<>();}

    /***
     * The method will create the new cutomer, if the validation passes
     * @return true, if the following method, which is addCustomer does put the newly
     * created customer is added to the list.
     */
    public boolean createCustomer(String designation) {
        boolean customerCreated = false;

        Customer customer = new Customer(designation);

        if(addCustomer(customer)){
            customerCreated = true;
        }

        return customerCreated;
    }

    public boolean addCustomer(Customer customer){
        boolean addedDesignation = false;

        if(listDoesNotContain(customer)){
            addedDesignation = this.customerList.add(customer);
        }
        return addedDesignation;
    }

    private boolean listDoesNotContain(Customer customer) {
        return !customerList.contains(customer);
    }

}
