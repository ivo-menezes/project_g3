package org.switch2022.project.model.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.typology.TypologyDDD;
import org.switch2022.project.model.valueobject.*;

import java.sql.Time;

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





}