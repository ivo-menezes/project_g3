package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.switch2022.project.mapper.*;
import org.switch2022.project.mapper.REST.*;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintStatus;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.service.SprintServiceDDD;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class SprintControllerTest {

    @MockBean
    SprintServiceDDD serviceDDD;
    @MockBean
    SprintRestDTOMapper sprintMapper;
    @MockBean
    UserStoryRestDtoMapper userStoryRestDtoMapper;
    @Autowired
    SprintController sprintController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void ensureControllerHasNoNullServiceInjection(){
        //arrange
        String message = "Sprint Service cannot be null/nonexistent";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintController(null, sprintMapper, userStoryRestDtoMapper);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureControllerHasNoNullMapperInjection(){
        //arrange
        String message = "Sprint Mapper cannot be null/nonexistent";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> {
            new SprintController(serviceDDD, null, userStoryRestDtoMapper);
        });
        String result = exception.getMessage();
        // assert
        assertEquals(message, result);
    }
    @Test
    public void ensureControllerReturnsCreatedHTTPStatus(){
        //arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        SprintRestDTO mockDTOUI = mock(SprintRestDTO.class);
        SprintRestDTO mockSavedDTOUI = mock(SprintRestDTO.class);
        SprintRestDTO mockDTOUIWithSprintNumber = mock(SprintRestDTO.class);

        mockDTOUIWithSprintNumber.sprintNumber = 1;
        mockDTOUI.projectCode = "P1";
        int sprintNumber = 1;

        NewSprintDTO mockDTOController = mock(NewSprintDTO.class);
        NewSprintDTO mockDTOToController = mock(NewSprintDTO.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        mockDTOController.projectCode = mockCode;

        when(serviceDDD.getNewSprintNumber(mockDTOController.projectCode)).thenReturn(sprintNumber);
        when(sprintMapper.toDomainDTO(mockDTOUI)).thenReturn(mockDTOController);
        when(sprintMapper.createProjectCode(mockDTOUI)).thenReturn(mockDTOController);
        when(serviceDDD.createSprint(mockDTOController)).thenReturn(mockDTOToController);
        when(sprintMapper.toRestDTO(mockDTOToController)).thenReturn(mockSavedDTOUI);

        //act
        ResponseEntity<SprintRestDTO> result = sprintController.createSprint(mockDTOUI);

        //assert
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(mockSavedDTOUI, result.getBody());
    }
    @Test
    public void ensureControllerDoesNotCreateSprintNumber(){
        // Arrange
        SprintRestDTO sprintDTOFromUI = mock(SprintRestDTO.class);
        sprintDTOFromUI.projectCode = "PROJECT_CODE";
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("PROJECT_CODE");
        NewSprintDTO toController = new NewSprintDTO();
        toController.projectCode = mockCode;
        sprintDTOFromUI.sprintNumber = 0;

        // Mock the behavior of the mapper
        when(sprintMapper.createProjectCode(sprintDTOFromUI)).thenReturn(toController);

        // Mock the behavior of the service

        when(sprintMapper.toDomainDTO(sprintDTOFromUI)).thenThrow(IllegalArgumentException.class);
        when(serviceDDD.createSprint(toController)).thenReturn(new NewSprintDTO());

        // Act
        ResponseEntity<SprintRestDTO> result = sprintController.createSprint(sprintDTOFromUI);

        // Assert
        assertEquals(400, result.getStatusCodeValue());

    }
    @Test
    public void ensureControllerReturnsBadResponseHTTPStatus(){
        //arrange
        SprintRestDTO mockDTOUI = mock(SprintRestDTO.class);

        NewSprintDTO mockDTOController = mock(NewSprintDTO.class);

        when(sprintMapper.toDomainDTO(mockDTOUI)).thenReturn(mockDTOController);
        when(serviceDDD.createSprint(mockDTOController)).thenThrow(IllegalArgumentException.class);

        //act
        ResponseEntity<SprintRestDTO> result = sprintController.createSprint(mockDTOUI);

        //assert
        assertEquals(500, result.getStatusCodeValue());
    }
    @Test
    public void createSprintWithExceptionReturnsInternalServerError() {
        //arrange
        SprintRestDTO sprintDTO = mock(SprintRestDTO.class);
        when(sprintMapper.toDomainDTO(sprintDTO)).thenThrow(new RuntimeException("Something went wrong"));

        //act
        ResponseEntity<SprintRestDTO> result = sprintController.createSprint(sprintDTO);

        //assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }
    @Test
    public void ensureControllerCreatesSprintLIst(){
        // Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        NewSprintDTO mockDTOController = new NewSprintDTO();
        mockDTOController.projectCode = projectCode;

        List<SprintRestDTO> mockList = new ArrayList<>();
        mockList.add(new SprintRestDTO());
        mockList.add(new SprintRestDTO());
        mockList.add(new SprintRestDTO());

        List<NewSprintDTO> mockControllerList = new ArrayList<>();
        mockControllerList.add(new NewSprintDTO());
        mockControllerList.add(new NewSprintDTO());
        mockControllerList.add(new NewSprintDTO());

        when(sprintMapper.getSprintList(anyList())).thenReturn(mockList);
        when(sprintMapper.createProjectCode(any(SprintRestDTO.class))).thenReturn(mockDTOController);
        when(serviceDDD.sprintList(any(ProjectCode.class))).thenReturn(mockControllerList);

        // Act
        ResponseEntity<List<SprintRestDTO>> result = sprintController.retrieveSprintList("P1");

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockList, result.getBody());
    }
    @Test
    public void ensureControllerThrowsException(){
        // Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        NewSprintDTO mockDTOController = new NewSprintDTO();
        mockDTOController.projectCode = projectCode;

        when(sprintMapper.createProjectCode(any(SprintRestDTO.class))).thenReturn(null);

        // Act
        ResponseEntity<List<SprintRestDTO>> result = sprintController.retrieveSprintList("P1");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @DisplayName("Ensure that the sprint status update was successful with sprint status closed.")
    @Test
    public void updateStatusSprintSuccessStatusClosed(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UpdateSprintDTO updateSprintDTO = mock(UpdateSprintDTO.class);
        UpdateSprintDomainDTO domainDTO = mock(UpdateSprintDomainDTO.class);

        when(sprintMapper.toDomainDTO(updateSprintDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateStatusSprint(domainDTO)).thenReturn(domainDTO);
        when(sprintMapper.toDataDTO(domainDTO)).thenReturn(updateSprintDTO);
        SprintID sprintID = domainDTO.sprintID;
        domainDTO.sprintStatus = SprintStatus.Closed;
        serviceDDD.updateProductBacklogAndUserStoryStatus(sprintID);

        //Act
        ResponseEntity<UpdateSprintDTO> responseEntity = sprintController.updateStatusSprint(updateSprintDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }

    @DisplayName("Ensure that the sprint status update was successful with sprint status not closed.")
    @Test
    public void updateStatusSprintSuccessStatusNotClosed(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UpdateSprintDTO updateSprintDTO = mock(UpdateSprintDTO.class);
        UpdateSprintDomainDTO domainDTO = mock(UpdateSprintDomainDTO.class);

        when(sprintMapper.toDomainDTO(updateSprintDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateStatusSprint(domainDTO)).thenReturn(domainDTO);
        when(sprintMapper.toDataDTO(domainDTO)).thenReturn(updateSprintDTO);
        SprintID sprintID = domainDTO.sprintID;
        domainDTO.sprintStatus = SprintStatus.Planned;

        //Act
        ResponseEntity<UpdateSprintDTO> responseEntity = sprintController.updateStatusSprint(updateSprintDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }

    @DisplayName("Ensure that the sprint status update fails.")
    @Test
    public void updateStatusSprintFails(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UpdateSprintDTO updateSprintDTO = mock(UpdateSprintDTO.class);
        UpdateSprintDomainDTO domainDTO = mock(UpdateSprintDomainDTO.class);

        when(sprintMapper.toDomainDTO(updateSprintDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateStatusSprint(domainDTO)).thenThrow(new IllegalArgumentException(""));

        //Act
        ResponseEntity<UpdateSprintDTO> responseEntity = sprintController.updateStatusSprint(updateSprintDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),400);
    }

    @DisplayName("Ensure that the US in sprint status update was successful.")
    @Test
    public void updateUsStatusSprintSuccess(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        InputUsInSprintStatusDTO inputUsInSprintStatusDTO = mock(InputUsInSprintStatusDTO.class);
        UpdateUsInSprintDomainDTO domainDTO = mock(UpdateUsInSprintDomainDTO.class);

        when(sprintMapper.uSInSprintToDomainDTO(inputUsInSprintStatusDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateUsInSprintStatus(domainDTO)).thenReturn(domainDTO);
        when(sprintMapper.uSInSprintToDataDTO(domainDTO)).thenReturn(inputUsInSprintStatusDTO);

        //Act
        ResponseEntity<InputUsInSprintStatusDTO> responseEntity = sprintController.updateUsInSprintStatus(inputUsInSprintStatusDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),200);
    }

    @DisplayName("Ensure that the US in sprint status update fails.")
    @Test
    public void updateUsStatusSprintFails(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        InputUsInSprintStatusDTO inputUsInSprintStatusDTO = mock(InputUsInSprintStatusDTO.class);
        UpdateUsInSprintDomainDTO domainDTO = mock(UpdateUsInSprintDomainDTO.class);

        when(sprintMapper.uSInSprintToDomainDTO(inputUsInSprintStatusDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateUsInSprintStatus(domainDTO)).thenThrow(new IllegalArgumentException(""));

        //Act
        ResponseEntity<InputUsInSprintStatusDTO> responseEntity = sprintController.updateUsInSprintStatus(inputUsInSprintStatusDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),400);
    }

    @DisplayName("Ensure that UserStoryInSprint was added to sprint backlog")
    @Test
    public void addUsToSprintBacklogSuccessfully(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewAddUsToSprintBacklogDTO newAddUsToSprintBacklogDTO = mock(NewAddUsToSprintBacklogDTO.class);
        UserStoryInSprintDTO userStoryInSprintDTO = mock(UserStoryInSprintDTO.class);
        AddUsToSprintBacklogDTO addUsToSprintBacklogDTO = mock(AddUsToSprintBacklogDTO.class);
        AddUsInSprintToBacklogDTO addUsInSprintToBacklogDTO = mock(AddUsInSprintToBacklogDTO.class);

        when(userStoryRestDtoMapper.toSprintBacklogDomainDTO(addUsToSprintBacklogDTO)).thenReturn(newAddUsToSprintBacklogDTO);
        when(serviceDDD.addUsToSprintBacklog(newAddUsToSprintBacklogDTO)).thenReturn(userStoryInSprintDTO);
        when(userStoryRestDtoMapper.toSprintBacklogRestDTO(userStoryInSprintDTO)).thenReturn(addUsInSprintToBacklogDTO);

        //Act
        ResponseEntity<AddUsInSprintToBacklogDTO> responseEntity = sprintController.addUSToOpenSprintBacklog(addUsToSprintBacklogDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(),202);
    }


    @DisplayName("Ensure that the Sprint Backlog List is returned with httpStatus 200.")
    @Test
    public void ensureSprintBacklogListIsReturnedWithOK(){
        //Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode,sprintNumber);


        UserStoryInSprint userStoryInSprintDoubleOne = mock(UserStoryInSprint.class);
        UserStoryInSprint userStoryInSprintDoubleTwo = mock(UserStoryInSprint.class);

        NewAssembledUSDTO newAssembledUSDTODoubleOne = mock(NewAssembledUSDTO.class);
        NewAssembledUSDTO newAssembledUSDTODoubleTwo = mock(NewAssembledUSDTO.class);

        AssembledUSRestDto assembledUSRestDtoDoubleOne = mock(AssembledUSRestDto.class);
        AssembledUSRestDto assembledUSRestDtoDoubleTwo = mock(AssembledUSRestDto.class);


        List<UserStoryInSprint> userStoryInSprintList = new ArrayList<>();
        userStoryInSprintList.add(userStoryInSprintDoubleOne);
        userStoryInSprintList.add(userStoryInSprintDoubleTwo);


        List<NewAssembledUSDTO> newAssembledUSDTOList = new ArrayList<>();
        newAssembledUSDTOList.add(newAssembledUSDTODoubleOne);
        newAssembledUSDTOList.add(newAssembledUSDTODoubleTwo);

        List<AssembledUSRestDto> restDtoList = new ArrayList<>();
        restDtoList.add(assembledUSRestDtoDoubleOne);
        restDtoList.add(assembledUSRestDtoDoubleTwo);

        when(serviceDDD.getUserStoryInSprintList(sprintID)).thenReturn(userStoryInSprintList);
        when(serviceDDD.createListOfAssembledUS(userStoryInSprintList)).thenReturn(newAssembledUSDTOList);

        when(sprintMapper.assembledUSToRestDto(newAssembledUSDTODoubleOne)).thenReturn(assembledUSRestDtoDoubleOne);
        when(sprintMapper.assembledUSToRestDto(newAssembledUSDTODoubleTwo)).thenReturn(assembledUSRestDtoDoubleTwo);

        //Act
        ResponseEntity<List<AssembledUSRestDto>> response = sprintController.getSprintBacklog(projectCode,sprintNumber);

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restDtoList, response.getBody());

    }

    @DisplayName("ensure consulting sprint Backlog returns HTTP status 404 - Not Found when service throws exception")
    @Test
    void shouldReturnStatusNotFound() {

        // Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        SprintNumber sprintNumber = new SprintNumber(1);
        SprintID sprintID = new SprintID(projectCode,sprintNumber);

        when(serviceDDD.getUserStoryInSprintList(sprintID)).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<?> response = sprintController.getSprintBacklog(projectCode,sprintNumber);

        // Assert
        assertEquals(404, response.getStatusCodeValue());

    }
}