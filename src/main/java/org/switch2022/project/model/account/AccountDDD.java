package org.switch2022.project.model.account;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.Objects;

public class AccountDDD implements AggregateRoot<AccountID> {
    private final AccountID accountID;
    private final Email email;
    private final Name name;
    private final PhoneNumber phoneNumber;
    private Photo photo;
    private final ProfileName profile;
    private final AccountStatus accountStatus;

    public AccountDDD (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Photo photo,
                       ProfileName profile){
        this(accountID, email, name, phoneNumber, photo, profile, AccountStatus.ACTIVE);
    }

    public AccountDDD (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, ProfileName profile){
        this(accountID, email, name, phoneNumber, profile, AccountStatus.ACTIVE);
    }

    public AccountDDD (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber, Photo photo,
                       ProfileName profile, AccountStatus accountStatus) {
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
        this.accountID = accountID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.profile = profile;
        this.accountStatus = accountStatus;
    }

    public AccountDDD (AccountID accountID, Email email, Name name, PhoneNumber phoneNumber,
                       ProfileName profile, AccountStatus accountStatus) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (profile == null) {
            throw new IllegalArgumentException("Profile cannot be null");
        }
        this.accountID = accountID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.accountStatus = accountStatus;
    }

    public AccountID identity() {return accountID; }

    public Email getEmail() { return email; }

    public Name getName() {
        return name;
    }
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Photo getPhoto() {
        return photo;
    }

    public ProfileName getProfile() {
        return profile;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDDD)) return false;
        AccountDDD that = (AccountDDD) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public boolean isUser(ProfileName profile){
        boolean isUser= false;
        String profileName = profile.toString();

        if (profileName.equals("User")) {
            isUser = true;
        }
        return isUser;
    }
}
