package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ProjectSprintDurationTest {

    @DisplayName("creating projectSprintDuration with value less than zero should generate exception")
    @Test
    void CreatingProjectSprintDurationWithNegativeValue() {
        // Arrange
        int projectSprintDuration = -1;
        String expectedMessage = "sprintDuration cannot have negative values and last longer than four weeks.";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectSprintDuration(projectSprintDuration);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating projectBudget with value greater than four should generate exception")
    @Test
    void CreatingProjectSprintDurationWithValueGreaterThanFour() {
        // Arrange
        int projectSprintDuration = 5;
        String expectedMessage = "sprintDuration cannot have negative values and last longer than four weeks.";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectSprintDuration(projectSprintDuration);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Ensure that two projectSprintDuration objects are the same.")
    @Test
    void projectSprintDurationEqualsSelf() {
        //Arrange
        ProjectSprintDuration projectSprintDurationOne = new ProjectSprintDuration(2);
        ProjectSprintDuration projectSprintDurationTwo = projectSprintDurationOne;

        //Act and Assert
        assertTrue(projectSprintDurationOne.equals(projectSprintDurationTwo));
    }

    @DisplayName("Ensure that two projectSprintDuration objects aren't the same.")
    @Test
    void projectSprintDurationNotEqualsDifferentValue() {
        //Arrange
        ProjectSprintDuration projectSprintDurationOne = new ProjectSprintDuration(2);
        ProjectSprintDuration projectSprintDurationTwo = new ProjectSprintDuration(4);

        //Act and Assert
        assertFalse(projectSprintDurationOne.equals(projectSprintDurationTwo));
    }

    @Test
    @DisplayName("Ensure value is retrieved")
    void ensureValueIsRetrieved(){
        //Arrange
        int expected = 3;
        ProjectSprintDuration projectSprintDurationOne = new ProjectSprintDuration(expected);

        //Act
        int result = projectSprintDurationOne.getValue();

        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        int sprintDuration = 0;
        ProjectSprintDuration projectSprintDuration = new ProjectSprintDuration(sprintDuration);

        //Act

        boolean isEqual = projectSprintDuration.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectSprintDuration projectSprintDurationOne = new ProjectSprintDuration(1);
        ProjectSprintDuration projectSprintDurationTwo = new ProjectSprintDuration(2);
        //Act
        int hashCode1 = projectSprintDurationOne.hashCode();
        int hashCode2 = projectSprintDurationTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }


}