package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.sprint.AssembledUS;
@Component

public class NewAssembledUSDTOMapper {

    public NewAssembledUSDTO toDto (AssembledUS assembledUS) {

        NewAssembledUSDTO dto = new NewAssembledUSDTO();
        dto.userStoryNumber = assembledUS.getUserStoryNumber();
        dto.projectCode = assembledUS.getProjectCode();
        dto.sprintNumber = assembledUS.getSprintNumber();
        dto.userStoryActor = assembledUS.getUserStoryActor();
        dto.userStoryDescription = assembledUS.getUserStoryDescription();
        dto.userStoryAcceptanceCriteria = assembledUS.getUserStoryAcceptanceCriteria();
        dto.userStoryStatus = assembledUS.getUserStoryStatus();
        dto.userStoryEffortEstimate = assembledUS.getUserStoryEffortEstimate();

        return dto;

    }



}
