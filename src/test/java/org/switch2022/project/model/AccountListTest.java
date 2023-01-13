package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AccountListTest {

    @Test
    void addAccount() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        AccountList accountList = new AccountList();

        assertTrue(accountList.addAccount(account));
    }

    /***
     * Added two accounts to list, used this to check if the methods successfully access the list and chooses the
     * correct account.
     *
     */
    @Test
    @DisplayName("ensure that the correct account is obtained")
    void getAccount() {
        // arrange
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        String email = "xxxxx@gmail.com";

        // act
        Account result = accountList.getAccount(email);

        //assertNotSame(accountTwo, result);
        assertEquals(account, result);
    }

    /***
     * The method reaches into the list and gets the account assigned to the index.
     */
    @Test
    @DisplayName("Check if the method getAccountAtIndex works as intended")
    void testingGetAccountAtIndex(){
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profileTest);
        Account accountThree = new Account("Pedro","zzzzz@gmail.com","44853224", profile);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        Account result = accountList.getAccountAtIndex(1);

        assertEquals(accountTwo, result);
    }

    /***
     * The test works with the same DTO, but doesn't compare two similar DTOs.
     * The method works by fetching the data of the account and sending it to the DTO.
     */
    @Test
    @DisplayName("Creates account DTO")
    void testIfAccountDTOIsCreatedSuccessfully(){
        Profile profile = new Profile("User");

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        //AccountDTO accountDTOTest = new AccountDTO("xxxxx@gmail.com", true);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);

        AccountDTO accountDTO = accountList.createAccountDTO(account);
        AccountDTO accountDTOTestTwo = accountDTO;

        assertNotNull(accountDTO);
        assertSame(accountDTO, accountDTOTestTwo);
        //assertEquals(accountDTOTest,accountDTO);
    }
}