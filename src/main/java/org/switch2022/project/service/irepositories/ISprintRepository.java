package org.switch2022.project.service.irepositories;

import org.switch2022.project.ddd.RepositoryNew;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.List;

public interface ISprintRepository extends RepositoryNew<SprintID, SprintDDD> {

    List<SprintDDD> findByProjectCode(ProjectCode projectCode);
}
