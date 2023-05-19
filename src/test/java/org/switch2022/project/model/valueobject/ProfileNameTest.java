package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.Project;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProfileNameTest {

    @DisplayName("creating profileName with null value should throw Exception")
    @Test
    void createProfileNameWithNullValueThrowsException() {
        // Arrange
        String profileName = null;
        String expectedMessage = "profileName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new ProfileName(profileName);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating profileName with empty value should throw Exception")
    @Test
    void createProfileNameWithEmptyValueThrowsException() {
        // Arrange
        String profileName = "";
        String expectedMessage = "profileName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProfileName(profileName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating profileName with blank value should throw Exception")
    @Test
    void createProfileNameWithWithBlankValueThrowsException() {
        // Arrange
        String profileName = "       ";
        String expectedMessage = "profileName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProfileName(profileName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("With same object we should return true")
    @Test
    void shouldReturnTrueEqualsSameObject() {
        ProfileName profileName = new ProfileName("Test");

        boolean isEquals = profileName.equals(profileName);

        assertTrue(isEquals);
    }

    @DisplayName("With same profile name we should return true")
    @Test
    void shouldReturnTrueEqualsSameProfileName() {
        ProfileName profileName1 = new ProfileName("Test");
        ProfileName profileName2 = new ProfileName("Test");

        boolean isEquals = profileName1.equals(profileName2);

        assertTrue(isEquals);
    }

    @DisplayName("With differents profiles names we should return false")
    @Test
    void shouldReturnFalseEqualsDifferentProfileName() {
        ProfileName profileName1 = new ProfileName("Test");
        ProfileName profileName2 = new ProfileName("Test1");

        boolean isEquals = profileName1.equals(profileName2);

        assertFalse(isEquals);
    }

    @Test
    @DisplayName("test hash code")
    void testHashCode() {
        ProfileName profileName = new ProfileName("Test");
        ProfileName profileNameTest = new ProfileName("Test");

        assertEquals(profileNameTest.hashCode(), profileName.hashCode());
        assertNotEquals(0, profileName.hashCode());
    }


}