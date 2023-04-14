package org.switch2022.project.model.userStory;

import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.valueobject.ProjectCode;

public interface IUserStoryFactory {

    UserStoryDDD createUserStory(UserStoryDTO dto, ProjectCode projectCode);
}
