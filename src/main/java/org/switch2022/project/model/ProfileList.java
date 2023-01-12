package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProfileList {
    private List<Profile> profileList;

    public ProfileList() {
        this.profileList = new ArrayList<Profile>();
    }

    public ProfileList(List<Profile> profileList) {
        if (profileList == null) {
            throw new IllegalArgumentException("Profile List must not be null.");
        }

        this.profileList = profileList;
    }

    public Profile getProfile(String profileName) {
        Profile profile = null;

        for (int i = 0; i < this.profileList.size() && profile == null; i++) {
            Profile p = this.profileList.get(i);
            String pName = p.getProfileName();
            if (pName.equals(profileName)) {
                profile = p;
            }
        }

        return profile;
    }

    public boolean createProfile(String profileName) {
        if (!validateProfileName(profileName)) {
            throw new IllegalArgumentException("Profile name is invalid.");
        }

        Profile newProfile = new Profile(profileName);

        return add(newProfile);
    }

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

    public boolean add(Profile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile must not be null");
        }
        return this.profileList.add(profile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfileList that = (ProfileList) o;
        return profileList.equals(that.profileList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileList);
    }
}
