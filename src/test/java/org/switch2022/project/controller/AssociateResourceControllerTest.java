package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AssociateResourceControllerTest {

    @Test
    @DisplayName("ensure the resource has been added.")
    void ensureAddResourceTrue() {
        //arrange
        AccountList accountList = new AccountList();
        RoleList roleList = new RoleList();
        ProjectList projectList = new ProjectList();
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com",1, 1,new Date(),25,100);
        AssociateResourceController associateResourceController = new AssociateResourceController(accountList,roleList,projectList);

        accountList.addAccount(new Account("Deborah", "deborah@hotmail.com", "938966782",new Profile("User")));
        roleList.addRole(new Role(1,"Team Member"));
        projectList.addProject(new Project(1,"test","test"));

        //act
        boolean result = associateResourceController.addResource(resourceDTO);

        //assert
        assertTrue(result);

    }

    @Test
    @DisplayName("ensure the resource has not been added. When the resource already exists.")
    void ensureAddResourceAlreadyExists() {
        //arrange
        AccountList accountList = new AccountList();
        RoleList roleList = new RoleList();
        ProjectList projectList = new ProjectList();
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com",1, 1,new Date(),25,100);
        AssociateResourceController associateResourceController = new AssociateResourceController(accountList,roleList,projectList);

        accountList.addAccount(new Account("Deborah", "deborah@hotmail.com", "938966782",new Profile("User")));
        roleList.addRole(new Role(1,"Team Member"));
        projectList.addProject(new Project(1,"test","test"));

        //act
        boolean Added = associateResourceController.addResource(resourceDTO);
        boolean result = associateResourceController.addResource(resourceDTO);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure the resource has not been added. When account is not user.")
    void ensureAddResourceAccountIsNotUser() {
        //arrange
        AccountList accountList = new AccountList();
        RoleList roleList = new RoleList();
        ProjectList projectList = new ProjectList();
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com",1, 1,new Date(),25,100);
        AssociateResourceController associateResourceController = new AssociateResourceController(accountList,roleList,projectList);

        accountList.addAccount(new Account("Deborah", "deborah@hotmail.com", "938966782",new Profile("Manager")));
        roleList.addRole(new Role(1,"Team Member"));
        projectList.addProject(new Project(1,"test","test"));

        //act
        boolean result = associateResourceController.addResource(resourceDTO);

        //assert
        assertFalse(result);
    }


}