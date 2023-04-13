package org.switch2022.project.model.profile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    @DisplayName("ensure Profile Administrator is successfully created")
    void createProfileAdminSucess() {
        Profile profile = new Profile("Administrator");
        boolean expected = true;
        boolean result = profile.isProfileNameValid("Administrator");
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("ensure non valid Profile name is not possible to use")
    void createProfileAdminFail() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile("Admiinistrator");
        });

        Assertions.assertEquals("Profile Name is not valid", exception.getMessage());
    }
    @Test
    @DisplayName("ensure a null Profile Name cannot be used")
    void createProfileNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile("");
        });

        Assertions.assertEquals("Profile Name is not valid", exception.getMessage());
    }
}