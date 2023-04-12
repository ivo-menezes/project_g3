package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.ProfileList;

import static org.junit.jupiter.api.Assertions.*;

class ChangeProfileControllerTest {
    /***
     *The Controller will access the profilelist and accountlist, in order to find the correct Profile and the
     * Account we wish to change, based on the email.
     */

    @Test
    @DisplayName("ensure that Profile is changed in Account")
    void changeProfile(){
        //assert
        Profile profile = new Profile("User");
        Profile profileTest = new Profile("Manager");
        ProfileList list = new ProfileList();
        list.add(profile);
        list.add(profileTest);

        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        Account accountTwo = new Account("Joao","yyyyyy@gmail.com","44851114", profileTest);
        Account accountExpected = new Account("Joana","xxxxx@gmail.com","22255588", profileTest);

        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        accountList.addAccount(accountTwo);

        ChangeProfileController controller = new ChangeProfileController(accountList, list);

        // arrange
        assertTrue(controller.changeProfile("xxxxx@gmail.com", "Manager"));
        Account result = accountList.getAccountAtIndex(0);

        /* act */
        assertEquals(accountExpected, result);
        assertNotNull(result);
    }
}