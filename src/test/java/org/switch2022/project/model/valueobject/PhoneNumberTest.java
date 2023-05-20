package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    @DisplayName(("Test for creation of phone number"))
    public void checkIfClassCreatesValidPhoneNumber() {
        new PhoneNumber("+351961234567");
    }

    @DisplayName("creating phone number with null value should throw Exception")
    @Test
    void createPhoneNumberWithNullThrowsException() {
        //Arrange
        String phoneNumber = null;
        String expectedMessage = "Phone number cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("creating phone number with empty value should throw Exception")
    @Test
    void createPhoneNumberWithEmptyThrowsException() {
        //Arrange
        String phoneNumber = "";
        String expectedMessage = "Phone number cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating phone number with blank value should throw Exception")
    @Test
    void createPhoneNumberWithBlankThrowsException() {
        //Arrange
        String phoneNumber = "     ";
        String expectedMessage = "Phone number cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("creating a not valid phone number should throw Exception (eight numbers)")
    @Test
    void createNotValidPhoneNumberThrowsExceptionEightNumbers() {
        //Arrange
        String phoneNumber = "+35196123456";
        String expectedMessage = "This is not a valid phone number";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("creating a not valid phone number should throw Exception (no country code)")
    @Test
    void createNotValidPhoneNumberThrowsExceptionNoCountryCode() {
        //Arrange
        String phoneNumber = "961234567";
        String expectedMessage = "This is not a valid phone number";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }
    @DisplayName("creating a not valid phone number should throw Exception (wrong country code)")
    @Test
    void createNotValidPhoneNumberThrowsExceptionWrongCountryCode() {
        //Arrange
        String phoneNumber = "+34961234567";
        String expectedMessage = "This is not a valid phone number";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PhoneNumber(phoneNumber);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }
    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        PhoneNumber phoneNumber = new PhoneNumber("+351961234567");

        //Act

        boolean isEqual = phoneNumber.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two equals object are equal")
    public void checkIfNameEqualsItselfWillNotReturnFalse() {

        //Arrange
        PhoneNumber phoneNumberOne = new PhoneNumber("+351961234567");
        PhoneNumber phoneNumberTwo = phoneNumberOne;

        //Act

        boolean isEqual = phoneNumberOne.equals(phoneNumberTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two phone number with same number are equal")
    public void checkIfPhoneNumberEqualsWithSamePhoneNumberAreEqual() {

        //Arrange
        PhoneNumber phoneNumberOne = new PhoneNumber("+351961234567");
        PhoneNumber phoneNumberTwo = new PhoneNumber("+351961234567");

        //Act

        boolean isEqual = phoneNumberOne.equals(phoneNumberTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals() {
        //Arrange
        PhoneNumber phoneNumberOne = new PhoneNumber("+351961234567");
        PhoneNumber phoneNumberTwo = new PhoneNumber("+351961234568");

        //Act

        boolean isNotEqual = phoneNumberOne.equals(phoneNumberTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        PhoneNumber phoneNumberOne = new PhoneNumber("+351961234567");
        PhoneNumber phoneNumberTwo = new PhoneNumber("+351961234568");
        //Act
        int hashCode1 = phoneNumberOne.hashCode();
        int hashCode2 = phoneNumberTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }



}