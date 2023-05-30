package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    /**
     * Method responsible for converting DTO in a DTO with primitive properties.
     * @param customer
     * @return CustomerOutputDTO object.
     */
    public CustomerOutputDTO toOutputDTO(CustomerDTO customer) {
        if (customer.customerID != null)
            return new CustomerOutputDTO(customer.customerID.getId(), customer.customerNIF.toString(), customer.customerDesignation.toString());
        else
            return new CustomerOutputDTO(null, customer.customerNIF.toString(), customer.customerDesignation.toString());
    }
}
