package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.TypologyList;

import static org.junit.jupiter.api.Assertions.*;

class CreateTypologyControllerTest {

    @Test
    @DisplayName("ensure valid typology is successfully created")
    void typologyCreatedSuccess(){
        //arrange
        String typologyDesignation = "Fixed cost";
        TypologyList typologyList = new TypologyList();
        CreateTypologyController controller = new CreateTypologyController(typologyList);
        //act
        boolean result = controller.createTypology(typologyDesignation);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("ensure invalid typology is not created")
    void typologyCreatedFail(){
        //arrange
        String typologyDesignation = "ThisIsATest";
        TypologyList typologyList = new TypologyList();
        CreateTypologyController controller = new CreateTypologyController(typologyList);
        //act
        boolean result = controller.createTypology(typologyDesignation);
        //assert
        assertFalse(result);
    }

}