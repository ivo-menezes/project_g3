package org.switch2022.project.model.sprint;

import org.switch2022.project.ddd.DomainEntity;
import org.switch2022.project.model.valueobject.SprintStatus;
import org.switch2022.project.model.valueobject.UserStoryEffortEstimate;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;
import org.switch2022.project.model.valueobject.UserStoryStatus;

import java.util.Objects;

/**
 * The class UserStoryInSprint represents user stories included in a specific sprint,
 * and associates a UserStoryInSprintID to a UserStoryEffortEstimate and a UserStoryStatus
 * (refers to the US status in a specific project and sprint, which may differ from
 * the US status in the product backlog). These user stories will be stored in a
 * sprint backlog.
 */
public class UserStoryInSprint implements DomainEntity<UserStoryInSprintID> {

    private final UserStoryInSprintID userStoryInSprintID;
    private final UserStoryEffortEstimate userStoryEffortEstimate;

    private UserStoryStatus userStoryInSprintStatus;



    /**
     * Protected constructor to instantiate a UserStoryInSprint.
     * @param userStoryInSprintID encapsulates a UserStoryID and a SprintID;
     * @param userStoryEffortEstimate encapsulates the effort associated to the
     *                                UserStoryInSprint;
     * @param userStoryInSprintStatus encapsulates the status for UserStoryInSprint.
     */
    public UserStoryInSprint(UserStoryInSprintID userStoryInSprintID,
                             UserStoryEffortEstimate userStoryEffortEstimate,
                             UserStoryStatus userStoryInSprintStatus) {
        if (userStoryInSprintID == null) {
            throw new IllegalArgumentException("ID for User Story in Sprint cannot be null");
        }
        if (userStoryEffortEstimate == null) {
            throw new IllegalArgumentException("User Story effort estimate in Sprint cannot be null");
        }
        if (userStoryInSprintStatus == null) {
            throw new IllegalArgumentException("Status for User Story in Sprint cannot be null");
        }

        this.userStoryInSprintID = userStoryInSprintID;
        this.userStoryEffortEstimate = userStoryEffortEstimate;
        this.userStoryInSprintStatus = userStoryInSprintStatus;
    }

    public void setStatus(UserStoryStatus status) {
        this.userStoryInSprintStatus = status;
    }
    /**
     * Retrieves the ID for UserStoryInSprint.
     * @return userStoryInSprintID
     */
    public UserStoryInSprintID identity() {
        return userStoryInSprintID;
    }

    /**
     * Retrieves the effort associated to the UserStoryInSprint.
     * @return userStoryEffortEstimate.
     */
    public UserStoryEffortEstimate getUserStoryEffortEstimate() {
        return userStoryEffortEstimate;
    }

    /**
     * Retrieves the status associated to the UserStoryInSprint.
     * @return userStoryInSprintStatus.
     */
    public UserStoryStatus getUserStoryInSprintStatus() {
        return userStoryInSprintStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryInSprint that = (UserStoryInSprint) o;
        return userStoryInSprintID.equals(that.userStoryInSprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryInSprintID);
    }
}
