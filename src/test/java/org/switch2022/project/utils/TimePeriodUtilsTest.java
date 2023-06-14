package org.switch2022.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.switch2022.project.utils.TimePeriodUtils.timePeriodContainsTimePeriod;
import static org.switch2022.project.utils.TimePeriodUtils.timePeriodsOverlap;

class TimePeriodUtilsTest {

    @Test
    @DisplayName("Ensure true is returned for overlap when the end of the first period " +
            "overlaps with the beginning of the second")
    void ensureTimePeriodsOverlap() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 5);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 3);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when the end of the second period " +
            "overlaps with the beginning of the first")
    void ensureTimePeriodsOverlap2() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when second period contains first one")
    void ensureTimePeriodsOverlap3() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when first period contains second one")
    void ensureTimePeriodsOverlap4() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 10);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when second period contains " +
            "first one and starts at the same time")
    void ensureTimePeriodsOverlap5() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when second period contains " +
            "first one and ends at the same time")
    void ensureTimePeriodsOverlap6() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 15);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when first period contains " +
            "second one and starts at the same time")
    void ensureTimePeriodsOverlap7() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 10);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned for overlap when first period contains " +
            "second one and ends at the same time")
    void ensureTimePeriodsOverlap8() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Ensure false is returned when there is not a period overlap")
    void ensureTimePeriodsDoNotOverlap() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondStartDate = calendar.getTime();


        calendar.set(2023, Calendar.JANUARY, 25);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertFalse(result);

    }

    @Test
    @DisplayName("Ensure false is returned when there is not a period overlap firstStartDate after secondEndDate")
    void ensureTimePeriodsDoNotOverlapFirstStartDateAfterSecondEndDate() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 5);
        Date secondStartDate = calendar.getTime();


        calendar.set(2023, Calendar.JANUARY, 9);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure true is returned when there is a complete overlap between periods")
    void ensureTimePeriodsOverlap9() {
        // Arrange
        TimePeriod firstTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date firstEndDate = calendar.getTime();

        when(firstTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(firstTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod secondTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondEndDate = calendar.getTime();

        when(secondTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(secondTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodsOverlap(firstTimePeriod, secondTimePeriod);

        // Assert
        assertTrue(result);
    }

    //Tests for "timePeriodContainsTimePeriod"

    @Test
    @DisplayName("Ensure true is returned when timePeriod contains another time period with same start date")
    void ensureBiggestTimePeriodContainsSmallestWithSameStartDate() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned when timePeriod contains another time period with same end date")
    void ensureBiggestTimePeriodContainsSmallestWithSameEndDate() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure true is returned when timePeriod contains another time period with different " +
            "start and end dates")
    void ensureBiggestTimePeriodContainsSmallestWithDifferentDates() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 10);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 15);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure false is returned when smallest time period overlaps with startDate of biggest period")
    void ensurePartialContainmentReturnsFalse() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 5);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 10);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure false is returned when smallest time period overlaps with endDate of biggest period")
    void ensurePartialContainmentReturnsFalse2() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 25);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 20);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure false is returned when smallest time period occurs before biggest time period")
    void ensureNoContainmentReturnsFalse() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 10);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 1);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 5);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure false is returned when smallest time period occurs after biggest time period")
    void ensureNoContainmentReturnsFalse2() {
        // Arrange
        TimePeriod biggestTimePeriod = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date firstStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 20);
        Date firstEndDate = calendar.getTime();

        when(biggestTimePeriod.getStartDate()).thenReturn(firstStartDate);
        when(biggestTimePeriod.getEndDate()).thenReturn(firstEndDate);

        TimePeriod smallestTimePeriod = mock(TimePeriod.class);
        calendar.set(2023, Calendar.JANUARY, 25);
        Date secondStartDate = calendar.getTime();

        calendar.set(2023, Calendar.JANUARY, 30);
        Date secondEndDate = calendar.getTime();

        when(smallestTimePeriod.getStartDate()).thenReturn(secondStartDate);
        when(smallestTimePeriod.getEndDate()).thenReturn(secondEndDate);

        // Act
        boolean result = timePeriodContainsTimePeriod(biggestTimePeriod, smallestTimePeriod);

        // Assert
        assertFalse(result);
    }

}