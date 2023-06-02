package org.switch2022.project.model.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResourceFactoryImplTest {

    @DisplayName("assert that creating resource succeeds")
    @Test
    void createResourceSucceeds() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        //Act
        ResourceDDD resource = factory.createResource(resourceID, email, costPerHour, role,
                percentageOfAllocation, projectCode, timePeriod);

        //Arrange
        assertInstanceOf(ResourceDDD.class, resource);
    }

    @DisplayName("assert that trying to create resource with null email throws Exception")
    @Test
    void createResourceNullEmailThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = null;
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "Email cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create resource with null cost per hour throws Exception")
    @Test
    void createResourceNullCostPerHourThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = null;
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "CostPerHour cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create resource with null role throws Exception")
    @Test
    void createResourceNullRoleThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = null;
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "Role cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create resource with null percentage of allocation throws Exception")
    @Test
    void createResourceNullPercentageOfAllocationThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = null;
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "PercentageOfAllocation cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create resource with null project code throws Exception")
    @Test
    void createResourceNullProjectCodeThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = null;
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "ProjectCode cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create resource with null time period throws Exception")
    @Test
    void createResourceNullTimePeriodThrowsException() {
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = null;

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "TimePeriod cannot be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceID, email, costPerHour, role, percentageOfAllocation
                    , projectCode, timePeriod);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating resource succeeds with a dto")
    @Test
    void createResourceSucceedsWithADTO() {
        //Arrange
        NewResourceDTO resourceDTO = mock(NewResourceDTO.class);

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        //Act
        ResourceDDD resource = factory.createResource(resourceDTO);

        //Arrange
        assertInstanceOf(ResourceDDD.class, resource);
    }

    @DisplayName("assert that trying to create resource with null dto throws Exception")
    @Test
    void createResourceNullDTOThrowsException() {
        //Arrange
        NewResourceDTO resourceDTO = null;

        ResourceFactoryImpl factory = new ResourceFactoryImpl();

        String expectedMessage = "Dto must not be null";

        //Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createResource(resourceDTO);
        });

        String resultMessage = result.getMessage();

        //Arrange
        assertEquals(expectedMessage, resultMessage);
    }
}