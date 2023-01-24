package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TypologyListTest {


    @Test
    @DisplayName("ensure creating valid typology succeeds")
    void createTypologySucceeds(){
        //arrange
        TypologyList typologyList = new TypologyList();
        String typologyDesignation = "Fixed cost";
        //act
        boolean result = typologyList.createTypology(typologyDesignation);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure adding typology to the list succeeds")
    void createAndAddTypologySucceeds(){
        //arrange
        Typology typology = new Typology ("Fixed cost");
        TypologyList typologyList = new TypologyList();
        //act
        boolean result = typologyList.add(typology);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure creating null typology and adding it to the list fails")
    void createAndAddTypologyFails(){
        //arrange
        Typology typology = new Typology ("Fixed cost");
        TypologyList typologyList = new TypologyList();
        typologyList.add(typology);
        //act
        Typology anotherTypology = null;
        typologyList.add(anotherTypology);
        //assert
        assertFalse(typologyList.add(anotherTypology));
    }

    @Test
    @DisplayName ("ensure new typology designation is valid")
    void validateTypologyDesignationSucceeds(){
        //arrange
        TypologyList typologyList = new TypologyList();
        String typologyDesignation = "Fixed cost";
        Typology typology = new Typology (typologyDesignation);
        typologyList.add(typology);
        String newTypologyDesignation = "Time and materials";
        //act
        boolean result = typologyList.validateTypologyDesignation(newTypologyDesignation);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName ("ensure failure when attempting to create already existent typology")
    void repeatedTypologyDesignationIsInvalid () {
        //arrange
        TypologyList typologyList = new TypologyList();
        String typologyDesignation = "Fixed cost";
        Typology typology = new Typology(typologyDesignation);
        typologyList.add(typology);
        boolean expected = false;
        //act
        boolean result = typologyList.createTypology("Fixed cost");

        //assert
        assertEquals(expected, result);
    }

}