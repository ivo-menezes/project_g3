package org.switch2022.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToControllerMapper;
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
    SprintDTOToControllerMapper toControllerMapper;
    @Autowired
    SprintServiceDDD sprintService;

    @Test
    public void ensureServiceIsInstantiated(){
        new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper);
    }
    @Test
    public void ensureServiceThrowsException(){
        //arrange
        String message = "sprintFactory cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(null, sprintRepository, toControllerMapper);
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
        SprintDTOToController toControllerDTO = new SprintDTOToController();
        //Create VO from sprintDTO:
        ProjectCode projectCode = new ProjectCode("AAA");
        sprintDTO.projectCode = projectCode;
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023),
                new Date(25 / 3 / 2023));
        toControllerDTO.sprintID = sprintID;
        toControllerDTO.timePeriod = timePeriod;
        sprintDTO.timePeriod = timePeriod;
        when(sprintRepository.findLastSprintNumber(projectCode)).thenReturn(0);
        //Create a mock sprint:
        SprintDDD sprint = mock(SprintDDD.class);
        //Mock and train a sprint factory:
        when(sprintFactory.createSprint(sprintID, timePeriod)).thenReturn(sprint);
        //Mock and train repository of sprints:
        when(sprintRepository.save(sprint)).thenReturn(sprint);
        //Mock and train mapper:
        when(toControllerMapper.convertToDTO(sprint)).thenReturn(toControllerDTO);

        //Act
        SprintDTOToController result = sprintService.createSprint(sprintDTO);

        //Assert
        assertEquals(toControllerDTO, result);
    }
    @Test
    public void ensureServiceCreatesList(){
        // arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");
        SprintDTOToController mockToController = mock(SprintDTOToController.class);
        SprintDTOToController mockToControllerTwo = mock(SprintDTOToController.class);

        List<SprintDDD> mockList = new ArrayList<>();
        mockList.add(mockSprint);
        mockList.add(mockSprintTwo);

        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);
        when(toControllerMapper.convertToDTO(mockSprint)).thenReturn(mockToController);
        when(toControllerMapper.convertToDTO(mockSprintTwo)).thenReturn(mockToControllerTwo);

        List<SprintDTOToController> expectedDDDList = new ArrayList<>();
        expectedDDDList.add(mockToController);
        expectedDDDList.add(mockToControllerTwo);

        // act
        List<SprintDTOToController> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }
    @Test
    public void ensureServiceFindsProjectButReturnsEmptyList(){
        // arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");
        SprintDTOToController mockToController = mock(SprintDTOToController.class);
        SprintDDD mockSprint = mock(SprintDDD.class);

        List<SprintDDD> mockList = new ArrayList<>();

        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);
        when(toControllerMapper.convertToDTO(mockSprint)).thenReturn(mockToController);

        List<SprintDTOToController> expectedDDDList = new ArrayList<>();

        // act
        List<SprintDTOToController> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }
}