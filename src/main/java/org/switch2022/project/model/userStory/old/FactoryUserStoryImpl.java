package org.switch2022.project.model.userStory.old;

public class FactoryUserStoryImpl implements IFactoryUserStory {
    @Override
    public UserStory createUserStory(String id, String actor, String text, String acceptanceCriteria) {
        return new UserStory(id, actor, text, acceptanceCriteria);
    }
}
