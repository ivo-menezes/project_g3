package org.switch2022.project.repository.JPA;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.List;

public interface SprintJPARepository extends CrudRepository<SprintJPA, SprintID> {
    @Query("SELECT MAX(s.sprintID.sprintNumber) FROM SprintJPA s WHERE s.sprintID.projectCode = :projectCode")
    Integer findMaxSprintNumberByProjectCode(@Param("projectCode") String projectCode);

    @Query("SELECT s FROM SprintJPA s WHERE s.sprintID.projectCode = :projectCode")
    List<SprintJPA> findAllByProjectCode(String projectCode);
}
