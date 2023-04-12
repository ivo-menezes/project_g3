package org.switch2022.project.model;

import org.switch2022.project.mapper.AccountDTO;
import org.switch2022.project.mapper.RegisterAccountDTO;
import org.switch2022.project.model.account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountList {
    private List<Account> accountList;

    public AccountList() {
        this.accountList = new ArrayList<>();
    }

    public AccountList(List<Account> accountList) {
        if (accountList == null) {
            throw new IllegalArgumentException("Account List must not be null.");
        }
        this.accountList = accountList;
    }

    public boolean addAccount(Account account) {
        boolean isAdded = true;
        if (account == null) {
            isAdded = false;
        }
        this.accountList.add(account);
        return isAdded;
    }

    /***
     * Retrieves an account from the accountList given an email
     * @param email to be found
     * @return account
     */
    public Account getAccount(String email) {
        Account account = null;

        for (int index = 0; index < this.accountList.size() && account == null; index++) {
            Account a = this.accountList.get(index);
            String aEmail = a.getEmail();
            if (aEmail.equals(email)) {
                account = a;
            }
        }
        return account;
    }

    /***
     * For the US004, needed to access the accountList size, but the .size didn't work.
     * Created the method listSize in order to give the Controller the int value
     * of the list size.
     * @return number of elements in accountList
     */
    public int listSize() {
        return this.accountList.size();
    }

    /***
     * For US004, this will access the account in the index given.
     * @param index of element to be retrieved
     * @return account at index
     */
    public Account getAccountAtIndex(int index) {
        Account account = this.accountList.get(index);
        return account;
    }

    /***
     * The method will be called to create an account DTO, using the data from the true account.
     * @param account
     * @return accountDTO
     */
    public AccountDTO createAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(account.getEmail(), account.getStatus());

        return accountDTO;
    }

    /**
     * registers an account in accountList
     *
     * @param dto     RegisterAccountDTO with account information
     * @param profile to be associated with account
     * @return true if account successfully registered, false otherwise
     */
    public boolean registerAccount(RegisterAccountDTO dto, Profile profile) {
        if (dto == null || profile == null) {
            throw new IllegalArgumentException("Account information and profile needed.");
        }

        String email = dto.email;

        boolean accountStored = false;
        // only add account if email is NOT in use
        if (!isEmailInUse(email)) {
            Account account = new Account(dto, profile);
            accountStored = this.accountList.add(account);
        }

        return accountStored;
    }

    /**
     * checks if any account in accountList already uses the given email
     *
     * @param email to be searched
     * @return true if an account with the given email is found, false otherwise
     */
    public boolean isEmailInUse(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }

        boolean emailFound = false;
        for (int i = 0; i < this.accountList.size() && !emailFound; i++) {
            Account currentAccount = this.accountList.get(i);
            String currentEmail = currentAccount.getEmail();
            if (email.equals(currentEmail)) {
                emailFound = true;
            }
        }

        return emailFound;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountList that = (AccountList) o;
        return accountList.equals(that.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountList);
    }
}
