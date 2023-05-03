package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.ProjectDTO_DDD;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.repository.ProjectRepository;
import org.switch2022.project.service.ProjectService;
import org.switch2022.project.service.UserStoryService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateProjectControllerDDDTest {

    @DisplayName("creating a controller with null ProjectService throws exception")
    @Test
    void createControllerWithNullProjectServiceThrowsException() {
        // Arrange
        String expectedMessage = "ProjectService must not be null.";
        ProjectService service = null;
        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CreateProjectControllerDDD(service);
        });
        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating a controller succeeds")
    @Test
    void createControllerSucceeds() {
        // Arrange
        ProjectService serviceDouble = mock(ProjectService.class);
        // Act
        CreateProjectControllerDDD controller = new CreateProjectControllerDDD(serviceDouble);
        // Assert
        assertInstanceOf(CreateProjectControllerDDD.class, controller);
    }

    @DisplayName("assert that creating a project succeeds")
    @Test
    void createProjectSuccess() {
        // arrange
        ProjectDTO_DDD projectDTODouble = mock(ProjectDTO_DDD.class);
        ProjectService service = mock(ProjectService.class);
        when(service.createProject(projectDTODouble)).thenReturn(true);
        CreateProjectControllerDDD controller = new CreateProjectControllerDDD(service);

        // act
        boolean result = controller.createProject(projectDTODouble);

        // assert
        assertTrue(result);
    }

    @DisplayName("creating a project with null projectDTO throws exception")
    @Test
    void createProjectNullProjectDTOThrowsException() {
        // arrange
        ProjectDTO_DDD projectDTODouble = null;
        ProjectService service = mock(ProjectService.class);

        String expectedMessage = "ProjectDTO must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CreateProjectControllerDDD(service).createProject(projectDTODouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
}