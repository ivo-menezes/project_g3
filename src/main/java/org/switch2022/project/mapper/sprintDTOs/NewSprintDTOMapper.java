package org.switch2022.project.mapper.sprintDTOs;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.sprint.SprintDDD;

@Component
public class NewSprintDTOMapper {
    public NewSprintDTO convertToDTO(SprintDDD sprint){
        NewSprintDTO newSprintDTO = new NewSprintDTO();

        newSprintDTO.sprintID = sprint.identity();
        newSprintDTO.timePeriod = sprint.getTimePeriod();
        newSprintDTO.status = sprint.getSprintStatus();

        return newSprintDTO;
    }
}
