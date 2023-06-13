package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryEffortEstimateTest {

    @DisplayName("ensure creating User Story effort estimate with null value throws Exception")
    @Test
    void createUserStoryEffortEstimateWithNullThrowsException() {
        // Arrange
        String expectedMessage = "User Story effort estimate cannot be null";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryEffortEstimate(null);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Ensure that two UserStoryEffortEstimate objects are the same.")
    @Test
    void userStoryEffortEstimateEqualsSelf() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = userStoryEffortEstimate_1;

        //Act and Assert
        assertEquals(userStoryEffortEstimate_1, userStoryEffortEstimate_2);
    }

    @DisplayName("Ensure that two UserStoryEffortEstimate objects aren't the same.")
    @Test
    void userStoryEffortEstimateNotEqualsDifferentValue() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = new UserStoryEffortEstimate(0.4);

        //Act and Assert
        assertNotEquals(userStoryEffortEstimate_1, userStoryEffortEstimate_2);
    }
    @Test
    @DisplayName("equal UserStoryEffortEstimate objects have same hash code")
    void ensureSameHashCode() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = userStoryEffortEstimate_1;

        //Act
        int userStoryEffortEstimate_1HashCode = userStoryEffortEstimate_1.hashCode();
        int userStoryEffortEstimate_2HashCode = userStoryEffortEstimate_2.hashCode();
        //Assert
        assertEquals(userStoryEffortEstimate_1HashCode, userStoryEffortEstimate_2HashCode);
    }

    @Test
    @DisplayName("different UserStoryEffortEstimate objets have different hash code")
    void ensureDifferentHashCode() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = new UserStoryEffortEstimate(0.4);

        //Act
        int userStoryEffortEstimate_1HashCode = userStoryEffortEstimate_1.hashCode();
        int userStoryEffortEstimate_2HashCode = userStoryEffortEstimate_2.hashCode();
        //Assert
        assertNotEquals(userStoryEffortEstimate_1HashCode, userStoryEffortEstimate_2HashCode);
    }

    @Test
    @DisplayName("object does not equal object of another class")
    void testEqualsWithAnotherClass() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate = new UserStoryEffortEstimate(0.2);

        String fake = "Fake UserStoryEffortEstimate";
        //Act
        boolean result = userStoryEffortEstimate.equals(fake);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate = new UserStoryEffortEstimate(0.2);

        //Act

        boolean isEqual = userStoryEffortEstimate.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that US Effort Estimate with same number are equal")
    public void checkIfUSeffortEstimateWithSameEstimateAreEqual() {

        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = new UserStoryEffortEstimate(0.2);

        //Act

        boolean isEqual = userStoryEffortEstimate_1.equals(userStoryEffortEstimate_2);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNotReturnEqualsWithDifferentEmails() {
        //Arrange
        UserStoryEffortEstimate userStoryEffortEstimate_1 = new UserStoryEffortEstimate(0.2);
        UserStoryEffortEstimate userStoryEffortEstimate_2 = new UserStoryEffortEstimate(0.4);

        //Act

        boolean isNotEqual = userStoryEffortEstimate_1.equals(userStoryEffortEstimate_2);

        // Assert
        assertNotEquals(true, isNotEqual);
    }
}