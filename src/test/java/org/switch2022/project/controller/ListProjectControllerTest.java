package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Test whether the Controller can create the Project DTO list")
    void testForCreatingProjectDTOList1() {

        Project project = new Project(200, "proj1", new Date(2023, Calendar.JANUARY, 10), new Date(2024, Calendar.JANUARY, 22), "client1", "Planned");
        Project projectTwo = new Project(201, "proj2", new Date(2003, Calendar.JANUARY, 10), new Date(2004, Calendar.JANUARY, 20), "client2", "Planned");
        Project projectThree = new Project(202, "proj3", new Date(2010, Calendar.FEBRUARY, 24), new Date(2026, Calendar.FEBRUARY, 24), "client3", "Planned");

        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        projectList.addProject(projectTwo);
        projectList.addProject(projectThree);

        ListProjectController controller = new ListProjectController(projectList);
        List result = controller.listProject(projectList);
        List<Integer> notExpected = Arrays.asList(200, 201, 202, 203);

        assertNotEquals(notExpected, result);
    }

    @Test
    void checkTheEmptyList(){
        ProjectList projectList = new ProjectList();
        AtomicReference<ListProjectController> controller = null;
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> controller.set(new ListProjectController(projectList)));
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

        // add some accounts to the list
        ListProjectController controller = new ListProjectController(projectList);
        List<ProjectDTO> result = controller.listProject(projectList);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof ProjectDTO);
    }
}
