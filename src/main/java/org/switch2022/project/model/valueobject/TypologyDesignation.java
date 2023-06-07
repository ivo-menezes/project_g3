package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;


public class TypologyDesignation implements ValueObject, Serializable {

    private final String designation;

    /**
     * Constructor for TypologyDesignation with specified designation
     *
     * @param designation to be set
     */
    public TypologyDesignation(String designation) {

        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Typology designation must not be null");
        } else {
            this.designation = designation;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypologyDesignation that = (TypologyDesignation) o;
        return designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }


    @Override
    public String toString() {
        return this.designation;
    }


}
