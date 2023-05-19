package org.switch2022.project.model.customer;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;

public class CustomerDDD implements AggregateRoot<CustomerNIF> {
    private final CustomerNIF customerNIF;
    private final CustomerDesignation customerDesignation;

    /**
     * Public constructor to instantiate a customer.
     * @param customerNIF
     * @param customerDesignation
     */
    public CustomerDDD(CustomerNIF customerNIF, CustomerDesignation customerDesignation) {
        if (customerNIF == null) {
            throw new IllegalArgumentException("customerNIF, cannot be null");
        }
        if (customerDesignation == null) {
            throw new IllegalArgumentException("customerDesignation cannot be null");
        }
        this.customerNIF = customerNIF;
        this.customerDesignation = customerDesignation;
    }

    @Override
    public CustomerNIF identity() {
        return this.customerNIF;
    }

    public CustomerNIF getCustomerNIF() {
        return customerNIF;
    }

    public CustomerDesignation getCustomerDesignation() {
        return customerDesignation;
    }
}
