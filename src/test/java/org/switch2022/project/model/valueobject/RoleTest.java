package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @DisplayName("creating role with null value should throw Exception")
    @Test
    void createRoleNameWithNullValueThrowsException() {
        // Arrange
        String role = null;
        String expectedMessage = "roleName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new Role(role);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating role with empty value should throw Exception")
    @Test
    void createRoleNameWithEmptyValueThrowsException() {
        // Arrange
        String role = "";
        String expectedMessage = "roleName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Role(role);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating role with blank value should throw Exception")
    @Test
    void createRoleNameWithWithBlankValueThrowsException() {
        // Arrange
        String role = "       ";
        String expectedMessage = "roleName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Role(role);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("With same object we should return true")
    @Test
    void shouldReturnTrueEqualsSameObject() {
        Role role = new Role("User");

        boolean isEquals = role.equals(role);

        assertTrue(isEquals);
    }

    @DisplayName("With same role we should return true")
    @Test
    void shouldReturnTrueEqualsSameProfileName() {
        Role role1 = new Role("Administrator");
        Role role2 = new Role("Administrator");

        boolean isEquals = role1.equals(role2);

        assertTrue(isEquals);
    }

    @DisplayName("With differents roles names we should return false")
    @Test
    void shouldReturnFalseEqualsDifferentProfileName() {
        Role role1 = new Role("Administrator");
        Role role2 = new Role("Manager");

        boolean isEquals = role1.equals(role2);

        assertFalse(isEquals);
    }

    @Test
    @DisplayName("test hash code")
    void testHashCode() {
        Role role1 = new Role("User");
        Role role2 = new Role("User");

        assertEquals(role1.hashCode(), role2.hashCode());
        assertNotEquals(0, role1.hashCode());
    }
}