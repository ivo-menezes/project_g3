package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.mapper.ResourceDTO;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.resource.Resource;
import org.switch2022.project.model.role.Role;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResourceTest {

    @Test
    @DisplayName("Ensure the account is a resource")
    void isAccountOfResourceTrue() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("Manager");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");
        Resource resource = new Resource(role, account, project, new Date(10-03-2021), 50, 80);

        //Act
        boolean result = resource.isAccountOfResource(account);

        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("Ensure the account is not a resource")
    void isAccountOfResourceFalse() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("Manager");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Account secondAccount = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Team Member");
        Resource resource = new Resource(role, account, project, new Date(10-03-2021), 50, 80);

        //Act
        boolean result = resource.isAccountOfResource(secondAccount);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure hasRole returns true if role matches")
    void hasRoleIsTrue() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(23-01-2023), 50, 80);

        //Act
        boolean result = resource.hasRole(role);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure hasRole returns false if role doesn't match")
    void hasRoleIsFalse() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Role anotherRole = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Scrum Master");
        Resource resource = new Resource(role, account, project, new Date(23-01-2023), 50, 80);

        //Act
        boolean result = resource.hasRole(anotherRole);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure isActive returns true if endDate is null")
    void isActiveIsTrue() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(10-03-2023), 50, 80);

        //Act
        boolean result = resource.isActive();

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure isActive returns false if endDate is not null")
    void isActiveIsFalse() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(),50, 80);
        Date endDate = new Date();

        //Act
        resource.setEndDate(endDate);
        boolean result = resource.isActive();

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Retrieve email from resource account")
    void retrieveEmailFromResource(){
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Mockito.when(account.getEmail()).thenReturn("deborah@gmail.com");
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        String emailExpected = "deborah@gmail.com";

        //Act
        String emailResult = resource.getEmailOfResource();

        //Assert
        assertEquals(emailExpected, emailResult);
    }
    @Test
    @DisplayName("Get role from resource")
    void getRoleFromResource() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        String roleExpected = "Product Owner";

        //Act
        String roleResult = resource.getRole();

        //Assert
        assertEquals(roleExpected, roleResult);
    }
    @Test
    @DisplayName("Create resourceDTO")
    void createResourceDTO() {
        //Arrange
        Profile profile = mock(Profile.class);
        Mockito.when(profile.getProfileName()).thenReturn("User");
        Project project = mock(Project.class);
        Account account = mock(Account.class);
        Mockito.when(account.getEmail()).thenReturn("deborah@gmail.com");
        Role role = mock(Role.class);
        Mockito.when(role.getName()).thenReturn("Product Owner");

        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        ResourceDTO expected = new ResourceDTO("deborah@gmail.com", "Product Owner");

        //Act
        ResourceDTO result = resource.createResourceDTO();

        //Assert
        assertEquals(expected, result);
    }
}