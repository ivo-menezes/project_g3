package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.switch2022.project.model.userStory.old.UserStory;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    @DisplayName("create a user story successfully")
    void createUserStory() {
        UserStory userStory = new UserStory("US001",
                "Product Owner",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        assertInstanceOf(UserStory.class, userStory);
    }

    @Test
    @DisplayName("throws exception with null id")
    void nullIdThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory(null,
                            "Product Owner",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "None");
                }
        );

        String expectedMessage = "User Story ID must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("US constructor throws exception with empty or blank id")
    void emptyOrBlankIdThrowsException(String id) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory(id,
                            "Product Owner",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "None");
                }
        );

        String expectedMessage = "User Story ID must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("US constructor throws exception with null actor")
    void nullActorThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US01",
                            null,
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "None");
                }
        );

        String expectedMessage = "User Story actor must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("US constructor throws exception with empty or blank actor")
    void emptyOrBlankActorThrowsException(String actor) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            actor,
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "None");
                }
        );

        String expectedMessage = "User Story actor must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("US constructor throws exception with null text")
    void nullTextThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US001",
                            "Product Owner",
                            null,
                            "None");
                }
        );

        String expectedMessage = "User Story text must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("US constructor throws exception with empty or blank text")
    void emptyOrBlankTextThrowsException(String text) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            "Manager",
                            text,
                            "None");
                }
        );

        String expectedMessage = "User Story text must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("US constructor throws exception with null acceptance criteria")
    void nullAcceptanceCriteriaThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US001",
                            "Product Owner",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            null);
                }
        );

        String expectedMessage = "User Story acceptance criteria must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("US constructor throws exception with empty or blank acceptance criteria")
    void emptyAcceptanceCriteriaThrowsException(String ac) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            "Manager",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            ac);
                }
        );

        String expectedMessage = "User Story acceptance criteria must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("object equals itself")
    void testEqualsWithItself() {
        UserStory us = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        UserStory us2 = us;
        boolean result = us.equals(us2);
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal null")
    void testEqualsWithNull() {
        UserStory us = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        boolean result = us.equals(null);
        assertFalse(result);
    }

    @Test
    @DisplayName("object does not equal object of another class")
    void testEqualsWithAnotherClass() {
        UserStory us = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        String fakeUserStory = "US001";
        boolean result = us.equals(fakeUserStory);
        assertFalse(result);
    }

    @Test
    @DisplayName("object equals object with same id")
    void testEqualsWithEqualId() {
        UserStory userStory = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        UserStory anotherUserStory = new UserStory("US002",
                "Administrator",
                "As Administrator, I want to delete all accounts",
                "None");

        boolean result = userStory.equals(anotherUserStory);
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal object with different id")
    void testEqualsWithDifferentId() {
        UserStory userStory = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        UserStory anotherUserStory = new UserStory("US006",
                "Administrator",
                "As Administrator, I want to delete all accounts",
                "None");

        boolean result = userStory.equals(anotherUserStory);
        assertFalse(result);
    }

    @Test
    @DisplayName("equal objects have same hash code")
    void testSameHashCode() {
        UserStory userStory = new UserStory("US002",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        UserStory anotherUserStory = new UserStory("US002",
                "Administrator",
                "As Administrator, I want to delete all accounts",
                "None");

        int userStoryHashCode = userStory.hashCode();
        int anotherUserStoryHashCode = anotherUserStory.hashCode();
        assertEquals(userStoryHashCode, anotherUserStoryHashCode);
    }

    @Test
    @DisplayName("different objects have different hash code")
    void testDifferentHashCode() {
        UserStory userStory = new UserStory("US001",
                "Manager",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
        UserStory anotherUserStory = new UserStory("US002",
                "Administrator",
                "As Administrator, I want to delete all accounts",
                "None");

        int userStoryHashCode = userStory.hashCode();
        int anotherUserStoryHashCode = anotherUserStory.hashCode();
        assertNotEquals(userStoryHashCode, anotherUserStoryHashCode);
    }

    @Test
    @DisplayName("ensure user story ID is returned")
    void ensureUserStoryIsReturned() {
        //arrange
        UserStory userStory = new UserStory("1", "Team member", "test", "text");
        String expected = "1";

        //act
        String result = userStory.getId();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure user story actor is returned")
    void ensureUserStoryActorIsReturned() {
        //arrange
        UserStory userStory = new UserStory("1", "Product Owner", "This is text", "These are acceptance criteria");
        String expected = "Product Owner";

        //act
        String result = userStory.getActor();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure user story text is returned")
    void ensureUserStoryTextIsReturned() {
        //arrange
        UserStory userStory = new UserStory("1", "Product Owner", "This is text", "These are acceptance criteria");
        String expected = "This is text";

        //act
        String result = userStory.getText();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure user story acceptanceCriteria are returned")
    void ensureUserStoryAcceptanceCriteriaAreReturned() {
        //arrange
        UserStory userStory = new UserStory("1", "Product Owner", "This is text", "These are acceptance criteria");
        String expected = "These are acceptance criteria";
        //act
        String result = userStory.getAcceptanceCriteria();

        //assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("ensure user story status is returned")
    void ensureUserStoryStatusIsReturned(){
        //arrange
        UserStory userStory = new UserStory("1", "Product Owner", "This is text", "These are acceptance criteria");
        UserStory.Status expected = UserStory.Status.TODO;

        //act
        UserStory.Status result = userStory.getStatus();

        //assert
        assertEquals(expected, result);

    }
}