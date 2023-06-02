package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.resource.ResourceDDD;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewResourceDTOMapperTest {

    @DisplayName("assert that new resource dto is created successfully")
    @Test
    void verifyNewResourceDTOSuccessfullyCreated() {
        //Arrange
        ResourceDDD resource = mock(ResourceDDD.class);
        NewResourceDTO dto = mock(NewResourceDTO.class);

        when(resource.identity()).thenReturn(dto.resourceID);
        when(resource.getEmail()).thenReturn(dto.email);
        when(resource.getCostPerHour()).thenReturn(dto.costPerHour);
        when(resource.getRole()).thenReturn(dto.role);
        when(resource.getPercentageOfAllocation()).thenReturn(dto.percentageOfAllocation);
        when(resource.getProjectCode()).thenReturn(dto.projectCode);
        when(resource.getTimePeriod()).thenReturn(dto.timePeriod);

        //Act and Assert
        assertInstanceOf(NewResourceDTO.class, dto);
    }

}