package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;

public class AccountStatusController {
    private AccountList accountList;

    public AccountStatusController(AccountList accountList) {
        this.accountList = accountList;
    }

    public boolean changeAccountStatus(String email, String status) {
        boolean statusChanged = false;

        if (!status.equals("inactive") && !status.equals("active")) {
            throw new IllegalArgumentException("Account status must be active or inactive.");
        }

        Account account = accountList.getAccount(email);

        switch (status) {
            case "inactive":
                statusChanged = account.inactivateAccount();
                break;
            case "active":
                statusChanged = account.activateAccount();
                break;
        }
        return statusChanged;
    }
}
