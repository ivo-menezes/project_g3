package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;


@Component
public class SprintFactoryImpl implements ISprintFactory {

    @Override
    public SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod) {
        if (sprintID == null || timePeriod == null) {
            throw new IllegalArgumentException("Missing value, please try again.");
        }

        return new SprintDDD(sprintID, timePeriod);
    }
}
