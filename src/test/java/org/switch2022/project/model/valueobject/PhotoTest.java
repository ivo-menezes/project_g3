package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @DisplayName("creating photo with null value should throw Exception")
    @Test
    void createPhotoWithNullValueThrowsException() {
        // Arrange
        String photoName = null;
        String expectedMessage = "photoName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result =assertThrows(IllegalArgumentException.class, () -> {
            new Photo(photoName);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating Photo with empty value should throw Exception")
    @Test
    void createPhotoWithEmptyValueThrowsException() {
        // Arrange
        String photoName = "";
        String expectedMessage = "photoName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Photo(photoName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating Photo with blank value should throw Exception")
    @Test
    void createPhotoWithWithBlankValueThrowsException() {
        // Arrange
        String photoName = "       ";
        String expectedMessage = "photoName cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Photo(photoName);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("With same object we should return true")
    @Test
    void shouldReturnTrueEqualsSameObject() {
        Photo photo = new Photo("Test");

        boolean isEquals = photo.equals(photo);

        assertTrue(isEquals);
    }

    @DisplayName("With same profile name we should return true")
    @Test
    void shouldReturnTrueEqualsSameProfileName() {
        Photo photo1 = new Photo("Test");
        Photo photo2 = new Photo("Test");

        boolean isEquals = photo1.equals(photo2);

        assertTrue(isEquals);
    }

    @DisplayName("With differents photo names we should return false")
    @Test
    void shouldReturnFalseEqualsDifferentPhotoName() {
        Photo photo1 = new Photo("Test");
        Photo photo2 = new Photo("Test2");

        boolean isEquals = photo1.equals(photo2);

        assertFalse(isEquals);
    }

    @Test
    @DisplayName("test hash code")
    void testHashCode() {
        Photo photo1 = new Photo("Test");
        Photo photo2 = new Photo("Test");

        assertEquals(photo1.hashCode(), photo2.hashCode());
        assertNotEquals(0, photo1.hashCode());
    }
}