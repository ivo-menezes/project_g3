package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateBusinessSectorControllerTest {

    @Test
    void createBusinessSector() {
        String designation = "Financial";
        CreateBusinessSectorController controller = new CreateBusinessSectorController();

        assertTrue(controller.createBusinessSector(designation));
    }
    @Test
    void checkIfItCreates(){
        String designation = "Financial";
        CreateBusinessSectorController controller = new CreateBusinessSectorController();

        assertTrue(controller.createBusinessSector(designation));
        assertNotEquals(false, controller.createBusinessSector(designation));
    }
    @Test
    void checkIfItDesignationIsNotValidated(){

        CreateBusinessSectorController controller = new CreateBusinessSectorController();

        assertFalse(controller.createBusinessSector(null));
        assertNotEquals(true, controller.createBusinessSector(null));
    }
}