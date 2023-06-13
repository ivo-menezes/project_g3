package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
class UserStoryAcceptanceCriteriaTest {

    final String expectedMessage = "UserStoryAcceptanceCriteria must not be null";
    final String acceptanceCriteria1 = "acceptanceCriteria1";
    final String acceptanceCriteria2 = "acceptanceCriteria2";

    @Test
    public void shouldCreateAValidAcceptanceCriteria() {

        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        assertNotNull(acceptanceCriteria);
    }

    @Test
    public void shouldThrowExceptionUserStoryAcceptanceCriteriaWithNullUserStoryAcceptanceCriteria() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryAcceptanceCriteria(null);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryAcceptanceCriteriaWithEmptyUserStoryAcceptanceCriteria() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryAcceptanceCriteria("");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryAcceptanceCriteriaWithBlankUserStoryAcceptanceCriteria() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryAcceptanceCriteria("   ");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryAcceptanceCriteriaWithTabUserStoryAcceptanceCriteria() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryAcceptanceCriteria("\t");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryAcceptanceCriteriaWithReturnUserStoryAcceptanceCriteria() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryAcceptanceCriteria("\n");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull() {

        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        boolean isEquals = false;

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject() {

        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        boolean isEquals = acceptanceCriteria.equals(acceptanceCriteria);

        assertTrue(true);
    }
    @Test
    public void shouldReturnTrueEqualsWithSameUserStoryAcceptanceCriteria() {

        UserStoryAcceptanceCriteria acceptanceCriteriaOne = new UserStoryAcceptanceCriteria(acceptanceCriteria1);
        UserStoryAcceptanceCriteria acceptanceCriteriaTwo = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        boolean isEquals = acceptanceCriteriaOne.equals(acceptanceCriteriaTwo);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithDifferentUserStoryAcceptanceCriteria() {

        UserStoryAcceptanceCriteria acceptanceCriteriaOne = new UserStoryAcceptanceCriteria(acceptanceCriteria1);
        UserStoryAcceptanceCriteria acceptanceCriteriaTwo = new UserStoryAcceptanceCriteria(acceptanceCriteria2);

        boolean isEquals = acceptanceCriteriaOne.equals(acceptanceCriteriaTwo);

        assertFalse(isEquals);
    }

    @Test
    public void toStringShouldReturnTheUserStoryAcceptanceCriteriaString() {

        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        assertEquals(acceptanceCriteria.toString(), acceptanceCriteria1);
    }

    @Test
    @DisplayName("Test to ensure that two Email with same name are equal")
    public void checkIfEmailIsEqualsWithSameEmailAreEqual() {

        //Arrange
        UserStoryAcceptanceCriteria acceptanceCriteriaOne = new UserStoryAcceptanceCriteria(acceptanceCriteria1);
        UserStoryAcceptanceCriteria acceptanceCriteriaTwo = acceptanceCriteriaOne;

        //Act

        boolean isEqual = acceptanceCriteriaOne.equals(acceptanceCriteriaTwo);

        // Assert
        assertTrue(isEqual);
    }


    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        UserStoryAcceptanceCriteria acceptanceCriteriaOne = new UserStoryAcceptanceCriteria(acceptanceCriteria1);

        //Act

        boolean isEqual = acceptanceCriteriaOne.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        UserStoryAcceptanceCriteria acceptanceCriteriaOne = new UserStoryAcceptanceCriteria(acceptanceCriteria1);
        UserStoryAcceptanceCriteria acceptanceCriteriaTwo = new UserStoryAcceptanceCriteria(acceptanceCriteria2);
        //Act
        int hashCode1 = acceptanceCriteriaOne.hashCode();
        int hashCode2 = acceptanceCriteriaTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

}

