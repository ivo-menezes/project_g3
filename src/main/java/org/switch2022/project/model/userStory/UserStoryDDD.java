package org.switch2022.project.model.userStory;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.Objects;

public class UserStoryDDD implements AggregateRoot<UserStoryID> {

    private ProjectCode projectCode;

    private UserStoryID userStoryID;

    private UserStoryActor userStoryActor;

    private Description userStoryDescription;

    private UserStoryAcceptanceCriteria userStoryAcceptanceCriteria;

    private UserStoryStatus userStoryStatus;


    /**
     * Public constructor to instantiate a user story.
     *
     * @param userStoryID                 alphanumeric user story id
     * @param userStoryActor              of user story
     * @param userStoryDescription        of user story
     * @param userStoryAcceptanceCriteria of user story
     */
    public UserStoryDDD(ProjectCode projectCode, UserStoryID userStoryID, UserStoryActor userStoryActor, Description userStoryDescription, UserStoryAcceptanceCriteria userStoryAcceptanceCriteria) {

        if (projectCode == null) {
            throw new IllegalArgumentException("User Story project code must not be null");
        }

        if (userStoryID == null) {
            throw new IllegalArgumentException("User Story ID must not be null");
        }

        if (userStoryActor == null) {
            throw new IllegalArgumentException("User Story actor must not be null");
        }

        if (userStoryDescription == null) {
            throw new IllegalArgumentException("User Story text must not be null");
        }

        if (userStoryAcceptanceCriteria == null) {
            throw new IllegalArgumentException("User Story acceptance criteria must not be null");
        }

        this.projectCode = projectCode;
        this.userStoryID = userStoryID;
        this.userStoryActor = userStoryActor;
        this.userStoryDescription = userStoryDescription;
        this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
        this.userStoryStatus = userStoryStatus.TO_DO;
    }

    public ProjectCode getProjectCode() { return projectCode; }

    public UserStoryID identity() { return userStoryID; }

    public UserStoryActor getActor() { return userStoryActor; }

    public Description getDescription() { return userStoryDescription; }

    public UserStoryAcceptanceCriteria getAcceptanceCriteria() { return userStoryAcceptanceCriteria;  }

    public UserStoryStatus getStatus() { return userStoryStatus; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStoryDDD)) {
            return false;
        }
        UserStoryDDD that = (UserStoryDDD) o;
        return projectCode.equals(that.projectCode) && userStoryID.equals(that.userStoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, userStoryID);
    }

}
