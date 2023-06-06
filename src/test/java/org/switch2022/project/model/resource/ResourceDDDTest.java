package org.switch2022.project.model.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResourceDDDTest {

    @Test
    @DisplayName("Ensure Resource is successfully created")
    void ensureResourceIsSuccessfullyCreated(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);

        //Act
        ResourceDDD resourceDDD = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Assert
        assertInstanceOf(ResourceDDD.class, resourceDDD);
    }

    @Test
    @DisplayName("Ensure Resource Id is returned")
    void ensureResourceIdIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        ResourceID resultId = resource.identity();

        //Assert
        assertEquals(resourceID, resultId);
    }

    @Test
    @DisplayName("Ensure resource email is returned")
    void ensureResourceEmailIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        Email resultEmail = resource.getEmail();

        //Assert
        assertEquals(email, resultEmail);
    }

    @Test
    @DisplayName("Ensure resource cost per hour is returned")
    void ensureResourceCostPerHourIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        CostPerHour resultCostPerHour = resource.getCostPerHour();

        //Assert
        assertEquals(costPerHour, resultCostPerHour);
    }

    @Test
    @DisplayName("Ensure resource role is returned")
    void ensureResourceRoleIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        Role resultRole = resource.getRole();

        //Assert
        assertEquals(role, resultRole);
    }

    @Test
    @DisplayName("Ensure resource percentage of allocation is returned")
    void ensureResourcePercentageOfAllocationIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        PercentageOfAllocation resultPercentageOfAllocation = resource.getPercentageOfAllocation();

        //Assert
        assertEquals(percentageOfAllocation, resultPercentageOfAllocation);
    }

    @Test
    @DisplayName("Ensure resource project code is returned")
    void ensureResourceProjectCodeIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        ProjectCode resultProjectCode = resource.getProjectCode();

        //Assert
        assertEquals(projectCode, resultProjectCode);
    }

    @Test
    @DisplayName("Ensure resource time period is returned")
    void ensureResourceTimePeriodIsReturned(){
        //Arrange
        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod =  mock(TimePeriod.class);
        ResourceDDD resource = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);

        //Act
        TimePeriod resultTimePeriod = resource.getTimePeriod();

        //Assert
        assertEquals(timePeriod, resultTimePeriod);
    }



}