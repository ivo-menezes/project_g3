package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryInSprintIDJpaTest {

    String projectCode = "P01";

    int sprintNumber = 1;

    String userStoryNumber = "US01";

    UserStoryInSprintIDJpa idJpa = new UserStoryInSprintIDJpa(projectCode,
            sprintNumber, userStoryNumber);

    @Test
    @DisplayName("ensure UserStoryInSprintID is successfully created")
    void ensureUSInSprintIDCreated() {
        // Act
        UserStoryInSprintIDJpa someUSInSprintIDJpa = new UserStoryInSprintIDJpa();

        // Assert
        assertInstanceOf(UserStoryInSprintIDJpa.class, someUSInSprintIDJpa);
    }

    @Test
    @DisplayName("ensure projectCode is retrieved")
    void ensureProjectCodeRetrieved() {
        // Act
        String result = idJpa.getProjectCode();

        // Assert
        assertEquals(projectCode, result);
    }

    @Test
    @DisplayName("ensure sprintNumber is retrieved")
    void ensureSprintNumberRetrieved() {
        // Act
        int result = idJpa.getSprintNumber();

        // Assert
        assertEquals(sprintNumber, result);
    }

    @Test
    @DisplayName("ensure USNumber is retrieved")
    void ensureUSNumberRetrieved() {
        // Act
        String result = idJpa.getUserStoryNumber();

        // Assert
        assertEquals(userStoryNumber, result);
    }

    @Test
    @DisplayName("ensure object is not null")
    void ensureUserStoryInSprintIDNotNull() {
        // Act
        boolean result = idJpa.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object equals itself")
    void ensureUserStoryInSprintIDEqualsItself() {
        // Assert
        assertEquals(idJpa, idJpa);
    }

    @Test
    @DisplayName("ensure object equals another object of same type")
    void ensureUserStoryInSprintIDEqualsObjectOfSameType() {
        // Arrange
        UserStoryInSprintIDJpa anotherUserStoryInSprintIDJpa = new UserStoryInSprintIDJpa(
                "P01", 1, "US01");

        // Act
        boolean result = idJpa.equals(anotherUserStoryInSprintIDJpa);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure object does not equal object of different type")
    void ensureUserStoryInSprintNotEqualObjectOfDifferentClass() {
        // Arrange
        int anotherUserStoryInSprintIDJpa = 123;

        // Act
        boolean result = idJpa.equals(anotherUserStoryInSprintIDJpa);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure objects with same hashcode are equal")
    void ensureObjectsWithSameHashCodeAreEqual() {
        // Arrange
        UserStoryInSprintIDJpa anotherUserStoryInSprintIDJpa = new UserStoryInSprintIDJpa(
                "P01", 1, "US01");

        // Act
        int hashCode1 = idJpa.hashCode();
        int hashCode2 = anotherUserStoryInSprintIDJpa.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("ensure objects with different hashcode are not equal")
    void ensureObjectsWithDifferentHashCodeAreNotEqual() {
        // Arrange
        UserStoryInSprintIDJpa anotherUserStoryInSprintIDJpa = new UserStoryInSprintIDJpa(
                "P02", 1, "US01");

        // Act
        int hashCode1 = idJpa.hashCode();
        int hashCode2 = anotherUserStoryInSprintIDJpa.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }



}