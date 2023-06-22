package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.*;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTOMapper;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.sprint.AssembledUS;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ISprintRepository;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @MockBean
    IUserStoryRepository userStoryRepository;
    @MockBean
    UserStoryInSprintDTOMapper userStoryInSprintDTOMapper;
    @MockBean
    AssembledUsAssembler assembledUsAssembler;
    @MockBean
    NewAssembledUSDTOMapper newAssembledUSDTOMapper;
    @Autowired
    SprintServiceDDD sprintService;

    @Test
    public void ensureServiceIsInstantiated(){
        assertInstanceOf(SprintServiceDDD.class,new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository, userStoryRepository,userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
    }
    @Test
    public void ensureServiceThrowsExceptionForFactory(){
        //arrange
        String message = "SprintFactory cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            new SprintServiceDDD(null, sprintRepository, toControllerMapper, projectRepository,
                    userStoryRepository, userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForSprintRepository(){
        //arrange
        String message = "SprintRepository cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            new SprintServiceDDD(sprintFactory, null, toControllerMapper, projectRepository,
                    userStoryRepository, userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForMapper(){
        //arrange
        String message = "NewSprintDTOMapper cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            new SprintServiceDDD(sprintFactory, sprintRepository, null, projectRepository,
                    userStoryRepository, userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForProjectRepository(){
        //arrange
        String message = "ProjectRepository cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, null,
                    userStoryRepository, userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }

    @Test
    public void ensureServiceThrowsExceptionForUserStoryRepository(){
        //arrange
        String message = "UserStoryRepository cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository,
                    null, userStoryInSprintDTOMapper, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForUserStoryInSprintDTOMapperRepository(){
        //arrange
        String message = "UserStoryInSprintDTOMapper cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
                new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository,
                        userStoryRepository, null, assembledUsAssembler, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureServiceThrowsExceptionForAssembledUsAssemblerRepository(){
        //arrange
        String message = "AssembledUsAssembler cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
                new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository,
                        userStoryRepository, userStoryInSprintDTOMapper, null, newAssembledUSDTOMapper));
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }

    @Test
    public void ensureServiceThrowsExceptionForNewAssembledUSDTOMapperRepository(){
        //arrange
        String message = "NewAssembledUSDTOMapper cannot be null.";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
                new SprintServiceDDD(sprintFactory, sprintRepository, toControllerMapper, projectRepository,
                        userStoryRepository, userStoryInSprintDTOMapper, assembledUsAssembler, null));
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
        RuntimeException exception = assertThrows(RuntimeException.class, ()->
            sprintService.getNewSprintNumber(mockCode));
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
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->
            sprintService.updateStatusSprint(sprintDomainDTO));
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

        List<SprintDDD> sprintList = new ArrayList<>();
        sprintList.add(lastSprint);

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);
        when(sprintRepository.findByProjectCode(projectCode)).thenReturn(sprintList);

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
    @DisplayName("Ensure sprint is successfully created")
    void ensureNonOverlappingSprintIsSuccessfullyCreated() {
        //Arrange
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        NewSprintDTO sprintDTO2 = mock(NewSprintDTO.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        ProjectDDD project = mock(ProjectDDD.class);
        TimePeriod projectTimePeriod = mock(TimePeriod.class);

        SprintDDD newSprint = mock(SprintDDD.class);
        TimePeriod newTimePeriod = mock(TimePeriod.class);

        SprintDDD lastSprint = mock(SprintDDD.class);
        TimePeriod lastTimePeriod = mock(TimePeriod.class);

        List<SprintDDD> sprintList = new ArrayList<>();
        sprintList.add(lastSprint);

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

        calendar.set(2023, Calendar.APRIL, 1);
        Date projectStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 30);
        Date projectEndDate = calendar.getTime();

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = newTimePeriod;

        //conferring behavior to startDate, endDate and timePeriod
        when(lastTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(lastTimePeriod.getEndDate()).thenReturn(firstEndDate);
        when(lastSprint.getTimePeriod()).thenReturn(lastTimePeriod);

        when(newTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(newTimePeriod.getEndDate()).thenReturn(secondEndDate);
        when(newSprint.getTimePeriod()).thenReturn(newTimePeriod);

        when(projectTimePeriod.getStartDate()).thenReturn(projectStartDate);
        when(projectTimePeriod.getEndDate()).thenReturn(projectEndDate);
        when(project.getTimePeriod()).thenReturn(projectTimePeriod);

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);

        when(sprintRepository.findByProjectCode(projectCode)).thenReturn(sprintList);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.of(project));

        when(sprintRepository.save(newSprint)).thenReturn(newSprint);
        when(toControllerMapper.convertToDTO(newSprint)).thenReturn(sprintDTO2);

        //Act
        NewSprintDTO result = sprintService.createSprint(sprintDTO);

        //Assert
        assertEquals(sprintDTO2, result);
    }

    @Test
    @DisplayName("Ensure sprint fails to be created because its time period is not contained within the project's")
    void ensureSprintIsNotCreatedBecauseItsTimePeriodIsNotContainedWithinProjects() {
        //Arrange
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        ProjectDDD project = mock(ProjectDDD.class);
        TimePeriod projectTimePeriod = mock(TimePeriod.class);

        SprintDDD newSprint = mock(SprintDDD.class);
        TimePeriod newTimePeriod = mock(TimePeriod.class);

        SprintDDD lastSprint = mock(SprintDDD.class);
        TimePeriod lastTimePeriod = mock(TimePeriod.class);

        List<SprintDDD> sprintList = new ArrayList<>();
        sprintList.add(lastSprint);

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

        calendar.set(2023, Calendar.APRIL, 1);
        Date projectStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 25);
        Date projectEndDate = calendar.getTime();

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = newTimePeriod;

        //conferring behavior to startDate, endDate and timePeriod
        when(lastTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(lastTimePeriod.getEndDate()).thenReturn(firstEndDate);
        when(lastSprint.getTimePeriod()).thenReturn(lastTimePeriod);

        when(newTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(newTimePeriod.getEndDate()).thenReturn(secondEndDate);
        when(newSprint.getTimePeriod()).thenReturn(newTimePeriod);

        when(projectTimePeriod.getStartDate()).thenReturn(projectStartDate);
        when(projectTimePeriod.getEndDate()).thenReturn(projectEndDate);
        when(project.getTimePeriod()).thenReturn(projectTimePeriod);

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);
        when(sprintRepository.findByProjectCode(projectCode)).thenReturn(sprintList);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.of(project));

        String expectedMessage = "The time period of the new sprint is not contained within the project's time period";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                sprintService.createSprint(sprintDTO));
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("Ensure sprint fails to be created because project optional is in fact empty")
    void ensureSprintIsNotCreatedBecauseProjectNotFound() {
        //Arrange
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        ProjectDDD project = mock(ProjectDDD.class);
        TimePeriod projectTimePeriod = mock(TimePeriod.class);

        SprintDDD newSprint = mock(SprintDDD.class);
        TimePeriod newTimePeriod = mock(TimePeriod.class);

        SprintDDD lastSprint = mock(SprintDDD.class);
        TimePeriod lastTimePeriod = mock(TimePeriod.class);

        List<SprintDDD> sprintList = new ArrayList<>();
        sprintList.add(lastSprint);

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

        calendar.set(2023, Calendar.APRIL, 1);
        Date projectStartDate = calendar.getTime();

        calendar.set(2023, Calendar.APRIL, 25);
        Date projectEndDate = calendar.getTime();

        sprintDTO.projectCode = projectCode;
        sprintDTO.timePeriod = newTimePeriod;

        //conferring behavior to startDate, endDate and timePeriod
        when(lastTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(lastTimePeriod.getEndDate()).thenReturn(firstEndDate);
        when(lastSprint.getTimePeriod()).thenReturn(lastTimePeriod);

        when(newTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(newTimePeriod.getEndDate()).thenReturn(secondEndDate);
        when(newSprint.getTimePeriod()).thenReturn(newTimePeriod);

        when(projectTimePeriod.getStartDate()).thenReturn(projectStartDate);
        when(projectTimePeriod.getEndDate()).thenReturn(projectEndDate);
        when(project.getTimePeriod()).thenReturn(projectTimePeriod);

        //conferring behavior to the dependencies
        when(sprintFactory.createSprint(sprintDTO)).thenReturn(newSprint);
        when(sprintRepository.findByProjectCode(projectCode)).thenReturn(sprintList);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.empty());

        String expectedMessage = "The time period of the new sprint is not contained within the project's time period";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                sprintService.createSprint(sprintDTO));
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure closing a sprint updates product backlog correctly")
    void ensureUpdateProductBacklogCorrectly() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        when(sprintID.getProjectCode()).thenReturn(projectCode);

        Optional<ProjectDDD> projectOptional = Optional.of(mock(ProjectDDD.class));
        Optional<SprintDDD> sprintOptional = Optional.of(mock(SprintDDD.class));
        when(projectRepository.getByID(projectCode)).thenReturn(projectOptional);
        when(sprintRepository.getByID(sprintID)).thenReturn(sprintOptional);
        ProjectDDD project = projectOptional.get();
        SprintDDD sprint = sprintOptional.get();

        List<UserStoryID> list = new ArrayList<>();
        when(sprint.listOfUserStoriesInSprintWithStatusDone()).thenReturn(list);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        list.add(userStoryID1);
        list.add(userStoryID2);

        Optional<UserStoryDDD> userStoryOptional1 = Optional.of(mock(UserStoryDDD.class));
        Optional<UserStoryDDD> userStoryOptional2 = Optional.of(mock(UserStoryDDD.class));
        when(userStoryRepository.getByID(userStoryID1)).thenReturn(userStoryOptional1);
        when(userStoryRepository.getByID(userStoryID2)).thenReturn(userStoryOptional2);
        UserStoryDDD userStory1 = userStoryOptional1.get();
        UserStoryDDD userStory2 = userStoryOptional2.get();

        doNothing().when(userStory1).setUserStoryStatus(UserStoryStatus.DONE);
        doNothing().when(project).removeUserStoryID(userStoryID1);
        UserStoryDDD savedUserStory1 = mock(UserStoryDDD.class);
        when(userStoryRepository.replace(userStory1)).thenReturn(savedUserStory1);

        doNothing().when(userStory2).setUserStoryStatus(UserStoryStatus.DONE);
        doNothing().when(project).removeUserStoryID(userStoryID2);
        UserStoryDDD savedUserStory2 = mock(UserStoryDDD.class);
        when(userStoryRepository.replace(userStory1)).thenReturn(savedUserStory2);

        ProjectDDD savedProject = mock(ProjectDDD.class);
        when(projectRepository.replace(project)).thenReturn(savedProject);

        // Act
        boolean result = sprintService.updateProductBacklogAndUserStoryStatus(sprintID);

        // Assert
        assertTrue(result);
        verify(userStory1, times(1)).setUserStoryStatus(UserStoryStatus.DONE);
        verify(project, times(1)).removeUserStoryID(userStoryID1);
    }

    @Test
    @DisplayName("Ensure closing a sprint throws exception with optional project and sprint empty")
    void ensureUpdateProductBacklogThrowsException() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        when(sprintID.getProjectCode()).thenReturn(projectCode);

        Optional<ProjectDDD> projectOptional = Optional.empty();
        Optional<SprintDDD> sprintOptional = Optional.empty();
        when(projectRepository.getByID(projectCode)).thenReturn(projectOptional);
        when(sprintRepository.getByID(sprintID)).thenReturn(sprintOptional);

        //Act
        boolean result = sprintService.updateProductBacklogAndUserStoryStatus(sprintID);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure closing a sprint with optional user story empty")
    void ensureUpdateProductBacklogWithOptionalUserStoryEmpty() {
        // Arrange
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        when(sprintID.getProjectCode()).thenReturn(projectCode);

        Optional<ProjectDDD> projectOptional = Optional.of(mock(ProjectDDD.class));
        Optional<SprintDDD> sprintOptional = Optional.of(mock(SprintDDD.class));
        when(projectRepository.getByID(projectCode)).thenReturn(projectOptional);
        when(sprintRepository.getByID(sprintID)).thenReturn(sprintOptional);
        ProjectDDD project = projectOptional.get();
        SprintDDD sprint = sprintOptional.get();

        List<UserStoryID> list = new ArrayList<>();
        when(sprint.listOfUserStoriesInSprintWithStatusDone()).thenReturn(list);
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        list.add(userStoryID1);

        Optional<UserStoryDDD> userStoryOptional = Optional.empty();
        when(userStoryRepository.getByID(userStoryID1)).thenReturn(userStoryOptional);

        ProjectDDD savedProject = mock(ProjectDDD.class);
        when(projectRepository.replace(project)).thenReturn(savedProject);

        // Act
        boolean result = sprintService.updateProductBacklogAndUserStoryStatus(sprintID);

        // Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Ensure userStoryInSprintList is successfully retrieved")
    void ensureUserStoryInSprintListIsRetrieved() {
        //Arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        SprintDDD sprint = mock(SprintDDD.class);
        //SprintBacklog sprintBacklog = mock(SprintBacklog.class);
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        List<UserStoryInSprint> list = new ArrayList<>();
        list.add(userStoryInSprint);

            //Train objects

        when(sprintRepository.findSprintBySprintID(sprintID)).thenReturn(Optional.of(sprint));
        when(sprint.getUserStoriesInSprintList()).thenReturn(list);

        //Act
        List<UserStoryInSprint> result = sprintService.getUserStoryInSprintList(sprintID);

        //Assert
        assertEquals(list, result);

    }
    @Test
    @DisplayName("Ensure that a user story is moved from product backlog to the sprint backlog and then converted into a DTO")
    void addUsToSprintBacklogFromProductBacklogAndConvertToDTO() {

        //Arrange
        NewAddUsToSprintBacklogDTO newAddUsToSprintBacklogDTO = new NewAddUsToSprintBacklogDTO();
        newAddUsToSprintBacklogDTO.projectCode = new ProjectCode("PJ26");
        newAddUsToSprintBacklogDTO.sprintNumber = new SprintNumber(26);
        newAddUsToSprintBacklogDTO.userStoryNumber = new UserStoryNumber("US26");
        newAddUsToSprintBacklogDTO.userStoryEffortEstimate = new UserStoryEffortEstimate(2.0);

        Optional<ProjectDDD> projectDDD = mock(Optional.class);
        ProjectDDD projectDDDMock = mock(ProjectDDD.class);
        when(projectRepository.getByID(newAddUsToSprintBacklogDTO.projectCode)).thenReturn(Optional.ofNullable(projectDDDMock));
        when(projectDDD.get()).thenReturn(projectDDDMock);

        List<UserStoryID> productBacklog = new ArrayList<>();
        UserStoryID userStoryID = new UserStoryID(newAddUsToSprintBacklogDTO.userStoryNumber, newAddUsToSprintBacklogDTO.projectCode);
        productBacklog.add(userStoryID);
        when(projectDDDMock. getProductBacklog()).thenReturn(productBacklog);
        Optional<UserStoryDDD> userStoryDDD = mock(Optional.class);
        UserStoryDDD userStoryDDDMock = mock(UserStoryDDD.class);
        when(userStoryRepository.getByID(userStoryID)).thenReturn(userStoryDDD);
        when(userStoryDDD.get()).thenReturn(userStoryDDDMock);
        UserStoryStatus userStoryStatus = UserStoryStatus.TO_DO;
        when(userStoryDDDMock.getStatus()).thenReturn(userStoryStatus);
        Optional<SprintDDD> sprintDDD = mock(Optional.class);
        SprintDDD sprintDDDMock = mock(SprintDDD.class);
        when(sprintRepository.getByID(any())).thenReturn(sprintDDD);
        when(sprintDDD.get()).thenReturn(sprintDDDMock);
        List<UserStoryInSprint> sprintBacklog = new ArrayList<>();
        SprintBacklog sprintBacklog1 = mock(SprintBacklog.class);
        when(sprintDDDMock.getUserStoriesInSprintList()).thenReturn(sprintBacklog);
        SprintID sprintID = new SprintID(newAddUsToSprintBacklogDTO.projectCode, newAddUsToSprintBacklogDTO.sprintNumber);
        UserStoryInSprintID userStoryInSprintID = new UserStoryInSprintID(sprintID, userStoryID);
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprintID, newAddUsToSprintBacklogDTO.userStoryEffortEstimate, userStoryStatus);
        when(sprintBacklog1.save(userStoryInSprint)).thenReturn(userStoryInSprint);
        UserStoryInSprintDTO userStoryInSprintDTO = new UserStoryInSprintDTO();
        when(userStoryInSprintDTOMapper.toDto(userStoryInSprint)).thenReturn(userStoryInSprintDTO);

        //Act
        UserStoryInSprintDTO result = sprintService.addUsToSprintBacklog(newAddUsToSprintBacklogDTO);

        //Assert
        assertEquals(userStoryInSprintDTO, result);
    }

    @Test
    @DisplayName("Ensure userStoryAssembledList is successfully retrieved")
    void ensureUserStoryAssembledListListIsRetrievedTwo() {

        // Arrange

        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);


        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryDDD userStory = mock(UserStoryDDD.class);
        UserStoryInSprintID userStoryInSprintID = mock(UserStoryInSprintID.class);
        SprintID sprintID = mock(SprintID.class);
        SprintDDD sprint = mock(SprintDDD.class);

        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        List<UserStoryInSprint> userStoryInSprintList = new ArrayList<>();
        userStoryInSprintList.add(userStoryInSprint);

        AssembledUS assembledUS = mock(AssembledUS.class);
        List<AssembledUS> assembledUSList = new ArrayList<>();
        assembledUSList.add(assembledUS);

        NewAssembledUSDTO newAssembledUSDTO = mock(NewAssembledUSDTO.class);
        List <NewAssembledUSDTO> assembledUSDtoList = new ArrayList<>();
        assembledUSDtoList.add(newAssembledUSDTO);


        // Train objects

        when(userStory.identity()).thenReturn(userStoryID);
        when(userStoryID.getUserStoryNumber()).thenReturn(userStoryNumber);

        when(userStoryID.getProjectCode()).thenReturn(projectCode);

        when(userStoryInSprint.identity()).thenReturn(userStoryInSprintID);
        when(userStoryInSprintID.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumber);


        //Método assembleUserStory

        when(userStory.getActor()).thenReturn(userStoryActor);

        when(userStory.getDescription()).thenReturn(userStoryDescription);

        when(userStory.getAcceptanceCriteria()).thenReturn(userStoryAcceptanceCriteria);

        when(userStoryInSprint.getUserStoryInSprintStatus()).thenReturn(userStoryStatus);

        when(userStoryInSprint.getUserStoryEffortEstimate()).thenReturn(userStoryEffortEstimate);

        when(sprintRepository.findSprintBySprintID(sprintID)).thenReturn(Optional.of(sprint));
        when(sprint.getUserStoriesInSprintList()).thenReturn(userStoryInSprintList);

        when(userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryRepository.getByID(userStoryID)).thenReturn(Optional.of(userStory));
        when(assembledUsAssembler.assembledUserStory(userStoryInSprint, userStory)).thenReturn(assembledUS);


        when(newAssembledUSDTOMapper.toDto(assembledUS)).thenReturn(newAssembledUSDTO);

        // Act

        List<NewAssembledUSDTO> result = sprintService.createListOfAssembledUS(userStoryInSprintList);

        // Assert
        assertEquals(assembledUSDtoList, result);
    }

}