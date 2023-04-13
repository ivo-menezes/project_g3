package org.switch2022.project.model.userStory;

import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.valueobject.Description;
import org.switch2022.project.model.valueobject.UserStoryAcceptanceCriteria;
import org.switch2022.project.model.valueobject.UserStoryActor;
import org.switch2022.project.model.valueobject.UserStoryID;

public class UserStoryFactoryImpl implements IUserStoryFactory {

    @Override
    public UserStoryDDD createUserStory(UserStoryDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("UserStoryDTO must not be null.");
        }

        UserStoryID id = new UserStoryID(dto.id);
        UserStoryActor actor = new UserStoryActor(dto.actor);
        Description description = new Description(dto.text);
        UserStoryAcceptanceCriteria criteria = new UserStoryAcceptanceCriteria(dto.acceptanceCriteria);

        return new UserStoryDDD(id, actor, description, criteria);
    }
}
