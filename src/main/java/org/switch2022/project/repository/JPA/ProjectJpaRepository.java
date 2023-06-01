package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.ProjectJpa;

public interface ProjectJpaRepository extends CrudRepository<ProjectJpa, String> {
}
