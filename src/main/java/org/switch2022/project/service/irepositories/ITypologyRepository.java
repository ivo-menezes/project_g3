package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;

import java.util.ArrayList;

public interface ITypologyRepository {

    TypologyDDD save(TypologyDDD typology);

    boolean containsTypologyDesignation(TypologyDesignation typologyDesignation);

    boolean containsID(TypologyID typologyID);

    ArrayList<TypologyDDD> getAll();
}
