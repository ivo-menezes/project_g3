package org.switch2022.project.model;

import java.util.Objects;

public class Typology {

    private final String typologyDesignation;


    /**
     * constructor that accepts typology designation
     *
     * @param typologyDesignation to be added to Typology
     */
    public Typology(String typologyDesignation) {
        if (!isTypologyDesignationValid(typologyDesignation)) {
            throw new IllegalArgumentException("Typology designation is not valid");
        }
        this.typologyDesignation = typologyDesignation;
    }

    /**
     * method that evaluates if typology designation is valid
     * @param typologyDesignation which must be "Fixed cost" or "Time and materials"
     * @return true if designation is valid. Returns false otherwise.
     */


    public boolean isTypologyDesignationValid(String typologyDesignation) {
        boolean isValid = false;

        if (typologyDesignation == "Fixed cost" || typologyDesignation == "Time and materials") {
            isValid = true;
        }
        return isValid;
    }

    /**
     * allows retrieving of typology
     * @return typology Designation
     */

    public String getTypologyDesignation() {
        return typologyDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Typology)) return false;
        Typology typology = (Typology) o;
        return typologyDesignation.equals(typology.typologyDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyDesignation);
    }
}
