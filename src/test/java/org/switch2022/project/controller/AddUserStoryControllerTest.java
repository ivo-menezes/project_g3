package org.switch2022.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.userStory.UserStory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddUserStoryControllerTest {

    @Test
    @DisplayName("ensure creating controller with null project list throws exception")
    void createControllerWithNullProjectListThrowsException() {
        // arrange
        String expected = "Project List must not be null";
        ProjectList projectList = null;
        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new AddUserStoryController(projectList));
        String resultMessage = result.getMessage();
        // assert
        assertEquals(expected, resultMessage);
    }

    @DisplayName("ensure creating controller succeeds")
    @Test
    void createControllerSucceeds() {
        // Arrange
        ProjectList projectList = mock(ProjectList.class);
        // Act
        AddUserStoryController controller = new AddUserStoryController(projectList);
        // Assert
        assertInstanceOf(AddUserStoryController.class, controller);
    }


    @Test
    @DisplayName("ensure user story is added from product backlog to sprint backlog")
    void ensureUserStoryIsAddedFromProductBacklogToSprintBacklog() {
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        AddUserStoryController addUserStoryController = new AddUserStoryController(projectList);

        Project project = mock(Project.class);
        when(projectList.getProject(123)).thenReturn(project);

        ProductBacklog productBacklog = mock(ProductBacklog.class);
        when(project.getProductBacklog()).thenReturn(productBacklog);

        SprintList sprintList = mock(SprintList.class);
        when(project.getSprintList()).thenReturn(sprintList);

        UserStory userStory2 = mock(UserStory.class);
        when(productBacklog.getUserStory("2")).thenReturn(userStory2);

        Sprint sprint = new Sprint(1, new Date(1 - 2 -2023), new Date(15- 2 -2023));
        when(sprintList.getSprint(1)).thenReturn(sprint);

        boolean expected = true;
        //act
        boolean result = addUserStoryController.addUserStoryFromProductBacklogToSprintBacklog(123, "2", 1);
        //assert
        assertEquals(expected,result);

    }

    @Test
    @DisplayName("ensure user story already in sprint backlog cannot be added")
    void ensureUserStoryAlreadyInSprintBacklogCannotBeAdded() {
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        AddUserStoryController addUserStoryController = new AddUserStoryController(projectList);

        Project project = mock(Project.class);
        when(projectList.getProject(123)).thenReturn(project);

        ProductBacklog productBacklog = mock(ProductBacklog.class);
        when(project.getProductBacklog()).thenReturn(productBacklog);

        SprintList sprintList = mock(SprintList.class);
        when(project.getSprintList()).thenReturn(sprintList);

        UserStory userStory2 = mock(UserStory.class);
        when(productBacklog.getUserStory("2")).thenReturn(userStory2);

        Sprint sprint = new Sprint(1, new Date(1-2-2023), new Date(15-2-2023));
        when(sprintList.getSprint(1)).thenReturn(sprint);

        sprint.addUserStoryToSprintBacklog(userStory2);

        boolean expected = false;
        //act
        boolean result = addUserStoryController.addUserStoryFromProductBacklogToSprintBacklog(123, "2", 1);
        //assert
        assertEquals(expected,result);
    }

    @Test
    @DisplayName("ensure exception is thrown if user story is not found")
    void ensureExceptionIsThrownIfUserStoryNotFound() {
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        AddUserStoryController addUserStoryController = new AddUserStoryController(projectList);

        Project project = mock(Project.class);
        when(projectList.getProject(123)).thenReturn(project);

        ProductBacklog productBacklog = mock(ProductBacklog.class);
        when(project.getProductBacklog()).thenReturn(productBacklog);

        when(productBacklog.getUserStory("2")).thenReturn(null);

        SprintList sprintList = mock(SprintList.class);
        when(project.getSprintList()).thenReturn(sprintList);

        Sprint sprint = new Sprint(1, new Date(1-2-2023), new Date(15-2-2023));
        when(sprintList.getSprint(1)).thenReturn(sprint);

        //act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addUserStoryController.addUserStoryFromProductBacklogToSprintBacklog(123, "2", 1));
    }

    @Test
    @DisplayName("ensure exception is thrown if sprint is not found")
    void ensureExceptionIsThrownIfSprintIsNotFound() {
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        AddUserStoryController addUserStoryController = new AddUserStoryController(projectList);

        Project project = mock(Project.class);
        when(projectList.getProject(123)).thenReturn(project);

        ProductBacklog productBacklog = mock(ProductBacklog.class);
        when(project.getProductBacklog()).thenReturn(productBacklog);

        UserStory userStory2 = mock(UserStory.class);
        when(productBacklog.getUserStory("2")).thenReturn(userStory2);

        SprintList sprintList = mock(SprintList.class);
        when(project.getSprintList()).thenReturn(sprintList);

        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> addUserStoryController.addUserStoryFromProductBacklogToSprintBacklog(123, "2", 1));
    }

    @Test
    @DisplayName("ensure exception is thrown if trying to add user story in non-existent project")
    void ensureUserStoryInNonExistentProjectCannotBeAdded() {
        //arrange
        ProjectList projectList = mock(ProjectList.class);
        AddUserStoryController addUserStoryController = new AddUserStoryController(projectList);

        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> addUserStoryController.addUserStoryFromProductBacklogToSprintBacklog(123, "2", 1));
    }

}