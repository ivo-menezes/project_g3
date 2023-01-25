package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    @Test
    void ensureIsAccountOfResourceTrue() {
        //arrange
        Profile profile = new Profile("Manager");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Deborah", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Team Member");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);

        //act
        boolean result = resource.isAccountOfResource(account);

        //assert
        assertTrue(result);
    }
    @Test
    void ensureIsAccountOfResourceFalse() {
        //arrange
        Profile profile = new Profile("Manager");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Deborah", "xxxxx@gmail.com", "33399988", profile);
        Account accountTwo = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role( "Team Member");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);

        //act
        boolean result = resource.isAccountOfResource(accountTwo);

        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure hasRole returns true if role matches")
    void hasRoleIsTrue() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        // act
        boolean result = resource.hasRole(role);
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure hasRole returns false if role doesn't match")
    void hasRoleIsFalse() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Role anotherRole = new Role( "Scrum Master");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        // act
        boolean result = resource.hasRole(anotherRole);
        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure isActive returns true if endDate is null")
    void isActiveIsTrue() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        // act
        boolean result = resource.isActive();
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure isActive returns false if endDate is not null")
    void isActiveIsFalse() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        Date endDate = new Date();
        // act
        resource.setEndDate(endDate);
        boolean result = resource.isActive();
        // assert
        assertFalse(result);
    }
    @Test
    @DisplayName("retrieve email from resource account")
    void retrieveEmailFromResource(){
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        String emailExpected = "xxxxx@gmail.com";
        // act
        String emailResult = resource.getEmailOfResource();
        // assert
        assertEquals(emailExpected, emailResult);
    }
    @Test
    @DisplayName("get role from resorce")
    void getRoleFromResource() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        String roleExpected = "Product Owner";
        // act
        String roleResult = resource.getRole();
        // assert
        assertEquals(roleExpected, roleResult);
    }
    @Test
    @DisplayName("create resourceDTO")
    void createResourceDTO() {
        // arrange
        Profile profile = new Profile("User");
        Project project = new Project(1, "test", "test");
        Account account = new Account ("Pedro", "xxxxx@gmail.com", "33399988", profile);
        Role role = new Role("Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        ResourceDTO expected = new ResourceDTO("xxxxx@gmail.com", "Product Owner");
        // act
        ResourceDTO result = resource.createResourceDTO();
        // assert
        assertEquals(expected, result);
    }


}