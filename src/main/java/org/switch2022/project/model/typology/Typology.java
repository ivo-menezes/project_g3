package org.switch2022.project.model.typology;

import java.util.Objects;

public class Typology {

    private String typologyDesignation;


    /**
     * constructor that accepts typology designation
     * object is created if it complies with business rules. Otherwise, an exception is thrown.
     * @param typologyDesignation to be added to Typology
     */
    public Typology(String typologyDesignation) {
        if(designationIsInvalid(typologyDesignation)){
            throw new IllegalArgumentException("Typology designation is invalid");
        }
        this.typologyDesignation = typologyDesignation;
    }

    private static boolean designationIsInvalid(String typologyDesignation) {
        return !typologyDesignation.equals("Fixed cost") && !typologyDesignation.equals("Time and materials");
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