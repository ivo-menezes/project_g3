package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;


@Component
public class TypologyDomainDataAssembler {


    /**
     * Converts Typology to TypologyJpa
     *
     * @param typologyDDD to be converted
     * @return the converted TypologyJpa
     */
    public TypologyJpa toData(TypologyDDD typologyDDD) {
        TypologyDesignation typologyDesignation = typologyDDD.getTypologyDesignation();
        String designation = typologyDesignation.toString();

        return new TypologyJpa(designation);
    }


    /**
     * Converts TypologyJpa to Typology
     *
     * @param typologyJpa to be converted
     * @return the converted Typology
     */
    public TypologyDDD toDomain(TypologyJpa typologyJpa) {
        Long id = typologyJpa.getId();
        TypologyID typologyID = new TypologyID(id);

        String designation = typologyJpa.getTypologyDesignation();
        TypologyDesignation typologyDesignation = new TypologyDesignation(designation);

        return new TypologyDDD(typologyID, typologyDesignation);
    }
}



