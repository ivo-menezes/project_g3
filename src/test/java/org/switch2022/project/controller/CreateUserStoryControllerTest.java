package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.mapper.UserStoryDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateUserStoryControllerTest {

    @DisplayName("creating a controller with null project list throws exception")
    @Test
    void createControllerWithNullProjectListThrowsException() {
        // Arrange
        String expectedMessage = "Project List must not be null";
        ProjectList projectList = null;
        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CreateUserStoryController(projectList);
        });
        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating a controller succeeds")
    @Test
    void createControllerSucceeds() {
        // Arrange
        ProjectList projectListDouble = mock(ProjectList.class);
        // Act
        CreateUserStoryController controller = new CreateUserStoryController(projectListDouble);
        // Assert
        assertInstanceOf(CreateUserStoryController.class, controller);
    }

    @DisplayName("create and add user story succeeds")
    @Test
    void createUserStorySucceedsWithIsolation() {
        // Arrange
        // create all necessary mock objects
        ProjectList projectListDouble = mock(ProjectList.class);
        Project projectDouble = mock(Project.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        // train the mock objects
        when(projectListDouble.getProject(1)).thenReturn(projectDouble);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
        when(productBacklogDouble.createAndAddUserStory(userStoryDTODouble, 0)).thenReturn(true);
        // create a new (real) controller
        CreateUserStoryController controller = new CreateUserStoryController(projectListDouble);

        // Act
        boolean result = controller.createUserStory(1, userStoryDTODouble, 0);

        // Assert
        assertTrue(result);
    }

    @DisplayName("create and add user story fails")
    @Test
    void createUserStoryFailsWithIsolation() {
        // Arrange
        // create all necessary mock objects
        ProjectList projectListDouble = mock(ProjectList.class);
        Project projectDouble = mock(Project.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        // train the mock objects
        when(projectListDouble.getProject(1)).thenReturn(projectDouble);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
        when(productBacklogDouble.createAndAddUserStory(userStoryDTODouble, 0)).thenReturn(false);
        // create a new (real) controller
        CreateUserStoryController controller = new CreateUserStoryController(projectListDouble);

        // Act
        boolean result = controller.createUserStory(1, userStoryDTODouble, 0);

        // Assert
        assertFalse(result);
    }

    @DisplayName("create and add user story from null DTO throws exception")
    @Test
    void createUserStoryWithNullDTOThrowsException() {
        // Arrange
        // create all necessary mock objects
        UserStoryDTO dto = null;

        ProjectList projectListDouble = mock(ProjectList.class);

        CreateUserStoryController controller = new CreateUserStoryController(projectListDouble);

        String expectedMessage = "UserStoryDTO must not be null";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            controller.createUserStory(1, dto, 0);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }
}