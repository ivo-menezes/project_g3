package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountList;

public class AccountStatusController {
    private final AccountList accountList;

    /**
     * constructor that accepts existing AccountList
     *
     * @param accountList AccountList to be added to controller
     */
    public AccountStatusController(AccountList accountList) {
        this.accountList = accountList;
    }

    /**
     * instructs a given account to change its status to active/inactive
     * @param email identifies account
     * @param status identifies desired status
     * @return true if status was successfully changed to desired status
     */
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
