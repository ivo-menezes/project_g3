package org.switch2022.project.model.userStory;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.model.valueobject.Description;
import org.switch2022.project.model.valueobject.UserStoryAcceptanceCriteria;
import org.switch2022.project.model.valueobject.UserStoryActor;
import org.switch2022.project.model.valueobject.UserStoryID;

@Component
public class UserStoryFactoryImpl implements IUserStoryFactory {

    @Override
    public UserStoryDDD createUserStory(UserStoryID userStoryID,
                                        UserStoryActor actor,
                                        Description description,
                                        UserStoryAcceptanceCriteria acceptanceCriteria) {

        if (userStoryID == null) {
            throw new IllegalArgumentException("userStoryID cannot be null");
        }

        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }

        if (acceptanceCriteria == null) {
            throw new IllegalArgumentException("acceptanceCriteria cannot be null");
        }

        return new UserStoryDDD(userStoryID, actor, description, acceptanceCriteria);
    }

    @Override
    public UserStoryDDD createUserStory(NewUserStoryInfoDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("dto must not be null");
        }

        UserStoryID id = new UserStoryID(dto.userStoryNumber, dto.projectCode);
        return new UserStoryDDD(id, dto.actor, dto.description, dto.acceptanceCriteria);
    }
}
