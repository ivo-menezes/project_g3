package org.switch2022.project.model.customer;

import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

public interface ICustomerFactory {

    CustomerDDD createCustomer (CustomerID customerID,
                                CustomerNIF customerNIF,
                                CustomerDesignation customerDesignation);
}
