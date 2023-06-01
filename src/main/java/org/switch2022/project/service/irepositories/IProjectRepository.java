package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.project.ProjectDDD;

public interface IProjectRepository {

    ProjectDDD save (ProjectDDD project);
}
