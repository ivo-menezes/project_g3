package org.switch2022.project.model.customer;

import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerNIF;

public interface ICostumerFactory {

    CustomerDDD createCustomer (CustomerNIF customerNIF, CustomerDesignation customerDesignation);
}
