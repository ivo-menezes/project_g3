package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.UserStoryEffortEstimate;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class UserStoryInSprintTest {
    @Test
    @DisplayName("ensure UserStoryInSprintID is not null")
    void ensureUSInSprintNotNull() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);
        String expectedMessage = "ID for User Story in Sprint cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryInSprint(null, userStoryEffortEstimate);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryInSprintID is returned")
    void ensureUSInSprintIDIsReturned() {
        //Arrange
        UserStoryInSprintID userStoryInSprint_ID = mock(UserStoryInSprintID.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);

        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprint_ID, userStoryEffortEstimate);
        UserStoryInSprintID expected = userStoryInSprint_ID;
        //Act
        UserStoryInSprintID result = userStoryInSprint.identity();
        //Assert
        assertEquals(expected, result);
    }



    @Test
    @DisplayName("ensure UserStoryEffortEstimate is not null")
    void ensureUSEfforteEstimateNotNull() {
        //Arrange
        UserStoryInSprintID userStoryInSprint_ID = mock(UserStoryInSprintID.class);
        String expectedMessage = "User Story effort estimate in Sprint cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryInSprint(userStoryInSprint_ID, null);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure UserStoryEffortEstimate is returned")
    void ensureUSEffortEstimateIsReturned() {
        //Arrange
        UserStoryInSprintID userStoryInSprint_ID = mock(UserStoryInSprintID.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);

        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprint_ID, userStoryEffortEstimate);
        UserStoryEffortEstimate expected = userStoryEffortEstimate;
        //Act
        UserStoryEffortEstimate result = userStoryInSprint.getUserStoryEffortEstimate();
        //Assert
        assertEquals(expected, result);
    }

}