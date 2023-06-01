package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;

import java.util.Optional;

public interface IProjectRepository {

    ProjectDDD save (ProjectDDD project);

    Optional<ProjectDDD> getByID(ProjectCode projectCode);

    ProjectDDD replace(ProjectDDD project);
}
