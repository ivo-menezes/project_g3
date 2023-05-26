package org.switch2022.project.model.customer;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

public class CustomerDDD implements AggregateRoot<CustomerID> {

    private final CustomerID customerID;
    private final CustomerNIF customerNIF;
    private final CustomerDesignation customerDesignation;

    /**
     * Public constructor to instantiate a customer.
     * @param customerID
     * @param customerNIF
     * @param customerDesignation
     */
    public CustomerDDD(CustomerID customerID, CustomerNIF customerNIF, CustomerDesignation customerDesignation) {
        if (customerNIF == null) {
            throw new IllegalArgumentException("customerNIF, cannot be null");
        }
        if (customerDesignation == null) {
            throw new IllegalArgumentException("customerDesignation cannot be null");
        }
        this.customerID = customerID;
        this.customerNIF = customerNIF;
        this.customerDesignation = customerDesignation;
    }

    @Override
    public CustomerID identity() {
        return this.customerID;
    }

    public CustomerNIF getCustomerNIF() {
        return customerNIF;
    }

    public CustomerDesignation getCustomerDesignation() {
        return customerDesignation;
    }
}
