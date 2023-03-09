package org.switch2022.project.controller;

import org.switch2022.project.model.*;

public class CreateSprintController {

    private ProjectList projectList;
    private SprintDTO sprintDTO;

    public CreateSprintController(ProjectList projectList, SprintDTO sprintDTO) {
        this.projectList = projectList;
        this.sprintDTO = sprintDTO;
    }

    public boolean createSprint(int projectCode, SprintDTO sprintDTO) {
        if (sprintDTO == null) {
            throw new IllegalArgumentException("SprintDTO must not be null.");
        }

        Project project = projectList.getProject(projectCode);
        return project.addSprint(sprintDTO);
    }
}

