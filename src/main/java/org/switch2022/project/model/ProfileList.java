package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileList {
    private List<Profile> profileList;

    public ProfileList() {
        this.profileList = new ArrayList<Profile>();
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
}
