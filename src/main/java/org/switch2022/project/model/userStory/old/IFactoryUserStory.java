package org.switch2022.project.model.userStory.old;

public interface IFactoryUserStory {

    UserStory createUserStory(String id, String actor, String text, String acceptanceCriteria);
}
