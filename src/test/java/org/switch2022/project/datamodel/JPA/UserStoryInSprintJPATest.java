package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryInSprintJPATest {
    UserStoryInSprintIDJpa idJpa = new UserStoryInSprintIDJpa(
            "P01", 1, "US01");

    Double effortEstimate = 0.2;

    String status = "TO_DO";

    UserStoryInSprintJPA userStoryInSprintJPA= new UserStoryInSprintJPA(
            idJpa, effortEstimate, status);

    @Test
    @DisplayName("ensure UserStoryInSprint is successfully created")
    void ensureUSInSprintJPACreated() {
        // Act
        UserStoryInSprintJPA someUSInSprintJPA = new UserStoryInSprintJPA();

        // Assert
        assertInstanceOf(UserStoryInSprintJPA.class, someUSInSprintJPA);
    }

    @Test
    @DisplayName("ensure UserStoryInSprint ID is retrieved")
    void ensureUSInSprintIDRetrieved() {
        // Act
        UserStoryInSprintIDJpa result = userStoryInSprintJPA.getID();

        // Assert
        assertEquals(idJpa, result);
    }

    @Test
    @DisplayName("ensure effort Estimate is retrieved")
    void ensureUSEffortEstimateRetrieved() {
        // Act
        Double result = userStoryInSprintJPA.getUserStoryEffortEstimate();

        // Assert
        assertEquals(effortEstimate, result);
    }

    @Test
    @DisplayName("ensure status is retrieved")
    void ensureUSInSprintStatusRetrieved() {
        // Act
        String result = userStoryInSprintJPA.getUserStoryInSprintStatus();

        // Assert
        assertEquals(status, result);
    }

    @Test
    @DisplayName("ensure object is not null")
    void ensureUserStoryInSprintNotNull() {
        // Act
        boolean result = userStoryInSprintJPA.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object equals itself")
    void ensureUserStoryInSprintEqualsItself() {
        // Assert
        assertEquals(userStoryInSprintJPA, userStoryInSprintJPA);
    }

    @Test
    @DisplayName("ensure object equals another object of same type")
    void ensureUserStoryInSprintEqualsObjectOfSameClass() {
        // Arrange
        UserStoryInSprintJPA anotherUserStoryInSprintJPA = new UserStoryInSprintJPA(
                idJpa, effortEstimate, status);

        // Act
        boolean result = userStoryInSprintJPA.equals(anotherUserStoryInSprintJPA);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure object does not equal object of different type")
    void ensureUserStoryInSprintNotEqualObjectOfDifferentClass() {
        // Arrange
        String anotherUserStoryInSprintJPA = "Just a fake";

        // Act
        boolean result = userStoryInSprintJPA.equals(anotherUserStoryInSprintJPA);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure objects with same hashcode are equal")
    void ensureObjectsWithSameHashCodeAreEqual() {
        // Arrange
        UserStoryInSprintJPA anotherUserStoryInSprintJPA = new UserStoryInSprintJPA(
                idJpa, effortEstimate, status);

        // Act
        int hashCode1 = userStoryInSprintJPA.hashCode();
        int hashCode2 = anotherUserStoryInSprintJPA.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("ensure objects with different hashcode are not equal")
    void ensureObjectsWithDifferentHashCodeAreNotEqual() {
        // Arrange
        UserStoryInSprintIDJpa idJpa2 = new UserStoryInSprintIDJpa(
                "P02", 1, "US01");
        UserStoryInSprintJPA anotherUserStoryInSprintJPA= new UserStoryInSprintJPA(
                idJpa2, effortEstimate, status);

        // Act
        int hashCode1 = userStoryInSprintJPA.hashCode();
        int hashCode2 = anotherUserStoryInSprintJPA.hashCode();

        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}