package org.switch2022.project.model;

import java.util.Objects;

public class Customer {

    private String designation;

    public Customer(String designation) {

        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer that = (Customer) o;
        return designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }
}
