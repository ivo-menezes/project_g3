package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.BeforeEach;
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
import org.switch2022.project.mapper.REST.SprintDTOMapper;
import org.switch2022.project.mapper.REST.SprintDTOUI;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
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
    SprintDTOMapper sprintMapper;
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

        SprintDTOUI mockDTOUI = mock(SprintDTOUI.class);
        SprintDTOUI mockSavedDTOUI = mock(SprintDTOUI.class);
        SprintDTOUI mockDTOUIWithSprintNumber = mock(SprintDTOUI.class);
        mockDTOUIWithSprintNumber.sprintNumber = 1;
        mockDTOUI.projectCode = "P1";

        SprintDTOController mockDTOController = mock(SprintDTOController.class);
        SprintDTOToController mockDTOToController = mock(SprintDTOToController.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        mockDTOController.projectCode = mockCode;

        when(sprintMapper.toDomainDTO(mockDTOUI)).thenReturn(mockDTOController);
        when(sprintMapper.createProjectCode(mockDTOUI)).thenReturn(mockDTOController);
        when(serviceDDD.generateSprintNumber(mockDTOController.projectCode)).thenReturn(1);
        when(serviceDDD.createSprint(mockDTOController)).thenReturn(mockDTOToController);
        when(sprintMapper.toRestDTO(mockDTOToController)).thenReturn(mockSavedDTOUI);

        //act
        ResponseEntity<SprintDTOUI> result = sprintController.createSprint(mockDTOUI);

        //assert
        assertEquals(201, result.getStatusCodeValue());
        assertEquals(mockSavedDTOUI, result.getBody());
    }
    @Test
    public void ensureControllerDoesNotCreateSprintNumber(){
        // Arrange
        SprintDTOUI sprintDTOFromUI = mock(SprintDTOUI.class);
        sprintDTOFromUI.projectCode = "PROJECT_CODE";
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("PROJECT_CODE");
        SprintDTOController toController = new SprintDTOController();
        toController.projectCode = mockCode;
        sprintDTOFromUI.sprintNumber = 0;

        // Mock the behavior of the mapper
        when(sprintMapper.createProjectCode(sprintDTOFromUI)).thenReturn(toController);

        // Mock the behavior of the service
        when(serviceDDD.generateSprintNumber(mockCode)).thenReturn(0);
        when(sprintMapper.toDomainDTO(sprintDTOFromUI)).thenThrow(IllegalArgumentException.class);
        when(serviceDDD.createSprint(toController)).thenReturn(new SprintDTOToController());

        // Act
        ResponseEntity<SprintDTOUI> result = sprintController.createSprint(sprintDTOFromUI);

        // Assert
        assertEquals(500, result.getStatusCodeValue());

    }
    @Test
    public void ensureControllerReturnsBadResponseHTTPStatus(){
        //arrange
        SprintDTOUI mockDTOUI = mock(SprintDTOUI.class);

        SprintDTOController mockDTOController = mock(SprintDTOController.class);

        when(sprintMapper.toDomainDTO(mockDTOUI)).thenReturn(mockDTOController);
        when(serviceDDD.createSprint(mockDTOController)).thenThrow(IllegalArgumentException.class);

        //act
        ResponseEntity<SprintDTOUI> result = sprintController.createSprint(mockDTOUI);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }
    @Test
    public void createSprintWithExceptionReturnsInternalServerError() {
        //arrange
        SprintDTOUI sprintDTO = mock(SprintDTOUI.class);
        when(sprintMapper.toDomainDTO(sprintDTO)).thenThrow(new RuntimeException("Something went wrong"));

        //act
        ResponseEntity<SprintDTOUI> result = sprintController.createSprint(sprintDTO);

        //assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
    @Test
    public void ensureControllerCreatesSprintLIst(){
        // Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        SprintDTOController mockDTOController = new SprintDTOController();
        mockDTOController.projectCode = projectCode;

        List<SprintDTOUI> mockList = new ArrayList<>();
        mockList.add(new SprintDTOUI());
        mockList.add(new SprintDTOUI());
        mockList.add(new SprintDTOUI());

        List<SprintDTOToController> mockControllerList = new ArrayList<>();
        mockControllerList.add(new SprintDTOToController());
        mockControllerList.add(new SprintDTOToController());
        mockControllerList.add(new SprintDTOToController());

        when(sprintMapper.getSprintList(anyList())).thenReturn(mockList);
        when(sprintMapper.createProjectCode(any(SprintDTOUI.class))).thenReturn(mockDTOController);
        when(serviceDDD.sprintList(any(ProjectCode.class))).thenReturn(mockControllerList);

        // Act
        ResponseEntity<List<SprintDTOUI>> result = sprintController.retrieveSprintList("P1");

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockList, result.getBody());
    }
    @Test
    public void ensureControllerThrowsException(){
        // Arrange

        ProjectCode projectCode = new ProjectCode("P1");
        SprintDTOController mockDTOController = new SprintDTOController();
        mockDTOController.projectCode = projectCode;

        when(sprintMapper.createProjectCode(any(SprintDTOUI.class))).thenReturn(null);

        // Act
        ResponseEntity<List<SprintDTOUI>> result = sprintController.retrieveSprintList("P1");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }
}