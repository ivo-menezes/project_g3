package org.switch2022.project.mapper.sprintDTOs;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.sprint.SprintDDD;

@Component
public class SprintDTOToControllerMapper {
    public SprintDTOToController convertToDTO(SprintDDD sprint){
        SprintDTOToController newSprintDTO = new SprintDTOToController();

        newSprintDTO.sprintID = sprint.identity();
        newSprintDTO.timePeriod = sprint.getTimePeriod();

        return newSprintDTO;
    }
}
