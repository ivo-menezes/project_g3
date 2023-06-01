package org.switch2022.project.model.account;

import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;

public class AccountFactoryImpl implements IAccountFactory {

    @Override
    public AccountDDD createAccount(AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Photo photo,
                                    Profile profile) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        AccountDDD accountDDD = new AccountDDD(accountID, email, name, phoneNumber, photo, profile);
        return accountDDD;
    }

    @Override
    public AccountDDD createAccount(AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Profile profile) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        AccountDDD accountDDD = new AccountDDD(accountID, email, name, phoneNumber, profile);
        return accountDDD;
    }
}