package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.model.*;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.sprint.Sprint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewScrumBoardControllerTest {
    @Test
    @DisplayName("Test if Scrum Board method works")
    void testTheShowScrumBoardMethod(){
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        SprintList sprintList = mock(SprintList.class);
        Project project = mock(Project.class);
        Sprint sprint = mock(Sprint.class);

        when(projectList.getProject(1)).thenReturn(project);
        when(project.getSprintList()).thenReturn(sprintList);
        when(sprintList.getSprint(1)).thenReturn(sprint);
        List<UserStoryDTO> userStories = new ArrayList<>();
        when(sprint.viewScrumBoardList()).thenReturn(userStories);
        ViewScrumBoardController controller = new ViewScrumBoardController(projectList);

        //act
        List<UserStoryDTO> result = controller.viewScrumBoard(1, 1);

        //assert
        assertEquals(userStories, result);
    }

    @Test
    @DisplayName("Ensure it catches empty lists")
    void checkForNotEmptyLists(){
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        SprintList sprintList = mock(SprintList.class);
        Project project = mock(Project.class);
        Sprint sprint = mock(Sprint.class);
        List<UserStoryDTO> userStories = new ArrayList<>();
        userStories.add(new UserStoryDTO());

        when(projectList.getProject(1)).thenReturn(project);
        when(project.getSprintList()).thenReturn(sprintList);
        when(sprintList.getSprint(1)).thenReturn(sprint);
        when(sprint.viewScrumBoardList()).thenReturn(userStories);
        ViewScrumBoardController controller = new ViewScrumBoardController(projectList);

        //act

        List<UserStoryDTO> result = controller.viewScrumBoard(1, 1);

        //assert
        assertFalse(result.isEmpty());
    }
}