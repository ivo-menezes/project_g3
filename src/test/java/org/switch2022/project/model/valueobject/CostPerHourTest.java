package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostPerHourTest {

    String expectedMessage = "CostPerHour cannot be negative";
    double cost1 = 2;
    double cost0 = 0;
    double cost2 = 3.3;
    double costNegative = -1;
    double anotherCostNegative = -5.3;

    @Test
    @DisplayName("creating a valid costPerHour")
    void createValidCostPerHour() {
        new CostPerHour(cost1);
    }

    @Test
    @DisplayName("creating a costPerHour with value 0")
    void createValidCostPerHourWithValue0() {
        new CostPerHour(cost0);
    }

    @Test
    @DisplayName("creating costPerHour with value less than zero should generate exception")
    void CreatingCostPerHourWithValueLessThanZeroThrowsException() {

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CostPerHour(costNegative);
        });

        String resultMessage = result.getMessage();


        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    void testConstructorWithNegativeValue() {

        try {
            new CostPerHour(anotherCostNegative);
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    @DisplayName("CostPerHour object equals another with same value")
    public void checkIfCreatingWithSameCostPerHourWillResultInEqualObjects(){

        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost1);

        assertEquals(costPerHour1, costPerHour2);
    }

    @Test
    @DisplayName("CostPerHour object not equals another with same value")
    public void checkIfCreatingWithSameCostPerHourWillResultInNotEqualObjects(){

        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost2);

        assertNotEquals(costPerHour1, costPerHour2);
    }

    @Test
    @DisplayName("Test for CostPerHour with same value won't cause issue")
    public void checkIfCreatingWithSameCostPerHourWillNotResultInError(){
        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost1);

        assertNotEquals(false, costPerHour1.equals(costPerHour2));
    }

    @Test
    @DisplayName(("Test for different CostPerHour objects won't result in true "))
    public void checkIfCreatingWithAnotherCostPerHourWillNotResultInTrue(){
        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost2);

        assertNotEquals(true, costPerHour1.equals(costPerHour2));
    }

    @Test
    @DisplayName("Ensure that two costPerHour objects are equals.")
    void twoCostPerHourEquals() {

        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost1);

        assertTrue(costPerHour1.equals(costPerHour2));
    }

    @Test
    @DisplayName("Ensure that two costPerHour objects are not equals.")
    void twoCostPerHourNotEquals() {

        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost2);

        assertFalse(costPerHour1.equals(costPerHour2));
    }

    @Test
    @DisplayName("Get the value of the costPerHour")
    void getValueShouldReturnTheCostPerHourValue(){

        CostPerHour costPerHour = new CostPerHour(cost1);

        assertEquals(costPerHour.getValue(), cost1);
    }

    @Test
    void hashCode_ShouldReturnSameValueForEqualObjects() {

        CostPerHour costPerHour1 = new CostPerHour(cost2);
        CostPerHour costPerHour2 = new CostPerHour(cost2);

        int hashCode1 = costPerHour1.hashCode();
        int hashCode2 = costPerHour2.hashCode();

        assertEquals(hashCode1, hashCode2, "hashCode() should return the same value for equal objects");
    }

    @Test
    void hashCode_ShouldReturnDifferentValueForDifferentObjects() {

        CostPerHour costPerHour1 = new CostPerHour(cost1);
        CostPerHour costPerHour2 = new CostPerHour(cost2);

        int hashCode1 = costPerHour1.hashCode();
        int hashCode2 = costPerHour2.hashCode();

        assertNotEquals(hashCode1, hashCode2, "hashCode() should return a different value for different objects");
    }
}