package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;

import static org.junit.jupiter.api.Assertions.*;

class ChangeProfileControllerTest {

    @Test
    @DisplayName("ensure that Profile is changed in Account")
    void changeProfile() throws Exception {
        //assert
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");
        ProfileList list = new ProfileList();
        list.add(profile);
        list.add(profileTest);
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);
        ChangeProfileController controller = new ChangeProfileController(accountList, list);


        /* arrange */
        controller.changeProfile("yyyyyy@gmail.com", "Manager");
        /* act */
        assertEquals(account, accountTwo);
    }
}