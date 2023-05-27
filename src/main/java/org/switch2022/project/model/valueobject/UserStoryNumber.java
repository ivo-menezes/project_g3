package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

/**
 * This value object holds an alphanumeric "number" of a UserStory.
 * It is NOT an ID because to uniquely identify a UserStory
 * we also need the ProjectCode of the Project it belongs to (see UserStoryID).
 */
public class UserStoryNumber implements ValueObject {

    private final String userStoryNumber;

    public UserStoryNumber(String userStoryNumber) {
        if (userStoryNumber == null || userStoryNumber.isBlank() || userStoryNumber.isEmpty()) {
            throw new IllegalArgumentException("userStoryNumber cannot be null/blank/empty");
        }

        this.userStoryNumber = userStoryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryNumber that = (UserStoryNumber) o;
        return Objects.equals(userStoryNumber, that.userStoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber);
    }

    @Override
    public String toString() {
        return userStoryNumber;
    }
}
