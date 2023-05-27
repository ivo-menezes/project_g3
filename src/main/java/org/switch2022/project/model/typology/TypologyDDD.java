
package org.switch2022.project.model.typology;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;

import java.util.Objects;


public class TypologyDDD implements AggregateRoot<TypologyID> {

    private final TypologyID typologyID;
    private final TypologyDesignation typologyDesignation;

    /**
     * Constructor for TypologyDDD
     * @param typologyID the entity id
     * @param typologyDesignation the entity designation
     */
    public TypologyDDD(TypologyID typologyID, TypologyDesignation typologyDesignation) {

        if(typologyDesignation == null) {
            throw new IllegalArgumentException("Typology designation must not be null");
        }
        this.typologyID = typologyID;
        this.typologyDesignation = typologyDesignation;
    }

    /**
     * Returns the identity of the TypologyDDD object
     * @return the TypologyID representing the identity of TypologyDDD
     */
    @Override
    public TypologyID identity() {
        return this.typologyID;
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

