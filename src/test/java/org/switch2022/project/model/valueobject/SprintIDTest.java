package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintIDTest {

    final String expectedMessage1 = "Project code must not be null";
    final String expectedMessage2 = "Sprint number must not be null";
    final ProjectCode projectCode = mock(ProjectCode.class);
    final SprintNumber sprintNumber = mock(SprintNumber.class);

    @Test
    @DisplayName("Check if sprint ID is created")
    public void checkIfSprintIdIsCreated() {
        new SprintID(projectCode, sprintNumber);
    }

    @Test
    @DisplayName("Throw exception if project code is null")
    public void throwExceptionIfProjectCodeIsNull() {
        //Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new SprintID(null, sprintNumber);
        });
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertTrue(actualMessage.contains(expectedMessage1));
    }

    @Test
    @DisplayName("Throw exception if sprint number is null")
    public void throwExceptionIfSprintNumberIsNull() {
        //Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new SprintID(projectCode, null);
        });
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertTrue(actualMessage.contains(expectedMessage2));
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {
        //Arrange
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        //Act
        boolean isEquals = sprintID.equals(null);
        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same object")
    public void returnTrueEqualsWithSameObject() {
        //Arrange
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        //Act
        boolean isEquals = sprintID.equals(sprintID);
        //Assert
        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same IDs")
    public void returnTrueEqualsWithSameIds() {
        //Arrange
        SprintID sprintID1 = new SprintID(projectCode, sprintNumber);
        SprintID sprintID2 = new SprintID(projectCode, sprintNumber);
        //Act
        boolean isEquals = sprintID1.equals(sprintID2);
        //Assert
        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return false in equals with different Ids")
    public void returnFalseEqualsWithDifferentSprintNumbers() {
        //Arrange
        SprintID sprintID1 = new SprintID(projectCode, sprintNumber);
        SprintNumber sprintNumber2 = mock(SprintNumber.class);
        SprintID sprintID2 = new SprintID(projectCode, sprintNumber2);
        //Act
        boolean isEquals = sprintID1.equals(sprintID2);
        //Assert
        assertFalse(isEquals);
    }
    @Test
    @DisplayName("Ensure projectCode is retrieved")
    void ensureProjectCodeIsRetrieved() {
        // Arrange
        ProjectCode expected = new ProjectCode("A5");
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(expected, sprintNumber);

        // Act
        ProjectCode result = sprintID.getProjectCode();

        // Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Ensure SprintNumber is retrieved")
    void ensureSprintNumberIsRetrieved() {
        // Arrange
        ProjectCode projectCode = new ProjectCode("A5");
        SprintNumber expected = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode, expected);

        // Act
        SprintNumber result = sprintID.getSprintNumber();

        // Assert
        assertEquals(expected, result);
    }
}