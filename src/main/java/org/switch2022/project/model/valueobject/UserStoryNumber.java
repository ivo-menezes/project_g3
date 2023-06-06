package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

/**
 * This value object holds an alphanumeric "number" of a UserStory.
 * It is NOT an ID because to uniquely identify a UserStory
 * we also need the ProjectCode of the Project it belongs to (see UserStoryID).
 */
public class UserStoryNumber implements ValueObject {

    private final String number;

    public UserStoryNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("userStoryNumber cannot be null");
        }

        if (number.isBlank()) {
            throw new IllegalArgumentException("userStoryNumber cannot be blank/empty");
        }

        this.number = number;
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
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number;
    }
}
