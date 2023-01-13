package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.controller.ChangeProfileController;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    /***
     * Testing both o.equal method and hashcode methods of the Account Class.
     */
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

    /***
     * Here we test whether the method of changing profile of an account works.
     */
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

    /***
     * The method getEmail is used simply to give an account and see what is its email.
     */
    @Test
    void testIfGetsEmail(){
        Profile firstProfile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", firstProfile);
        String expected = "xxxxx@gmail.com";

        String email = account.getEmail(account);

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