package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.userStory.UserStoryDDD;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewUserStoryInfoDTOMapper {

    public NewUserStoryInfoDTO toDto(UserStoryDDD userStory) {

        NewUserStoryInfoDTO dto = new NewUserStoryInfoDTO();
        dto.userStoryNumber = userStory.identity().getUserStoryNumber();
        dto.projectCode = userStory.identity().getProjectCode();
        dto.actor = userStory.getActor();
        dto.description = userStory.getDescription();
        dto.acceptanceCriteria = userStory.getAcceptanceCriteria();
        dto.status = userStory.getStatus();

        return dto;
    }

    public List<NewUserStoryInfoDTO> toDtoList(List<UserStoryDDD> userStoryList) {

        List<NewUserStoryInfoDTO> dtoList = new ArrayList<>();

        for (UserStoryDDD userStory : userStoryList) {
            NewUserStoryInfoDTO dto = toDto(userStory);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
