package org.switch2022.project.model;

import java.util.Objects;

public class Account {
    private String name;
    private String email;
    private String phone;
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
        if (name == null || email == null || phone == null || photo == null) {
            throw new IllegalArgumentException("Name/Email/Phone are mandatory details.");
        }
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.profile = profile;
    }

    /***
     * This should return an email from a specific account given.
     * @param account
     * @return email
     */
    public String getEmail(Account account) {
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
        return isActive == account.isActive && name.equals(account.name) && email.equals(account.email) && phone.equals(account.phone) && Objects.equals(photo, account.photo) && profile.equals(account.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, photo, profile, isActive);
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void inactivateAccount() {
        this.isActive = false;
    }

    public void activateAccount() {
        this.isActive = true;
    }

    public boolean getStatus() {
        // Should all public get methods return a copy of attributes, instead
        // of the attributes themselves?
        boolean cloneIsActive = isActive;

        return cloneIsActive;
    }


}
