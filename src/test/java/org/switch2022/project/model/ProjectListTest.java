package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}