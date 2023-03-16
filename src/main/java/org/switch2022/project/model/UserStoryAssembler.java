package org.switch2022.project.model;

public class UserStoryAssembler {

    public UserStoryDTO toDTO(UserStory userStory) {
        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id=userStory.getId();
        userStoryDTO.status=userStory.getStatus();
        return userStoryDTO;
    }
}
