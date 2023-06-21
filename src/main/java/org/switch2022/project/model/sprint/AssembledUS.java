package org.switch2022.project.model.sprint;

import org.switch2022.project.model.valueobject.*;

import java.util.Objects;

/**
 * The class AssembledUS compiles  UserStoryInSprint objects and
 * UserStory objects.
 */

public class AssembledUS {

    private UserStoryNumber userStoryNumber;
    private ProjectCode projectCode;
    private SprintNumber sprintNumber;
    private UserStoryActor userStoryActor;
    private Description userStoryDescription;
    private UserStoryAcceptanceCriteria userStoryAcceptanceCriteria;
    private UserStoryStatus userStoryStatus;
    private UserStoryEffortEstimate userStoryEffortEstimate;


    /**
     * Constructor for AssembledUS
     * @param userStoryNumber - user story number from UserStoryDDD
     * @param projectCode - project code from UserStoryDDD
     * @param sprintNumber - sprint number from UserStoryDDD
     * @param userStoryActor - Actor from UserStoryDDD
     * @param userStoryDescription - Description from UserStoryDDD
     * @param userStoryAcceptanceCriteria - Acceptance criteria from UserStoryDDD
     * @param userStoryStatus - Status from UserStoryInSprint
     * @param userStoryEffortEstimate - Effort estimate from UserStoryInSprint
     */
    public AssembledUS(UserStoryNumber userStoryNumber, ProjectCode projectCode,
                       SprintNumber sprintNumber, UserStoryActor userStoryActor,
                       Description userStoryDescription, UserStoryAcceptanceCriteria userStoryAcceptanceCriteria,
                       UserStoryStatus userStoryStatus, UserStoryEffortEstimate userStoryEffortEstimate) {
        if (userStoryNumber == null) {
            throw new IllegalArgumentException("User story number cannot be null");
        }
        if (projectCode == null) {
            throw new IllegalArgumentException("Project code cannot be null");
        }
        if (sprintNumber == null) {
            throw new IllegalArgumentException("Sprint Number code cannot be null");
        }
        if (userStoryActor == null) {
            throw new IllegalArgumentException("User story actor code cannot be null");
        }
        if (userStoryDescription == null) {
            throw new IllegalArgumentException("User story description cannot be null");
        }
        if (userStoryAcceptanceCriteria == null) {
            throw new IllegalArgumentException("User story acceptance criteria cannot be null");
        }
        if (userStoryStatus == null) {
            throw new IllegalArgumentException("User story status cannot be null");
        }
        if (userStoryEffortEstimate == null) {
            throw new IllegalArgumentException("User story effort estimate cannot be null");
        }
        this.userStoryNumber = userStoryNumber;
        this.projectCode = projectCode;
        this.sprintNumber = sprintNumber;
        this.userStoryActor = userStoryActor;
        this.userStoryDescription = userStoryDescription;
        this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
        this.userStoryStatus = userStoryStatus;
        this.userStoryEffortEstimate = userStoryEffortEstimate;
    }


    public UserStoryNumber getUserStoryNumber() {
        return userStoryNumber;
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public SprintNumber getSprintNumber() {
        return sprintNumber;
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

    public UserStoryEffortEstimate getUserStoryEffortEstimate() {
        return userStoryEffortEstimate;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssembledUS that = (AssembledUS) o;
        return Objects.equals(userStoryNumber, that.userStoryNumber) &&
                Objects.equals(projectCode, that.projectCode) &&
                Objects.equals(sprintNumber, that.sprintNumber) &&
                Objects.equals(userStoryActor, that.userStoryActor) &&
                Objects.equals(userStoryDescription, that.userStoryDescription) &&
                Objects.equals(userStoryAcceptanceCriteria, that.userStoryAcceptanceCriteria) &&
                Objects.equals(userStoryStatus, that.userStoryStatus) &&
                Objects.equals(userStoryEffortEstimate, that.userStoryEffortEstimate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryNumber, projectCode, sprintNumber,
                userStoryActor, userStoryDescription, userStoryAcceptanceCriteria,
                userStoryStatus, userStoryEffortEstimate);
    }
}