package org.switch2022.project.model.userStory;

import org.switch2022.project.model.userStory.UserStory;

public interface IFactoryUserStory {

    UserStory createUserStory(String id, String actor, String text, String acceptanceCriteria);
}
