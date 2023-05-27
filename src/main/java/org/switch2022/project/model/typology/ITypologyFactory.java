
package org.switch2022.project.model.typology;


import org.switch2022.project.model.valueobject.TypologyDesignation;
import org.switch2022.project.model.valueobject.TypologyID;

public interface ITypologyFactory {


    TypologyDDD createTypology(TypologyID typologyID, TypologyDesignation typologyDesignation);
}


