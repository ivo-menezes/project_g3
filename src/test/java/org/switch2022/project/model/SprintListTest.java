package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void addSameSprint() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        //arrange
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");


        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 2;
        sprintDTOTwo.startDate = formatter.parse("01/03/2022");
        sprintDTOTwo.endDate = formatter.parse("30/03/2023");

        //act
        SprintList sprintList = new SprintList();
        sprintList.createSprint(sprintDTO);
        sprintList.createSprint(sprintDTOTwo);

        //assert
        assertFalse(sprintList.createSprint(sprintDTOTwo));
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
    @Test
    void doesNotAddSprintWithinExistingDates() throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        //Arrange

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 2;
        sprintDTOTwo.startDate = formatter.parse("02/03/2022");
        sprintDTOTwo.endDate = formatter.parse("30/03/2022");

        SprintDTO sprintDTOThree = new SprintDTO();
        sprintDTOThree.sprintNumber = 3;
        sprintDTOThree.startDate = formatter.parse("02/03/2022");
        sprintDTOThree.endDate = formatter.parse("30/04/2022");

        SprintDTO sprintDTOFour = new SprintDTO();
        sprintDTOFour.sprintNumber = 4;
        sprintDTOFour.startDate = formatter.parse("02/03/2022");
        sprintDTOFour.endDate = formatter.parse("30/03/2022");

        //Act

        SprintList sprintList = new SprintList();

        sprintList.createSprint(sprintDTO);
        sprintList.createSprint(sprintDTOTwo);
        sprintList.createSprint(sprintDTOThree);

        //Assert

        assertFalse(sprintList.createSprint(sprintDTOFour));
    }

    @Test
    void checkIfDoesNotAddSprintDTOWithOverLappingDates() throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        //Arrange

        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 2;
        sprintDTOTwo.startDate = formatter.parse("30/03/2022");
        sprintDTOTwo.endDate = formatter.parse("30/03/2022");

        SprintDTO sprintDTOThree = new SprintDTO();
        sprintDTOThree.sprintNumber = 3;
        sprintDTOThree.startDate = formatter.parse("02/04/2022");
        sprintDTOThree.endDate = formatter.parse("30/04/2022");

        SprintDTO sprintDTOFour = new SprintDTO();
        sprintDTOFour.sprintNumber = 4;
        sprintDTOFour.startDate = formatter.parse("02/03/2022");
        sprintDTOFour.endDate = formatter.parse("02/03/2022");

        //Act

        SprintList sprintList = new SprintList();
        sprintList.createSprint(sprintDTO);
        sprintList.createSprint(sprintDTOTwo);
        sprintList.createSprint(sprintDTOThree);

        //Assert

        assertFalse(sprintList.createSprint(sprintDTOFour));
    }
}
