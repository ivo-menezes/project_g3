package org.switch2022.project.controller;

import org.switch2022.project.model.*;

public class EstimateEffortController {
    private final ProjectList projectList;

    public EstimateEffortController (ProjectList projectList) {
        if (projectList == null) {
            throw new IllegalArgumentException("Project List must not be null");
        }
        this.projectList = projectList;
    }

    public boolean estimateEffortUserStory (int projectCode, int sprintNumber, String userStoryID, double effort) {
        Project project = projectList.getProject(projectCode);
        ProductBacklog productBacklog = project.getProductBacklog();
        UserStory userStory = productBacklog.getUserStory(userStoryID);
        SprintList sprintList = project.getSprintList();
        Sprint sprint = sprintList.getSprint(sprintNumber);

        return sprint.estimateEffortForUserStory(userStory, effort);}
}
