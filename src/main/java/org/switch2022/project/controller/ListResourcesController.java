package org.switch2022.project.controller;

import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.model.ResourceDTO;

import java.util.List;

public class ListResourcesController {
    private final ProjectList projectList;

    /** constructor that accepts existing ProjectList
     *
     * @param projectList ProjectList to be added to controller
     */
    public ListResourcesController(ProjectList projectList) {
        if(projectList.listSize()<=0) {
            throw new IllegalArgumentException("List is empty");
        }
        this.projectList = projectList;
    }


    public List<ResourceDTO> listResources(int projectCode) {
        Project project = projectList.getProject(projectCode);
        return project.listResources();
    }
}
