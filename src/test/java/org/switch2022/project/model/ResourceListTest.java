package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.mapper.ResourceDTO;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.role.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResourceListTest {

    @Test
    @DisplayName("Ensure the resource has been added.")
    void addResourceIsSuccessful() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");
        ResourceList resourceList = new ResourceList();

        //Act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure the same resource cannot been added twice")
    void addSameResourceFails() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");
        ResourceList resourceList = new ResourceList();

        //Act
        resourceList.addResource(account, role, project, resourceDTO);
        boolean result = resourceList.addResource(account, role, project, resourceDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure more than one team member can be added")
    void addTwoTeamMembersIsSuccessful() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO firstResourceDTO = mock(ResourceDTO.class);
        ResourceDTO secondResourceDTO = mock(ResourceDTO.class);
        Account firstAccount = mock(Account.class);
        Account secondAccount = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");
        ResourceList resourceList = new ResourceList();

        //Act
        resourceList.addResource(firstAccount, role, project, firstResourceDTO);
        boolean result = resourceList.addResource(secondAccount, role, project, secondResourceDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure a PO can be successfully added")
    void addPOSuccess() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO resourceDTO = mock(ResourceDTO.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        ResourceList resourceList = new ResourceList();

        //Act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure a second PO cannot be added")
    void addSecondPOFails() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO firstResourceDTO = mock(ResourceDTO.class);
        ResourceDTO secondResourceDTO = mock(ResourceDTO.class);
        Account firstAccount = mock(Account.class);
        Account secondAccount = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        ResourceList resourceList = new ResourceList();

        //Act
        resourceList.addResource(firstAccount, role, project, firstResourceDTO);
        boolean result = resourceList.addResource(secondAccount, role, project, secondResourceDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure a second SM cannot be added")
    void addSecondSMFails() {
        //Arrange
        Project project = mock(Project.class);
        ResourceDTO firstResourceDTO = mock(ResourceDTO.class);
        ResourceDTO secondResourceDTO = mock(ResourceDTO.class);
        Account firstAccount = mock(Account.class);
        Account secondAccount = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Scrum Master");
        ResourceList resourceList = new ResourceList();

        //Act
        resourceList.addResource(firstAccount, role, project, firstResourceDTO);
        boolean result = resourceList.addResource(secondAccount, role, project, secondResourceDTO);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("get a list of resources as a list of DTOs")
    void getAListOfResources() {
        //Arrange
        Project project = mock(Project.class);
        Account firstAccount = mock(Account.class);
        Mockito.when(firstAccount.getEmail()).thenReturn("deborah@hotmail.com");
        Account secondAccount = mock(Account.class);
        Mockito.when(secondAccount.getEmail()).thenReturn("pedro@example.com");
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");

        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(10-03-2023), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com", "Team Member", 1, new Date(10-03-2021), 50, 95);
        ResourceList resourceList = new ResourceList();
        resourceList.addResource(firstAccount, role, project, resourceDTO);
        resourceList.addResource(secondAccount, role, project, resourceDTO1);

        List<ResourceDTO> listExpected = new ArrayList<>();
        ResourceDTO resourceDTO_Output = new ResourceDTO("deborah@hotmail.com", "Team Member");
        ResourceDTO resourceDTO1_Output = new ResourceDTO("pedro@example.com", "Team Member");
        listExpected.add(resourceDTO_Output);
        listExpected.add(resourceDTO1_Output);

        //Act
        List<ResourceDTO> listResult = resourceList.listResources();

        //Assert
        assertEquals(listExpected, listResult);
    }

}