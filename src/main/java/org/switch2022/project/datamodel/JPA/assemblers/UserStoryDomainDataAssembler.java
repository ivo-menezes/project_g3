package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.model.userStory.UserStoryDDD;

@Component
public class UserStoryDomainDataAssembler {

    public UserStoryJpa toData(UserStoryDDD userStory) {
        return new UserStoryJpa(
                userStory.identity(),
                userStory.getActor(),
                userStory.getDescription(),
                userStory.getAcceptanceCriteria(),
                userStory.getStatus());
    }

    public UserStoryDDD toDomain(UserStoryJpa userStoryJpa) {
        return new UserStoryDDD(
                userStoryJpa.getUserStoryID(),
                userStoryJpa.getUserStoryActor(),
                userStoryJpa.getUserStoryDescription(),
                userStoryJpa.getUserStoryAcceptanceCriteria());
    }
}
