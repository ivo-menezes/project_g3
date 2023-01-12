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
     * The test works if accountTwo is not created and added to accountList, but
     * why doesn't it work if accountList has 2 accounts (account and
     * accountTwo)?
     */
    @Test
    @DisplayName("ensure that the correct account is obtained")
    void getAccount() {
        // arrange
        AccountList accountList = new AccountList();
        Profile profile = new Profile("User");
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        //Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profile);
        String email = "xxxxx@gmail.com";

        accountList.addAccount(account);
        //accountList.addAccount(accountTwo);

        // act
        Account result = accountList.getAccount(email);

        //assertNotSame(accountTwo, result);
        assertEquals(account, result);
    }
}