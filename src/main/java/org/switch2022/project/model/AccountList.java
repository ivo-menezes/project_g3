package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

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

        public Account getAccountAtIndex(int index){
            Account account = this.accountList.get(index);
            return account;
        }

}
