package org.switch2022.project.repository;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintRepositoryTest {


    @Test
    public void checkIfRepositoryIsCorrectlyCalled(){
        new SprintRepository();
    }

    @Test
    public void checkIfRepositoryAddsSprintCorrectly(){
        SprintRepository sprintData = new SprintRepository();
        SprintDDD sprintMock = mock(SprintDDD.class);

        boolean result = sprintData.save(sprintMock);

        assertTrue(result);
    }

    @Test
    public void checkIfRepositoryDoesNotContainID(){
        SprintRepository sprintData = new SprintRepository();
        SprintID sprintIDMock = mock(SprintID.class);

        boolean result = sprintData.containsID(sprintIDMock);

        assertFalse(result);
    }
    private int countIterable(Iterable<?> iterable) {
        int count = 0;
        for (Object item : iterable) {
            count++;
        }
        return count;
    }
    @Test
    public void checkIfRepositoryHasAnythingInIt(){
        SprintRepository sprintDataNew = new SprintRepository();

        Iterable<SprintDDD> result = sprintDataNew.findAll();

        assertEquals(0, countIterable(result));
    }
     @Test
    public void checkIfRepositoryContainsID() throws ParseException {
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         SprintRepository sprintData = new SprintRepository();

         TimePeriod newTimePeriod = new TimePeriod(formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));
         ProjectCode projectCode = new ProjectCode("PT6");
         SprintNumber newSprintNumber = new SprintNumber(5);

         SprintID sprintID = new SprintID(projectCode, newSprintNumber);
         SprintID sprintIDNumberTwo = new SprintID(projectCode, newSprintNumber);

         SprintDDD newSprint = new SprintDDD(sprintID, newTimePeriod);

         sprintData.save(newSprint);
         boolean result = sprintData.containsID(sprintIDNumberTwo);

         assertTrue(result);
     }

    @Test
    public void checkIfRepositoryHasSprintWithID() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SprintRepository sprintData = new SprintRepository();

        TimePeriod newTimePeriod = new TimePeriod(formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));
        TimePeriod anotherTimePeriod = new TimePeriod(formatter.parse("16/02/2022"), formatter.parse("25/02/2022"));
        ProjectCode projectCode = new ProjectCode("PT6");
        SprintNumber newSprintNumber = new SprintNumber(5);
        SprintNumber anotherSprintNumber = new SprintNumber(6);

        SprintID sprintID = new SprintID(projectCode, newSprintNumber);
        SprintID sprintIDNumberTwo = new SprintID(projectCode, anotherSprintNumber);

        SprintDDD newSprint = new SprintDDD(sprintID, newTimePeriod);
        SprintDDD anotherSprint = new SprintDDD(sprintIDNumberTwo, anotherTimePeriod);

        sprintData.save(newSprint);
        sprintData.save(anotherSprint);

        Optional result = sprintData.getByID(sprintIDNumberTwo);

        assertTrue(result.isPresent());
    }

    @Test
    public void checkIfRepositoryObtainsSprintWithID() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SprintRepository sprintData = new SprintRepository();

        TimePeriod newTimePeriod = new TimePeriod(formatter.parse("01/02/2022"), formatter.parse("15/02/2022"));
        TimePeriod anotherTimePeriod = new TimePeriod(formatter.parse("16/02/2022"), formatter.parse("25/02/2022"));
        ProjectCode projectCode = new ProjectCode("PT6");
        SprintNumber newSprintNumber = new SprintNumber(5);
        SprintNumber anotherSprintNumber = new SprintNumber(6);
        SprintNumber thirdSprintNumber = new SprintNumber(7);

        SprintID sprintID = new SprintID(projectCode, newSprintNumber);
        SprintID sprintIDNumberTwo = new SprintID(projectCode, anotherSprintNumber);
        SprintID sprintIDNumberThree = new SprintID(projectCode, thirdSprintNumber);

        SprintDDD newSprint = new SprintDDD(sprintID, newTimePeriod);
        SprintDDD anotherSprint = new SprintDDD(sprintIDNumberTwo, anotherTimePeriod);

        sprintData.save(newSprint);
        sprintData.save(anotherSprint);

        Optional result = sprintData.getByID(sprintIDNumberThree);

        assertTrue(result.isEmpty());
    }
}