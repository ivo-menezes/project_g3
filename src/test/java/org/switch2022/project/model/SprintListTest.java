package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SprintListTest {

    @Test
    void createSprint() {
        //arrange
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(01 / 01 / 2023);
        sprintDTO.endDate = new Date(31 / 01 / 2023);
        SprintList sprintList = new SprintList();

        //act
        boolean result = sprintList.createSprint(sprintDTO);

        //assert
        assertTrue(result);
    }

    @Test
    void createSprintSameNumber() {
        //arrange
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(01 / 01 / 2023);
        sprintDTO.endDate = new Date(31 / 01 / 2023);
        SprintList sprintList = new SprintList();

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 1;
        sprintDTOTwo.startDate = new Date(02 / 01 / 2023);
        sprintDTOTwo.endDate = new Date(30 / 01 / 2023);

        //act
        sprintList.createSprint(sprintDTO);
        boolean result = sprintList.createSprint(sprintDTOTwo);

        //assert
        assertFalse(result);
    }

    @Test
    void addTwoSprints() {
        //arrange
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(1 / 2 / 2023);
        sprintDTO.endDate = new Date(31 / 2 / 2023);
        SprintList sprintList = new SprintList();

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 2;
        sprintDTOTwo.startDate = new Date(2 / 3 / 2023);
        sprintDTOTwo.endDate = new Date(30 / 3 / 2023);

        //act
        sprintList.createSprint(sprintDTO);
        boolean result = sprintList.createSprint(sprintDTOTwo);

        //assert
        assertTrue(result);
    }
    @Test
    void hasSprint() {
        //arrange
        Sprint sprint = new Sprint(1, new Date(01 / 01 / 2023), new Date(31 / 01 / 2023));
        Sprint sprintTwo = new Sprint(1, new Date(01 / 01 / 2023), new Date(31 / 01 / 2023));
        SprintList sprintList = new SprintList();

        //act
        sprintList.add(sprint);
        sprintList.add(sprintTwo);

        //assert
        assertTrue(sprintList.hasSprint(sprintTwo));
    }

    @Test
    void addSprint1() {
        Sprint sprint1 = new Sprint(1, new Date(7 / 3 / 2023), new Date(21 / 03 / 2023));
        Sprint sprint2 = new Sprint(2, new Date(22 / 3 / 2023), new Date(04 / 04 / 2023));
        SprintList sprintList = new SprintList();

        sprintList.add(sprint1);
        assertEquals(true, sprintList.hasSprint(sprint1));
    }

    @Test
    void addSprint2() {
        Sprint sprint1 = new Sprint(1, new Date(07 / 03 / 2023), new Date(21 / 03 / 2023));
        Sprint sprint2 = new Sprint(2, new Date(22 / 03 / 2023), new Date(04 / 04 / 2023));
        SprintList sprintList = new SprintList();

        sprintList.add(sprint2);
        assertEquals(true, sprintList.hasSprint(sprint2));
    }
    @Test
    void addSprint1And2() {
        Sprint sprint1 = new Sprint(1, new Date(07 / 03 / 2023), new Date(21 / 03 / 2023));
        Sprint sprint2 = new Sprint(2, new Date(22 / 03 / 2023), new Date(04 / 04 / 2023));
        SprintList sprintList = new SprintList();

        sprintList.add(sprint1);
        sprintList.add(sprint2);

        assertEquals(true, sprintList.hasSprint(sprint1));
        assertEquals(true, sprintList.hasSprint(sprint2));
        }
        @Test
        void addSameSprint() {
        Sprint sprint1 = new Sprint(1, new Date(07 / 03 / 2023), new Date(21 / 03 / 2023));
        SprintList sprintList = new SprintList();

        sprintList.add(sprint1);
        sprintList.add(sprint1);

        assertEquals(true, sprintList.hasSprint(sprint1));

        }
        @Test
        void addSprintNull() {

        Sprint sprint = null;
        SprintList sprintList = new SprintList();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sprintList.add(null);
        });
        String expectedMessage = "Sprint must not be empty";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
