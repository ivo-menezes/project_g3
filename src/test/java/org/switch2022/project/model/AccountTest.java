package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertNotEquals(false, account.equals(accountTest));
    }

    @Test
    void testHashCode() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTest = account;

        assertEquals(accountTest.hashCode(), account.hashCode());
        assertNotEquals(0, account.hashCode());
    }
    @Test
    void checkIfNameIsNull(){
        Profile profile = new Profile("User");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Account account = new Account(null,"xxxxx@gmail.com","22255588", profile);
        });
        Assertions.assertEquals("Name/Email/Phone are mandatory details.", exception.getMessage());
    }
    @Test
    void checkIfEmailIsNull(){
        Profile profile = new Profile("User");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Account account = new Account("Joana",null,"22255588", profile);
        });
        Assertions.assertEquals("Name/Email/Phone are mandatory details.", exception.getMessage());
    }
    @Test
    void checkIfPhoneIsNull(){
        Profile profile = new Profile("User");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Account account = new Account("Joana","xxxx@gmail.com",null, profile);
        });
        Assertions.assertEquals("Name/Email/Phone are mandatory details.", exception.getMessage());
    }
    @Test
    void checkIfProfileIsNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Profile profile = new Profile(null);
            Account account = new Account("Joana","xxxx@gmail.com","22255588", profile);
        });
        Assertions.assertEquals("Profile Name is not valid", exception.getMessage());
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

        String email = account.getEmail();

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
    @DisplayName("ensure status is activated and catches issues in mutations")
    void ensureStatusIsActiveForMutation(){
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account ("Joana", "xxxxx@gmail.com", "22255588", profile);
        // act
        account.activateAccount();
        // assert
        assertNotEquals(false, account.getStatus());
    }
    @Test
    @DisplayName("ensure status is inactivated and catches issues in mutations")
    void ensureStatusIsInactiveForMutation(){
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account ("Joana", "xxxxx@gmail.com", "22255588", profile);
        // act
        account.inactivateAccount();
        // assert
        assertNotEquals(true, account.getStatus());
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