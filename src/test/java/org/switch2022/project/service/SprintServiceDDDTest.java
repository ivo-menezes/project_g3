package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTOController;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SprintServiceDDDTest {

    @MockBean
    ISprintFactory sprintFactory;
    @MockBean
    ISprintRepository sprintRepository;
    @MockBean
    Repository<ProjectCode, ProjectDDD> projectRepository;
    @Autowired
    SprintServiceDDD sprintService;

    @Test
    public void ensureServiceIsInstantiated(){
        new SprintServiceDDD(sprintFactory, sprintRepository, projectRepository);
    }
    @Test
    public void ensureServiceThrowsException(){
        //arrange
        String message = "sprintFactory cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(null, sprintRepository, projectRepository);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceCreatesSprintSuccessfully() {
        //Arrange
        //Create sprintDTO:
        SprintDTOController sprintDTO = new SprintDTOController();
        //Create VO from sprintDTO:
        ProjectCode projectCode = new ProjectCode("AAA");
        sprintDTO.projectCode = projectCode;
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023),
                new Date(25 / 3 / 2023));
        sprintDTO.timePeriod = timePeriod;
        when(sprintRepository.findLastSprintNumber(projectCode)).thenReturn(0);
        //Mock and train repository of projects:
        ProjectDDD project = mock(ProjectDDD.class);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.of(project));
        //Create a mock sprint:
        SprintDDD sprint = mock(SprintDDD.class);
        //Mock and train a sprint factory:
        when(sprintFactory.createSprint(sprintID, timePeriod)).thenReturn(sprint);
        //Mock and train repository of sprints:
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Act
        SprintDDD result = sprintService.createSprint(sprintDTO);

        //Assert
        assertEquals(sprint, result);
    }
    @Test
    public void ensureServiceDoesNotCreatesSprintSuccessfully(){
        // arrange
        SprintDTOController mockDTO = mock(SprintDTOController.class);
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintID mockID = mock(SprintID.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        mockDTO.projectCode = mockCode;

        when(projectRepository.getByID(mockDTO.projectCode)).thenReturn(Optional.empty());
        when(sprintFactory.createSprint(mockID, mockDTO.timePeriod)).thenReturn(mockSprint);
        when(sprintRepository.save(mockSprint)).thenReturn(mockSprint);

        String message = "ProjectCode is not valid!";

        // act
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            sprintService.createSprint(mockDTO);
        });
        String result = exception.getMessage();

        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceCreatesList(){
        // arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");

        ProjectDDD mockProject = mock(ProjectDDD.class);
        Optional<ProjectDDD> mockOptional = Optional.of(mockProject);

        List<SprintDDD> mockList = new ArrayList<>();
        mockList.add(mockSprint);
        mockList.add(mockSprintTwo);

        when(projectRepository.getByID(mockCode)).thenReturn(mockOptional);
        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);

        List<SprintDDD> expectedDDDList = new ArrayList<>();
        expectedDDDList.add(mockSprint);
        expectedDDDList.add(mockSprintTwo);

        // act
        List<SprintDDD> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }
    @Test
    public void ensureServiceFindsProjectButReturnsEmptyList(){
        // arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");

        ProjectDDD mockProject = mock(ProjectDDD.class);
        Optional<ProjectDDD> mockOptional = Optional.of(mockProject);

        List<SprintDDD> mockList = new ArrayList<>();

        when(projectRepository.getByID(mockCode)).thenReturn(mockOptional);
        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);

        List<SprintDDD> expectedDDDList = new ArrayList<>();

        // act
        List<SprintDDD> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }

    @Test
    public void ensureServiceThrowsExceptionForProjectNotFound(){
        //arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");

        Optional<ProjectDDD> mockOptional = Optional.empty();

        when(projectRepository.getByID(mockCode)).thenReturn(mockOptional);

        String message = "ProjectCode is not valid!";

        // act
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            sprintService.sprintList(mockCode);
        });
        String result = exception.getMessage();

        // assert
        assertEquals(message, result);
    }
}