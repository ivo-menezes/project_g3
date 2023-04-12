package org.switch2022.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.Profile;
import org.switch2022.project.model.ProfileList;

import static org.junit.jupiter.api.Assertions.*;

class AccountStatusControllerTest {

    @Test
    @DisplayName("ensure account status is inactivated")
    void ensureAccountStatusIsInactivated() {
        //arrange
        Profile profile = new Profile("User");
        ProfileList list = new ProfileList();
        list.add(profile);
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        AccountStatusController controller = new AccountStatusController(accountList);
        // act
        boolean result = controller.changeAccountStatus("xxxxx@gmail.com", "inactive");
        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure activating active account returns false")
    void ensureActivatingActiveAccountReturnsFalse() {
        //arrange
        Profile profile = new Profile("User");
        ProfileList list = new ProfileList();
        list.add(profile);
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        AccountStatusController controller = new AccountStatusController(accountList);
        // act
        boolean result = controller.changeAccountStatus("xxxxx@gmail.com", "active");
        // assert
        assertFalse(result);
    }
    @Test
    @DisplayName("ensure changing to an invalid status fails")
    void ensureChangingToAnInvalidStatusFails() {
        // arrange
        Profile profile = new Profile("User");
        ProfileList list = new ProfileList();
        list.add(profile);
        Account account = new Account("Joana","xxxxx@gmail.com","22255588", profile);
        AccountList accountList = new AccountList();
        accountList.addAccount(account);
        AccountStatusController controller = new AccountStatusController(accountList);

        // act

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.changeAccountStatus("xxxxx@gmail.com", "blabla");
        });

        // assert
        assertEquals("Account status must be active or inactive.", exception.getMessage());

    }

}