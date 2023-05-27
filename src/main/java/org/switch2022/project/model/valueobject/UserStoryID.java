package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.util.Objects;

public class UserStoryID implements DomainId {

    private UserStoryNumber userStoryNumber;
    private ProjectCode projectCode;

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

    @Override
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof UserStoryID) {
            UserStoryID userStoryID = (UserStoryID) object;

            if (this.userStoryNumber.equals(userStoryID.userStoryNumber) && this.projectCode.equals(userStoryID.projectCode))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber, projectCode);
    }

    public UserStoryNumber getUserStoryNumber() {
        return userStoryNumber;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }
}
