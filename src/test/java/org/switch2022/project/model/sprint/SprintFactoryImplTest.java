package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintFactoryImplTest {

    @Test
    @DisplayName("ensure that creating sprint succeeds")
    void ensureSprintSucceeds() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        SprintFactoryImpl factory = new SprintFactoryImpl();
        //Act
        SprintDDD sprint = factory.createSprint(sprintID, timePeriod);
        //Assert
        assertInstanceOf(SprintDDD.class, sprint);
    }

    @Test
    @DisplayName("ensure that creating sprint with null ID fails")
    void ensureSprintNullIDFails() {
        //Arrange
        TimePeriod timePeriod = mock(TimePeriod.class);
        SprintFactoryImpl factory = new SprintFactoryImpl();

        String expectedMessage = "Missing value, please try again.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createSprint(null, timePeriod);
        });

        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that creating sprint with null time period fails")
    void ensureSprintNullTimePeriodFails() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        SprintFactoryImpl factory = new SprintFactoryImpl();

        String expectedMessage = "Missing value, please try again.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createSprint(sprintID, null);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


}

