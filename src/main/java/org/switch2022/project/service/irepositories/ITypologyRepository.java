package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;

import java.util.ArrayList;
import java.util.Optional;

public interface ITypologyRepository {

    TypologyDDD save(TypologyDDD typology);

    boolean containsTypologyDesignation(TypologyDesignation typologyDesignation);

    Optional<TypologyDDD> getByDesignation(TypologyDesignation typologyDesignation);

    ArrayList<TypologyDDD> getAll();
}
