package org.switch2022.project.datamodel.JPA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectJpaTest {
        private String projectCode;
        private String projectName;
        private String description;
        private String projectStatus;
        private Date startDate;
        private Date endDate;
        private int sprintDuration;
        private int projectNumberOfPlannedSprints;
        private long customerID;
        private long businessSectorID;
        private long typologyID;
        private float projectBudget;
        private List<String> productBacklog;

    @BeforeEach
        public void setUp() {
            projectCode = "P001";
            projectName = "Awesome project";
            description = "This project is fabulous!";
            projectStatus = "Planned";

            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1);
            startDate = calendar.getTime();

            calendar.set(2023, Calendar.MAY, 1);
            endDate = calendar.getTime();

            sprintDuration = 2;
            projectNumberOfPlannedSprints = 5;
            customerID = 10L;
            businessSectorID = 11L;
            typologyID = 12L;
            projectBudget = 1000.0f;
            productBacklog = new ArrayList<>();
        }

    @Test
    @DisplayName("Ensure ProjectJpa is successfully created")
    void ensureProjectJpaIsSuccessfullyCreated(){
        //Arrange

        //Act
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Assert
        assertInstanceOf(ProjectJpa.class,projectJpa);
    }

    @Test
    @DisplayName("Ensure ProjectJpa is correctly created by no argument constructor")
    void ensureEmptyConstructorCreatesProjectJpa() {
        //Arrange and Act
        ProjectJpa projectJpa = new ProjectJpa();

        //Assert
        assertInstanceOf(ProjectJpa.class, projectJpa);
    }

    @Test
    @DisplayName("Ensure projectCode is retrieved")
    void ensureProjectCodeIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        String retrievedCode = projectJpa.getProjectCode();

        //Assert
        assertEquals(projectCode, retrievedCode);
    }

    @Test
    @DisplayName("Ensure projectName is retrieved")
    void ensureProjectNameIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        String retrievedName = projectJpa.getProjectName();

        //Assert
        assertEquals(projectName, retrievedName);
    }
    @Test
    @DisplayName("Ensure project description is retrieved")
    void ensureProjectDescriptionIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        String retrievedDescription = projectJpa.getDescription();

        //Assert
        assertEquals(description, retrievedDescription);
    }

    @Test
    @DisplayName("Ensure project status is retrieved")
    void ensureProjectStatusIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        String retrievedStatus = projectJpa.getProjectStatus();

        //Assert
        assertEquals(projectStatus, retrievedStatus);
    }

    @Test
    @DisplayName("Ensure project start date is retrieved")
    void ensureProjectStartDateIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        Date retrievedStartDate = projectJpa.getStartDate();

        //Assert
        assertEquals(startDate, retrievedStartDate);
    }

    @Test
    @DisplayName("Ensure project end date is retrieved")
    void ensureProjectEndDateIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        Date retrievedEndDate = projectJpa.getEndDate();

        //Assert
        assertEquals(endDate, retrievedEndDate);
    }

    @Test
    @DisplayName("Ensure project sprintDuration  is retrieved")
    void ensureProjectSprintDurationIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        int retrievedSprintDuration  = projectJpa.getSprintDuration();

        //Assert
        assertEquals(sprintDuration, retrievedSprintDuration);
    }

    @Test
    @DisplayName("Ensure project number of planned sprints is retrieved")
    void ensureProjectNumberOfPlannedSprintsIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        int retrievedNumberSprints  = projectJpa.getProjectNumberOfPlannedSprints();

        //Assert
        assertEquals(projectNumberOfPlannedSprints , retrievedNumberSprints);
    }

    @Test
    @DisplayName("Ensure project customer NIF is retrieved")
    void ensureProjectCustomerNifIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        long retrievedCustomerID  = projectJpa.getCustomerID();

        //Assert
        assertEquals(customerID , retrievedCustomerID);
    }

    @Test
    @DisplayName("Ensure project business sector is retrieved")
    void ensureProjectBusinessSectorIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        long retrievedBusinessSectorID  = projectJpa.getBusinessSectorID();

        //Assert
        assertEquals(businessSectorID , retrievedBusinessSectorID);
    }

    @Test
    @DisplayName("Ensure project typology is retrieved")
    void ensureProjectTypologyIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        long retrievedTypologyID  = projectJpa.getTypologyID();

        //Assert
        assertEquals(typologyID , retrievedTypologyID);
    }

    @Test
    @DisplayName("Ensure project budget is retrieved")
    void ensureProjectBudgetIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        float retrievedBudget  = projectJpa.getProjectBudget();

        //Assert
        assertEquals(projectBudget , retrievedBudget);
    }

    @Test
    @DisplayName("Ensure project productBacklog is retrieved")
    void ensureProjectProductBacklogIsRetrieved(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        List<String> retrievedBacklog  = projectJpa.getProductBacklog();

        //Assert
        assertEquals(productBacklog , retrievedBacklog);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectNotEqualNull(){
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        boolean result = projectJpa.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void objectEqualsSameObject() {
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        // Act
        boolean result = projectJpa.equals(projectJpa);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same project code")
    void objectEqualsSameProjectCode() {
        //Arrange
        ProjectJpa projectJpa1 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        ProjectJpa projectJpa2 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        boolean result = projectJpa1.equals(projectJpa2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different project code")
    void objectDoesNotEqualProjectWithDifferentCode() {
        //Arrange
        ProjectJpa projectJpa1 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        String projectCode2 = "P002";
        ProjectJpa projectJpa2 = new ProjectJpa(projectCode2, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        boolean result = projectJpa1.equals(projectJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        ProjectJpa projectJpa = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);
        String fakeProjectJpa = "Wannabe projectJpa";

        //Act
        boolean result = projectJpa.equals(fakeProjectJpa);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        ProjectJpa projectJpa1 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        ProjectJpa projectJpa2 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        int hashCode1 = projectJpa1.hashCode();
        int hashCode2 = projectJpa2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectJpa projectJpa1 = new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        String projectCode2 = "P002";
        ProjectJpa projectJpa2 = new ProjectJpa(projectCode2, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints, customerID,
                businessSectorID, typologyID, projectBudget, productBacklog);

        //Act
        int hashCode1 = projectJpa1.hashCode();
        int hashCode2 = projectJpa2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}
