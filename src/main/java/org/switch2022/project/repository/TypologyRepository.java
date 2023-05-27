package org.switch2022.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.switch2022.project.datamodel.JPA.TypologyJpa;
import org.switch2022.project.datamodel.JPA.assemblers.TypologyDomainDataAssembler;
import org.switch2022.project.model.typology.*;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.repository.JPA.TypologyJpaRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.Optional;


@Repository
public class TypologyRepository implements ITypologyRepository {

    @Autowired
    private final TypologyJpaRepository typologyJpaRepository;

    @Autowired
    private final TypologyDomainDataAssembler typologyDomainDataAssembler;

    public TypologyRepository(TypologyJpaRepository typologyJpaRepository, TypologyDomainDataAssembler typologyAssembler) {
        this.typologyJpaRepository = typologyJpaRepository;
        this.typologyDomainDataAssembler = typologyAssembler;
    }


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
     * Retrieves a TypologyDDD object from the repository based on the specified typology designation
     *
     * @param typologyDesignation of the object to retrieve
     * @return an Optional containing the TypologyDDD object if found, or an empty Optional otherwise.
     */

    public Optional<TypologyDDD> getByDesignation(TypologyDesignation typologyDesignation) {
        String designation = typologyDesignation.toString();
        Optional<TypologyJpa> oTypologyJpa = typologyJpaRepository.getByTypologyDesignation(designation);

        if (oTypologyJpa.isPresent()) {
            TypologyJpa typologyDDDJpa = oTypologyJpa.get();

            TypologyDDD typology = typologyDomainDataAssembler.toDomain(typologyDDDJpa);
            return Optional.of(typology);
        } else {
            return Optional.empty();
        }
    }
}


