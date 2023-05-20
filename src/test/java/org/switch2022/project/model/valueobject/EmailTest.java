package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName(("Test for creation of email"))
    public void checkIfClassCreatesValidPhoneNumber() {
        new Email("test@gmail.com");
    }

    @DisplayName("creating email with null value should throw Exception")
    @Test
    void createEmailWithNullThrowsException() {
        //Arrange
        String email = null;
        String expectedMessage = "Email cannot be null/empty/blank";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Email(email);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("creating email with empty value should throw Exception")
    @Test
    void createEmailWithEmptyThrowsException() {
        //Arrange
        String email = "";
        String expectedMessage = "Email cannot be null/empty/blank";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Email(email);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating email with blank value should throw Exception")
    @Test
    void createPhoneEmailWithBlankThrowsException() {
        //Arrange
        String email = "      ";
        String expectedMessage = "Email cannot be null/empty/blank";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Email(email);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("creating a not email should throw Exception")
    @Test
    void createNotValidEmailThrowsException() {
        //Arrange
        String email = "test.gmail";
        String expectedMessage = "This is not a valid Email";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Email(email);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        Email email = new Email("test@gmail.com");

        //Act

        boolean isEqual = email.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two equals object are equal")
    public void checkIfEmailEqualsItselfWillNotReturnFalse() {

        //Arrange
        Email emailOne = new Email("test@gmail.com");
        Email emailTwo = emailOne;

        //Act

        boolean isEqual = emailOne.equals(emailTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two phone number with same number are equal")
    public void checkIfEmailIsEqualsWithSameEmailAreEqual() {

        //Arrange
        Email emailOne = new Email("test@gmail.com");
        Email emailTwo = new Email("test@gmail.com");

        //Act

        boolean isEqual = emailOne.equals(emailTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals() {
        //Arrange
        Email emailOne = new Email("test@gmail.com");
        Email emailTwo = new Email("testtwo@gmail.com");

        //Act

        boolean isNotEqual = emailOne.equals(emailTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        Email emailOne = new Email("test@gmail.com");
        Email emailTwo = new Email("testtwo@gmail.com");
        //Act
        int hashCode1 = emailOne.hashCode();
        int hashCode2 = emailTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

}