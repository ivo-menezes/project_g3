package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TypologyDesignation implements DomainId {

    private String designation;

    /**
     * Constructor for TypologyDesignation with specified designation
     * @param designation to be set
     */
    public TypologyDesignation(String designation) {

        if (designation != null && !designation.isBlank() && !designation.isEmpty()) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("Typology designation must not be null");
        }
    }

    /**
     * Default constructor used by the persistence framework
     */
    protected TypologyDesignation() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof TypologyDesignation) {
            TypologyDesignation designation = (TypologyDesignation) object;
            if (this.designation.equals(designation.designation))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }


    @Override
    public String toString(){
        return this.designation;
    }
}
