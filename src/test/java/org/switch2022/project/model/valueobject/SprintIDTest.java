package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintIDTest {

    final String expectedMessage1 = "Project code must not be null";
    final String expectedMessage2 = "Sprint number must not be null";
    final ProjectCode projectCode = mock(ProjectCode.class);
    final SprintNumber sprintNumber = mock(SprintNumber.class);

    @Test
    @DisplayName("Check if sprint ID is created")
    public void checkIfSprintIdIsCreated() {
        new SprintID(projectCode, sprintNumber);
    }

    @Test
    @DisplayName("Throw exception if project code is null")
    public void throwExceptionIfProjectCodeIsNull() {

        Exception exception = assertThrows(Exception.class, () -> {
            new SprintID(null, sprintNumber);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage1));
    }

    @Test
    @DisplayName("Throw exception if sprint number is null")
    public void throwExceptionIfSprintNumberIsNull() {

        Exception exception = assertThrows(Exception.class, () -> {
            new SprintID(projectCode, null);
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage2));
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        SprintID sprintID = new SprintID(projectCode, sprintNumber);

        boolean isEquals = sprintID.equals(null);

        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same object")
    public void returnTrueEqualsWithSameObject() {

        SprintID sprintID = new SprintID(projectCode, sprintNumber);

        boolean isEquals = sprintID.equals(sprintID);

        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same IDs")
    public void returnTrueEqualsWithSameIds() {

        SprintID sprintID1 = new SprintID(projectCode, sprintNumber);
        SprintID sprintID2 = new SprintID(projectCode, sprintNumber);

        boolean isEquals = sprintID1.equals(sprintID2);

        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return false in equals with different Ids")
    public void returnFalseEqualsWithDifferentSprintNumbers() {

        SprintID sprintID1 = new SprintID(projectCode, sprintNumber);
        SprintNumber sprintNumber2 = mock(SprintNumber.class);
        SprintID sprintID2 = new SprintID(projectCode, sprintNumber2);

        boolean isEquals = sprintID1.equals(sprintID2);

        assertFalse(isEquals);
    }

}