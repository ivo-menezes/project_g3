package org.switch2022.project.controller;

import org.switch2022.project.model.*;
import org.switch2022.project.model.userStory.UserStory;

public class AddUserStoryController {

    private final ProjectList projectList;

    /**
     * Constructor for Add User Story Controller.
     * @param projectList lists all the projects.
     */
    public AddUserStoryController(ProjectList projectList) {
        if (projectList == null) {
            throw new IllegalArgumentException("Project List must not be null");
        }
        this.projectList = projectList;
    }

    /**
     * Adds user story from product backlog to sprint backlog.
     * @param projectCode is the ID of the project;
     * @param userStoryID is the ID for the user story to be added;
     * @param sprintNumber is the ID of the sprint to which the sprint backlog belongs.
     * @return boolean true if operation succeded; return false otherwise.
     */
    public boolean addUserStoryFromProductBacklogToSprintBacklog(int projectCode, String userStoryID, int sprintNumber) {
        boolean added;

        Project project = projectList.getProject(projectCode);
        ProductBacklog productBacklog = project.getProductBacklog();
        SprintList sprintList = project.getSprintList();
        UserStory userStory = productBacklog.getUserStory(userStoryID);
        Sprint sprint = sprintList.getSprint(sprintNumber);

        added = sprint.addUserStoryToSprintBacklog(userStory);

        return added;
    }

}
