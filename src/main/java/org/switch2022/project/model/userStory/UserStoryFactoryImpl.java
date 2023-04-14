package org.switch2022.project.model.userStory;

import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.valueobject.*;

public class UserStoryFactoryImpl implements IUserStoryFactory {

    @Override
    public UserStoryDDD createUserStory(UserStoryDTO dto, ProjectCode projectCode) {

        if (dto == null) {
            throw new IllegalArgumentException("UserStoryDTO must not be null.");
        }

        UserStoryID id = new UserStoryID(dto.id);
        UserStoryActor actor = new UserStoryActor(dto.actor);
        Description description = new Description(dto.text);
        UserStoryAcceptanceCriteria criteria = new UserStoryAcceptanceCriteria(dto.acceptanceCriteria);

        return new UserStoryDDD(projectCode, id, actor, description, criteria);
    }
}
