package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypologyList {

    private List<Typology> typologyList;

    /**
     * constructor that initializes a Typology list as an empty ArrayList
     */

    public TypologyList(){
        this.typologyList = new ArrayList<>();
    }


    /**
     * creates a new typology
     * @param typologyDesignation String with the designation of the typology
     * @return true if new typology is successfully created, false otherwise
     */

    public boolean createTypology(String typologyDesignation){

            Typology newTypology = new Typology(typologyDesignation);

        return add(newTypology);
    }


    /**
     * adds typology to typology list.
     * @param typology to be added to typology list
     * @return true if typology is successfully added to the list, false it's already listed.
     */

    public boolean add(Typology typology) {
        boolean typologyAddedSuccess= true;

        if(listDoesNotContain(typology)){
            this.typologyList.add(typology);
        }else {
            typologyAddedSuccess = false;
        }
        return typologyAddedSuccess;
    }

    /**
     * checks if typologyList already contains a typology
     * @param typology object to be added to the list
     * @return true if the object is new to the list, false otherwise.
     */

    private boolean listDoesNotContain(Typology typology){

        return !typologyList.contains(typology);
    }
}
