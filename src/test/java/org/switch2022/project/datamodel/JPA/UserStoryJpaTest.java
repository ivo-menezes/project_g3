package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryJpaTest {

    UserStoryJpaId userStoryJpaId = new UserStoryJpaId("XPTO", "US001");
    String actor = "Administrator";
    String description = "This is a description";
    String criteria = "No acceptance criteria because PO is lazy";
    String status = "TO_DO";
    UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryJpaId,
            actor,
            description,
            criteria,
            status);

    @DisplayName("ensure instantiating UserStoryJpa using no-args constructor is successful")
    @Test
    void shouldInstantiateUserStoryJpa() {
        // Act
        UserStoryJpa anotherUserStoryJpa = new UserStoryJpa();

        // Assert
        assertInstanceOf(UserStoryJpa.class, anotherUserStoryJpa);
    }

    @Test
    void getId() {
        // Act
        UserStoryJpaId result = userStoryJpa.getId();

        // Assert
        assertEquals(userStoryJpaId, result);
    }

    @Test
    void getActor() {
        // Act
        String result = userStoryJpa.getActor();

        // Assert
        assertEquals(actor, result);
    }

    @Test
    void getDescription() {
        // Act
        String result = userStoryJpa.getDescription();

        // Assert
        assertEquals(description, result);
    }

    @Test
    void getAcceptanceCriteria() {
        // Act
        String result = userStoryJpa.getAcceptanceCriteria();

        // Assert
        assertEquals(criteria, result);
    }

    @Test
    void getStatus() {
        // Act
        String result = userStoryJpa.getStatus();

        // Assert
        assertEquals(status, result);
    }


    @Test
    void doesNotEqualNull() {
        // Act
        boolean result = userStoryJpa.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void equalsItself() {
        // Assert
        assertEquals(userStoryJpa, userStoryJpa);
    }

    @Test
    void equalsSimilar() {
        UserStoryJpa yetAnotherUserStoryJpa = new UserStoryJpa(userStoryJpaId,
                actor,
                description,
                criteria,
                status);

        assertEquals(userStoryJpa, yetAnotherUserStoryJpa);
    }

    @Test
    void doesNotEqualDifferentId() {
        UserStoryJpaId anotherId = new UserStoryJpaId("XYZ", "US004");
        UserStoryJpa yetAnotherUserStoryJpa = new UserStoryJpa(anotherId,
                actor,
                description,
                criteria,
                status);

        assertNotEquals(yetAnotherUserStoryJpa, userStoryJpa);

    }

    @Test
    void doesNotEqualDifferentClass() {
        String blah = "I'm a unique snowflake";
        assertNotEquals(userStoryJpa, blah);
    }

    @Test
    void sameHashCode() {
        // Arrange
        UserStoryJpa yetAnotherUserStoryJpa = new UserStoryJpa(userStoryJpaId,
                actor,
                description,
                criteria,
                status);

        // Act
        int oneHashCode = userStoryJpa.hashCode();
        int anotherHashCode = yetAnotherUserStoryJpa.hashCode();

        // Assert
        assertEquals(oneHashCode, anotherHashCode);
    }

    @Test
    void differentHashCode() {
        // Arrange
        UserStoryJpaId anotherId = new UserStoryJpaId("XYZ", "US004");
        UserStoryJpa yetAnotherUserStoryJpa = new UserStoryJpa(anotherId,
                actor,
                description,
                criteria,
                status);

        // Act
        int oneHashCode = userStoryJpa.hashCode();
        int anotherHashCode = yetAnotherUserStoryJpa.hashCode();

        // Assert
        assertNotEquals(oneHashCode, anotherHashCode);
    }
}