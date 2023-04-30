package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryNumberTest {

    @DisplayName("creating UserStoryNumber with null value should throw Exception")
    @Test
    void createUserStoryNumberWithNullThrowsException() {
        // Arrange
        String userStoryNumber = null;
        String expectedMessage = "userStoryNumber cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber(userStoryNumber);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating UserStoryNumber with empty value should throw Exception")
    @Test
    void createUserStoryNumberWithEmptyThrowsException() {
        // Arrange
        String userStoryNumber = "";
        String expectedMessage = "userStoryNumber cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber(userStoryNumber);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating UserStoryNumber with blank value should throw Exception")
    @Test
    void createUserStoryNumberWithBlankThrowsException() {
        // Arrange
        String userStoryNumber = "       ";
        String expectedMessage = "userStoryNumber cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber(userStoryNumber);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

}