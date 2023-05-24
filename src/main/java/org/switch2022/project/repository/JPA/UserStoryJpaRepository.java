package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.model.valueobject.UserStoryID;

public interface UserStoryJpaRepository extends CrudRepository<UserStoryJpa, UserStoryID> {
}
