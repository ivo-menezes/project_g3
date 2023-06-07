
package org.switch2022.project.model.typology;


import org.springframework.stereotype.Component;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;

@Component
public class TypologyFactoryImpl implements ITypologyFactory{

    /**
     * Creates a new Typology instance with provided parameters
     * @param typologyID the identity
     * @param typologyDesignation the designation
     * @return the created Typology
     */
    @Override
    public TypologyDDD createTypology(TypologyID typologyID, TypologyDesignation typologyDesignation) {
        if (typologyDesignation == null) {
            throw new IllegalArgumentException("Typology designation must not be null");
        }

        return new TypologyDDD(typologyID, typologyDesignation);
    }
}







