package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserStoryJpaIdTest {

    @DisplayName("ensure instantiating UserStoryJpaId using no-args constructor is successful")
    @Test
    void shouldInstantiateUserStoryJpaId() {
        // Act
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId();

        // Assert
        assertInstanceOf(UserStoryJpaId.class, userStoryJpaId);
    }

    @Test
    void getProjectCode() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        // Act
        String result = userStoryJpaId.getProjectCode();

        // Assert
        assertEquals(code, result);
    }

    @Test
    void getUserStoryNumber() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        // Act
        String result = userStoryJpaId.getUserStoryNumber();

        // Assert
        assertEquals(number, result);
    }

    @Test
    void doesNotEqualNull() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        // Act/Assert
        assertNotEquals(null, userStoryJpaId);
    }

    @Test
    void equalsItself() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        // Act/Assert
        assertEquals(userStoryJpaId, userStoryJpaId);
    }

    @Test
    void equalsSimilar() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);
        UserStoryJpaId anotherUserStoryJpaId = new UserStoryJpaId(code, number);

        // Act/Assert
        assertEquals(anotherUserStoryJpaId, userStoryJpaId);
    }

    @Test
    void doesNotEqualDifferentProjectCode() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        String anotherCode = "XYZ";
        UserStoryJpaId anotherUserStoryJpaId = new UserStoryJpaId(anotherCode, number);

        // Act/Assert
        assertNotEquals(anotherUserStoryJpaId, userStoryJpaId);
    }

    @Test
    void doesNotEqualDifferentUserStoryNumber() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        String anotherNumber = "US003";
        UserStoryJpaId anotherUserStoryJpaId = new UserStoryJpaId(code, anotherNumber);

        // Act/Assert
        assertNotEquals(anotherUserStoryJpaId, userStoryJpaId);
    }

    @Test
    void doesNotEqualDifferentClass() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);
        String blah = "I'm a unique snowflake";

        // Act
        boolean result = userStoryJpaId.equals(blah);

        // Assert
        assertFalse(result);
    }

    @Test
    void sameHashCode() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        UserStoryJpaId anotherUserStoryJpaId = new UserStoryJpaId(code, number);

        // Act
        int oneHashCode = userStoryJpaId.hashCode();
        int anotherHashCode = anotherUserStoryJpaId.hashCode();

        // Assert
        assertEquals(oneHashCode, anotherHashCode);
    }

    @Test
    void differentHashCode() {
        // Arrange
        String code = "XPTO";
        String number = "US001";
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(code, number);

        String anotherCode = "XYZ";
        UserStoryJpaId anotherUserStoryJpaId = new UserStoryJpaId(anotherCode, number);

        // Act
        int oneHashCode = userStoryJpaId.hashCode();
        int anotherHashCode = anotherUserStoryJpaId.hashCode();

        // Assert
        assertNotEquals(oneHashCode, anotherHashCode);
    }
}