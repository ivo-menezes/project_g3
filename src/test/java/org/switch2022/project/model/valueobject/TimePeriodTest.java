package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimePeriodTest {

    private Date startDate;
    private Date endDate;
    private Date startDateTwo;

    @BeforeEach
    public void initDates() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 4);
        startDate = calendar.getTime();

        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        startDateTwo = calendar.getTime();

        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        endDate = calendar.getTime();
    }



    @Test
    @DisplayName(("Test for creation of Time Period"))
    public void checkIfClassCreatesValidTimePeriod() {

         //act
        new TimePeriod(startDate, endDate);

    }

    @Test
    @DisplayName(("Ensure start date is not null"))
    public void ensureStartDateIsNotNull() {

        //Arrange

        Date startDate = (null);
        String expectedMessage = "Start date must not be null";

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDate);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName(("Ensure end date is not null"))
    public void ensureEndDateIsNotNull() {

        //Arrange

        Date endDate = null;
        String expectedMessage = "End date must not be null";

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDate);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName(("Ensure start date is sooner end date"))
    public void ensureStartDateIsSoonerEndDate() {

        //Arrange

        Date endDateSwitched = startDate;
        startDate = endDate;

        String expectedMessage = "Start date must be sooner than end date";

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDateSwitched);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName("Return false in equals with null")
    public void returnFalseEqualsWithNull() {
        //Arrange

        TimePeriod timePeriodOne = new TimePeriod(startDate, endDate);

        //act

        boolean isEqual = timePeriodOne.equals(null);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    @DisplayName("Test to ensure that two Sprint with same number are equal")
    public void checkIfTimePeriodEqualsWithSameDatesAreEqual() {

        //Arrange
        TimePeriod timePeriodOne = new TimePeriod(startDate, endDate);
        TimePeriod timePeriodTwo = timePeriodOne;

        //Act

        boolean isEqual = timePeriodOne.equals(timePeriodTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the two equals object are equal")
    public void checkIfTimePeriodEqualsItselfWillNotReturnFalse() {
        //Arrange

        TimePeriod timePeriodOne = new TimePeriod(startDate, endDate);
        TimePeriod timePeriodTwo = new TimePeriod(startDate, endDate);


        //act

        boolean isEqual = timePeriodOne.equals(timePeriodTwo);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals() {
        //Arrange

        TimePeriod timePeriodOne = new TimePeriod(startDate, endDate);
        TimePeriod timePeriodTwo = new TimePeriod(startDateTwo, endDate);

        //act
        boolean isNotEqual = timePeriodOne.equals(timePeriodTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }


}