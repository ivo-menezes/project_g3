package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTOMapper;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class SprintServiceDDDTest {

    @MockBean
    ISprintFactory sprintFactory;
    @MockBean
    ISprintRepository sprintRepository;
    @MockBean
    NewSprintDTOMapper toControllerMapper;
    @MockBean
    IProjectRepository projectRepository;
    @Autowired
    SprintServiceDDD sprintService;

    @Test
    public void ensureServiceIsInstantiated(){
        new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository);
    }
    @Test
    public void ensureServiceThrowsExceptionForFactory(){
        //arrange
        String message = "SprintFactory cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(null, sprintRepository, toControllerMapper, projectRepository);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForSprintRepository(){
        //arrange
        String message = "SprintRepository cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(sprintFactory, null, toControllerMapper, projectRepository);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForMapper(){
        //arrange
        String message = "NewSprintDTOMapper cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(sprintFactory, sprintRepository, null, projectRepository);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForProjectRepository(){
        //arrange
        String message = "ProjectRepository cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, null);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }

    @Test
    public void ensureServiceCreatesSprintSuccessfully() {
        //arrange
        //Create sprintDTO:
        NewSprintDTO sprintDTO = new NewSprintDTO();
        NewSprintDTO toControllerDTO = new NewSprintDTO();

        //Create VO from sprintDTO and toControllerDTO:

        ProjectCode projectCode = new ProjectCode("AAA");
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        SprintStatus status = SprintStatus.Planned;
        TimePeriod timePeriod = new TimePeriod(new Date(10 / 3 / 2023),
                new Date(25 / 3 / 2023));

        toControllerDTO.sprintID = new SprintID(projectCode, sprintNumber);
        toControllerDTO.timePeriod = timePeriod;
        toControllerDTO.status = status;

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = timePeriod;
        sprintDTO.sprintNumber = sprintNumber;
        sprintDTO.sprintID = sprintID;

        //Create a mock sprint:
        SprintDDD sprint = mock(SprintDDD.class);
        when(sprint.identity()).thenReturn(sprintID);
        when(sprint.getTimePeriod()).thenReturn(timePeriod);
        when(sprint.getSprintStatus()).thenReturn(status);

        //Mock and train a sprint factory:
        when(sprintFactory.newSprintID(sprintDTO.projectCode, sprintDTO.sprintNumber)).thenReturn(sprintID);
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(sprint);

        //Mock and train repository of sprints:
        when(sprintRepository.save(sprint)).thenReturn(sprint);

        //Mock and train mapper:
        when(toControllerMapper.convertToDTO(sprint)).thenReturn(toControllerDTO);

        //act
        NewSprintDTO result = sprintService.createSprint(sprintDTO);

        //assert
        assertEquals(toControllerDTO, result);
    }
    @Test
    public void ensureServiceCreatesList(){
        // arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");
        NewSprintDTO mockToController = mock(NewSprintDTO.class);
        NewSprintDTO mockToControllerTwo = mock(NewSprintDTO.class);

        List<SprintDDD> mockList = new ArrayList<>();
        mockList.add(mockSprint);
        mockList.add(mockSprintTwo);

        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);
        when(toControllerMapper.convertToDTO(mockSprint)).thenReturn(mockToController);
        when(toControllerMapper.convertToDTO(mockSprintTwo)).thenReturn(mockToControllerTwo);

        List<NewSprintDTO> expectedDDDList = new ArrayList<>();
        expectedDDDList.add(mockToController);
        expectedDDDList.add(mockToControllerTwo);

        // act
        List<NewSprintDTO> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }
    @Test
    public void ensureServiceFindsProjectButReturnsEmptyList(){
        // arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");
        NewSprintDTO mockToController = mock(NewSprintDTO.class);
        SprintDDD mockSprint = mock(SprintDDD.class);

        List<SprintDDD> mockList = new ArrayList<>();

        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);
        when(toControllerMapper.convertToDTO(mockSprint)).thenReturn(mockToController);

        List<NewSprintDTO> expectedDDDList = new ArrayList<>();

        // act
        List<NewSprintDTO> result = sprintService.sprintList(mockCode);

        // assert
        assertEquals(expectedDDDList, result);
    }
    @Test
    public void ensureServicesCreateSprintNumber(){
        //arrange
        ProjectCode mockCode = mock(ProjectCode.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        SprintDDD mockSprintThree = mock(SprintDDD.class);

        List<SprintDDD> mockList = new ArrayList<>();
        mockList.add(mockSprint);
        mockList.add(mockSprintTwo);
        mockList.add(mockSprintThree);

        when(projectRepository.existsByProjectCode(mockCode.toString())).thenReturn(true);
        when(sprintRepository.findByProjectCode(mockCode)).thenReturn(mockList);

        //act
        int result = sprintService.getNewSprintNumber(mockCode);

        //assert
        assertEquals(4, result);
    }
    @Test
    public void ensureServicesCanNotFindProject(){
        //arrange
        ProjectCode mockCode = mock(ProjectCode.class);

        when(projectRepository.existsByProjectCode(mockCode.toString())).thenReturn(false);

        String expectedMessage = "There is no project with this code.";

        //act
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            sprintService.getNewSprintNumber(mockCode);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expectedMessage, result);
    }

    @Test
    @DisplayName("Ensure that the sprint status has been successfully changed.")
    public void updateStatusSprintSuccess(){
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        SprintStatus sprintStatus = mock(SprintStatus.class);
        UpdateSprintDomainDTO sprintDomainDTO = mock(UpdateSprintDomainDTO.class);
        sprintDomainDTO.sprintID = sprintID;
        sprintDomainDTO.sprintStatus = sprintStatus;

        SprintDDD sprint = mock(SprintDDD.class);

        Optional<SprintDDD> sprintOptional = mock(Optional.class);
        when(sprintRepository.getByID(any())).thenReturn(sprintOptional);
        when(sprintOptional.isEmpty()).thenReturn(false);
        when(sprintOptional.get()).thenReturn(sprint);
        when(sprintRepository.replace(sprint)).thenReturn(sprint);

        //Act
        UpdateSprintDomainDTO result = sprintService.updateStatusSprint(sprintDomainDTO);

        //Assert
        assertEquals(sprintDomainDTO,result);
    }

    @Test
    @DisplayName("Ensure that an exception is thrown when the sprintID does not exist.")
    public void updateStatusSprintThrowsException(){
        //Arrange
        SprintStatus sprintStatus = mock(SprintStatus.class);
        UpdateSprintDomainDTO sprintDomainDTO = mock(UpdateSprintDomainDTO.class);
        sprintDomainDTO.sprintStatus = sprintStatus;

        Optional<SprintDDD> sprintOptional = mock(Optional.class);
        when(sprintRepository.getByID(any())).thenReturn(sprintOptional);
        when(sprintOptional.isEmpty()).thenReturn(true);

        String expectedMessage = "Sprint id does not exist";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->{
            sprintService.updateStatusSprint(sprintDomainDTO);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expectedMessage,result);
    }

    @Test
    @DisplayName("Ensure sprint is not created due to time period overlap with previous sprint")
    void ensureSprintOverlappingPreviousSprintThrowsException() {
        //Arrange
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        SprintDDD newSprint = mock(SprintDDD.class);
        TimePeriod newTimePeriod = mock(TimePeriod.class);

        SprintDDD lastSprint = mock(SprintDDD.class);
        TimePeriod lastTimePeriod = mock(TimePeriod.class);

        //setting up dates for mock TimePeriods
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.APRIL, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 10);
        Date firstEndDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 5);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 15);
        Date secondEndDate = calendar.getTime();

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = newTimePeriod;

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);
        when(sprintRepository.findLastSprintByProjectCode(projectCode)).thenReturn(Optional.of(lastSprint));

        //conferring behavior to startDate, endDate and timePeriod
        when(lastTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(lastTimePeriod.getEndDate()).thenReturn(firstEndDate);
        when(lastSprint.getTimePeriod()).thenReturn(lastTimePeriod);

        when(newTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(newTimePeriod.getEndDate()).thenReturn(secondEndDate);
        when(newSprint.getTimePeriod()).thenReturn(newTimePeriod);

        String expectedMessage = "The time period of the new sprint overlaps with the last sprint";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                sprintService.createSprint(sprintDTO));
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure no overlapping sprint is successfully created")
    void ensureNonOverlappingSprintIsSuccessfullyCreated() {
        //Arrange
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        NewSprintDTO sprintDTO2 = mock(NewSprintDTO.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        SprintDDD newSprint = mock(SprintDDD.class);
        TimePeriod newTimePeriod = mock(TimePeriod.class);

        SprintDDD lastSprint = mock(SprintDDD.class);
        TimePeriod lastTimePeriod = mock(TimePeriod.class);

        //setting up dates for mock TimePeriods
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.APRIL, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 10);
        Date firstEndDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 12);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 30);
        Date secondEndDate = calendar.getTime();

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = newTimePeriod;

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);
        when(sprintRepository.findLastSprintByProjectCode(projectCode)).thenReturn(Optional.of(lastSprint));

        //conferring behavior to startDate, endDate and timePeriod
        when(lastTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(lastTimePeriod.getEndDate()).thenReturn(firstEndDate);
        when(lastSprint.getTimePeriod()).thenReturn(lastTimePeriod);

        when(newTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(newTimePeriod.getEndDate()).thenReturn(secondEndDate);
        when(newSprint.getTimePeriod()).thenReturn(newTimePeriod);

        when(sprintRepository.findLastSprintByProjectCode(projectCode)).thenReturn(Optional.of(lastSprint));

        when(sprintRepository.save(newSprint)).thenReturn(newSprint);

        when(toControllerMapper.convertToDTO(newSprint)).thenReturn(sprintDTO2);

        //act
        NewSprintDTO result = sprintService.createSprint(sprintDTO);

        //assert
        assertEquals(sprintDTO2, result);
    }


}