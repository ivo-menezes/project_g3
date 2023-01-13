package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountList {
        private List<Account> accountList;

        public AccountList(){
            this.accountList = new ArrayList<>();
        }
        public AccountList(List<Account> accountList){
            if (accountList == null) {
                throw new IllegalArgumentException("Account List must not be null.");
            }
            this.accountList = accountList;
        }

        public boolean addAccount(Account account){
            if (account == null) {
                throw new IllegalArgumentException("Account must not be null");
            }
            return this.accountList.add(account);
        }

        /***
         * This is supposed to find an account in the list and get it out for other methods
         * @param email
         * @return account
         */
        public Account getAccount(String email){
            Account account = null;

        for(int index = 0; index < this.accountList.size() && account == null; index++){
            Account a = this.accountList.get(index);
            String aEmail = a.getEmail(a);
            if(aEmail == email){
                account = a;
            }
        }
            return account;
        }

    /***
     * For the US004, needed to access the accountList size, but the .size didn't work.
     * Created the method listSize in order to give the Controller the int value
     * of the list size.
     * @return
     */
        public int listSize(){
            return this.accountList.size();
        }

    /***
     * For US004, this will access the account in the index given.
     * @param index
     * @return
     */
    public Account getAccountAtIndex(int index){
            Account account = this.accountList.get(index);
            return account;
        }

    /***
     * The method will be called to create an account DTO, using the data from the true account.
     * @param account
     * @return
     */
    public AccountDTO createAccountDTO(Account account){
        AccountDTO accountDTO =  new AccountDTO(account.getEmail(account), account.getStatus());

        return accountDTO;
    }
}
