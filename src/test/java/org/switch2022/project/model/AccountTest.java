package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testEqualsAndSame() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTest = account;

        assertTrue(account.equals(accountTest));
        assertSame(accountTest, account);
    }

    @Test
    void testHashCode() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTest = account;

        assertEquals(accountTest.hashCode(), account.hashCode());
    }
    @Test
    void checkIfNameIsNull(){

    }

    @Test
    void checkIfProfileIsSet() {
        // arrange
        Profile firstProfile = new Profile("User");
        Profile secondProfile = new Profile("Manager");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", firstProfile);
        Account testAccount = new Account("Joana", "xxxxx@gmail.com","22255588", secondProfile);
        // act
        account.setProfile(secondProfile);
        // assert
        assertEquals(testAccount, account);
    }
    @Test
    void testIfGetsEmail(){
        Profile firstProfile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", firstProfile);
        String expected = "xxxxx@gmail.com";

        String email = account.seeEmail(account);

        assertEquals(expected, email);
    }
    @Test
    @DisplayName("ensure status is inactivated")
    void ensureStatusIsInactived(){
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account ("Joana", "xxxxx@gmail.com", "22255588", profile);
        // act
        account.inactivateAccount();
        // assert
        assertFalse(account.getStatus());
    }

    @Test
    @DisplayName("ensure status is reactivated")
    void ensureStatusIsReactivated(){
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account ("Joana", "xxxxx@gmail.com", "22255588", profile);
        // act
        account.activateAccount();
        // assert
        assertTrue(account.getStatus());
    }
}