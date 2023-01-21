package org.switch2022.project.model;

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
        Resource resource = new Resource(role, account, project, new Date(), null, 50, 80);

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
        Resource resource = new Resource(role, account, project, new Date(), null, 50, 80);

        //act
        boolean result = resource.isAccountOfResource(accountTwo);

        //assert
        assertFalse(result);
    }
}