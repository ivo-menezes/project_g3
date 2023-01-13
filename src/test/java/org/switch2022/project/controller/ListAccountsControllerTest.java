package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;

import static org.junit.jupiter.api.Assertions.*;

class ListAccountsControllerTest {

    @Test
    @DisplayName("Test whether the Controller can create the Account DTO list")
    void testForCreatingAccountDTOList(){
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profileTest);
        Account accountThree = new Account("Pedro","zzzzz@gmail.com","22255588", profileTest);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        accountList.addAccount(accountThree);

        ListAccountsController controller = new ListAccountsController(accountList);

        controller.listAccounts(accountList);

        assertNotNull(controller);
    }

}