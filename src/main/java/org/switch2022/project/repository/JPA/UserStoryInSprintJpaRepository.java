package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintIDJpa;

import java.util.Optional;

public interface UserStoryInSprintJpaRepository extends CrudRepository<UserStoryInSprintJPA, UserStoryInSprintIDJpa> {
}
