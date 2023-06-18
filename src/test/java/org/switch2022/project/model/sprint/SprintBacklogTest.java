package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;
import org.switch2022.project.model.valueobject.UserStoryStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintBacklogTest {
    @Test
    @DisplayName("ensure that passing a null UserStoryInSprint throwns exception")
    void ensureUserStoryInSprintNotNull() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        String expectedMessage = "User Story in Sprint cannot be null";

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> sprintBacklog.add(null));

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that adding a UserStoryInSprint to an empty backlog succeeds")
    void ensureAddingUSInSprintEmptyBacklogSucceeds() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);

        //Act
        boolean result = sprintBacklog.add(userStoryInSprint);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure that adding a UserStoryInSprint already in the backlog throws exception")
    void ensureAddingUSInSprintAlreadyInBacklogFails() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        //add UserStoryInSprint for the first time
        sprintBacklog.add(userStoryInSprint);
        String expectedMessage = "UserStoryInSprint already in backlog";
        //add UserStoryInSprint for the second time
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> sprintBacklog.add(userStoryInSprint));

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that returning backlog with 1 UserStoryInSprint succeeds")
    void ensureReturningBacklogWithUserStoryInSprintSucceeds() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        sprintBacklog.add(userStoryInSprint);
        int expectedSize = 1;

        //Act
        List<UserStoryInSprint> result = sprintBacklog.getUserStoriesInSprintList();
        int resultSize = result.size();

        //Assert
        assertEquals(expectedSize, resultSize);
    }

    @Test
    @DisplayName("Ensure that returns a correct list of user stories in sprint with status done")
    void ensureReturnsListOfUserStoriesInSprintWithStatusDone() {
        //Arrange
        SprintBacklog sprintBacklog = new SprintBacklog();
        UserStoryInSprint userStoryInSprint1 = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryInSprint2 = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryInSprint3 = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryInSprint4 = mock(UserStoryInSprint.class);

        sprintBacklog.add(userStoryInSprint1);
        sprintBacklog.add(userStoryInSprint2);
        sprintBacklog.add(userStoryInSprint3);
        sprintBacklog.add(userStoryInSprint4);

        UserStoryStatus userStoryStatusDone = UserStoryStatus.DONE;
        UserStoryStatus userStoryStatusToDo = UserStoryStatus.TO_DO;
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryInSprintID userStoryInSprintID1 = mock(UserStoryInSprintID.class);
        UserStoryInSprintID userStoryInSprintID2 = mock(UserStoryInSprintID.class);

        when(userStoryInSprint1.getUserStoryInSprintStatus()).thenReturn(userStoryStatusDone);
        when(userStoryInSprint2.getUserStoryInSprintStatus()).thenReturn(userStoryStatusDone);
        when(userStoryInSprint3.getUserStoryInSprintStatus()).thenReturn(userStoryStatusToDo);
        when(userStoryInSprint4.getUserStoryInSprintStatus()).thenReturn(userStoryStatusToDo);

        when(userStoryInSprint1.identity()).thenReturn(userStoryInSprintID1);
        when(userStoryInSprint2.identity()).thenReturn(userStoryInSprintID2);
        when(userStoryInSprintID1.getUserStoryID()).thenReturn(userStoryID1);
        when(userStoryInSprintID2.getUserStoryID()).thenReturn(userStoryID2);

        List<UserStoryID> listUserStoryIDs = new ArrayList<>();
        listUserStoryIDs.add(userStoryID1);
        listUserStoryIDs.add(userStoryID2);

        //Act
        List<UserStoryID> result = sprintBacklog.listOfUserStoriesInSprintWithStatusDone();

        //Assert
        assertEquals(listUserStoryIDs, result);
    }
}