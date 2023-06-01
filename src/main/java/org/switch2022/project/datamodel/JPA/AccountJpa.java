package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String name;

    private String phoneNumber;

    private String photo;

    private String profile;

    private String accountStatus;

    public AccountJpa(@NonNull String email, @NonNull String name, @NonNull String phoneNumber, @NonNull String photo,
                      @NonNull String profile, @NonNull String accountStatus) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.profile = profile;
        this.accountStatus = accountStatus;
    }

    public AccountJpa(@NonNull String email, @NonNull String name, @NonNull String phoneNumber,
                      @NonNull String profile, @NonNull String accountStatus) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profile = profile;
        this.accountStatus = accountStatus;
    }

    public AccountJpa() {
    }

    public Long identity() {
        return id;
    }

    public String getEmail() { return email; }

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public String getProfile() {
        return profile;
    }
    public String getAccountStatus() {
        return accountStatus;
    }

}
