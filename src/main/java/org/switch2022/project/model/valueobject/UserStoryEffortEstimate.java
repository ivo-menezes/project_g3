package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;
/**
 * This value object holds an effort estimate for a UserStory.
 */
public class UserStoryEffortEstimate implements ValueObject {

    private final Double userStoryEffortEstimate;

    public UserStoryEffortEstimate (Double userStoryEffortEstimate) {
        if (userStoryEffortEstimate == null) {
            throw new IllegalArgumentException("User Story effort estimate cannot be null");
        }
        this.userStoryEffortEstimate = userStoryEffortEstimate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryEffortEstimate that = (UserStoryEffortEstimate) o;
        return Objects.equals(userStoryEffortEstimate, that.userStoryEffortEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryEffortEstimate);
    }

}
