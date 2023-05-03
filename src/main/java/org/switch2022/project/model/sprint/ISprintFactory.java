package org.switch2022.project.model.sprint;

import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

public interface IFactorySprint {
    SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod);
}
