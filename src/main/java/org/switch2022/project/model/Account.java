package org.switch2022.project.model;

import java.util.Objects;

public class Account {
    private static String name;
    private static String email;
    private static String phone;
    private static String photo;
    private static Profile profile;
    private boolean isActive = true;

    public Account(String name, String email, String phone, Profile profile) {
        if(name == null || email == null || phone == null){
            throw new IllegalArgumentException("Name/Email/Phone are mandatory details.");
        }
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

    public Account(String name, String email, String phone, String photo, Profile profile) {
        if(name == null || email == null || phone == null || photo == null){
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
    public String seeEmail(Account account){
        return email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
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
}
