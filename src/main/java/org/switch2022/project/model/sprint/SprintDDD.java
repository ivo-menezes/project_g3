package org.switch2022.project.model.sprint;


import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintStatus;
import org.switch2022.project.model.valueobject.TimePeriod;
import org.switch2022.project.model.valueobject.UserStoryID;

import java.util.List;
import java.util.Objects;

public class SprintDDD implements AggregateRoot<SprintID> {

    private final SprintID sprintID;
    private final TimePeriod timePeriod;

    private SprintStatus status;

    private final SprintBacklog sprintBacklog;

    /**
     * Public constructor to instantiate a sprint.
     *
     * @param sprintID   contains projectCode and sprintNumber;
     * @param timePeriod contains startDate and endDate.
     */
    public SprintDDD(SprintID sprintID, TimePeriod timePeriod){
        this(sprintID, timePeriod, SprintStatus.Planned);
    }

    public SprintDDD(SprintID sprintID, TimePeriod timePeriod, SprintStatus status) {
        if(sprintID == null || timePeriod == null || status == null){
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        this.sprintID = sprintID;
        this.timePeriod = timePeriod;
        this.status = status;
        this.sprintBacklog = new SprintBacklog();
    }

    public SprintDDD(SprintID sprintID,
                     TimePeriod timePeriod,
                     SprintStatus status,
                     List<UserStoryInSprint> userStoryInSprintList) {
        if(sprintID == null || timePeriod == null || status == null){
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        this.sprintID = sprintID;
        this.timePeriod = timePeriod;
        this.status = status;
        this.sprintBacklog = new SprintBacklog(userStoryInSprintList);
    }

    public SprintID identity() {
        return sprintID;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }
    public SprintStatus getSprintStatus(){
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public List<UserStoryInSprint> getUserStoriesInSprintList() {
        return sprintBacklog.getUserStoriesInSprintList();
    }
    public UserStoryInSprint save(UserStoryInSprint userStoryInSprint) {
        return sprintBacklog.save(userStoryInSprint);
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

    public List<UserStoryID> listOfUserStoriesInSprintWithStatusDone() {
        return sprintBacklog.listOfUserStoriesInSprintWithStatusDone();
    }
}
