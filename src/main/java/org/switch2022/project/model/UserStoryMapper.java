package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class UserStoryMapper {


    /**
     * This method converts a user story in a user story DTO
     * @param userStory to be converted
     * @return user story DTO
     */
    private UserStoryDTO toDTO(UserStory userStory) {

        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = userStory.getId();
        userStoryDTO.actor = userStory.getActor();
        userStoryDTO.text = userStory.getText();
        userStoryDTO.acceptanceCriteria = userStory.getAcceptanceCriteria();
        userStoryDTO.status = userStory.getStatus();

        return userStoryDTO;
    }

    /**
     * This method accepts a userStory list which is converted in a userStoryDTO list
     * @param userStoryList list of user stories
     * @return a list of user story DTOs
     */
    public List<UserStoryDTO> toDTOList(List<UserStory> userStoryList) {

        List<UserStoryDTO> DTOList = new ArrayList<>();
        for (UserStory userStory : userStoryList){
            UserStoryDTO dto = toDTO(userStory);
            DTOList.add(dto);
        }

        return DTOList;

    }

}
