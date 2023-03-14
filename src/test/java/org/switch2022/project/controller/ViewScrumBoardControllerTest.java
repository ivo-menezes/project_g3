package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

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
        when(sprint.createScrumBoardList()).thenReturn(userStories);

        //act
        ViewScrumBoardController controller = new ViewScrumBoardController(projectList);
        List<UserStoryDTO> result = controller.showScrumBoard(1, 1);

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
        when(sprint.createScrumBoardList()).thenReturn(userStories);

        //act

        ViewScrumBoardController controller = new ViewScrumBoardController(projectList);
        List<UserStoryDTO> result = controller.showScrumBoard(1, 1);

        //assert
        assertFalse(result.isEmpty());
    }
}