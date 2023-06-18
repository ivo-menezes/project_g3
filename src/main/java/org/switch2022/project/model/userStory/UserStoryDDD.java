package org.switch2022.project.model.userStory;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.Objects;

public class UserStoryDDD implements AggregateRoot<UserStoryID> {

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
    public UserStoryDDD(UserStoryID userStoryID,
                        UserStoryActor userStoryActor,
                        Description userStoryDescription,
                        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria) {

        this(userStoryID, userStoryActor, userStoryDescription, userStoryAcceptanceCriteria, UserStoryStatus.TO_DO);

    }

    public UserStoryDDD(UserStoryID userStoryID,
                        UserStoryActor userStoryActor,
                        Description userStoryDescription,
                        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria,
                        UserStoryStatus status) {

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

        this.userStoryID = userStoryID;
        this.userStoryActor = userStoryActor;
        this.userStoryDescription = userStoryDescription;
        this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
        this.userStoryStatus = status;
    }


    public UserStoryID identity() {
        return userStoryID;
    }

    public UserStoryActor getActor() {
        return userStoryActor;
    }

    public Description getDescription() {
        return userStoryDescription;
    }

    public UserStoryAcceptanceCriteria getAcceptanceCriteria() {
        return userStoryAcceptanceCriteria;
    }

    public UserStoryStatus getStatus() {
        return userStoryStatus;
    }

    public void setUserStoryStatus(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryDDD that = (UserStoryDDD) o;
        return Objects.equals(userStoryID, that.userStoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryID);
    }
}
