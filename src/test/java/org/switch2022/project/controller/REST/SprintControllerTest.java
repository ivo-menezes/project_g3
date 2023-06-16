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
import org.switch2022.project.mapper.REST.SprintRestDTO;
import org.switch2022.project.mapper.REST.SprintRestDTOMapper;
import org.switch2022.project.mapper.UpdateSprintDTO;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.ProjectCode;
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
            new SprintController(null, sprintMapper);
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
            new SprintController(serviceDDD, null);
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

    @DisplayName("Ensure that the sprint status update was successful.")
    @Test
    public void updateStatusSprintSuccess(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UpdateSprintDTO updateSprintDTO = mock(UpdateSprintDTO.class);
        UpdateSprintDomainDTO domainDTO = mock(UpdateSprintDomainDTO.class);

        when(sprintMapper.toDomainDTO(updateSprintDTO)).thenReturn(domainDTO);
        when(serviceDDD.updateStatusSprint(domainDTO)).thenReturn(domainDTO);
        when(sprintMapper.toDataDTO(domainDTO)).thenReturn(updateSprintDTO);

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
}