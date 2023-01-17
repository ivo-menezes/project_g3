package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountDTO;
import org.switch2022.project.model.AccountList;
import org.switch2022.project.model.ProfileList;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsController {

    private static AccountList accountList;
    private List<AccountDTO> AccountDTOList;
    public ListAccountsController(AccountList accountList){
        ListAccountsController.accountList = accountList;
    }

    /***
     * The methods receives an accountList and will create a list of accountDTOs to send.
     * containing only email and status of each account in the list.
     * @param accountList
     * @return
     */
    public List listAccounts(AccountList accountList){
        AccountDTOList = new ArrayList<>();
        Account account = null;

        int listSize = this.accountList.listSize();

        for(int index = 0; index < listSize; index++){
            account = accountList.getAccountAtIndex(index);
            AccountDTOList.add(accountList.createAccountDTO(account));
        }
        return AccountDTOList;
    }
}
