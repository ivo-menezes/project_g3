package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfileListTest {

    @Test
    void getProfile() {
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
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            profileList.createProfile(null);
        });
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

        ArrayList<Profile> arrayList = new ArrayList<>();
        arrayList.add(profile);
        ProfileList testProfileList = new ProfileList(arrayList);
        // act
        profileList.add(profile);
        // assert
        assertEquals(profileList, testProfileList);
    }

    @Test
    @DisplayName("ensure adding a null profile throw exception")
    void addNullProfile() {
        ProfileList profileList = new ProfileList();
        Profile profile = null;

        ArrayList<Profile> arrayList = new ArrayList<>();
        arrayList.add(profile);
        ProfileList testProfileList = new ProfileList(arrayList);
        // act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            profileList.add(profile);
        });
        // assert
        assertEquals("Profile must not be null", exception.getMessage());
    }
}