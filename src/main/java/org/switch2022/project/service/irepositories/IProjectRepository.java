package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {

    ProjectDDD save (ProjectDDD project);
    boolean existsByProjectCode(String projectCode);

    Optional<ProjectDDD> getByID(ProjectCode projectCode);

    ProjectDDD replace(ProjectDDD project);

    List<ProjectDDD> getAllProjects();
}
