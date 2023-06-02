package org.switch2022.project.model.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountDDDTest {

    @Test
    @DisplayName("create a account successfully with all parameters")
    void createAccountWithAllParameters() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        //Act
        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Assert
        assertInstanceOf(AccountDDD.class, account);
    }

    @Test
    @DisplayName("create a account successfully without photo")
    void createAccountWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        //Act
        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                profileDouble);

        //Assert
        assertInstanceOf(AccountDDD.class, account);
    }

    @Test
    @DisplayName("Account constructor throws exception with null email")
    void nullEmailThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = null;
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Email cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            photoDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();
        //Assert

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor without photo throws exception with null email")
    void nullEmailThrowsExceptionWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = null;
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Email cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();
        //Assert

        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor throws exception with null name")
    void nullNameThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = null;
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Name cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            photoDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor without photo throws exception with null name")
    void nullNameThrowsExceptionWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = null;
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Name cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor throws exception with null phone number")
    void nullPhoneNumberThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = null;
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Phone number cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            photoDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor without photo throws exception with null phone number")
    void nullPhoneNumberThrowsExceptionWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = null;
        ProfileName profileDouble = mock(ProfileName.class);

        String expectedMessage = "Phone number cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor throws exception with null profile")
    void nullProfileThrowsException() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = null;

        String expectedMessage = "Profile cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            photoDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Account constructor without photo throws exception with null profile")
    void nullProfileThrowsExceptionWithoutPhoto() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        ProfileName profileDouble = null;

        String expectedMessage = "Profile cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new AccountDDD(accountIDDouble,
                            emailDouble,
                            nameDouble,
                            phoneNumberDouble,
                            profileDouble);
                });

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("object equals itself")
    void testEqualsWithItself() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        AccountDDD account1 = account;
        //Act
        boolean result = account.equals(account1);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal null")
    void testEqualsWithNull() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Act
        boolean result = account.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("object does not equal object of another class")
    void testEqualsWithAnotherClass() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        String fakeAccount = "Account1";
        //Act
        boolean result = account.equals(fakeAccount);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("object equals object with same id")
    void testEqualsWithEqualId() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        AccountDDD account1 = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Act
        boolean result = account.equals(account1);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal object with different id")
    void testEqualsWithDifferentId() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        AccountID accountIDDouble2 = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Email emailDouble2 = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        AccountDDD account1 = new AccountDDD(accountIDDouble2, emailDouble2, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Act
        boolean result = account.equals(account1);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("equal objects have same hash code")
    void testSameHashCode() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        AccountID accountIDDouble2 = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDouble2 = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDouble2 = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);
        ProfileName profileDouble2 = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        AccountDDD account1 = new AccountDDD(accountIDDouble2, emailDouble, nameDouble2, phoneNumberDouble2,
                photoDouble2, profileDouble2);

        //Act
        int accountHashCode = account.hashCode();
        int account1HashCode = account1.hashCode();

        //Assert
        assertEquals(accountHashCode, account1HashCode);
    }

    @Test
    @DisplayName("different objects have different hash code")
    void testDifferentHashCode() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        AccountID accountIDDouble2 = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Email emailDouble2 = mock(Email.class);
        Name nameDouble = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        PhoneNumber phoneNumberDouble2 = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        Photo photoDouble2 = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);
        ProfileName profileDouble2 = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        AccountDDD account1 = new AccountDDD(accountIDDouble2, emailDouble2, nameDouble2, phoneNumberDouble2,
                photoDouble2, profileDouble2);

        //Act
        int accountHashCode = account.hashCode();
        int account1HashCode = account1.hashCode();

        //Assert
        assertNotEquals(accountHashCode, account1HashCode);
    }

    @Test
    @DisplayName("ensure AccountID is returned")
    void ensureAccountIDIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        AccountID expected = accountIDDouble;

        //Act
        AccountID result = account.identity();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Email is returned")
    void ensureEmailIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        Email expected = emailDouble;

        //Act
        Email result = account.getEmail();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Name is returned")
    void ensureNameIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        Name expected = nameDouble;

        //Act
        Name result = account.getName();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Phone Number is returned")
    void ensurePhoneNumberIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        PhoneNumber expected = phoneNumberDouble;

        //Act
        PhoneNumber result = account.getPhoneNumber();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Photo is returned")
    void ensurePhotoIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        Photo expected = photoDouble;

        //Act
        Photo result = account.getPhoto();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Profile is returned")
    void ensureProfileIsReturned() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);
        ProfileName expected = profileDouble;

        //Act
        ProfileName result = account.getProfile();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure Account Status is returned")
    void ensureAccountStatusIsReturned() {
        //Arrange
        AccountDDD account = mock(AccountDDD.class);
        AccountStatus accountStatus = mock(AccountStatus.class);

        when(account.getAccountStatus()).thenReturn(accountStatus);

        //Act
        AccountStatus result = account.getAccountStatus();

        //Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("ensure that profile is user")
    void ensureProfileOfAccountIsUser() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        when(profileDouble.toString()).thenReturn("User");

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Act
        boolean expected = true;
        boolean result = account.isUser(profileDouble);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure that profile is not user")
    void ensureProfileOfAccountIsNotUser() {
        //Arrange
        AccountID accountIDDouble = mock(AccountID.class);
        Email emailDouble = mock(Email.class);
        Name nameDouble = mock(Name.class);
        PhoneNumber phoneNumberDouble = mock(PhoneNumber.class);
        Photo photoDouble = mock(Photo.class);
        ProfileName profileDouble = mock(ProfileName.class);

        when(profileDouble.toString()).thenReturn("Administrator");

        AccountDDD account = new AccountDDD(accountIDDouble, emailDouble, nameDouble, phoneNumberDouble,
                photoDouble, profileDouble);

        //Act
        boolean expected = false;
        boolean result = account.isUser(profileDouble);

        //Assert
        assertEquals(expected, result);
    }
}