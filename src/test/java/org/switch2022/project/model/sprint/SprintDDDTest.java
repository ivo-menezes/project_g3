package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintStatus;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintDDDTest {

    @Test
    @DisplayName("Create sprint successfully")
    void createSprintSuccessfully() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        //Act
        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        //Assert
        assertInstanceOf(SprintDDD.class, sprint);
    }

    @Test
    @DisplayName("equal sprint objects have same hash code")
    void ensureSameHashCode() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod_1 = mock(TimePeriod.class);
        TimePeriod timePeriod_2 = mock(TimePeriod.class);

        SprintDDD sprint_1 = new SprintDDD(sprintID, timePeriod_1);
        SprintDDD sprint_2 = new SprintDDD(sprintID, timePeriod_2);
        //Act
        int sprint_1HashCode = sprint_1.hashCode();
        int sprint_2HashCode = sprint_2.hashCode();
        //Assert
        assertEquals(sprint_1HashCode, sprint_2HashCode);
    }

    @Test
    @DisplayName("different sprint objets have different hash code")
    void ensureDifferentHashCode() {
        //Arrange
        SprintID sprintID_1 = mock(SprintID.class);
        SprintID sprintID_2 = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint_1 = new SprintDDD(sprintID_1, timePeriod);
        SprintDDD sprint_2 = new SprintDDD(sprintID_2, timePeriod);
        //Act
        int sprint_1HashCode = sprint_1.hashCode();
        int sprint_2HashCode = sprint_2.hashCode();
        //Assert
        assertNotEquals(sprint_1HashCode, sprint_2HashCode);
    }

    @Test
    @DisplayName("ensure SprintID is not null")
    void checkIfSprintNumberIsNull() {
        //Arrange
        TimePeriod timePeriod = mock(TimePeriod.class);
        String expectedMessage = "Missing value, please try again.";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new SprintDDD(null, timePeriod);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure time period is not null")
    void checkIfTimePeriodIsNull() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        String expectedMessage = "Missing value, please try again.";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new SprintDDD(sprintID, null);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure sprint ID is returned")
    void ensureSprintIDIsReturned() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);

        //Act
        SprintID result = sprint.identity();
        //Assert
        assertEquals(sprintID, result);
    }

    @Test
    @DisplayName("ensure time period is returned")
    void ensureTimePeriodIsReturned() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        //Act
        TimePeriod result = sprint.getTimePeriod();
        //Assert
        assertEquals(timePeriod, result);
    }

    @Test
    @DisplayName("object equals itself")
    void testEqualsWithItself() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint_1 = new SprintDDD(sprintID, timePeriod);
        //Act
        boolean result = sprint_1.equals(sprint_1);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal null")
    void testEqualsWithNull() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        //Act
        boolean result = sprint.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("object does not equal object of another class")
    void testEqualsWithAnotherClass() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        String fakeSprint = "Fake Sprint";
        //Act
        boolean result = sprint.equals(fakeSprint);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("object equals object with same id")
    void testEqualsWithSameID() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint_1 = new SprintDDD(sprintID, timePeriod);
        SprintDDD sprint_2 = new SprintDDD(sprintID, timePeriod);
        //Act
        boolean result = sprint_1.equals(sprint_2);
        //Assert
        assertTrue(result);

    }
    @Test
    @DisplayName("Return the status of a sprint")
    void testIfStatusIsReturned(){
        //arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);

        //act
        SprintStatus resultStatus = sprint.getSprintStatus();

        //assert
        assertEquals(SprintStatus.Planned, resultStatus);
    }

    @Test
    @DisplayName("Ensure the sprint status has been successfully changed")
    void setStatus(){
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID,timePeriod,SprintStatus.Open);

        //act
        sprint.setStatus(SprintStatus.Closed);
        SprintStatus result = sprint.getSprintStatus();

        //assert
        assertEquals(SprintStatus.Closed, result);
    }

    @Test
    @DisplayName("Ensure that a list of UserStoryInSprint is successfully retrieved")
    void ensureSprintBacklogIsRetrieved(){
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        List<UserStoryInSprint> expected = new ArrayList<>();

        //Act
        List<UserStoryInSprint> result = sprint.getUserStoriesInSprintList();
        //Assert
        assertEquals(expected, result);

    }

}