package org.switch2022.project.model.sprint;

import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

public interface ISprintFactory {
    SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod);
}