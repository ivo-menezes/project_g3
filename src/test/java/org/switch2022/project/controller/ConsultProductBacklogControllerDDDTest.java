package org.switch2022.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.mapper.UserStoryDTOForListDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.service.UserStoryService;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConsultProductBacklogControllerDDDTest {
    private ConsultProductBacklogControllerDDD controller;
    private UserStoryService serviceMock;

    @BeforeEach
    void setUp() {
        serviceMock = mock(UserStoryService.class);
        controller = new ConsultProductBacklogControllerDDD(serviceMock);
    }
    @DisplayName("testing a controller with null UserStoryService throws exception")
    @Test
    void createControllerWithNullUserStoryServiceThrowsException() {
        // Arrange
        String expectedMessage = "UserStoryService must not be null.";
        UserStoryService service = null;
        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ConsultProductBacklogControllerDDD(null);
        });
        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("getProjectBacklog() with null project code throws IllegalArgumentException")
    void testGetProjectBacklogWithNullProjectCodeThrowsIllegalArgumentException() {
        // Arrange
        UserStoryService service = Mockito.mock(UserStoryService.class);
        ConsultProductBacklogControllerDDD controller = new ConsultProductBacklogControllerDDD(service);

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.getProjectBacklog(null);
        });
        assertEquals("projectCode must not be null.", exception.getMessage());
    }

    @DisplayName("test GetProjectBacklog with non existent ProjectCode")
    @Test
    void testGetProjectBacklogWithNonexistentProjectCode() {

        when(serviceMock.getProductBacklog(any(ProjectCode.class))).thenReturn(Optional.empty());
        Optional<List<UserStoryDTOForListDDD>> result;
        result = controller.getProjectBacklog(new ProjectCode("NONEXISTENT"));
        assertEquals(Optional.empty(), result);
    }
    @DisplayName("test GetProjectBacklog method with existing ProjectCode")
    @Test
    void testGetProjectBacklogWithExistingProjectCode() {

      List<UserStoryDTOForListDDD> mockProductBacklog = List.of(new UserStoryDTOForListDDD(), new UserStoryDTOForListDDD());
      when(serviceMock.getProductBacklog(any(ProjectCode.class))).thenReturn(Optional.of(mockProductBacklog));
      Optional<List<UserStoryDTOForListDDD>> result = controller.getProjectBacklog(new ProjectCode("EXISTING"));
      assertEquals(Optional.of(mockProductBacklog), result);
    }
}
