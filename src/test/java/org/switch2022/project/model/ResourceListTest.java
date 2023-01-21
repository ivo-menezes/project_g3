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
    @DisplayName("ensure the resource has not been added.")
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
        assertFalse(result);
    }
}