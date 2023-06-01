package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.model.valueobject.Role;

import java.util.Optional;

public interface ResourceRepositoryJPA extends CrudRepository<ResourceJPA, Long> {
    boolean existsByRole(String role);


}
