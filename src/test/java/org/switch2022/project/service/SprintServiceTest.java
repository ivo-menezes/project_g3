package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class SprintServiceTest {

    @Test
    @DisplayName("ensure that creating SprintService with null SprintFactory throws Exception")
    void createSprintServiceNullFactoryThrowsException() {
        //Arrange
        ISprintFactory factory = null;
        Repository<ProjectCode, ProjectDDD> projectRepository = mock(Repository.class);
        Repository<SprintID, SprintDDD> sprintRepository = mock(Repository.class);

        String expectedMessage = "sprintFactory cannot be null.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new SprintService(factory, projectRepository, sprintRepository);
        });

        String resultMessage = result.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that creating SprintService with null ProjectRepository throws Exception")
    void createSprintServiceNullProjRepositoryThrowsException() {
        //Arrange
        ISprintFactory factory = mock(ISprintFactory.class);
        Repository<ProjectCode, ProjectDDD> projectRepository = null;
        Repository<SprintID, SprintDDD> sprintRepository = mock(Repository.class);

        String expectedMessage = "projectRepository cannot be null.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new SprintService(factory, projectRepository, sprintRepository);
        });

        String resultMessage = result.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that creating SprintService with null SprintRepository throws Exception")
    void createSprintServiceNullSprintRepositoryThrowsException() {
        //Arrange
        ISprintFactory factory = mock(ISprintFactory.class);
        Repository<ProjectCode, ProjectDDD> projectRepository = mock(Repository.class);
        Repository<SprintID, SprintDDD> sprintRepository = null;

        String expectedMessage = "sprintRepository cannot be null.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new SprintService(factory, projectRepository, sprintRepository);
        });

        String resultMessage = result.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}