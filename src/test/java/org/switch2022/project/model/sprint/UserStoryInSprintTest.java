package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class UserStoryInSprintTest {
    @Test
    @DisplayName("ensure UserStoryInSprintID is not null")
    void ensureUSInSprintNotNull() {
        //Arrange
        String expectedMessage = "ID for User Story in Sprint must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryInSprint(null);
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

        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprint_ID);
        UserStoryInSprintID expected = userStoryInSprint_ID;
        //Act
        UserStoryInSprintID result = userStoryInSprint.identity();
        //Assert
        assertEquals(expected, result);
    }

}