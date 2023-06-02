package org.switch2022.project.model.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AccountFactoryImplTest {

    @DisplayName("assert that creating Account succeeds with all parameters")
    @Test
    void createAccountSucceedsWithAllParameters() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        //Act
        AccountDDD account = factory.createAccount(accountIDDouble, emailDouble,
                nameDouble, phoneNumberDouble, photoDouble, profileDouble);

        //Arrange
        assertInstanceOf(AccountDDD.class, account);
    }

    @DisplayName("assert that creating Account succeeds without photo")
    @Test
    void createAccountSucceedsWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        //Act
        AccountDDD account = factory.createAccount(accountIDDouble, emailDouble,
                nameDouble, phoneNumberDouble, profileDouble);

        //Arrange
        assertInstanceOf(AccountDDD.class, account);
    }

    @DisplayName("assert that trying to create account with null email throws Exception")
    @Test
    void createAccountNullEmailThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email email = null;
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Email cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, email, nameDouble, phoneNumberDouble, photoDouble, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null email and without photo throws Exception")
    @Test
    void createAccountNullEmailAndWithoutPhotoThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email email = null;
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Email cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, email, nameDouble, phoneNumberDouble, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null name throws Exception")
    @Test
    void createAccountNullNameThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name name = null;
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Name cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, name, phoneNumberDouble, photoDouble, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null name and without photo throws Exception")
    @Test
    void createAccountNullNameAndWithoutPhotoThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name name = null;
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Name cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, name, phoneNumberDouble, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null phone number throws Exception")
    @Test
    void createAccountNullPhoneNumberThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumber = null;
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Phone number cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, nameDouble, phoneNumber, photoDouble, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null phone number and without photo throws Exception")
    @Test
    void createAccountNullPhoneNumberAndWithoutPhotoThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumber = null;
        ProfileName profileDouble = mock(ProfileName.class);

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Phone number cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, nameDouble, phoneNumber, profileDouble);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null profile throws Exception")
    @Test
    void createAccountNullProfileThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profile = null;

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Profile cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble, photoDouble, profile);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create account with null profile and without photo throws Exception")
    @Test
    void createAccountNullProfileAndWithoutPhotoThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profile = null;

        AccountFactoryImpl factory = new AccountFactoryImpl();

        String expectedMessage = "Profile cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAccount(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble, profile);
        });

        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}