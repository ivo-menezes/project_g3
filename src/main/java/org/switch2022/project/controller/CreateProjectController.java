package org.switch2022.project.controller;

import org.switch2022.project.model.ProjectList;

public class CreateProjectController {

    private ProjectList projectList;

    /**
     * constructor that accepts existing ProjectList
     *
     * @param projectList ProjectList to be added to controller
     */

    public CreateProjectController(ProjectList projectList) {
        this.projectList = projectList;
    }

    /**
     * instructs projectlist to create new project with given code, name and description
     *
     * @param code
     * @param name
     * @param description
     * @return true if project successfully created, throw exception otherwise
     */
    public boolean createProject(int code, String name, String description) {
        return this.projectList.createProject(code, name, description);
    }
}
