package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.mapper.ProjectDTO;
import org.switch2022.project.model.ProjectList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListProjectControllerTest {

    @Test
    @DisplayName("Test whether the Controller can create the Project DTO list")
    void testForCreatingProjectDTOList() {

        Project project = new Project(200, "proj1", new Date(2023, Calendar.JANUARY, 10), new Date(2024, Calendar.JANUARY, 22), "client1", "Planned");
        Project projectTwo = new Project(201, "proj2", new Date(2003, Calendar.JANUARY, 10), new Date(2004, Calendar.JANUARY, 20), "client2", "Planned");
        Project projectThree = new Project(202, "proj3", new Date(2010, Calendar.FEBRUARY, 24), new Date(2026, Calendar.FEBRUARY, 24), "client3", "Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        projectList.addProject(projectThree);

        ListProjectController controller = new ListProjectController(projectList);

        controller.listProject(projectList);

        assertNotNull(controller);
    }

    @Test
    @DisplayName("ensure that a valid list of project DTO is created")
    void ensureThatAValidListOfProjectDTOisCreated() {
        // arrange
        // create all necessary mock objects
        Project project1 = mock(Project.class);
        Project project2 = mock(Project.class);
        Project project3 = mock(Project.class);
        ProjectList projectList = mock(ProjectList.class);
        ProjectDTO projectDTO1 = mock(ProjectDTO.class);
        ProjectDTO projectDTO2 = mock(ProjectDTO.class);
        ProjectDTO projectDTO3 = mock(ProjectDTO.class);
        // train the mock objects
        when(projectList.listSize()).thenReturn(3);
        when(projectList.getProjectByIndex(0)).thenReturn(project1);
        when(projectList.getProjectByIndex(1)).thenReturn(project2);
        when(projectList.getProjectByIndex(2)).thenReturn(project3);
        when(projectList.createProjectDTO(project1)).thenReturn(projectDTO1);
        when(projectList.createProjectDTO(project2)).thenReturn(projectDTO2);
        when(projectList.createProjectDTO(project3)).thenReturn(projectDTO3);
        // create a new (real) controller
        ListProjectController controller = new ListProjectController(projectList);

        // act
        List<ProjectDTO> result = controller.listProject(projectList);

        //assert
        assertTrue(result.size() == 3);
    }

    @Test
    @DisplayName("ensure an empty project list throws an exception")
    public void ensureEmptyProjectListThrowsException_WithMocks(){
        // arrange
        ProjectList projectList = mock(ProjectList.class);

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new ListProjectController(projectList));
        String resultMessage = result.getMessage();

        // assert
        assertEquals("List is empty", resultMessage);
    }

    @Test
    public void testListProject_ShouldReturnListOProjectDTO() {

        Project project = new Project(200, "proj1", new Date(2023, Calendar.JANUARY, 10), new Date(2024, Calendar.JANUARY, 22), "client1", "Planned");
        Project projectTwo = new Project(201, "proj2", new Date(2003, Calendar.JANUARY, 10), new Date(2004, Calendar.JANUARY, 20), "client2", "Planned");
        Project projectThree = new Project(202, "proj3", new Date(2010, Calendar.FEBRUARY, 24), new Date(2026, Calendar.FEBRUARY, 24), "client3", "Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        projectList.addProject(projectThree);

        // add some projects to the list
        ListProjectController controller = new ListProjectController(projectList);
        List<ProjectDTO> result = controller.listProject(projectList);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof ProjectDTO);
    }
}
