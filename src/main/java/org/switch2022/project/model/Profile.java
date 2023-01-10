package org.switch2022.project.model;

public class Profile {
    private String profileName;

    public Profile(String profileName){
        if (!isProfileNameValid(profileName)) {
            throw new IllegalArgumentException("Profile Name is not valid");
        }
        this.profileName = profileName;
    }

    public boolean isProfileNameValid (String profileName) {
        boolean isValid = false;

        if (profileName == "Administrator" || profileName == "Manager" || profileName == "User") {
            isValid = true;
        } return isValid;
    }
}
