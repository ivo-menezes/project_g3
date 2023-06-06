package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.REST.ProjectRestDto;
import org.switch2022.project.mapper.REST.ProjectRestDtoMapper;
import org.switch2022.project.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
class ProjectControllerTest {

    @MockBean
    ProjectService projectService;

    @MockBean
    ProjectRestDtoMapper dtoMapper;

    @Autowired
    ProjectController projectController;

    @Test
    @DisplayName("Ensure project is created successfully")
    void ensureProjectIsCreatedSuccessfully(){
        //Arrange
        ProjectRestDto projectRestDto1 = mock(ProjectRestDto.class);
        ProjectRestDto projectRestDto2 = mock(ProjectRestDto.class);

        NewProjectDTO newProjectDto1 = mock(NewProjectDTO.class);
        NewProjectDTO newProjectDto2 = mock(NewProjectDTO.class);

        when(dtoMapper.toDomainDto(projectRestDto1)).thenReturn(newProjectDto1);
        when(projectService.createProject(newProjectDto1)).thenReturn(newProjectDto2);
        when(dtoMapper.toRestDto(newProjectDto2)).thenReturn(projectRestDto2);

        //Act
        ResponseEntity<?> response = projectController.createProject(projectRestDto1);

        //Assert
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Ensure project fails to be created")
    void ensureProjectFailsToBeCreated(){
        //Arrange
        ProjectRestDto projectRestDto = mock(ProjectRestDto.class);
        NewProjectDTO newProjectDto = mock(NewProjectDTO.class);

        when(dtoMapper.toDomainDto(projectRestDto)).thenReturn(newProjectDto);
        when(projectService.createProject(newProjectDto)).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<?> response = projectController.createProject(projectRestDto);

        //Assert
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Ensure project list is returned with HttpStatus 200")
    void ensureProjectListIsReturnedWithOK(){
        //Arrange
        NewProjectDTO newProjectDTODouble1 = mock(NewProjectDTO.class);
        NewProjectDTO newProjectDTODouble2 = mock(NewProjectDTO.class);
        NewProjectDTO newProjectDTODouble3 = mock(NewProjectDTO.class);

        ProjectRestDto projectRestDtoDouble1 = mock(ProjectRestDto.class);
        ProjectRestDto projectRestDtoDouble2 = mock(ProjectRestDto.class);
        ProjectRestDto projectRestDtoDouble3 = mock(ProjectRestDto.class);

        List<NewProjectDTO> projectDtoListDouble = new ArrayList<>();
        projectDtoListDouble.add(newProjectDTODouble1);
        projectDtoListDouble.add(newProjectDTODouble2);
        projectDtoListDouble.add(newProjectDTODouble3);

        when(projectService.getAllProjects()).thenReturn(projectDtoListDouble);

        when(dtoMapper.toRestDto(newProjectDTODouble1)).thenReturn(projectRestDtoDouble1);
        when(dtoMapper.toRestDto(newProjectDTODouble2)).thenReturn(projectRestDtoDouble2);
        when(dtoMapper.toRestDto(newProjectDTODouble3)).thenReturn(projectRestDtoDouble3);

        List<ProjectRestDto> projectRestDtoList = new ArrayList<>();
        projectRestDtoList.add(projectRestDtoDouble1);
        projectRestDtoList.add(projectRestDtoDouble2);
        projectRestDtoList.add(projectRestDtoDouble3);

        //Act
        ResponseEntity<List<ProjectRestDto>> response = projectController.getAllProjects();

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(projectRestDtoList, response.getBody());
    }

    @Test
    @DisplayName("Ensure project list is returned with HttpStatus 400")
    void ensureProjectListIsReturnedWithBadRequest(){
        //Arrange
        when(projectService.getAllProjects()).thenThrow(RuntimeException.class);

        //Act
        ResponseEntity<List<ProjectRestDto>> response = projectController.getAllProjects();

        //Assert
        assertEquals(400, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Ensure project fails to be created with null projectService")
    void ensureProjectFailsToBeCreatedWithNullService(){
        //Arrange
        String expectedMessage = "Project Service must not be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectController(null, dtoMapper));

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure project fails to be created with null dtoMapper")
    void ensureProjectFailsToBeCreatedWithNullDtoMapper(){
        //Arrange
        String expectedMessage = "Project Rest Dto Mapper must not be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectController(projectService, null));

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}

