package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

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
    @DisplayName("ensure user story successfully added to sprint Backlog")
    void addUserStoryInSprintToSprintBacklogTrue() {
        //Arrange
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        //Act
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryInSprint);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure the same user story cannot been added twice to sprint Backlog")
    void addUserStoryInSprintToSprintBacklogFalse() {
        //Arrange
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        //Act
        sprint.addUserStoryToSprintBacklog(userStoryInSprint);
        boolean result = sprint.addUserStoryToSprintBacklog(userStoryInSprint);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure that when a user story equals null it is not added to the Sprint Backlog")
    void addUserStoryInSprintToSprintBacklogNull() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        String expectedMessage = "UserStoryInSprint must not be null";

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            sprint.addUserStoryToSprintBacklog(null);
        });
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
        SprintID expected = sprintID;
        //Act
        SprintID result = sprint.identity();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure time period is returned")
    void ensureTimePeriodIsReturned() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);
        TimePeriod expected = timePeriod;
        //Act
        TimePeriod result = sprint.getTimePeriod();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("object equals itself")
    void testEqualsWithItself() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintDDD sprint_1 = new SprintDDD(sprintID, timePeriod);
        SprintDDD sprint_2 = sprint_1;
        //Act
        boolean result = sprint_1.equals(sprint_2);
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

}