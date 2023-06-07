package org.switch2022.project.datamodel.JPA;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class ResourceJPATest {

    @Test
    @DisplayName("Ensure ResourceJPA is successfully created")
    void ensureResourceJpaIsSuccessfullyCreated() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);

        //Act
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Assert
        assertInstanceOf(ResourceJPA.class, resourceJPA);
    }

    @Test
    @DisplayName("Ensure ResourceJPA is correctly created by no arguments constructor")
    void ensureResourceJpaIsCreatedByDefaultConstructor() {
        // Arrange and Act
        ResourceJPA resourceJPA = new ResourceJPA();

        // Assert
        assertInstanceOf(ResourceJPA.class, resourceJPA);
    }

    @Test
    @DisplayName("Ensure resource email is returned")
    void ensureResourceEmailIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        long resultAccountID = resourceJPA.getAccountID();

        //Assert
        assertEquals(accountID, resultAccountID);
    }

    @Test
    @DisplayName("Ensure resource costPerHour is returned")
    void ensureResourceCostPerHourIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        double resultCostPerHour = resourceJPA.getCostPerHour();

        //Assert
        assertEquals(costPerHour, resultCostPerHour);
    }

    @Test
    @DisplayName("Ensure resource role is returned")
    void ensureResourceRoleIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        String resultRole = resourceJPA.getRole();

        //Assert
        assertEquals(role, resultRole);
    }

    @Test
    @DisplayName("Ensure resource PercentageOfAllocation is returned")
    void ensureResourcePercentageOfAllocationIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        double resultPercentageOfAllocation = resourceJPA.getPercentageOfAllocation();

        //Assert
        assertEquals(percentageOfAllocation, resultPercentageOfAllocation);
    }

    @Test
    @DisplayName("Ensure resource project code is returned")
    void ensureResourceProjectCodeIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        String resultProjectCode = resourceJPA.getProjectCode();

        //Assert
        assertEquals(projectCode, resultProjectCode);
    }

    @Test
    @DisplayName("Ensure resource StartDate is returned")
    void ensureResourceStartDateIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        Date resultStartDate = resourceJPA.getStartDate();

        //Assert
        assertEquals(startDate, resultStartDate);
    }

    @Test
    @DisplayName("Ensure resource endDate is returned")
    void ensureResourceEndDateIsReturned() {
        //Arrange
        long accountID = 3L;
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        Date resultEndDAte = resourceJPA.getEndDate();

        //Assert
        assertEquals(endDate, resultEndDAte);
    }

}