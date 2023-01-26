package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProjectListTest {

    @Test
    @DisplayName("ensure return the project")
    void ensureGetProject() {
        Project project = new Project(1, "testOne", "testTwo", new Date(), new Date(), 2, 2, "Planned", 1000);
        ProjectList projectList = new ProjectList();
        projectList.addProject(project);

        //act
        Project result = projectList.getProject(1);

        //assert
        assertEquals(project, result);
    }

    @Test
    @DisplayName("ensure return null when doesn't found project")
    void ensureGetProjectNull() {
        Project project = new Project(1, "testOne", "testTwo", new Date(), new Date(), 2, 2, "Planned", 1000);
        ProjectList projectList = new ProjectList();
        projectList.addProject(project);

        //act
        Project result = projectList.getProject(2);

        //assert
        assertNull(result);
    }

    @Test
    @DisplayName("ensure the project has been added.")
    void ensureAddProjectTrue() {
        //arrange
        ProjectList projectList = new ProjectList();

        //act
        boolean result = projectList.addProject(new Project(1, "test", "test"));

        //
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if the method getProjectIndex works as intended")
    void testingGetProjectIndex() {

        Project project = new Project(200,"proj1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),"client1","Planned");
        Project projectTwo = new Project(201,"proj2",new Date(2003,Calendar.JANUARY,10), new Date(2004,Calendar.JANUARY,20),"client2", "Planned");
        Project projectThree = new Project(202,"proj3",new Date(2010,Calendar.FEBRUARY,24), new Date(2026,Calendar.FEBRUARY,24),"client3", "Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        projectList.addProject(projectThree);

        Project result = projectList.getProjectIndex(1);

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
