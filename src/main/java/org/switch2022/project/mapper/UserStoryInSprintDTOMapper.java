package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.sprint.UserStoryInSprint;

@Component
public class UserStoryInSprintDTOMapper {
    public UserStoryInSprintDTO toDto(UserStoryInSprint userStoryInSprint) {

        UserStoryInSprintDTO userStoryInSprintDTO = new UserStoryInSprintDTO();
        userStoryInSprintDTO.userStoryInSprintID = userStoryInSprint.identity();
        userStoryInSprintDTO.userStoryEffortEstimate = userStoryInSprint.getUserStoryEffortEstimate();
        userStoryInSprintDTO.userStoryStatus = userStoryInSprint.getUserStoryInSprintStatus();

        return userStoryInSprintDTO;
    }
}
