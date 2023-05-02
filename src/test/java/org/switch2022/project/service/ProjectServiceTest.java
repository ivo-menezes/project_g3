package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO_DDD;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.repository.ProjectRepository;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @DisplayName("assert that creating a ProjectService with null ProjectFactory throws Exception")
    @Test
    void createProjectNullFactoryThrowsException() {
        // arrange
        IProjectFactory factory = null;
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);

        String expectedMessage = "projectFactory must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(factory,projectRepositoryDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a ProjectService with null ProjectRepository throws Exception")
    @Test
    void createProjectNullRepositoryThrowsException() {
        // arrange
        IProjectFactory factoryDouble = mock(IProjectFactory.class);
        Repository<ProjectCode, ProjectDDD> projectRepository = null;

        String expectedMessage = "projectRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(factoryDouble, projectRepository);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a project succeeds")
    @Test
    void createProjectSuccess() {
        // arrange
        ProjectDTO_DDD projectDTODouble = mock(ProjectDTO_DDD.class);
        projectDTODouble.code="Project A";
        projectDTODouble.name="XPTO";
        projectDTODouble.description="bla bla";
        projectDTODouble.StartDate= new Date(10/03/2023);
        projectDTODouble.endDate= new Date(20/04/2024);
        projectDTODouble.budget= 1235.00f;
        projectDTODouble.sprintDuration= 2;
        projectDTODouble.numberOfPlannedSprints=7;

        ProjectDDD project = mock(ProjectDDD.class);

        IProjectFactory factory = mock(IProjectFactory.class);
        when(factory.createProject(any(), any(), any(), any(),any(),any(),any(),any())).thenReturn(project);

        ProjectRepository repository = mock(ProjectRepository.class);
        when(repository.save(project)).thenReturn(true);

        ProjectService service = new ProjectService(factory, repository);

        // act
        boolean result = service.createProject(projectDTODouble);

        // assert
        assertTrue(result);
    }

}