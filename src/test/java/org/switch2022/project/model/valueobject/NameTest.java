package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName(("Test for creation of name"))
    public void checkIfClassCreatesValidName() {
        new Name("teste");
    }


    @DisplayName("creating name with null value should throw Exception")
    @Test
    void createNameWithNullThrowsException() {
        // Arrange
        String name = null;
        String expectedMessage = "Name cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });

        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("creating name with empty value should throw Exception")
    @Test
    void createNameWithEmptyThrowsException() {
        // Arrange
        String name = "";
        String expectedMessage = "Name cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating name with blank value should throw Exception")
    @Test
    void createNameWithBlankThrowsException() {
        // Arrange
        String name = "       ";
        String expectedMessage = "Name cannot be null/blank/empty";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        Name name = new Name("Test");

        //Act

        boolean isEqual = name.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two equals object are equal")
    public void checkIfNameEqualsItselfWillNotReturnFalse() {

        //Arrange
        Name name = new Name("Test");
        Name nameTwo = name;

        //Act

        boolean isEqual = name.equals(nameTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two names with same name are equal")
    public void checkIfNameEqualsWithSameNameAreEqual() {

        //Arrange
        Name name = new Name("Test");
        Name nameTwo = new Name("Test");

        //Act

        boolean isEqual = name.equals(nameTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals() {
        //Arrange
        Name name = new Name("Test");
        Name nameTwo = new Name("TestTwo");

        //Act

        boolean isNotEqual = name.equals(nameTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        Name nameOne = new Name("NameOne");
        Name nameTwo = new Name("NameTwo");

        //Act
        int hashCode1 = nameOne.hashCode();
        int hashCode2 = nameTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }


}