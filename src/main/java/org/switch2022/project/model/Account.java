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
}
