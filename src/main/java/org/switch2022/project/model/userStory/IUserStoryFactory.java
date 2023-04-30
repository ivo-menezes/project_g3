package org.switch2022.project.model.userStory;

import org.switch2022.project.model.valueobject.*;

public interface IUserStoryFactory {

    UserStoryDDD createUserStory(UserStoryID userStoryID,
                                 UserStoryActor actor,
                                 Description description,
                                 UserStoryAcceptanceCriteria acceptanceCriteria);
}
