package org.switch2022.project.controller;

import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.mapper.ResourceDTO;

import java.util.List;

public class ListResourcesController {
    private final ProjectList projectList;

    /** constructor that accepts existing ProjectList
     *
     * @param projectList ProjectList to be added to controller
     */
    public ListResourcesController(ProjectList projectList) {
        this.projectList = projectList;
    }


    public List<ResourceDTO> listResources(int projectCode) {
        Project project = projectList.getProject(projectCode);
        return project.listResources();
    }
}
