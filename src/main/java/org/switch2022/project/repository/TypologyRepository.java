package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.datamodel.JPA.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.repository.JPA.TypologyJpaRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.ArrayList;


@Repository
public class TypologyRepository implements ITypologyRepository {

    @Autowired
    TypologyJpaRepository typologyJpaRepository;

    @Autowired
    TypologyDomainDataAssembler typologyDomainDataAssembler;

    /**
     * Saves a TypologyDDD object in the repository by converting it to a TypologyJpa
     *
     * @param typology The TypologyDDD object to be saved
     * @return the saved TypologyDDD
     */
    public TypologyDDD save(TypologyDDD typology) {

        TypologyDesignation typologyDesignation = typology.getTypologyDesignation();

        if (containsTypologyDesignation(typologyDesignation)) {
            throw new IllegalArgumentException("Typology already exists");
        } else {
            TypologyJpa typologyJpa = typologyDomainDataAssembler.toData(typology);
            TypologyJpa savedTypologyJpa = typologyJpaRepository.save(typologyJpa);
            return typologyDomainDataAssembler.toDomain(savedTypologyJpa);
        }
    }


    /**
     * Checks if a Typology with the specified business key (TypologyDesignation) already exists
     *
     * @param typologyDesignation to check
     * @return true if a typology with that typologyDesignation already exists, false otherwise.
     */

    public boolean containsTypologyDesignation(TypologyDesignation typologyDesignation) {
        String designation = typologyDesignation.toString();
        return typologyJpaRepository.existsByTypologyDesignation(designation);
    }

    /**
     * Verifies if this id exists
     * @param typologyID the id to be checked
     * @return true if it already exists, false otherwise
     */
    public boolean containsID(TypologyID typologyID) {
        return typologyJpaRepository.existsById(typologyID.getId());
    }


    /**
     * Method responsible for return all Typologies from database.
     * @return ArrayList<TypologyDDD>
     */
    public ArrayList<TypologyDDD> getAll() {
        ArrayList<TypologyDDD> typologies = new ArrayList();

        Iterable<TypologyJpa> TypologiesJPA = typologyJpaRepository.findAll();

        for (TypologyJpa typologyJpa : TypologiesJPA) {
            typologies.add(typologyDomainDataAssembler.toDomain(typologyJpa));
        }

        return typologies;
    }
}


