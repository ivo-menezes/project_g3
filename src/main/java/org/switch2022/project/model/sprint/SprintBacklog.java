package org.switch2022.project.model.sprint;

import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The class SprintBacklog stores a list of UserStoryInSprint objects, representing
 * the user stories addressed in each sprint.
 */
public class SprintBacklog {

    private final List<UserStoryInSprint> userStoriesInSprintList;

    protected SprintBacklog() {
        this.userStoriesInSprintList = new ArrayList<>();
    }

    /**
     * Adds a UserStoryInSprint to the list of user stories in sprint.
     *
     * @param userStoryInSprint concerns user stories addressed in each sprint;
     * @return true if the addition was successful, false or exception otherwise.
     */
    protected boolean add(UserStoryInSprint userStoryInSprint) {

        if (userStoryInSprint == null) {
            throw new IllegalArgumentException("User Story in Sprint cannot be null");
        }

        if (userStoriesInSprintList.contains(userStoryInSprint)) {
            throw new IllegalArgumentException("UserStoryInSprint already in backlog");
        }

        return this.userStoriesInSprintList.add(userStoryInSprint);
    }


    /**
     * Returns a copy of the list of user stories in sprint.
     * @return List<UserStoryInSprint>
     */
    public List<UserStoryInSprint> getUserStoriesInSprintList() {
        return List.copyOf(this.userStoriesInSprintList);
    }

    protected List<UserStoryID> listOfUserStoriesInSprintWithStatusDone() {
        List<UserStoryID> listUserStoryIDs = new ArrayList<>();
        List<UserStoryInSprint> finalSprintBacklog = getUserStoriesInSprintList();

        for (UserStoryInSprint userStoryInSprint: finalSprintBacklog) {
            if (userStoryInSprint.getUserStoryInSprintStatus().equals(UserStoryStatus.DONE)) {
                UserStoryID userStoryID = userStoryInSprint.identity().getUserStoryID();
                listUserStoryIDs.add(userStoryID);
            }
        }
        return listUserStoryIDs;
    }

    public UserStoryInSprint save(UserStoryInSprint userStoryInSprint) {

        if (userStoryInSprint == null) {
            throw new IllegalArgumentException("User Story in Sprint cannot be null");
        }

        if (userStoriesInSprintList.contains(userStoryInSprint)) {
            throw new IllegalArgumentException("UserStoryInSprint already in backlog");
        }
        userStoriesInSprintList.add(userStoryInSprint);

        return userStoryInSprint;
    }
}



