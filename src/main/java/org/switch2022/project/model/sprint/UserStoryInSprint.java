package org.switch2022.project.model.sprint;

import org.switch2022.project.ddd.DomainEntity;
import org.switch2022.project.model.valueobject.UserStoryEffortEstimate;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;

/**
 * The class UserStoryInSprint represents user stories included in a specific sprint,
 * and associates a UserStoryInSprintID to a UserStoryEffortEstimate. These user stories
 * will be stored in a sprint backlog.
 */
public class UserStoryInSprint implements DomainEntity<UserStoryInSprintID> {

    private final UserStoryInSprintID userStoryInSprintID;
    private final UserStoryEffortEstimate userStoryEffortEstimate;

    /**
     * Protected constructor to instantiate a UserStoryInSprint.
     * @param userStoryInSprintID encapsulates a UserStoryID and a SprintID;
     * @param userStoryEffortEstimate encapsulates the effort associated to the UserStoryInSprint.
     */
    protected UserStoryInSprint(UserStoryInSprintID userStoryInSprintID,
                                UserStoryEffortEstimate userStoryEffortEstimate) {
        if (userStoryInSprintID == null) {
            throw new IllegalArgumentException("ID for User Story in Sprint cannot be null");
        }
        if (userStoryEffortEstimate == null) {
            throw new IllegalArgumentException("User Story effort estimate in Sprint cannot be null");
        }

        this.userStoryInSprintID = userStoryInSprintID;
        this.userStoryEffortEstimate = userStoryEffortEstimate;
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
    protected UserStoryEffortEstimate getUserStoryEffortEstimate() {
        return userStoryEffortEstimate;
    }
}
