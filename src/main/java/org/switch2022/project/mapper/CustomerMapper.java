package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapper {
    /**
     * Method responsible for converting DTO in a DTO with primitive properties.
     *
     * @param customer
     * @return CustomerOutputDTO object.
     */
    public CustomerOutputDTO toOutputDTO(CustomerDTO customer) {
        if (customer.customerID != null) {
            return new CustomerOutputDTO(customer.customerID.getId(),
                    customer.customerNIF.toString(), customer.customerDesignation.toString());
        } else {
            return new CustomerOutputDTO(null, customer.customerNIF.toString(),
                    customer.customerDesignation.toString());
        }
    }

    /**
     * Method responsible for converting ArrayList of DTO in a ArrayList of DTO with primitive properties.
     *
     * @return CustomerOutputDTO object.
     */
    public ArrayList<CustomerOutputDTO> toOutputDTO(ArrayList<CustomerDTO> customers) {

        ArrayList<CustomerOutputDTO> customersDTO = new ArrayList();

        for (CustomerDTO customer : customers) {

            CustomerOutputDTO customerOutPut = new CustomerOutputDTO(customer.customerID.getId(),
                    customer.customerNIF.toString(), customer.customerDesignation.toString());

            customersDTO.add(customerOutPut);
        }

        return customersDTO;
    }
}
