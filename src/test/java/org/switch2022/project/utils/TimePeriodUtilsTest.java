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
import static org.switch2022.project.utils.TimePeriodUtils.timePeriodsOverlap;

class TimePeriodUtilsTest {

    @Test
    @DisplayName("Ensure true is returned when there is a period overlap")
    void ensureTimePeriodsOverlaps() {
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
}