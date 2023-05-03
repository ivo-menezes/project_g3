package org.switch2022.project.model.sprint;

import org.switch2022.project.ddd.DomainEntity;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;

public class UserStoryInSprint implements DomainEntity<UserStoryInSprintID> {

    private UserStoryInSprintID userStoryInSprintID;

    public UserStoryInSprint(UserStoryInSprintID userStoryInSprintID) {
        if (userStoryInSprintID == null) {
            throw new IllegalArgumentException("ID for User Story in Sprint must not be null");
        }

        this.userStoryInSprintID = userStoryInSprintID;
    }

    public UserStoryInSprintID identity() {return userStoryInSprintID; }

}
