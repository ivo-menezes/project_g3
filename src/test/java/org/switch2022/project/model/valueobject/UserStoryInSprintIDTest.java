package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryInSprintIDTest {

    final String expectedMessage1 = "Sprint ID must not be null";
    final String expectedMessage2 = "User story ID must not be null";
    final SprintID sprintID = mock(SprintID.class);
    final UserStoryID userStoryID = mock(UserStoryID.class);

    @Test
    @DisplayName("Check if UserStoryInSprintID is created")
    public void checkIfUSInSprintIdIsCreated() {
        new UserStoryInSprintID(sprintID, userStoryID);
    }

    @Test
    @DisplayName("Throw exception if SprintID is null")
    public void throwExceptionIfSprintIDIsNull() {
        //Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryInSprintID(null, userStoryID);
        });
        //Act
        String actualMessage = exception.getMessage();
        // Assert
        assertTrue(actualMessage.contains(expectedMessage1));
    }

    @Test
    @DisplayName("Throw exception if UserStoryID is null")
    public void throwExceptionIfUserStoryIDIsNull() {
        //Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryInSprintID(sprintID, null);
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
        UserStoryInSprintID userStoryInSprintID = new UserStoryInSprintID(sprintID, userStoryID);
        //Act
        boolean isEquals = userStoryInSprintID.equals(null);
        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same object")
    public void returnTrueEqualsWithSameObject() {
        //Arrange
        UserStoryInSprintID userStoryInSprintID = new UserStoryInSprintID(sprintID, userStoryID);
        //Act
        boolean isEquals = userStoryInSprintID.equals(userStoryInSprintID);
        //Assert
        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return true in equals with same IDs")
    public void returnTrueEqualsWithSameIds() {
        //Arrange
        UserStoryInSprintID userStoryInSprintID1 = new UserStoryInSprintID(sprintID, userStoryID);
        UserStoryInSprintID userStoryInSprintID2 = new UserStoryInSprintID(sprintID, userStoryID);
        //Act
        boolean isEquals = userStoryInSprintID1.equals(userStoryInSprintID2);
        //Assert
        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Return false in equals with different SprintIds")
    public void returnFalseEqualsWithDifferentSprintIDs() {
        //Arrange
        UserStoryInSprintID userStoryInSprintID1 = new UserStoryInSprintID(sprintID, userStoryID);
        SprintID sprintID2 = mock(SprintID.class);
        UserStoryInSprintID userStoryInSprintID2 = new UserStoryInSprintID(sprintID2, userStoryID);
        //Act
        boolean isEquals = userStoryInSprintID1.equals(userStoryInSprintID2);
        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Return false in equals with different UserStoryIds")
    public void returnFalseEqualsWithDifferentUserStoryIDs() {
        //Arrange
        UserStoryInSprintID userStoryInSprintID1 = new UserStoryInSprintID(sprintID, userStoryID);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryInSprintID userStoryInSprintID2 = new UserStoryInSprintID(sprintID, userStoryID2);
        //Act
        boolean isEquals = userStoryInSprintID1.equals(userStoryInSprintID2);
        //Assert
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        UserStoryInSprintID userStoryInSprintID1 = new UserStoryInSprintID(sprintID, userStoryID);
        SprintID sprintID2 = mock(SprintID.class);
        UserStoryInSprintID userStoryInSprintID2 = new UserStoryInSprintID(sprintID2, userStoryID);
        //Act
        int hashCode1 = userStoryInSprintID1.hashCode();
        int hashCode2 = userStoryInSprintID2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

}