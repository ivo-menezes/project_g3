package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserStoryID implements DomainId, Serializable {

    @Embedded
    private UserStoryNumber userStoryNumber;
    @Embedded
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
}
