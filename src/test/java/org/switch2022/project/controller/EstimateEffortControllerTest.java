package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EstimateEffortControllerTest {

    @DisplayName("estimating a controller with null project list throws exception")
    @Test
    void estimateControllerWithNullProjectListThrowsException() {
        // Arrange
        String expectedMessage = "Project List must not be null";
        ProjectList projectList = null;
        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new EstimateEffortController(projectList);
        });
        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("estimating the effort of an user story succeeds")
    @Test
    void estimateEffortUserStorySucceeds(){
        // Arrange
        ProjectList projectListDouble = mock(ProjectList.class);
        Project projectDouble = mock(Project.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        UserStory userStoryDouble1 = mock(UserStory.class);
        SprintList sprintListDouble = mock(SprintList.class);
        Sprint sprintDouble = mock(Sprint.class);

        when(projectListDouble.getProject(1)).thenReturn(projectDouble);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
        when(productBacklogDouble.getUserStory("US001")).thenReturn(userStoryDouble1);
        when(projectDouble.getSprintList()). thenReturn(sprintListDouble);
        when(sprintListDouble.getSprint(1)). thenReturn(sprintDouble);
        when(sprintDouble.estimateEffortForUserStory(userStoryDouble1, 3.0)).thenReturn(true);

        // create a new (real) controller
        EstimateEffortController controller = new EstimateEffortController(projectListDouble);

        // Act
        boolean result = controller.estimateEffortUserStory(1, 1, "US001", 3.0);

        // Assert
        assertTrue(result);
    }

    @DisplayName("estimating the effort of an user story fails")
    @Test
    void estimateEfforUserStoryFails() {
        // Arrange
        ProjectList projectListDouble = mock(ProjectList.class);
        Project projectDouble = mock(Project.class);
        ProductBacklog productBacklogDouble = mock(ProductBacklog.class);
        UserStory userStoryDouble1 = mock(UserStory.class);
        SprintList sprintListDouble = mock(SprintList.class);
        Sprint sprintDouble = mock(Sprint.class);

        when(projectListDouble.getProject(1)).thenReturn(projectDouble);
        when(projectDouble.getProductBacklog()).thenReturn(productBacklogDouble);
        when(productBacklogDouble.getUserStory("US001")).thenReturn(userStoryDouble1);
        when(projectDouble.getSprintList()). thenReturn(sprintListDouble);
        when(sprintListDouble.getSprint(1)). thenReturn(sprintDouble);
        when(sprintDouble.estimateEffortForUserStory(userStoryDouble1, 3.0)).thenReturn(true);

        // create a new (real) controller
        EstimateEffortController controller = new EstimateEffortController(projectListDouble);

        // Act
        boolean result = controller.estimateEffortUserStory(1, 1, "US002", 3.0);

        // Assert
        assertFalse(result);
    }
}