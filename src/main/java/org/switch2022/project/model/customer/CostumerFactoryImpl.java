package org.switch2022.project.model.customer;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;

@Component
public class CostumerFactoryImpl implements  ICostumerFactory {

    /**
     * Method to create the customer object.
     * @param customerNIF
     * @param customerDesignation
     * @return a customer object
     */
    @Override
    public CustomerDDD createCustomer(CustomerNIF customerNIF, CustomerDesignation customerDesignation) {

        if (customerNIF == null) {
            throw new IllegalArgumentException("customerNIF, cannot be null");
        }
        if (customerDesignation == null) {
            throw new IllegalArgumentException("customerDesignation cannot be null");
        }

        CustomerDDD customer = new CustomerDDD(customerNIF,customerDesignation);
        return customer;
    }
}
