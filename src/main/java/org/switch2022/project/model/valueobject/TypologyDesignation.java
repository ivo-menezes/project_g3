package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.util.Objects;

public class TypologyDesignation implements DomainId {

    private String designation;

    public TypologyDesignation(String designation) {

        if (designation != null && !designation.isBlank() && !designation.isEmpty()) {
            this.designation = designation;
        } else {
            throw new IllegalArgumentException("Typology designation must not be null");
        }
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
}
