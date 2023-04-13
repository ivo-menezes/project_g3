package org.switch2022.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.ProjectList;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectControllerTest {

    @Test
    @DisplayName("Ensure that project are created")
    void createProjectSuccess() {
        ProjectList projectList = new ProjectList();
        CreateProjectController controller = new CreateProjectController(projectList);
        boolean result = controller.createProject(1,"Project Test", "For test");
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure that project not created and throws exception")
    void createProjectWithoutSuccessAndThrowsException() {
        ProjectList projectList = new ProjectList();
        CreateProjectController controller = new CreateProjectController(projectList);
        int code = 0;
        String name = "Project Test";
        String description = "For test";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.createProject(code, name, description));
    }

    @Test
    @DisplayName("Ensure that project not created because already exists")
    void createProjectWithoutSuccess() {
        ProjectList projectList = new ProjectList();
        Project projectTest = new Project(1,"Project Test", "For test");
        projectList.addProject(projectTest);
        CreateProjectController controller = new CreateProjectController(projectList);
        int code = 1;
        String name = "Project Test";
        String description = "For test";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> controller.createProject(code, name, description));
    }
}