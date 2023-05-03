package org.switch2022.project.model.sprint;

import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

public class SprintFactoryImpl implements ISprintFactory {

    @Override
    public SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod) {
        if (sprintID == null || timePeriod == null) {
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);

        return sprint;
    }
}
