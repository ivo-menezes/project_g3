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
        String expectedMessage = "userStoryNumber cannot be null";

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
        String expectedMessage = "userStoryNumber cannot be blank/empty";

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
        String expectedMessage = "userStoryNumber cannot be blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryNumber(userStoryNumber);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating UserStoryNumber with valid number succeeds")
    @Test
    void createUserStoryNumberSucceeds() {
        // Arrange
        String userStoryNumber = "US001";

        // Act
        UserStoryNumber result = new UserStoryNumber(userStoryNumber);

        // Assert
        assertInstanceOf(UserStoryNumber.class, result);
    }

    @DisplayName("UserStoryNumber equals same object")
    @Test
    void userStoryNumberEqualsSameObject() {
        // Arrange
        UserStoryNumber userStoryNumber = new UserStoryNumber("US001");
        UserStoryNumber sameUserStoryNumber = userStoryNumber;

        // Act
        boolean result = userStoryNumber.equals(sameUserStoryNumber);

        // Assert
        assertTrue(result);
    }

    @DisplayName("UserStoryNumber does not equal null")
    @Test
    void userStoryNumberDoesNotEqualNull() {
        // Arrange
        UserStoryNumber userStoryNumber = new UserStoryNumber("US001");
        UserStoryNumber nullUserStoryNumber = null;

        // Act
        boolean result = userStoryNumber.equals(nullUserStoryNumber);

        // Assert
        assertFalse(result);
    }

    @DisplayName("UserStoryNumber does not equal object of different class")
    @Test
    void userStoryNumberDoesNotEqualObjectOfAnotherClass() {
        // Arrange
        String userStoryNumberString = "US001";
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryNumberString);

        // Act
        boolean result = userStoryNumber.equals(userStoryNumberString);

        // Assert
        assertFalse(result);
    }

    @DisplayName("UserStoryNumber equals another UserStoryNumber with same value")
    @Test
    void userStoryNumberEqualsSameValue() {
        // Arrange
        String userStoryNumberString = "US001";
        UserStoryNumber aUserStoryNumber = new UserStoryNumber(userStoryNumberString);
        UserStoryNumber anotherUserStoryNumber = new UserStoryNumber(userStoryNumberString);

        // Act
        boolean result = aUserStoryNumber.equals(anotherUserStoryNumber);

        // Assert
        assertTrue(result);
    }

    @DisplayName("UserStoryNumber does not equal UserStoryNumber with different value")
    @Test
    void userStoryNumberDoesNotEqualAnotherValue() {
        // Arrange
        UserStoryNumber aUserStoryNumber = new UserStoryNumber("US001");
        UserStoryNumber anotherUserStoryNumber = new UserStoryNumber("US002");

        // Act
        boolean result = aUserStoryNumber.equals(anotherUserStoryNumber);

        // Assert
        assertFalse(result);
    }

    @DisplayName("similar UserStoryNumber have same hash code")
    @Test
    void userStoryNumberHasSameHashCode() {
        // Arrange
        UserStoryNumber aUserStoryNumber = new UserStoryNumber("US001");
        UserStoryNumber anotherUserStoryNumber = new UserStoryNumber("US001");

        // Act & Assert
        assertEquals(aUserStoryNumber.hashCode(), anotherUserStoryNumber.hashCode());
    }

    @DisplayName("different UserStoryNumber have different hash code")
    @Test
    void userStoryNumberHasDifferentHashCode() {
        // Arrange
        UserStoryNumber aUserStoryNumber = new UserStoryNumber("US001");
        UserStoryNumber anotherUserStoryNumber = new UserStoryNumber("US002");

        // Act & Assert
        assertNotEquals(aUserStoryNumber.hashCode(), anotherUserStoryNumber.hashCode());
    }

    @DisplayName("test toString method works")
    @Test
    void userStoryNumberToStringSuccess() {
        // Arrange
        String userStoryNumberString = "US001";
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryNumberString);

        // Act
        boolean result = userStoryNumberString.equals(userStoryNumber.toString());

        // Assert
        assertTrue(result);
    }
}