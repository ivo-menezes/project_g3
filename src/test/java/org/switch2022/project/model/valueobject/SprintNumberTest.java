package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SprintNumberTest {

    @Test
    @DisplayName(("Test for creation of SprintNumber"))
    public void checkIfClassCreatesValidSprinNumber(){
        new SprintNumber(1);
    }

    @Test
    @DisplayName(("Test for creation SprintNumber with zero value should throw Exception "))
    public void checkIfZeroThrowsException(){
        //Arrange
        int sprintNumber = 0;
        String expectedMessage = "Sprint number has to be higher than 0." ;

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new SprintNumber(sprintNumber);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);

        //Act

        boolean isEqual = sprintNumberOne.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two equals object are equal")
    public void checkIfUserPrintNumberEqualsItselfWillNotReturnFalse() {

        //Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintNumber sprintNumberTwo = sprintNumberOne;

        //Act

        boolean isEqual = sprintNumberOne.equals(sprintNumberTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two Sprint with same number are equal")
    public void checkIfSprintNumberEqualsWithSameNumberAreEqual() {

        //Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintNumber sprintNumberTwo = new SprintNumber(1);

        //Act

        boolean isEqual = sprintNumberOne.equals(sprintNumberTwo);

        // Assert
        assertTrue(isEqual);
    }



    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals(){
        //Arrange
        SprintNumber sprintNumberOne = new SprintNumber(1);
        SprintNumber sprintNumberTwo = new SprintNumber(2);

        //Act

        boolean isNotEqual = sprintNumberOne.equals(sprintNumberTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }

    @Test
    @DisplayName("Ensure Sprint Number is returned")
    void ensureSprintNumberIsReturned(){
        //Arrange
        int expected = 1;
        SprintNumber sprintNumber = new SprintNumber(expected);

        //Act
        long result = sprintNumber.getValue();

        //Assert
        assertEquals(expected, result);
    }


}