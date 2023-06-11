package org.switch2022.project.model.sprint;

import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.*;

public interface ISprintFactory {
    SprintID newSprintID(ProjectCode projectCode, SprintNumber newSprintNumber);

    SprintDDD createSprint(NewSprintDTO sprintDTO);

    SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod, SprintStatus status);
}
