package org.switch2022.project.model;

import org.switch2022.project.model.profile.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProfileList {
    private final List<Profile> profileList;

    /**
     * constructor that initializes a ProfileList as an empty ArrayList
     */
    public ProfileList() {
        this.profileList = new ArrayList<>();
    }

    /**
     * retrieves a profile by its name
     *
     * @param profileName name of profile to be retrieved
     * @return profile with searched name
     */
    public Profile getProfileByName(String profileName) {
        Profile wantedProfile = null;

        for (int i = 0; i < this.profileList.size() && wantedProfile == null; i++) {
            Profile currentProfile = this.profileList.get(i);
            String currentProfileName = currentProfile.getProfileName();
            if (currentProfileName.equals(profileName)) {
                wantedProfile = currentProfile;
            }
        }

        if (wantedProfile == null) {
            throw new NoSuchElementException("A profile with this name does not exist.");
        }

        return wantedProfile;
    }

    /**
     * creates a new profile and adds it to the profileList
     *
     * @param profileName String for the name of the new profile
     * @return true if profile successfully created and saved, false otherwise
     */
    public boolean createProfile(String profileName) {
        if (!validateProfileName(profileName)) {
            throw new IllegalArgumentException("Profile name is invalid.");
        }

        Profile newProfile = new Profile(profileName);

        return add(newProfile);
    }

    /**
     * validates if a Profile with the same name already exists
     *
     * @param profileName String of new profile name
     * @return true if name is valid, false otherwise
     */
    public boolean validateProfileName(String profileName) {
        boolean profileValid = true;
        if (profileName == null) {
            profileValid = false;
        }

        for (int i = 0; i < this.profileList.size() && profileValid; i++) {
            Profile p = this.profileList.get(i);
            String pName = p.getProfileName();
            if (pName.equals(profileName)) {
                profileValid = false;
            }
        }

        return profileValid;
    }

    /**
     * adds a new profile to the profileList
     *
     * @param profile Profile to be added
     * @return true if profile successfully added, false otherwise
     */
    public boolean add(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile must not be null");
        }
        return this.profileList.add(profile);
    }

}
