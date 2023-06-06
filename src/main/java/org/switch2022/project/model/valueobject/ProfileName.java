package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class ProfileName implements ValueObject {

    private final String profileName;

    public ProfileName(String profileName) {
        if (profileName == null || profileName.isBlank() || profileName.isEmpty()) {
            throw new IllegalArgumentException("profileName cannot be null/blank/empty");}
        if (!profileName.equals("Administrator") && !profileName.equals("Manager") && !profileName.equals("User")) {
            throw new IllegalArgumentException("ProfileName is not valid");
        }

        this.profileName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof ProfileName)) {return false;}
        ProfileName that = (ProfileName) o;
        return Objects.equals(profileName, that.profileName);
    }

    @Override
    public int hashCode() { return Objects.hash(profileName);}

    @Override
    public String toString() {
        return profileName;
    }

}
