package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryTest {

    @Test
    @DisplayName("create a user story successfully")
    void createUserStory() {
        new UserStory("US001",
                "Product Owner",
                "As Product Owner, I want to create a user story and add it to the Product Backlog",
                "None");
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

    @Test
    @DisplayName("throws exception with blank actor")
    void blankActorThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            "    ",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "None");
                }
        );

        String expectedMessage = "User Story actor must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("throws exception with empty text")
    void emptyTextThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            "Manager",
                            "",
                            "None");
                }
        );

        String expectedMessage = "User Story text must not be empty";
        String resultMessage = exception.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("throws exception with empty acceptance criteria")
    void emptyAcceptanceCriteriaThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStory("US002",
                            "Manager",
                            "As Product Owner, I want to create a user story and add it to the Product Backlog",
                            "");
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

}