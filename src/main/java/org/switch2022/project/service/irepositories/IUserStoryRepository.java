package org.switch2022.project.service.irepositories;

import org.switch2022.project.ddd.RepositoryNew;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.UserStoryID;

/**
 * Interface for operations on a repository of UserStory entities
 */
public interface IUserStoryRepository extends RepositoryNew<UserStoryID, UserStoryDDD> {
}
