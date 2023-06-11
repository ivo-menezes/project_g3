package org.switch2022.project.model.sprint;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.*;


@Component
public class SprintFactoryImpl implements ISprintFactory {

    @Override
    public SprintID newSprintID(ProjectCode projectCode, SprintNumber newSprintNumber){
        return new SprintID(projectCode, newSprintNumber);
    }
    @Override
    public SprintDDD createSprint(NewSprintDTO sprintDTO) {
        return new SprintDDD(sprintDTO.sprintID, sprintDTO.timePeriod);
    }
    @Override
    public SprintDDD createSprint(SprintID sprintID, TimePeriod timePeriod, SprintStatus status) {
        if (sprintID == null || timePeriod == null) {
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        return new SprintDDD(sprintID, timePeriod, status);
    }
}
