package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.userStory.UserStoryDDD;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStoryMapperDDD {

    /**
     * Converts a user story in a user story DTO
     * @param userStory to be converted to a DTO
     * @return user story DTO named <code>UserStoryDTOForListDDD</code> dto disambiguate from the already existing <code>UserStoryDTO</code>
     */
   private UserStoryDTOForListDDD toDTO(UserStoryDDD userStory) {

       UserStoryDTOForListDDD userStoryDTO = new UserStoryDTOForListDDD();
       userStoryDTO.id = userStory.identity();
       userStoryDTO.actor = userStory.getActor();
       userStoryDTO.description = userStory.getDescription();
       userStoryDTO.acceptanceCriteria = userStory.getAcceptanceCriteria();
       userStoryDTO.status = userStory.getStatus();

       return userStoryDTO;
   }

    /**
     * Accepts a list of userStories which are converted in userStoryDTOs (<code>UserStoryDTOForList</code>) and added to a new list (<code>productBacklog</code>)
     * @param openUserStoryList list of user stories not completed nor canceled
     * @return a list of user story DTOs which is the productBacklog
     */

   public List<UserStoryDTOForListDDD> toDTOList (List<UserStoryDDD> openUserStoryList) {

       List<UserStoryDTOForListDDD> productBacklog = new ArrayList<>();
       for(UserStoryDDD userStory : openUserStoryList){
           UserStoryDTOForListDDD dto = toDTO(userStory);
           productBacklog.add(dto);
       }
       return productBacklog;
   }
}
