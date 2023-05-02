package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectNumberOfPlannedSprintsTest {

    @DisplayName("creating projectNumberOfPlannedSprints with value less than zero should generate exception")
    @Test
    void CreatingProjectNumberOfPlannedSprintsWithNegativeValue() {
        // Arrange
        int projectNumberOfPlannedSprints = -1;
        String expectedMessage = "numberOfPlannedSprints cannot be negative.";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectNumberOfPlannedSprints(projectNumberOfPlannedSprints);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Ensure that two projectNumberOfPlannedSprints objects are the same.")
    @Test
    void projectNumberOfPlannedSprintsEqualsSelf() {
        //Arrange
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsOne = new ProjectNumberOfPlannedSprints(5);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsTwo = projectNumberOfPlannedSprintsOne;

        //Act and Assert
        assertTrue(projectNumberOfPlannedSprintsOne.equals(projectNumberOfPlannedSprintsTwo));
    }

    @DisplayName("Ensure that two projectNumberOfPlannedSprints objects aren't the same.")
    @Test
    void projectNumberOfPlannedSprintsNotEqualsDifferentValue() {
        //Arrange
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsOne = new ProjectNumberOfPlannedSprints(5);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsTwo = new ProjectNumberOfPlannedSprints(7);

        //Act and Assert
        assertFalse(projectNumberOfPlannedSprintsOne.equals(projectNumberOfPlannedSprintsTwo));
    }

}