package org.switch2022.project.controller;

import org.switch2022.project.model.ProfileList;

public class CreateProfileController {
    private ProfileList profileList;

    /**
     * constructor that accepts existing ProfileList
     *
     * @param profileList ProfileList to be added to controller
     */
    public CreateProfileController(ProfileList profileList) {
        this.profileList = profileList;
    }

    /**
     * instructs profileList to create new profile with given name
     *
     * @param profileName String for name of new profile
     * @return true if profile successfully created, false otherwise
     */
    public boolean createProfile(String profileName) {
        return this.profileList.createProfile(profileName);
    }
}
