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
     * This method will validate whether there is a customer in the list with
     * the input designation.
     * @param designation this is the data for the validation
     * @return a boolean, according to whether the designation already exists or not
     */

    public boolean validateCustomer(String designation){
        boolean isDesignationValid = null != designation;
        for (int index = 0; index < this.customerList.size() && isDesignationValid; index++) {
            Customer customer = this.customerList.get(index);
            String cDesign = customer.getDesignation();
            if (cDesign.equals(designation)) {
                isDesignationValid = false;
                break;
            }
        }
        return isDesignationValid;
    }

    /***
     * The method will create the new cutomer, if the validation passes
     * @return true, if the following method, which is addCustomer does put the newly
     * created customer is added to the list.
     */
    public boolean createCustomer(String designation) {
        boolean customerCreated = false;

        if (validateCustomer(designation)){
            customerCreated = true;
        }

        Customer customer = new Customer(designation);
        addCustomer(customer);

        return customerCreated;
    }

    public boolean addCustomer(Customer customer){
        boolean addedDesignation = true;
        if(customer == null){
            addedDesignation = false;
        }
        this.customerList.add(customer);
        return addedDesignation;
    }

}
