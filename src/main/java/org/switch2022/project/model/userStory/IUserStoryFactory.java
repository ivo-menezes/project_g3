package org.switch2022.project.model.userStory;

import org.switch2022.project.mapper.UserStoryDTO;

public interface IUserStoryFactory {

    UserStoryDDD createUserStory(UserStoryDTO dto);
}
