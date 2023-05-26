package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class CustomerJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String customerNIF;

    private  String customerDesignation;

    public CustomerJPA(String customerNIF, String customerDesignation) {
        if (customerNIF == null) {
            throw new IllegalArgumentException("customerNIF, cannot be null");
        }
        if (customerDesignation == null) {
            throw new IllegalArgumentException("customerDesignation cannot be null");
        }
        this.customerNIF = customerNIF;
        this.customerDesignation = customerDesignation;
    }

    protected CustomerJPA() {}

    public Long getId() {
        return id;
    }
    public String getCustomerNIF() {
        return customerNIF;
    }

    public String getCustomerDesignation() {
        return customerDesignation;
    }

}
