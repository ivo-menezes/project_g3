package org.switch2022.project.mapper;

import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.model.userStory.old.UserStory;

public class UserStoryScrumBoardMapper {

    public UserStoryDTO toDTO(UserStory userStory) {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id=userStory.getId();
        userStoryDTO.status=userStory.getStatus();
        return userStoryDTO;
    }
}
