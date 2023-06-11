package org.switch2022.project.model.sprint;


import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SprintDDD implements AggregateRoot<SprintID> {

    private final SprintID sprintID;
    private final TimePeriod timePeriod;

    private final List<UserStoryInSprint> sprintBacklog = new ArrayList<>();

    /**
     * Public constructor to instantiate a sprint.
     *
     * @param sprintID   contains projectCode and sprintNumber;
     * @param timePeriod contains startDate and endDate.
     */
    public SprintDDD(SprintID sprintID, TimePeriod timePeriod) {
        if (sprintID == null || timePeriod == null) {
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        this.sprintID = sprintID;
        this.timePeriod = timePeriod;
    }

    public SprintID identity() {
        return sprintID;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SprintDDD sprintDDD = (SprintDDD) o;
        return sprintID.equals(sprintDDD.sprintID);
    }


    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

    /**
     * Add a user story to the sprint backlog.
     *
     * @param userStoryInSprint relates a specific US to a specific sprint.
     * @return true if the userStoryInSprint was added to the Sprint Backlog, or false otherwise.
     */
    public boolean addUserStoryToSprintBacklog(UserStoryInSprint userStoryInSprint) {
        boolean added = false;

        if (!this.existsUserStory(userStoryInSprint)) {
            this.sprintBacklog.add(userStoryInSprint);
            added = true;
        }
        return added;
    }

    /**
     * Checks if it contains the user story in the sprint backlog.
     *
     * @param userStoryInSprint relates a specific US to a specific sprint.
     * @return returns false if the user story does not exist, or true otherwise.
     */
    private boolean existsUserStory(UserStoryInSprint userStoryInSprint) {
        boolean exists = false;

        if (userStoryInSprint == null) {
            throw new IllegalArgumentException("UserStoryInSprint must not be null");
        }
        if (sprintBacklog.contains(userStoryInSprint)) {
            exists = true;
        }
        return exists;
    }

}
