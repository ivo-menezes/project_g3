package org.switch2022.project.controller;

import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.mapper.UserStoryDTO;

public class CreateUserStoryController {
    private final ProjectList projectList;

    public CreateUserStoryController(ProjectList projectList) {
        if (projectList == null) {
            throw new IllegalArgumentException("Project List must not be null");
        }
        this.projectList = projectList;
    }

    public boolean createUserStory(int projectCode, UserStoryDTO userStoryDTO, int priority) {
        if (userStoryDTO == null) {
            throw new IllegalArgumentException("UserStoryDTO must not be null");
        }

        Project project = projectList.getProject(projectCode);
        ProductBacklog productBacklog = project.getProductBacklog();
        return productBacklog.createAndAddUserStory(userStoryDTO, priority);
    }
}
