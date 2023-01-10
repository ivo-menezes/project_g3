package org.switch2022.project.model;

public class Account {
    private String name;
    private String email;
    private String phone;
    private String photo;
    private Profile profile;
    private boolean isActive = true;

    public Account(String name, String email, String phone, Profile profile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

    public Account(String name, String email, String phone, String photo, Profile profile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
        this.profile = profile;
    }
}
