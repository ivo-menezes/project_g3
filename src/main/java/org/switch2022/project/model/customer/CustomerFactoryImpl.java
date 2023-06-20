package org.switch2022.project.model.customer;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

@Component
public class CustomerFactoryImpl implements ICustomerFactory {

    /**
     * Method to create the customer object.
     * @param customerID
     * @param customerNIF
     * @param customerDesignation
     * @return a customer object
     */
    @Override
    public CustomerDDD createCustomer(CustomerID customerID,
                                      CustomerNIF customerNIF,
                                      CustomerDesignation customerDesignation) {

        if (customerNIF == null) {
            throw new IllegalArgumentException("customerNIF, cannot be null");
        }
        if (customerDesignation == null) {
            throw new IllegalArgumentException("customerDesignation cannot be null");
        }

        CustomerDDD customer = new CustomerDDD(customerID,customerNIF,customerDesignation);
        return customer;
    }
}
