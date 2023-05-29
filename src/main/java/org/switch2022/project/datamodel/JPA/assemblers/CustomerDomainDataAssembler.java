package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.CustomerJPA;
import org.switch2022.project.model.customer.CustomerDDD;
import org.switch2022.project.model.valueobject.CustomerDesignation;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.CustomerNIF;

@Component
public class CustomerDomainDataAssembler {

    /**
     * Method responsible for converting domain objects into JPA.
     * @param customer
     * @return object in JPA format for persistence in the database.
     */
    public CustomerJPA toData (CustomerDDD customer) {
        return new CustomerJPA(customer.getCustomerNIF().toString(), customer.getCustomerDesignation().toString());
    }

    /**
     * Method responsible for converting JPA objects into domain objects.
     * @param customerJPA
     * @return domain objects
     */
    public CustomerDDD toDomain(CustomerJPA customerJPA) {
        CustomerNIF customerNIF = new CustomerNIF(customerJPA.getCustomerNIF());
        CustomerDesignation customerDesignation = new CustomerDesignation(customerJPA.getCustomerDesignation());
        CustomerID customerID = new CustomerID(customerJPA.getId());
        return new CustomerDDD(customerID,customerNIF,customerDesignation);
    }
}
