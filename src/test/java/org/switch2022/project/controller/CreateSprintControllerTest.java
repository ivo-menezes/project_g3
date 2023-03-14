package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.switch2022.project.model.Project;
import org.switch2022.project.model.ProjectList;
import org.switch2022.project.model.SprintDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = formatter.parse("01/02/2022");
        sprintDTO.endDate = formatter.parse("15/02/2022");
        when(projectMock.addSprint(sprintDTO)).thenReturn(true);

        CreateSprintController controller = new CreateSprintController(projectList);
        boolean result = controller.createSprint(projectCode,sprintDTO);

        assertTrue(result);
    }
}
