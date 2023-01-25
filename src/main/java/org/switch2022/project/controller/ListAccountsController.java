package org.switch2022.project.controller;

import org.switch2022.project.model.Account;
import org.switch2022.project.model.AccountDTO;
import org.switch2022.project.model.AccountList;

import java.util.ArrayList;
import java.util.List;

public class ListAccountsController {
    private AccountList accountList;
    public ListAccountsController(AccountList accountList){
        if(accountList.listSize()<=0){
            throw new IllegalArgumentException("List is empty");
        }
        this.accountList = accountList;
    }

    /***
     * The methods receives an accountList and will create a list of accountDTOs to send.
     * containing only email and status of each account in the list.
     * @return AccountListDTO
     */
    public List listAccounts(AccountList accountList) {
        List<AccountDTO> listAccountDTO = new ArrayList<>();
        Account account;
        this.accountList = accountList;

        int listSize = this.accountList.listSize();

            for(int index = 0; index < listSize; index++) {
            account = this.accountList.getAccountAtIndex(index);
            listAccountDTO.add(this.accountList.createAccountDTO(account));
        }
        return listAccountDTO;
    }
}
