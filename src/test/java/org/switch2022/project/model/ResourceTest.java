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
        Role role = new Role(1, "Team Member");
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
        Role role = new Role(1, "Team Member");
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
        Role role = new Role(1, "Product Owner");
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
        Role role = new Role(1, "Product Owner");
        Role anotherRole = new Role(2, "Scrum Master");
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
        Role role = new Role(1, "Product Owner");
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
        Role role = new Role(1, "Product Owner");
        Resource resource = new Resource(role, account, project, new Date(), 50, 80);
        Date endDate = new Date();
        // act
        resource.setEndDate(endDate);
        boolean result = resource.isActive();
        // assert
        assertFalse(result);
    }
}