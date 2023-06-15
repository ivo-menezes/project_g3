package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

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

}