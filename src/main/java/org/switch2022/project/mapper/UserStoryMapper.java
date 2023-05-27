package org.switch2022.project.mapper;

import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.model.userStory.old.IFactoryUserStory;
import org.switch2022.project.model.userStory.old.UserStory;

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

    /**
     * Creates a User Story from a UserStoryDTO.
     *
     * @param dto containing user story data
     * @param factory that creates UserStory objects
     * @return a new instance of UserStory with the appropriate data
     */
    public UserStory fromDTO(UserStoryDTO dto, IFactoryUserStory factory) {

        return factory.createUserStory(dto.id,
                dto.actor,
                dto.text,
                dto.acceptanceCriteria);
    }

}
