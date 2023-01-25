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
        TypologyList typologyList = new TypologyList();
        CreateTypologyController controller = new CreateTypologyController(typologyList);

        //act
        boolean result = controller.createTypology("Fixed cost");

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure invalid/repeated typology is not created")
    void typologyCreatedFail(){
        //arrange
        TypologyList typologyList = new TypologyList();
        CreateTypologyController controller = new CreateTypologyController(typologyList);
        controller.createTypology("Fixed cost");

        //act
        boolean result = controller.createTypology("Fixed cost");

        //assert
        assertFalse(result);
    }
}