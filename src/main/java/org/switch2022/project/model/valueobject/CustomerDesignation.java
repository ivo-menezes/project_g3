package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;
import java.util.Objects;

public class CustomerDesignation implements ValueObject {

    private String customerDesignation;

    /**
     * Constructor for CustomerDesignation.
     * @param customerDesignation is the customer's name.
     *
     */
    public CustomerDesignation (String customerDesignation) {
        if (customerDesignation==null || customerDesignation.isBlank() || customerDesignation.isEmpty()) {
            throw new IllegalArgumentException("customerDesignation cannot be null/blank/empty");
        }
        this.customerDesignation = customerDesignation;
    }

    protected CustomerDesignation () {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDesignation)) return false;
        CustomerDesignation that = (CustomerDesignation) o;
        return customerDesignation.equals(that.customerDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerDesignation);
    }

    @Override
    public String toString() {
        return this.customerDesignation;
    }
}
