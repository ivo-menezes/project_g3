package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.mapper.ProjectDTO;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectListTest {

    @Test
    @DisplayName("Ensure return the project")
    void ensureGetProject() {
        //Arrange
        Project project = mock(Project.class);
        Mockito.when(project.getCode()).thenReturn(1);
        ProjectList projectList = new ProjectList();
        projectList.addProject(project);

        //Act
        Project result = projectList.getProject(1);

        //Arrange
        assertEquals(project, result);
    }

    @Test
    @DisplayName("Ensure an exception is returned when the sprint is not found.")
    void ensureGetProjectNull() {
        //Arrange
        Project project = mock(Project.class);
        Mockito.when(project.getCode()).thenReturn(1);
        ProjectList projectList = new ProjectList();
        projectList.addProject(project);

        //Act and Assert
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            projectList.getProject(20);
        });
    }

    @Test
    @DisplayName("Ensure the project has been added.")
    void ensureAddProjectTrue() {
        //Arrange
        Project project = mock(Project.class);
        Mockito.when(project.getCode()).thenReturn(1);
        ProjectList projectList = new ProjectList();

        //Act
        boolean result = projectList.addProject(project);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if the method getProjectByIndex works as intended")
    void testingGetProjectByIndex() {
        //Arrange
        Project projectOne = mock(Project.class);
        Project projectTwo = mock(Project.class);
        Project projectThree = mock(Project.class);

        ProjectList projectList = new ProjectList();
        projectList.addProject(projectOne);
        projectList.addProject(projectTwo);
        projectList.addProject(projectThree);

        //Act
        Project result = projectList.getProjectByIndex(1);

        //Assert
        assertEquals(projectTwo, result);
    }

    @Test
    @DisplayName("Creates project DTO")
    void testIfProjectDTOIsCreatedSuccessfully() {

        Project project = new Project(200,"proj1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),"client1", "Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);

        ProjectDTO projectDTO = projectList.createProjectDTO(project);
        ProjectDTO projectDTOTestTwo = projectDTO;

        assertNotNull(projectDTO);
        assertSame(projectDTO, projectDTOTestTwo);
    }
    @Test
    @DisplayName("ensure the adding project method catches the null project")
    void checkIfTheListSizeIsNotZero(){

        Project project = new Project(200,"proj1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),"client1","Planned");
        Project projectTwo = new Project(201,"proj2",new Date(2003,Calendar.JANUARY,10), new Date(2004,Calendar.JANUARY,20),"Client2","Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        int expected = 2;

        int result = projectList.listSize();
        assertEquals(expected, result);
        assertNotEquals(0, result);
    }

    @Test
    @DisplayName("Create project success in a empty list")
    void testCreateProjectSuccessInAEmptyList(){
        ProjectList projectList = new ProjectList();
        int code = 2;
        String name = "Project Test";
        String description = "For Test";

        boolean result = projectList.createProject(code, name, description);
        assertTrue(result);
    }

    @Test
    @DisplayName("Create project fail")
    void testCreateProjectFail(){
        ProjectList projectList = new ProjectList();
        int code = 0;
        String name = "Project Test";
        String description = "For Test";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> projectList.createProject(code, name, description));
    }

    @Test
    @DisplayName("Create project success in a filled list")
    void testCreateProjectSuccessInAFilledList(){
        //Arrange
        ProjectList projectList = new ProjectList();
        Project projectTest = new Project(1, "Project Test", "For Test");
        projectList.addProject(projectTest);
        //Act
        int code = 2;
        String name = "Project Number Two";
        String description = "For Test";
        boolean result = projectList.createProject(code, name, description);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Create duplicated project in a filled list")
    void testCreateDuplicatedProjectInAFilledList(){
        //Arrange
        ProjectList projectList = new ProjectList();
        Project projectTest = new Project(1, "Project Test", "For Test");
        projectList.addProject(projectTest);
        //Act
        int code = 1;
        String name = "Project Test";
        String description = "For Test";
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> projectList.createProject(code, name, description));
    }

    @Test
    @DisplayName("validate the code of project with success")
    void validateProjectCodeSuccess_ReturnTrue(){
        ProjectList projectList = new ProjectList();
        int code = 1;
        boolean result = projectList.validateProjectCode(code);
        assertTrue(result);
    }

    @Test
    @DisplayName("validate if the code of project fail")
    void validateTheCodeOfProject_ReturnFalse(){
        ProjectList projectList = new ProjectList();
        int code = 0;
        boolean result = projectList.validateProjectCode(code);
        assertFalse(result);
    }

}
