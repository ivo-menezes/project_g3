package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListResourcesControllerTest {
    @Test
    @DisplayName("get a list of resources as a list of DTOs")
    void getAListOfResources() {
        // arrange
        // --Setup for project:
        Project project = new Project(1, "test", "test");
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Account account1 = new Account("Pedro", "pedro@example.com", "9666666", new Profile("User"));
        Role role = new Role("Team Member");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com", "Team Member", 1, new Date(),50,95);
        project.addResource(account, role, resourceDTO);
        project.addResource(account1, role, resourceDTO1);
        // --Add project to a projectList:
        ProjectList projectList = new ProjectList();
        projectList.addProject(project);
        // -- Set projectlist as an attribute of ListResourcesController
        ListResourcesController listResourcesController = new ListResourcesController(projectList);

        // --Setup for the expected output:
        List<ResourceDTO> listExpected = new ArrayList<>();
        ResourceDTO resourceDTO_Output = new ResourceDTO("deborah@hotmail.com", "Team Member");
        ResourceDTO resourceDTO1_Output = new ResourceDTO("pedro@example.com", "Team Member");
        listExpected.add(resourceDTO_Output);
        listExpected.add(resourceDTO1_Output);
        // act
        List<ResourceDTO> listResult = listResourcesController.listResources(1);
        // assert
        assertEquals(listExpected, listResult);
    }

}