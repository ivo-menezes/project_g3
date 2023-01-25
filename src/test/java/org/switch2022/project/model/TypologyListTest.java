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

        //act
        boolean result = typologyList.createTypology("Fixed cost");

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure creating already existing typology fails")
    void ensureCreatingAlreadyExistingTypologyFails(){
        //arrange
        TypologyList typologyList = new TypologyList();
        typologyList.createTypology("Fixed cost");

        //act
        boolean result = typologyList.createTypology("Fixed cost");

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure creating a second valid typology succeeds")
    void createAnotherTypologySucceeds(){
        //arrange
        TypologyList typologyList = new TypologyList();
        typologyList.createTypology("Fixed cost");

        //act
        boolean result = typologyList.createTypology("Time and materials");

        //assert
        assertTrue(result);
    }

}