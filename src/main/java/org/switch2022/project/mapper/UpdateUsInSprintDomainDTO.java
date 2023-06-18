package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;
import org.switch2022.project.model.valueobject.UserStoryStatus;

public class UpdateUsInSprintDomainDTO {

    public UserStoryInSprintID userStoryInSprintID;
    public SprintNumber sprintNumber;
    public UserStoryStatus userStoryInSprintStatus;
}
