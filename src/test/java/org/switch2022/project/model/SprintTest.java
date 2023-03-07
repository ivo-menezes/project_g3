package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SprintTest {

    /***
     * Testing the creation of Sprint successfully
     */
    @Test
    void createSprintSuccessfully(){
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertTrue(sprint.equals(sprint));
    }

    @Test
    void ensureThatHashCodeIsTested(){
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertNotEquals(0, sprint.hashCode());
    }

    @Test
    void ensureThatHashCodeIsTestedAgain(){
        Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));

        assertEquals(sprint.hashCode(), sprint.hashCode());
    }

    /***
     * The tests to ensure any empty field isn't accepted.
     */
    @Test
    void checkIfSprintNumberIsNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(0, new Date(2021, Calendar.JANUARY, 1), new Date(2021, Calendar.FEBRUARY, 1));
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }
    @Test
    void checkIfStartDateIsNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(1, null, new Date(2021, Calendar.FEBRUARY, 1));
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }
    @Test
    void checkIfEndDateIsNull(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Sprint sprint = new Sprint(1, new Date(2021, Calendar.JANUARY, 1), null);
        });
        Assertions.assertEquals("Missing value, please try again.", exception.getMessage());
    }
}