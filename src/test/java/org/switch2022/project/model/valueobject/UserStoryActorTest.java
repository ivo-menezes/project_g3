package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStoryActorTest {

    final String expectedMessage = "User Story actor must not be empty";
    final String userStoryActor1 = "Test1";
    final String userStoryActor2 = "Test2";


    @Test
    public void shouldCreateAValidUserStoryActor () {
        new UserStoryActor(userStoryActor1);
    }

    @Test
    public void shouldThrowExceptionUserStoryActorWithNullUserStoryActor() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryActor(null);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryActorWithBlankUserStoryActor() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryActor("  ");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionUserStoryActorWithEmptyUserStoryActor() {

        Exception exception = assertThrows(Exception.class, () -> {
            new UserStoryActor("");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldReturnFalseEqualsWithNull() {

        UserStoryActor userStoryActor = new UserStoryActor(userStoryActor1);

        boolean isEquals = userStoryActor.equals(null);

        assertFalse(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject() {

        UserStoryActor userStoryActor = new UserStoryActor(userStoryActor1);

        boolean isEquals = userStoryActor.equals(userStoryActor);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameUserStoryActor() {

        UserStoryActor userStoryActor = new UserStoryActor(userStoryActor1);
        UserStoryActor userStoryActorTest = new UserStoryActor(userStoryActor1);

        boolean isEquals = userStoryActor.equals(userStoryActorTest);

        assertTrue(isEquals);
    }

    @Test
    public void shouldReturnFalseEqualsWithDifferentUserStoryActor() {

        UserStoryActor userStoryActorTest1 = new UserStoryActor(userStoryActor1);
        UserStoryActor userStoryActorTest2 = new UserStoryActor(userStoryActor2);

        boolean isEquals = userStoryActorTest1.equals(userStoryActorTest2);

        assertFalse(isEquals);
    }

    @Test
    public void toStringShouldReturnTheUserStoryActorString() {

        UserStoryActor userStoryActor = new UserStoryActor(userStoryActor1);

        assertEquals(userStoryActor.toString(), userStoryActor1);
    }

    @DisplayName("similar UserStoryActor have same hash code")
    @Test
    void userStoryActorHasSameHashCode() {
        // Arrange
        UserStoryActor aUserStoryActor = new UserStoryActor("Administrator");
        UserStoryActor anotherUserStoryActor = new UserStoryActor("Administrator");

        // Act & Assert
        assertEquals(aUserStoryActor.hashCode(), anotherUserStoryActor.hashCode());
    }

    @DisplayName("different UserStoryActor have different hash code")
    @Test
    void userStoryActorHasDifferentHashCode() {
        // Arrange
        UserStoryActor aUserStoryActor = new UserStoryActor("Administrator");
        UserStoryActor anotherUserStoryActor = new UserStoryActor("Manager");

        // Act & Assert
        assertNotEquals(aUserStoryActor.hashCode(), anotherUserStoryActor.hashCode());
    }

}
