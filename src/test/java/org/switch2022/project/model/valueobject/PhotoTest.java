package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @DisplayName("With same object we should return true")
    @Test
    void shouldReturnTrueEqualsSameObject() {
        Photo photo = new Photo("Test");

        boolean isEquals = photo.equals(photo);

        assertTrue(isEquals);
    }

    @DisplayName("With different objects we should return false")
    @Test
    void shouldReturnFalseWithDifferentObjects() {
        Photo photo = new Photo("Test");
        String fakePhoto = "Paint";

        boolean isEquals = photo.equals(fakePhoto);

        assertFalse(isEquals);
    }

    @DisplayName("With same profile name we should return true")
    @Test
    void shouldReturnTrueEqualsSameProfileName() {
        Photo photo1 = new Photo("Test");
        Photo photo2 = new Photo("Test");

        boolean isEquals = photo1.equals(photo2);

        assertTrue(isEquals);
    }

    @DisplayName("With different photo names we should return false")
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