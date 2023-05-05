package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.util.Objects;

public class UserStoryInSprintID implements DomainId {

    private final SprintID sprintID;

    private final UserStoryID userStoryID;

    public UserStoryInSprintID (SprintID sprintID, UserStoryID userStoryID) {
        if (sprintID == null) {
            throw new IllegalArgumentException("Sprint ID must not be null");
        }
        if (userStoryID == null) {
            throw new IllegalArgumentException("User story ID must not be null");
        }

        this.sprintID = sprintID;
        this.userStoryID = userStoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStoryInSprintID)) {
            return false;
        }
        UserStoryInSprintID that = (UserStoryInSprintID) o;
        return Objects.equals(sprintID, that.sprintID) && Objects.equals(userStoryID, that.userStoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID, userStoryID);
    }
}
