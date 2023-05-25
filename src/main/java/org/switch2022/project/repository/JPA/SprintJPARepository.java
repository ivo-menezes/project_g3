package org.switch2022.project.repository.JPA;

import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.model.valueobject.SprintID;

public interface SprintJPARepository extends CrudRepository<SprintJPA, SprintID> {
}
