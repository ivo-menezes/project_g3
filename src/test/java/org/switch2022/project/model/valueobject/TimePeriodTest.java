package org.switch2022.project.model.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimePeriodTest {

    @Test
    @DisplayName(("Test for creation of Time Period"))
    public void checkIfClassCreatesValidTimePeriod() {

        //Arrange

        Date startDate = new Date(2023 , Calendar.JANUARY,5);
        Date endDate = new Date(2023 , Calendar.JANUARY ,5);

        //act
        new TimePeriod(startDate, endDate);

    }

    @Test
    @DisplayName(("Ensure start date is not null"))
    public void ensureStartDateIsNotNull() {

        //Arrange

        Date startDate = (null);
        Date endDate = new Date(2023 , Calendar.JANUARY ,5);
        String expectedMessage = "Start date must not be null" ;

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDate);;
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName(("Ensure end date is not null"))
    public void ensureEndDateIsNotNull() {

        //Arrange

        Date startDate = new Date(2023 , Calendar.JANUARY ,5);
        Date endDate = null;
        String expectedMessage = "End date must not be null" ;

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDate);;
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }
    @Test
    @DisplayName(("Ensure start date is sooner end date"))
    public void ensureStartDateIsSoonerEndDate() {

        //Arrange

        Date startDate = new Date(2023 , Calendar.JANUARY ,5 );
        Date endDate = new Date(2023 , Calendar.JANUARY ,4);
        String expectedMessage = "Start date must be sooner than end date" ;

        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new TimePeriod(startDate, endDate);;
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName("Test to ensure the two equals object are equal")
    public void checkIfUserStoryPriorityEqualsItselfWillNotReturnFalse() {
        //Arrange
        Date startDate = new Date(2023, Calendar.JANUARY, 4);
        Date endDate = new Date(2023, Calendar.JANUARY, 5);
        TimePeriod timePeriodOne = new TimePeriod(startDate,endDate);
        TimePeriod timePeriodTwo = new TimePeriod(startDate,endDate);




        //act

        boolean isEqual = timePeriodOne.equals(timePeriodTwo);

        // Assert
        assertEquals(true, isEqual);
    }

    @Test
    @DisplayName("Test to ensure the return won't be true for different objects")
    public void checkIfWillNoReturnEquals() {
        //Arrange
        Date startDate = new Date(2023, Calendar.JANUARY, 3);
        Date endDate = new Date(2023, Calendar.JANUARY, 4);
        Date startDateTwo = new Date(2023, Calendar.JANUARY, 2);
        TimePeriod timePeriodOne = new TimePeriod(startDate,endDate);
        TimePeriod timePeriodTwo = new TimePeriod(startDateTwo,endDate);

        //act
        boolean isNotEqual = timePeriodOne.equals(timePeriodTwo);

        // Assert
        assertNotEquals(true, isNotEqual);
    }




}