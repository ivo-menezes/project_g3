package org.switch2022.project.model.userStory;

import org.switch2022.project.model.valueobject.*;

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

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, actor, description, acceptanceCriteria);

        return userStory;
    }
}
