package org.switch2022.project.controller;

import org.switch2022.project.model.*;

public class CreateSprintController {

    private ProjectList projectList;
    private SprintDTO sprintDTO;

    public CreateSprintController(ProjectList projectList, SprintDTO sprintDTO) {
        this.projectList = projectList;
        this.sprintDTO = sprintDTO;
    }

    /**
     * With projectCode and SprintDTO as parameters this method gets a project by is code and adds a sprint to it
     * @param projectCode
     * @param sprintDTO
     * @return boolean if sprint is added to the project
     */
    public boolean createSprint(int projectCode, SprintDTO sprintDTO) {
        if (sprintDTO == null) {
            throw new IllegalArgumentException("SprintDTO must not be null.");
        }

        Project project = projectList.getProject(projectCode);
        return project.addSprint(sprintDTO);
    }
}

