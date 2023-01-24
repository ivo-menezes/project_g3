package org.switch2022.project.model;

import java.util.Objects;

public class Typology {

    private String typologyDesignation;


    /**
     * constructor that accepts typology designation
     *
     * @param typologyDesignation to be added to Typology
     */
    public Typology(String typologyDesignation) {

        this.typologyDesignation = typologyDesignation;
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