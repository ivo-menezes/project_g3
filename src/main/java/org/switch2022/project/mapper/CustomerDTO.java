package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

import java.io.Serializable;

/**
 * DTO responsible for transferring customer properties.
 */
public class CustomerDTO implements Serializable {

    public CustomerID customerID;
    public CustomerNIF customerNIF;
    public CustomerDesignation customerDesignation;
}
