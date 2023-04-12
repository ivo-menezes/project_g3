package org.switch2022.project.model.profile;

public class Profile {
    private String profileName;

    public Profile(String profileName){
        if (!isProfileNameValid(profileName)) {
            throw new IllegalArgumentException("Profile Name is not valid");
        }
        this.profileName = profileName;
    }

    /**
     * validates if the profile name can be used
     *
     * @param profileName
     * @return true if profile name is valid, false otherwise
     */
    public boolean isProfileNameValid (String profileName) {
        boolean isValid = false;

        if (profileName == "Administrator" || profileName == "Manager" || profileName == "User") {
            isValid = true;
        } return isValid;
    }

    public String getProfileName() {
        return profileName;
    }
}
