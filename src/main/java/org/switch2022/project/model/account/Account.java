package org.switch2022.project.model.account;

import org.switch2022.project.mapper.RegisterAccountDTO;
import org.switch2022.project.model.profile.Profile;

import java.util.Objects;

public class Account {
    private final String name;
    private final String email;
    private final String phone;
    private String photo;
    private Profile profile;
    private boolean isActive = true;

    public Account(String name, String email, String phone, Profile profile) {
        if (name == null || email == null || phone == null) {
            throw new IllegalArgumentException("Name/Email/Phone are mandatory details.");
        }
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

    public Account(String name, String email, String phone, String photo, Profile profile) {
        this(name, email, phone, profile);
        this.photo = photo;
    }

    public Account(RegisterAccountDTO dto, Profile profile) {
        if (dto == null || profile == null) {
            throw new IllegalArgumentException("Account information must not be null");
        }

        this.name = dto.name;
        this.email = dto.email;
        this.phone = dto.phone;
        this.photo = dto.photo;
        this.profile = profile;
    }

    /***
     * This should return an email from a specific account given.
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, profile);
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean inactivateAccount() {
        boolean statusChanged = false;
        if (this.isActive) {
            this.isActive = false;
            statusChanged = true;
        }
        return statusChanged;
    }

    public boolean activateAccount() {
        boolean statusChanged = false;
        if (!this.isActive) {
            this.isActive = true;
            statusChanged = true;
        }
        return statusChanged;
    }

    public boolean getStatus() {
        return this.isActive;
    }

    /**
     * Method to check if an account has user profile.
     * @return true if the account has user profile, false otherwise.
     */
    public boolean isUser(){
        boolean isUser= false;

        if (this.profile.getProfileName().equals("User")) {
            isUser = true;
        }
        return isUser;
    }


}
