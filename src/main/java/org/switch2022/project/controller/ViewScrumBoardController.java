package org.switch2022.project.controller;

import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.*;

import java.util.List;

/***
 *
 */
public class ViewScrumBoardController {
    final private ProjectList projectList;

    public ViewScrumBoardController(ProjectList projectList) {
        this.projectList = projectList;
    }

    /***
     *The method will retrieve the project and the sprint from the respective project and provide a list
     * of UserStoryDTOs containing only the ID and status of each, which should be graphically
     * represented in the UI layer.
     * @param projectCode of the project we wish to view the Scrum Board
     * @param sprintNumber of the Sprint we wish to view the Scrum Board
     * @return list of UserStoryDTOs containing only the ID and Status.
     */
    public List<UserStoryDTO> viewScrumBoard(int projectCode, int sprintNumber){
        Project project = projectList.getProject(projectCode);
        SprintList sprintlist = project.getSprintList();
        Sprint sprint = sprintlist.getSprint(sprintNumber);

        return sprint.viewScrumBoardList();
    }
}
