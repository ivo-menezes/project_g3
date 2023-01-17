package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProfileList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateProfileControllerTest {

    @Test
    @DisplayName("ensure creating a profile succeeds")
    void createProfileSuccess() {
        ProfileList profileList = new ProfileList();
        CreateProfileController controller = new CreateProfileController(profileList);
        boolean result = controller.createProfile("Administrator");
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure it catches any false value")
    void createProfile(){
        ProfileList profileList = new ProfileList();
        CreateProfileController controller = new CreateProfileController(profileList);
        boolean result = controller.createProfile("Administrator");
        assertNotEquals(false, result);
    }
}