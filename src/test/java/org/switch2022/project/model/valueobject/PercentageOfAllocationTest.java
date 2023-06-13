package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentageOfAllocationTest {

    String expectedMessage1 = "PercentageOfAllocation cannot be negative";
    String expectedMessage2 = "PercentageOfAllocation cannot be greater than 100";
    double percentageAlloc1 = 35.2;
    double percentageAlloc0 = 0;
    double percentageAlloc100 = 100;
    double percentageAlloc2 = 66.3;
    double percentageAllocNegative = -21.5;
    double percentageAllocInvalid = 120.3;

    @Test
    @DisplayName("creating a valid percentageOfAllocation")
    void createValidPercentageOfAllocation() {
        new PercentageOfAllocation(percentageAlloc1);
    }

    @Test
    @DisplayName("creating a valid percentageOfAllocation with value 0")
    void createValidPercentageOfAllocationWithValue0() {
        new PercentageOfAllocation(percentageAlloc0);
    }

    @Test
    @DisplayName("creating a valid percentageOfAllocation with value 0")
    void createValidPercentageOfAllocationWithValue100() {
        new PercentageOfAllocation(percentageAlloc100);
    }

    @Test
    @DisplayName("creating percentageOfAllocation with value less than zero should generate exception")
    void CreatingPercentageOfAllocationWithValueLessThanZeroThrowsException() {

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PercentageOfAllocation(percentageAllocNegative);
        });

        String resultMessage = result.getMessage();

        assertEquals(expectedMessage1, resultMessage);
    }

    @Test
    @DisplayName("creating percentageOfAllocation with value greater than 100 should generate exception")
    void CreatingPercentageOfAllocationWithValueGreaterThan100ThrowsException() {

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new PercentageOfAllocation(percentageAllocInvalid);
        });


        String resultMessage = result.getMessage();

        assertEquals(expectedMessage2, resultMessage);
    }

    @Test
    @DisplayName("PercentageOfAllocation object equals another with same value")
    public void checkIfCreatingWithSamePercentageOfAllocationWillResultInEqualObjects(){

        PercentageOfAllocation percentageOfAllocation1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation percentageOfAllocation2 = new PercentageOfAllocation(percentageAlloc1);
        boolean isEqual = percentageOfAllocation1.equals(percentageOfAllocation2);

        assertTrue(isEqual);
    }

    @Test
    @DisplayName("PercentageOfAllocation object not equals another with same value")
    public void checkIfCreatingWithSamePercentageOfAllocationWillResultInNotEqualObjects(){

        PercentageOfAllocation percentageOfAllocation1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation percentageOfAllocation2 = new PercentageOfAllocation(percentageAlloc2);
        boolean isEqual = percentageOfAllocation1.equals(percentageOfAllocation2);

        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Ensure that two percentageOfAllocation objects are the same.")
    void costPerHourEqualsSelf() {

        PercentageOfAllocation percentageOfAllocation1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation percentageOfAllocation2 = percentageOfAllocation1;

        assertSame(percentageOfAllocation1,percentageOfAllocation2);
    }


    @Test
    @DisplayName("Ensure that two percentageOfAllocation objects aren't the same.")
    void projectNotEqualsDifferentValue() {

        PercentageOfAllocation percentageOfAllocation1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation percentageOfAllocation2 = new PercentageOfAllocation(percentageAlloc2);

        assertNotSame(percentageOfAllocation1,percentageOfAllocation2);
    }

    @Test
    @DisplayName("Get the value of the percentageOfAllocation")
    void getValueShouldReturnTheCostPerHourValue(){

        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(percentageAlloc1);

        assertEquals(percentageOfAllocation.getValue(), percentageAlloc1);
    }

    @Test
    void hashCode_ShouldReturnSameValueForEqualObjects() {

        PercentageOfAllocation costPerHour1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation costPerHour2 = new PercentageOfAllocation(percentageAlloc1);

        int hashCode1 = costPerHour1.hashCode();
        int hashCode2 = costPerHour2.hashCode();

        assertEquals(hashCode1, hashCode2, "hashCode() should return the same value for equal objects");
    }

    @Test
    void hashCode_ShouldReturnDifferentValueForDifferentObjects() {

        PercentageOfAllocation p1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation p2 = new PercentageOfAllocation(percentageAlloc2);

        int hashCode1 = p1.hashCode();
        int hashCode2 = p2.hashCode();

        assertNotEquals(hashCode1, hashCode2, "hashCode() should return a different value for different objects");
    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {

        //Arrange
        PercentageOfAllocation p = new PercentageOfAllocation(percentageAlloc1);

        //Act

        boolean isEqual = p.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two equals object are equal")
    public void checkIfPercentageOfAllocationEqualItselfWillNotReturnFalse() {

        //Arrange

        PercentageOfAllocation p1 = new PercentageOfAllocation(percentageAlloc1);
        PercentageOfAllocation p2 = p1;

        //Act

        boolean isEqual = p1.equals(p2);

        // Assert
        assertTrue(isEqual);
    }

}