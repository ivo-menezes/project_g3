package org.switch2022.project.repository.JPA;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;

import java.util.List;

public interface SprintJPARepository extends CrudRepository<SprintJPA, SprintJpaID> {
    @Query("SELECT s FROM SprintJPA s WHERE s.sprintID.projectCode = :projectCode")
    List<SprintJPA> findAllByProjectCode(String projectCode);

}
