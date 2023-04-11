package org.switch2022.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.model.SprintList;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateSprintControllerTest {

    @Test
    @DisplayName("creating Sprint with success")
    void createSprintWithSuccess() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        int projectCode = 1;
        Project projectMock = mock(Project.class);
        ProjectList projectList = mock(ProjectList.class);
        when(projectList.getProject(projectCode)).thenReturn(projectMock);
        SprintList sprintList = mock(SprintList.class);
        when(projectMock.getSprintList()).thenReturn(sprintList);
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");
        when(sprintList.createSprint(sprintDTO)).thenReturn(true);

        CreateSprintController controller = new CreateSprintController(projectList);
        boolean result = controller.createSprint(projectCode,sprintDTO);

        assertTrue(result);
    }
    @Test
    @DisplayName("Check if the provided projectList is null")
    void checkTheProjectListIsNull(){
        ProjectList projectListMock = null;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreateSprintController controller = new CreateSprintController(projectListMock);
        });
        Assertions.assertEquals("ProjectList must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the provided DTO is null")
    void checkIfDTOIsNull(){
        int projectCode = 1;
        SprintDTO sprintDTOMock = null;
        Project projectMock = mock(Project.class);
        ProjectList projectListMock = mock(ProjectList.class);
        when(projectListMock.getProject(projectCode)).thenReturn(projectMock);
        CreateSprintController controller = new CreateSprintController(projectListMock);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.createSprint(projectCode, sprintDTOMock);
        });
        Assertions.assertEquals("SprintDTO must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the second DTO is not inserted.")
    void checkIfTheMethodComesFalse() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        int projectCode = 1;
        Project projectMock = mock(Project.class);
        ProjectList projectListMock = mock(ProjectList.class);
        when(projectListMock.getProject(projectCode)).thenReturn(projectMock);
        SprintList sprintList = mock(SprintList.class);
        when(projectMock.getSprintList()).thenReturn(sprintList);
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");

        when(sprintList.createSprint(sprintDTO)).thenReturn(true);
        when(sprintList.createSprint(sprintDTOTwo)).thenReturn(false);

        CreateSprintController controller = new CreateSprintController(projectListMock);
        controller.createSprint(projectCode,sprintDTO);
        boolean result = controller.createSprint(projectCode,sprintDTOTwo);

        assertFalse(result);
    }
}
