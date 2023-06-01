package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.REST.ProjectRestDto;
import org.switch2022.project.mapper.REST.ProjectRestDtoMapper;
import org.switch2022.project.service.ProjectService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}

