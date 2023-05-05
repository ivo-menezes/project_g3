package org.switch2022.project.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTO_DDD;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;
import org.switch2022.project.service.SprintService;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateSprintControllerTest {

    @Test
    @DisplayName("ensure creating a controller succeeds")
    void createControllerSucceeds() {
        // Arrange
        SprintService sprintService = mock(SprintService.class);
        // Act
        CreateSprintController sprintController = new CreateSprintController(sprintService);
        // Assert
        assertInstanceOf(CreateSprintController.class, sprintController);
    }

    @Test
    @DisplayName("create sprint with success")
    void createSprint() {
        // Arrange
        SprintService sprintService = mock(SprintService.class);
        ISprintFactory factory = mock(ISprintFactory.class);
        Repository<ProjectCode, ProjectDDD> projectRepository = mock(Repository.class);
        Repository<SprintID, SprintDDD> sprintRepository = mock(Repository.class);

        SprintDTO_DDD sprintDto = mock(SprintDTO_DDD.class);

        when(sprintService.createSprint(sprintDto)).thenReturn(true);
        sprintDto.projectCode = "AAA";
        sprintDto.sprintNumber = 1;
        sprintDto.startDate = new Date(10/ 3 /2023);
        sprintDto.endDate = new Date(25/ 3 /2023);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectDDD project = mock(ProjectDDD.class);
        when(projectRepository.getByID(projectCode)).thenReturn(Optional.of(project));
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        SprintDDD sprint = mock(SprintDDD.class);
        when(factory.createSprint(sprintID,timePeriod)).thenReturn(sprint);
        when(sprintRepository.save(sprint)).thenReturn(true);

        CreateSprintController sprintController = new CreateSprintController(sprintService);

        //Act
        boolean result = sprintController.createSprint(sprintDto);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Check if the provided sprintService is null")
    void checkIfSprintServiceIsNull(){
        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreateSprintController controller = new CreateSprintController(null);
        });
        // Arrange
        Assertions.assertEquals("SprintService must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the provided DTO is null")
    void checkIfDTOIsNull(){
        // Arrange
        SprintService sprintService = mock(SprintService.class);

        CreateSprintController controller = new CreateSprintController(sprintService);
        // Act
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.createSprint(null);
        });
        // Assert
        Assertions.assertEquals("SprintDTO must not be null.", exception.getMessage());
    }


}
