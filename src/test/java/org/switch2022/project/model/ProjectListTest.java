package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.controller.ListProjectController;

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
    @DisplayName("ensure the resource has been added in the Project.")
    void ensureAddResourceToProjectTrue() {
        //arrange
        ProjectList projectList = new ProjectList();
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role("Team Member");

        projectList.addProject(new Project(1, "test", "test"));

        //act
        boolean result = projectList.addResourceToProject(account,role,resourceDTO);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure the resource has not been added in the Project.")
    void ensureAddResourceToProjectFalse() {
        //arrange
        ProjectList projectList = new ProjectList();
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role("Team Member");

        projectList.addProject(new Project(1, "test", "test"));

        //act
        boolean added = projectList.addResourceToProject(account,role,resourceDTO);
        boolean result = projectList.addResourceToProject(account,role,resourceDTO);

        //assert
        assertFalse(result);
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

        Project project = new Project(200,"proj1","project1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),3,10,"Planned",2000);
        Project projectTwo = new Project(201,"proj2","project2",new Date(2003,Calendar.JANUARY,10), new Date(2004,Calendar.JANUARY,20),2,5,"Closed",10000);
        Project projectThree = new Project(202,"proj3","project3",new Date(2010,Calendar.FEBRUARY,24), new Date(2026,Calendar.FEBRUARY,24),2,100,"WARRANTY", 200000);

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

        Project project = new Project(200,"proj1","project1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),3,10,"Planned",2000);

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

        Project project = new Project(200,"proj1","project1",new Date(2023, Calendar.JANUARY,10),new Date(2024,Calendar.JANUARY,22),3,10,"Planned",2000);
        Project projectTwo = new Project(201,"proj2","project2",new Date(2003,Calendar.JANUARY,10), new Date(2004,Calendar.JANUARY,20),2,5,"Closed",10000);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        int expected = 2;

        int result = projectList.listSize();
        assertEquals(expected, result);
        assertNotEquals(0, result);
    }
}
