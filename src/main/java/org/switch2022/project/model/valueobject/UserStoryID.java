package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.util.Objects;

public class UserStoryID implements DomainId {

    private final UserStoryNumber userStoryNumber;
    private final ProjectCode projectCode;

    public UserStoryID (UserStoryNumber userStoryNumber, ProjectCode projectCode) {
        if (userStoryNumber == null) {
            throw new IllegalArgumentException("userStoryNumber must not be null");
        }

        if (projectCode == null) {
            throw  new IllegalArgumentException("projectCode must not be null");
        }

        this.userStoryNumber = userStoryNumber;
        this.projectCode = projectCode;
    }

    public UserStoryNumber getUserStoryNumber() {
        return userStoryNumber;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        UserStoryID that = (UserStoryID) o;
        return Objects.equals(userStoryNumber, that.userStoryNumber) &&
                Objects.equals(projectCode, that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber, projectCode);
    }
}
