package org.switch2022.project.service.irepositories;

import org.switch2022.project.ddd.RepositoryNew;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.List;
import java.util.Optional;

public interface ISprintRepository extends RepositoryNew<SprintID, SprintDDD> {

    List<SprintDDD> findByProjectCode(ProjectCode projectCode);

    SprintDDD replace(SprintDDD sprint);

    Optional<SprintDDD> findLastSprintByProjectCode(ProjectCode projectCode);

    Optional<SprintDDD> findSprintBySprintID(SprintID sprintID);
}
