package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNameTest {

    @DisplayName("creating projectName with null value should throw Exception")
    @Test
    void createProjectNameWithNullThrowsException() {
        // Arrange
        String projectName = null;
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating projectName with empty value should throw Exception")
    @Test
    void createProjectNameWithEmptyThrowsException() {
        // Arrange
        String projectName = "";
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating projectName with blank value should throw Exception")
    @Test
    void createUserStoryNumberWithBlankThrowsException() {
        // Arrange
        String projectName = "       ";
        String expectedMessage = "projectName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectName(projectName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


}