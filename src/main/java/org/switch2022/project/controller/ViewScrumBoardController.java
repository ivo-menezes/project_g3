package org.switch2022.project.controller;

import org.switch2022.project.model.*;

import java.util.List;

public class ViewScrumBoardController {
    private ProjectList projectList;

    public ViewScrumBoardController(ProjectList projectList) {
        this.projectList = projectList;
    }

    public List<UserStoryDTO> showScrumBoard(int projectCode, int sprintNumber){
        Project project = projectList.getProject(projectCode);
        SprintList sprintlist = project.getSprintList();
        Sprint sprint = sprintlist.getSprint(sprintNumber);

        return sprint.createScrumBoardList();
    }
}
