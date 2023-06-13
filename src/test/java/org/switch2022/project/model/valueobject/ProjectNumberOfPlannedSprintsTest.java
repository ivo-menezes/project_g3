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

    @Test
    @DisplayName("Ensure value is retrieved")
    void ensureValueIsRetrieved(){
        //Arrange
        int expected = 5;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = new ProjectNumberOfPlannedSprints(expected);

        //Act
        int result = projectNumberOfPlannedSprints.getValue();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        int numberOfPlannedSprints = 0;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = new ProjectNumberOfPlannedSprints(numberOfPlannedSprints);

        //Act

        boolean isEqual = projectNumberOfPlannedSprints.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsOne = new ProjectNumberOfPlannedSprints(1);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsTwo = new ProjectNumberOfPlannedSprints(2);
        //Act
        int hashCode1 = projectNumberOfPlannedSprintsOne.hashCode();
        int hashCode2 = projectNumberOfPlannedSprintsTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }



}