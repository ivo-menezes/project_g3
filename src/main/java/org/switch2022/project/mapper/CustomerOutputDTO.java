package org.switch2022.project.mapper;

import java.util.Objects;

public class CustomerOutputDTO {

    public Long customerID;
    public String customerNIF;
    public String customerDesignation;

    public CustomerOutputDTO(Long customerID, String customerNIF, String customerDesignation){
        this.customerID = customerID;
        this.customerNIF = customerNIF;
        this.customerDesignation = customerDesignation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerOutputDTO)) return false;
        CustomerOutputDTO that = (CustomerOutputDTO) o;
        return Objects.equals(customerID, that.customerID)
                && Objects.equals(customerNIF, that.customerNIF)
                && Objects.equals(customerDesignation, that.customerDesignation);
    }

}
