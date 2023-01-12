package org.switch2022.project.model;

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
     * The test is not working properly yet. Not sure why
     */
    @Test
    void getAccount() {
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);

        Account result = accountList.getAccount("xxxxx@gmail.com");

        assertNotSame(accountTwo, result);
        assertEquals(account, result);
    }
}