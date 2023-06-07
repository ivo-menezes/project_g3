package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ResourceDomainAssemblerDataTest {

    @Test
    @DisplayName("Ensure Resource is correctly converted to ResourceJpa")
    void ensureResourceIsConvertedToResourceJpa(){
        //Arrange
        AccountID accountID = mock(AccountID.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        ResourceDDD resourceDDD = mock(ResourceDDD.class);

        when(resourceDDD.getAccountID()).thenReturn(accountID);
        when(accountID.getId()).thenReturn(3L);
        when(resourceDDD.getCostPerHour()).thenReturn(costPerHour);
        when(costPerHour.getValue()).thenReturn(10.0);
        when(resourceDDD.getRole()).thenReturn(role);
        when(role.toString()).thenReturn("Product Owner");
        when(resourceDDD.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(percentageOfAllocation.getValue()).thenReturn(15.0);
        when(resourceDDD.getProjectCode()).thenReturn(projectCode);
        when(projectCode.toString()).thenReturn("P26");
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        when(resourceDDD.getTimePeriod()).thenReturn(timePeriod);
        when(timePeriod.getStartDate()).thenReturn(startDate);
        when(timePeriod.getEndDate()).thenReturn(endDate);

        ResourceDomainAssemblerData assembler = new ResourceDomainAssemblerData();

        //Act
        ResourceJPA returnedResourceJpa = assembler.toData(resourceDDD);

        //Assert
        assertInstanceOf(ResourceJPA.class, returnedResourceJpa);
    }

    @Test
    @DisplayName("Ensure ResourceJPA is correctly converted to Resource")
    void ensureResourceJpaIsConvertedToResource(){
        //Arrange
        long accountID = 3L;
        double costPerHour = 3.0;
        String role = "Product Owner";
        double percentageOfAllocation = 10.0;
        String projectCode = "P26";
        Date startDate = new Date(2021 - 3 - 22);
        Date endDate = new Date(2023 - 4 - 23);

        ResourceJPA resourceJPA = mock(ResourceJPA.class);

        when(resourceJPA.getAccountID()).thenReturn(accountID);
        when(resourceJPA.getCostPerHour()).thenReturn(costPerHour);
        when(resourceJPA.getRole()).thenReturn(role);
        when(resourceJPA.getPercentageOfAllocation()).thenReturn(percentageOfAllocation);
        when(resourceJPA.getProjectCode()).thenReturn(projectCode);
        when(resourceJPA.getStartDate()).thenReturn(startDate);
        when(resourceJPA.getEndDate()).thenReturn(endDate);

        ResourceDomainAssemblerData assembler = new ResourceDomainAssemblerData();

        //Act
        ResourceDDD returnedResource = assembler.toDomain(resourceJPA);

        //Assert
        assertInstanceOf(ResourceDDD.class, returnedResource);
    }

}