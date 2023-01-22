package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceListTest {

    @Test
    @DisplayName("ensure the resource has been added.")
    void ensureAddResourceTrue() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role(1, "Team Member");
        ResourceList resourceList = new ResourceList();

        //act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure the same resource cannot been added twice")
    void ensureAddResourceFalse() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role(1, "Team Member");
        ResourceList resourceList = new ResourceList();

        //act
        boolean added = resourceList.addResource(account, role, project, resourceDTO);
        boolean result = resourceList.addResource(account,role,project,resourceDTO);

        //assert
        assertTrue(added);
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure more than one team member can be added")
    void addTwoTeamMembersIsSuccessful() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com", 1, 1, new Date(),50,95);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Account account1 = new Account("Pedro", "pedro@example.com", "9666666", new Profile("User"));
        Role role = new Role(1, "Team Member");
        ResourceList resourceList = new ResourceList();
        //act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);
        boolean result1 = resourceList.addResource(account1, role, project, resourceDTO1);
        // assert
        assertTrue(result);
        assertTrue(result1);
    }

    @Test
    @DisplayName("ensure a PO can be successfully added")
    void addPOSuccess() {
        // arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role(1, "Product Owner");
        ResourceList resourceList = new ResourceList();
        // act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure a second PO cannot be added")
    void addSecondPOFails() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com",1,1,new Date(),50,95);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Account account1 = new Account("Pedro", "pedro@example.com", "966666", new Profile("User"));
        Role role = new Role(1, "Product Owner");
        ResourceList resourceList = new ResourceList();
        // act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);
        boolean result1 = resourceList.addResource(account1, role, project, resourceDTO1);
        // assert
        assertTrue(result);
        assertFalse(result1);
    }

    @Test
    @DisplayName("ensure a second SM cannot be added")
    void addSecondSMFails() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", 1, 1, new Date(), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com",1,1,new Date(),50,95);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Account account1 = new Account("Pedro", "pedro@example.com", "966666", new Profile("User"));
        Role role = new Role(1, "Scrum Master");
        ResourceList resourceList = new ResourceList();
        // act
        boolean result = resourceList.addResource(account, role, project, resourceDTO);
        boolean result1 = resourceList.addResource(account1, role, project, resourceDTO1);
        // assert
        assertTrue(result);
        assertFalse(result1);
    }
}