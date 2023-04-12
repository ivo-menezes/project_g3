package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileListTest {

    @Test
    @DisplayName("ensure retrieving an existing profile by name succeeds")
    void getProfileByNameSuccess() {
        ProfileList profileList = new ProfileList();
        Profile profile = new Profile("Administrator");
        Profile profileMan = new Profile("Manager");
        String prof = "Administrator";

        profileList.add(profile);
        profileList.add(profileMan);

        Profile result = profileList.getProfileByName(prof);

        assertEquals(profile, result);
    }

    @Test
    @DisplayName("ensure retrieving a non-existing profile by name throws exception")
    void getProfileByNameFailure() {
        ProfileList profileList = new ProfileList();
        Profile profile = new Profile("Manager");
        profileList.add(profile);
        String nonExistingName = "Administrator";
        String expectedMessage = "A profile with this name does not exist.";

        NoSuchElementException result = assertThrows(NoSuchElementException.class,
                () -> profileList.getProfileByName(nonExistingName));
        String resultMessage = result.getMessage();
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure creating a valid profile succeeds")
    void createProfileSuccess() {
        // arrange
        ProfileList profileList = new ProfileList();
        boolean results = profileList.createProfile("Manager");
        assertTrue(results);
    }

    @Test
    @DisplayName("ensure creating a null profile fails")
    void createProfileNullFails() {
        // arrange
        ProfileList profileList = new ProfileList();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> profileList.createProfile(null));
        assertEquals("Profile name is invalid.", exception.getMessage());

    }

    @Test
    @DisplayName("ensure non existing profile name is valid")
    void validateProfileNameSuccess() {
        // arrange
        ProfileList profileList = new ProfileList();
        Profile profile = new Profile("Manager");
        profileList.add(profile);
        String newProfileName = "Administrator";
        // act
        boolean result = profileList.validateProfileName(newProfileName);
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure existing profile name is not valid")
    void validateProfileNameFails() {
        // arrange
        ProfileList profileList = new ProfileList();
        Profile profile = new Profile("Manager");
        profileList.add(profile);
        String newProfileName = "Manager";
        // act
        boolean result = profileList.validateProfileName(newProfileName);
        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure adding a valid profile succeeds")
    void addValidProfile() {
        // arrange
        ProfileList profileList = new ProfileList();
        Profile profile = new Profile("Administrator");
        String expectedFailureMessage = "A profile with this name does not exist.";

        // act
        NoSuchElementException resultFailure = assertThrows(NoSuchElementException.class,
                () -> profileList.getProfileByName("Administrator"));
        String resultFailureMessage = resultFailure.getMessage();
        profileList.add(profile);
        Profile retrievedProfile = profileList.getProfileByName("Administrator");
        // assert
        assertEquals(expectedFailureMessage, resultFailureMessage);
        assertEquals(profile, retrievedProfile);
    }

    @Test
    @DisplayName("ensure adding a null profile throw exception")
    void addNullProfile() {
        ProfileList profileList = new ProfileList();
        Profile profile = null;
        // act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> profileList.add(profile));
        // assert
        assertEquals("Profile must not be null", exception.getMessage());
    }
}