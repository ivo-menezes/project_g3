package org.switch2022.project.controller;

import org.switch2022.project.model.TypologyList;

public class CreateTypologyController {

    private TypologyList typologyList;


    /**
     * constructor that accepts existing TypologyList.
     * @param typologyList to be added to the controller
     */

    public CreateTypologyController(TypologyList typologyList) {

        this.typologyList = typologyList;
    }

    /**
     * requests typologyList to create a new typology
     * @param typologyDesignation of the new typology
     * @return true if typology is successfully created, false otherwise
     */


    public boolean createTypology(String typologyDesignation){

        return this.typologyList.createTypology(typologyDesignation);
    }

}
