package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTO_DDD;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    @DisplayName("assert that creating a sprint with total isolation succeeds")
    void assertCreatingSprintSucceeds() {
        //Arrange
        //Create sprintDTO:
        SprintDTO_DDD sprintDTO = new SprintDTO_DDD();
        //Create VO from sprintDTO:
        ProjectCode projectCode = new ProjectCode(sprintDTO.projectCode = "AAA");
        SprintNumber sprintNumber = new SprintNumber(sprintDTO.sprintNumber = 1);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        TimePeriod timePeriod = new TimePeriod(sprintDTO.startDate = new Date(10 / 3 / 2023),
                sprintDTO.endDate = new Date(25 / 3 / 2023));
        //Mock and train repository of projects:
        Repository<ProjectCode, ProjectDDD> projectRepository = mock(Repository.class);
        ProjectDDD project = mock(ProjectDDD.class);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.of(project));
        //Create a mock sprint:
        SprintDDD sprint = mock(SprintDDD.class);
        //Mock and train a sprint factory:
        ISprintFactory factory = mock(ISprintFactory.class);
        when(factory.createSprint(sprintID, timePeriod)).thenReturn(sprint);
        //Mock and train repository of sprints:
        Repository<SprintID, SprintDDD> sprintRepository = mock(Repository.class);
        when(sprintRepository.save(sprint)).thenReturn(true);
        //Create real sprintService:
        SprintService sprintService = new SprintService(factory, projectRepository, sprintRepository);

        //Act
        boolean result = sprintService.createSprint(sprintDTO);

        //Assert
        assertTrue(result);

    }
}