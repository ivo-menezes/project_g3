
package org.switch2022.project.model.typology;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.TypologyDesignation;

import java.util.Objects;


public class TypologyDDD implements AggregateRoot<TypologyDesignation> {

    private final TypologyDesignation typologyDesignation;

    /**
     * Constructor for TypologyDDD with specified TypologyDesignation
     * @param typologyDesignation to construct the entity
     */
    public TypologyDDD(TypologyDesignation typologyDesignation) {

        if(typologyDesignation == null) {
            throw new IllegalArgumentException("Typology designation must not be null");
        }
        this.typologyDesignation = typologyDesignation;
    }

    /**
     * Returns the identity of the TypologyDDD object
     * @return the TypologyDesignation representing the identity of TypologyDDD
     */
    @Override
    public TypologyDesignation identity() {
        return this.typologyDesignation;
    }

    /**
     * Returns the TypologyDesignation of the TypologyDDD object
     * @return the TypologyDesignation associated with the TypologyDDD object
     */
    public TypologyDesignation getTypologyDesignation() {
        return this.typologyDesignation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof TypologyDDD) {
            TypologyDDD typologyDDD = (TypologyDDD) object;
            if (this.typologyDesignation.equals(typologyDDD.typologyDesignation))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typologyDesignation);
    }

    @Override
    public String toString(){
        return this.typologyDesignation.toString();
    }
}

