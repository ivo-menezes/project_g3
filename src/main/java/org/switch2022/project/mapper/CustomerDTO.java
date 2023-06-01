package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO responsible for transferring customer properties.
 */
public class CustomerDTO implements Serializable {

    public CustomerID customerID;
    public CustomerNIF customerNIF;
    public CustomerDesignation customerDesignation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(customerID, that.customerID) && Objects.equals(customerNIF, that.customerNIF) && Objects.equals(customerDesignation, that.customerDesignation);
    }
}
