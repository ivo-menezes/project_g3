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
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);

        //Act
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

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
    @DisplayName("US constructor throws exception with null or blank or empty email")
    void nullBlankOrEmptyEmailThrowsException() {
        //Arrange
        String emailEmpty = "";
        String emailBlank = "    ";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "Email designation must not be null";

        IllegalArgumentException exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(emailEmpty, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);});
        IllegalArgumentException exceptionBlank = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(emailBlank, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);});
        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(null, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);});
        //Act
        String resultMessageEmpty = exceptionEmpty.getMessage();
        String resultMessageBlank = exceptionBlank.getMessage();
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageEmpty);
        assertEquals(expectedMessage, resultMessageBlank);
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with negative costPerHour")
    void nullBlankOrEmptyCostPerHourThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = -2;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "CostPerHour designation must not be null";

        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);});
        //Act
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with null or blank or empty percentageOfAllocation")
    void nullBlankOrEmptyPercentageOfAllocationThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = -10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "PercentageOfAllocation designation must not be null";

        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);});
        //Act
        String resultMessageNull = exceptionNull.getMessage();
        //Assert

        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with null or blank or empty role")
    void nullBlankOrEmptyRoleThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String roleEmpty = "";
        String roleBlank = "   ";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "Role designation must not be null";

        IllegalArgumentException exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, roleEmpty, percentageOfAllocation, projectCode, startDate, endDate);});
        IllegalArgumentException exceptionBlank = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, roleBlank, percentageOfAllocation, projectCode, startDate, endDate);});
        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, null, percentageOfAllocation, projectCode, startDate, endDate);});
        //Act
        String resultMessageEmpty = exceptionEmpty.getMessage();
        String resultMessageBlank = exceptionBlank.getMessage();
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageEmpty);
        assertEquals(expectedMessage, resultMessageBlank);
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with null or blank or empty projectCode")
    void nullBlankOrEmptyProjectCodeThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCodeBlank = "   ";
        String projectCodeEmpty = "";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "ProjectCode designation must not be null";

        IllegalArgumentException exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCodeEmpty, startDate, endDate);});
        IllegalArgumentException exceptionBlank = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCodeBlank, startDate, endDate);});
        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, null, startDate, endDate);});
        //Act
        String resultMessageEmpty = exceptionEmpty.getMessage();
        String resultMessageBlank = exceptionBlank.getMessage();
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageEmpty);
        assertEquals(expectedMessage, resultMessageBlank);
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with null or blank or empty startDate")
    void nullBlankOrEmptyStartDateThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date endDate = new Date(2023 - 4 - 23);
        String expectedMessage = "StartDate designation must not be null";


        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, null, endDate);});
        //Act
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("US constructor throws exception with null or blank or empty endDate")
    void nullBlankOrEmptyEndDateThrowsException() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        String expectedMessage = "EndDate designation must not be null";

        IllegalArgumentException exceptionNull = assertThrows(IllegalArgumentException.class, () -> {new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, null);});
        //Act
        String resultMessageNull = exceptionNull.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessageNull);
    }

    @Test
    @DisplayName("Ensure resource email is returned")
    void ensureResourceEmailIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        String resultEmail = resourceJPA.getEmail();

        //Assert
        assertEquals(email, resultEmail);
    }

    @Test
    @DisplayName("Ensure resource costPerHour is returned")
    void ensureResourceCostPerHourIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        double resultCostPerHour = resourceJPA.getCostPerHour();

        //Assert
        assertEquals(costPerHour, resultCostPerHour);
    }

    @Test
    @DisplayName("Ensure resource role is returned")
    void ensureResourceRoleIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        String resultRole = resourceJPA.getRole();

        //Assert
        assertEquals(role, resultRole);
    }

    @Test
    @DisplayName("Ensure resource PercentageOfAllocation is returned")
    void ensureResourcePercentageOfAllocationIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        double resultPercentageOfAllocation = resourceJPA.getPercentageOfAllocation();

        //Assert
        assertEquals(percentageOfAllocation, resultPercentageOfAllocation);
    }

    @Test
    @DisplayName("Ensure resource project code is returned")
    void ensureResourceProjectCodeIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        String resultProjectCode = resourceJPA.getProjectCode();

        //Assert
        assertEquals(projectCode, resultProjectCode);
    }

    @Test
    @DisplayName("Ensure resource StartDate is returned")
    void ensureResourceStartDateIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        Date resultStartDate = resourceJPA.getStartDate();

        //Assert
        assertEquals(startDate, resultStartDate);
    }

    @Test
    @DisplayName("Ensure resource endDate is returned")
    void ensureResourceEndDateIsReturned() {
        //Arrange
        String email = "test@gmail.com";
        double costPerHour = 3;
        String role = "PO";
        double percentageOfAllocation = 10;
        String projectCode = "P26";
        Date startDate = new Date(2023 - 3 - 23);
        Date endDate = new Date(2023 - 4 - 23);
        ResourceJPA resourceJPA = new ResourceJPA(email, costPerHour, role, percentageOfAllocation, projectCode, startDate, endDate);

        //Act
        Date resultEndDAte = resourceJPA.getEndDate();

        //Assert
        assertEquals(endDate, resultEndDAte);
    }

}