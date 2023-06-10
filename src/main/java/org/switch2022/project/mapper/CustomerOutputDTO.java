package org.switch2022.project.mapper;

public class CustomerOutputDTO {

    public Long customerID;
    public String customerNIF;
    public String customerDesignation;

    public CustomerOutputDTO(Long customerID, String customerNIF, String customerDesignation){
        this.customerID = customerID;
        this.customerNIF = customerNIF;
        this.customerDesignation = customerDesignation;
    }
}
