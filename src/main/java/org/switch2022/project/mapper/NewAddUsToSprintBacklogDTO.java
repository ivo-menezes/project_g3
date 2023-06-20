package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.UserStoryEffortEstimate;
import org.switch2022.project.model.valueobject.UserStoryNumber;

public class NewAddUsToSprintBacklogDTO {
    public ProjectCode projectCode;
    public SprintNumber sprintNumber;
    public UserStoryNumber userStoryNumber;
    public UserStoryEffortEstimate userStoryEffortEstimate;
}
