package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SprintFactoryImplTest {

    @Test
    @DisplayName("ensure that creating sprint succeeds")
    void ensureSprintSucceeds() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        NewSprintDTO sprintDTO = mock(NewSprintDTO.class);
        sprintDTO.sprintID = sprintID;
        sprintDTO.timePeriod = timePeriod;

        SprintFactoryImpl factory = new SprintFactoryImpl();
        //Act
        SprintDDD sprint = factory.createSprint(sprintDTO);
        //Assert
        assertInstanceOf(SprintDDD.class, sprint);
    }

    @Test
    @DisplayName("ensure that creating sprint with null ID fails")
    void ensureSprintNullIDFails() {
        //Arrange
        TimePeriod timePeriod = mock(TimePeriod.class);
        SprintStatus status = SprintStatus.Open;
        SprintFactoryImpl factory = new SprintFactoryImpl();

        String expectedMessage = "Missing value, please try again.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createSprint(null, timePeriod, status);
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
        SprintStatus status = SprintStatus.Open;

        String expectedMessage = "Missing value, please try again.";
        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createSprint(sprintID, null, status);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("Ensure the factory creates Sprint successfully")
    void ensureSprintCreationIsSuccessful() {
        //Arrange
        SprintID sprintID = mock(SprintID.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        SprintStatus status = SprintStatus.Open;

        SprintFactoryImpl factory = new SprintFactoryImpl();
        //Act
        SprintDDD sprint = factory.createSprint(sprintID, timePeriod, status);
        //Assert
        assertInstanceOf(SprintDDD.class, sprint);
    }
    @Test
    @DisplayName("Return new SprintID")
    void ensureSprintIDIsCorrectlyCreated(){
        //arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        SprintNumber mockNumber = mock(SprintNumber.class);

        SprintFactoryImpl factory = new SprintFactoryImpl();
        //act
        SprintID sprintResult = factory.newSprintID(mockCode, mockNumber);

        //assert
        assertInstanceOf(SprintID.class, sprintResult);
    }

}

