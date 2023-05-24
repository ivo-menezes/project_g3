package org.switch2022.project.datamodel.JPA;

import org.switch2022.project.model.valueobject.*;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "userstories")
public class UserStoryJpa {

    @EmbeddedId
    private final UserStoryID userStoryID;

    @Embedded
    private final UserStoryActor userStoryActor;

    @Embedded
    private final Description userStoryDescription;

    @Embedded
    private final UserStoryAcceptanceCriteria userStoryAcceptanceCriteria;

    @Embedded
    private final UserStoryStatus userStoryStatus;

    public UserStoryJpa(UserStoryID userStoryID, UserStoryActor userStoryActor, Description userStoryDescription, UserStoryAcceptanceCriteria userStoryAcceptanceCriteria, UserStoryStatus userStoryStatus) {
        this.userStoryID = userStoryID;
        this.userStoryActor = userStoryActor;
        this.userStoryDescription = userStoryDescription;
        this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
        this.userStoryStatus = userStoryStatus;
    }

    public UserStoryID getUserStoryID() {
        return userStoryID;
    }

    public UserStoryActor getUserStoryActor() {
        return userStoryActor;
    }

    public Description getUserStoryDescription() {
        return userStoryDescription;
    }

    public UserStoryAcceptanceCriteria getUserStoryAcceptanceCriteria() {
        return userStoryAcceptanceCriteria;
    }

    public UserStoryStatus getUserStoryStatus() {
        return userStoryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryJpa that = (UserStoryJpa) o;
        return Objects.equals(userStoryID, that.userStoryID) && Objects.equals(userStoryActor, that.userStoryActor) && Objects.equals(userStoryDescription, that.userStoryDescription) && Objects.equals(userStoryAcceptanceCriteria, that.userStoryAcceptanceCriteria) && userStoryStatus == that.userStoryStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID, userStoryActor, userStoryDescription, userStoryAcceptanceCriteria, userStoryStatus);
    }
}
