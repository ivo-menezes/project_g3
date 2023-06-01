package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountJpaTest {

    @Test
    @DisplayName("Ensure accountJpa is created with all parameters")
    void ensureAccountJpaIsCreatedWithAllParameters() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        //Act
        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Assert
        assertInstanceOf(AccountJpa.class, accountJpa);
    }

    @Test
    @DisplayName("Ensure accountJpa is created without photo")
    void ensureAccountJpaIsCreatedWithoutPhoto() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String profile = "User";
        String accountStatus = "Active";

        //Act
        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, profile, accountStatus);

        //Assert
        assertInstanceOf(AccountJpa.class, accountJpa);
    }

    @Test
    @DisplayName("Ensure AccountJpa is correctly created by no arguments constructor")
    void ensureAccountJpaIsCreatedByDefaultConstructor() {
        //Arrange & Act
        AccountJpa accountJpa = new AccountJpa();

        //Assert
        assertInstanceOf(AccountJpa.class, accountJpa);
    }

    @Test
    @DisplayName("Ensure email  is returned")
    void ensureEmailIsReturned() {
    //Arrange
    String email = "xpto@gmail.com";
    String name = "Ricardo";
    String phoneNumber = "+351912345678";
    String photo = ".";
    String profile = "User";
    String accountStatus = "Active";

    AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

    //Act
    String result = accountJpa.getEmail();

    //Assert
    assertEquals(email, result);
    }

    @Test
    @DisplayName("Ensure name  is returned")
    void ensureNameIsReturned() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Act
        String result = accountJpa.getName();

        //Assert
        assertEquals(name, result);
    }

    @Test
    @DisplayName("Ensure phoneNumber  is returned")
    void ensurePhoneNumberIsReturned() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Act
        String result = accountJpa.getPhoneNumber();

        //Assert
        assertEquals(phoneNumber, result);
    }

    @Test
    @DisplayName("Ensure photo  is returned")
    void ensurePhotoIsReturned() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Act
        String result = accountJpa.getPhoto();

        //Assert
        assertEquals(photo, result);
    }

    @Test
    @DisplayName("Ensure profile is returned")
    void ensureProfileIsReturned() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Act
        String result = accountJpa.getProfile();

        //Assert
        assertEquals(profile, result);
    }

    @Test
    @DisplayName("Ensure status  is returned")
    void ensureStatusIsReturned() {
        //Arrange
        String email = "xpto@gmail.com";
        String name = "Ricardo";
        String phoneNumber = "+351912345678";
        String photo = ".";
        String profile = "User";
        String accountStatus = "Active";

        AccountJpa accountJpa = new AccountJpa(email, name, phoneNumber, photo, profile, accountStatus);

        //Act
        String result = accountJpa.getAccountStatus();

        //Assert
        assertEquals(accountStatus, result);
    }
}