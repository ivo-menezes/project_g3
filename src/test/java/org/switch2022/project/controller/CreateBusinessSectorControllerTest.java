package org.switch2022.project.controller;


import org.junit.jupiter.api.Test;
import org.switch2022.project.model.BusinessSectorList;

import static org.junit.jupiter.api.Assertions.*;

class CreateBusinessSectorControllerTest {


    @Test
    void createdSuccessfully(){
        BusinessSectorList newList = new BusinessSectorList();
        CreateBusinessSectorController controller = new CreateBusinessSectorController(newList);
        boolean resultedList = controller.createBusinessSector("Financial");

        assertTrue(resultedList);
        assertNotEquals(false, resultedList);
    }

    @Test
    void createBusinessSector() {
        String designation = "Financial";
        BusinessSectorList newList = new BusinessSectorList();
        CreateBusinessSectorController controller = new CreateBusinessSectorController(newList);

        assertTrue(controller.createBusinessSector(designation));
    }

    @Test
    void checkIfItCreates(){
        String designation = "Financial";
        BusinessSectorList newList = new BusinessSectorList();
        CreateBusinessSectorController controller = new CreateBusinessSectorController(newList);

        assertTrue(controller.createBusinessSector(designation));
        assertNotEquals(false, controller.createBusinessSector(designation));
    }

    /***
     * Using a null to ensure the tests catch the error.
     */
    @Test
    void checkIfItDesignationIsNotValidated(){
        BusinessSectorList newList = new BusinessSectorList();
        CreateBusinessSectorController controller = new CreateBusinessSectorController(newList);

        assertFalse(controller.createBusinessSector(null));
        assertNotEquals(true, controller.createBusinessSector(null));
    }
}