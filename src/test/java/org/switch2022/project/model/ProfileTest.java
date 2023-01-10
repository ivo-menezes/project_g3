package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void createProfileAdminSucess() {
        Profile profile = new Profile("Administrator");
        boolean expected = true;
        boolean result = profile.isProfileNameValid("Administrator");
        assertEquals(expected,result);
    }

    @Test
    void createProfileAdminFail() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile("Admiinistrator");
        });

        Assertions.assertEquals("Profile Name is not valid", exception.getMessage());
    }
    @Test
    void createProfileNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile("");
        });

        Assertions.assertEquals("Profile Name is not valid", exception.getMessage());
    }
}