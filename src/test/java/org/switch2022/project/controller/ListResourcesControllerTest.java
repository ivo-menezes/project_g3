package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ListResourcesControllerTest {
    @Test
    @DisplayName("get a list of resources as a list of DTOs")
    void GetAListOfResources() {
        //Arrange
        Project project = mock(Project.class);
        List<ResourceDTO> resources = new ArrayList<>();

        resources.add(new ResourceDTO("deborah@hotmail.com", "Team Member"));
        resources.add(new ResourceDTO("pedro@example.com", "Team Member"));

        Mockito.when(project.listResources()).thenReturn(resources);

        ProjectList projectList = mock(ProjectList.class);
        Mockito.when(projectList.getProject(1)).thenReturn(project);

        ListResourcesController listResourcesController = new ListResourcesController(projectList);

        //Create expected result
        List<ResourceDTO> listExpected = new ArrayList<>();
        listExpected.add(new ResourceDTO("deborah@hotmail.com", "Team Member"));
        listExpected.add (new ResourceDTO("pedro@example.com", "Team Member"));

        //Act
        List<ResourceDTO> listResult = listResourcesController.listResources(1);

        //Assert
        assertEquals(listExpected, listResult);
    }
}