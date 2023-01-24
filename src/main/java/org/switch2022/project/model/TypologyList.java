package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypologyList {

    private List<Typology> typologyList;

    /**
     * constructor that initializes a Typology list as an empty ArrayList
     */

    public TypologyList(){
        this.typologyList = new ArrayList<Typology>();
    }


    /**
     * creates a new typology
     * @param typologyDesignation String with the designation of the typology
     * @return true if new typology is successfully created, false otherwise
     */

    public boolean createTypology(String typologyDesignation){

        boolean typologyCreated = false;

        if(validateTypologyDesignation(typologyDesignation)) {
            typologyCreated = true;
        }
            Typology newTypology = new Typology(typologyDesignation);
            add(newTypology);

            return typologyCreated;
    }


    /**
     * validates typology designation according to business rules
     * @param typologyDesignation designation of the intended typology
     * @return true if typology name is valid, false otherwise
     */

    public boolean validateTypologyDesignation(String typologyDesignation) {

        boolean typologyValid = false;

        if(typologyDesignation.equals("Fixed cost") || typologyDesignation.equals("Time and materials")) {
            typologyValid = true;
        }

        for (int i = 0; i < this.typologyList.size() && typologyValid; i++) {
            Typology t = this.typologyList.get(i);
            String tDesignation = t.getTypologyDesignation();
            if (tDesignation.equals(typologyDesignation)) {
                typologyValid = false;
            }
        }
        return typologyValid;
    }


    /**
     * checks if typology is successfully added to typology list
     * @param typology to be added to typology list
     * @return true if typology is successfully added to the list, false otherwise
     */

    public boolean add(Typology typology) {
        boolean typologyAddedSuccess= true;
        if (typology == null) {
            typologyAddedSuccess = false;
        }
        this.typologyList.add(typology);
        return typologyAddedSuccess;
    }
}
