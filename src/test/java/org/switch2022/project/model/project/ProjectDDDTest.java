package org.switch2022.project.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectDDDTest {

    @Mock
    private ProjectCode projectCodeDouble;
    @Mock
    private ProjectName projectNameDouble;
    @Mock
    private Description descriptionDouble;
    @Mock
    private ProjectStatus projectStatusDouble;
    @Mock
    private TimePeriod timePeriodDouble;
    @Mock
    private ProjectSprintDuration projectSprintDurationDouble;
    @Mock
    private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble;
    @Mock
    private CustomerID customerIDDouble;
    @Mock
    private BusinessSectorID businessSectorIDDouble;
    @Mock
    private TypologyID typologyIDDouble;
    @Mock
    private ProjectBudget projectBudgetDouble;

    @Mock
    private ProductBacklogDDD productBacklogDouble;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /***
     * The test is used to ensure the ProjectDDD is correctly created, using a
     * mock for the ProjectCode class.
     */
    @Test
    @DisplayName("Test for a successful creation of ProjectDDD")
    public void checkIfTheProjectDDDIsSuccessfulCreated() {
        // arrange

        // act
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        // assert
        assertInstanceOf(ProjectDDD.class, project);
    }


    /***
     * This tests to ensure the mock object is correctly inserted into the product backlog.
     */

    @Test
    @DisplayName("Test for a successful addition to Backlog")
    public void checkIfTheProjectDDDAddsToBacklog() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        boolean result = project.addToProductBacklog(userStoryID, priority);

        //Assert
        assertTrue(result);
    }


    /***
     * This tests for a successful call for the list, using the UserStoryID.
     */

    @Test
    @DisplayName("Test for a successful call of List")
    public void checkIfTheProjectDDDReturnsProductBacklog() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        project.addToProductBacklog(userStoryID, priority);

        //Assert
        assertEquals(userStoryID, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }


    @Test
    @DisplayName("Test for the list returning more than one UserStory")
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithNegativeValueSize() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(0);
        UserStoryID userStoryIDTwo = mock(UserStoryID.class);
        UserStoryPriority priorityTwo = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(1);

        //Assert
        project.addToProductBacklog(userStoryID, priority);
        project.addToProductBacklog(userStoryIDTwo, priorityTwo);

        //Act
        assertNotEquals(-2, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }

    @Test
    @DisplayName("Test for the list returning more than one UserStory")
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithValuesAboveTheTrueSize() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(0);
        UserStoryID userStoryIDTwo = mock(UserStoryID.class);
        UserStoryPriority priorityTwo = mock(UserStoryPriority.class);
        when(priority.getValue()).thenReturn(1);

        //Assert
        project.addToProductBacklog(userStoryID, priority);
        project.addToProductBacklog(userStoryIDTwo, priorityTwo);

        //Act
        assertNotEquals(3, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }

    @Test
    @DisplayName("Project constructor throws exception with null code")
    void ensureNullCodeThrowsException() {
        //Arrange
        String expectedMessage = "Project code must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(null,
                            projectNameDouble,
                            descriptionDouble,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            customerIDDouble,
                            businessSectorIDDouble,
                            typologyIDDouble,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null name")
    void ensureNullNameThrowsException() {
        //Arrange
        String expectedMessage = "Project name must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            null,
                            descriptionDouble,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            customerIDDouble,
                            businessSectorIDDouble,
                            typologyIDDouble,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null description")
    void ensureNullDescriptionThrowsException() {
        //Arrange
        String expectedMessage = "Description must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            null,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            customerIDDouble,
                            businessSectorIDDouble,
                            typologyIDDouble,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null customer")
    void ensureNullCustomerThrowsException() {
        String expectedMessage = "Customer ID must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            null,
                            businessSectorIDDouble,
                            typologyIDDouble,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null business sector")
    void nullTimePeriodThrowsException() {
        String expectedMessage = "Business sector ID must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            customerIDDouble,
                            null,
                            typologyIDDouble,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null typology")
    void nullBudgetThrowsException() {
        String expectedMessage = "Typology ID must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            timePeriodDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble,
                            customerIDDouble,
                            businessSectorIDDouble,
                            null,
                            projectBudgetDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure projectCode is retrieved")
    void ensureProjectCodeIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        ProjectCode retrievedCode = project.getProjectCode();

        //Assert
        assertEquals(projectCodeDouble, retrievedCode);
    }

    @Test
    @DisplayName("Ensure project identity (projectCode) is retrieved")
    void ensureProjectIdentityIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        ProjectCode retrievedCode = project.identity();

        //Assert
        assertEquals(projectCodeDouble, retrievedCode);
    }

    @Test
    @DisplayName("Ensure projectName is retrieved")
    void ensureProjectNameIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        ProjectName retrievedName = project.getProjectName();

        //Assert
        assertEquals(projectNameDouble, retrievedName);
    }

    @Test
    @DisplayName("Ensure project description is retrieved")
    void ensureProjectDescriptionIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        Description retrievedDescription = project.getDescription();

        //Assert
        assertEquals(descriptionDouble, retrievedDescription);
    }

    @Test
    @DisplayName("Ensure project status is retrieved")
    void ensureProjectStatusIsRetrieved() {
        //Arrange
        List<UserStoryID> userStoryIDs = new ArrayList<>();
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble,
                userStoryIDs);
        //Act
        ProjectStatus retrievedStatus = project.getProjectStatus();

        //Assert
        assertEquals(projectStatusDouble, retrievedStatus);
    }

    @Test
    @DisplayName("Ensure project time period is retrieved")
    void ensureProjectTimePeriodIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        TimePeriod retrievedTimePeriod = project.getTimePeriod();

        //Assert
        assertEquals(timePeriodDouble, retrievedTimePeriod);
    }

    @Test
    @DisplayName("Ensure project sprint duration is retrieved")
    void ensureProjectSprintDurationIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        ProjectSprintDuration retrievedSprintDuration = project.getProjectSprintDuration();

        //Assert
        assertEquals(projectSprintDurationDouble, retrievedSprintDuration);
    }

    @Test
    @DisplayName("Ensure project number of planned sprints is retrieved")
    void ensureProjectNumberOfPlannedSprintsIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        ProjectNumberOfPlannedSprints retrievedPlannedSprints = project.getProjectNumberOfPlannedSprints();

        //Assert
        assertEquals(projectNumberOfPlannedSprintsDouble, retrievedPlannedSprints);
    }

    @Test
    @DisplayName("Ensure project customerID is retrieved")
    void ensureProjectCustomerIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        //Act
        CustomerID retrievedCustomerID = project.getCustomerID();

        //Assert
        assertEquals(customerIDDouble, retrievedCustomerID);
    }

    @Test
    @DisplayName("Ensure project business sector is retrieved")
    void ensureProjectBusinessSectorIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        BusinessSectorID retrievedBusinessSectorID = project.getBusinessSectorID();

        //Assert
        assertEquals(businessSectorIDDouble, retrievedBusinessSectorID);
    }

    @Test
    @DisplayName("Ensure project typology is retrieved")
    void ensureProjectTypologyIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        TypologyID retrievedTypologyID = project.getTypologyID();

        //Assert
        assertEquals(typologyIDDouble, retrievedTypologyID);
    }

    @Test
    @DisplayName("Ensure project budget is retrieved")
    void ensureProjectBudgetIsRetrieved() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        ProjectBudget retrievedBudget = project.getProjectBudget();

        //Assert
        assertEquals(projectBudgetDouble, retrievedBudget);
    }

    @Test
    @DisplayName("Ensure project productBacklog is retrieved")
    void ensureProjectProductBacklogIsRetrieved() {
        //Arrange
        List<UserStoryID> userStoryIDs = new ArrayList<>();
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble,
                userStoryIDs);

        //Act
        List<UserStoryID> retrievedBacklog = project.getProductBacklog();

        //Assert
        assertInstanceOf(List.class, retrievedBacklog);
    }

    @Test
    @DisplayName("Ensure object does not equal null")
    void ensureObjectNotEqualNull() {
        //Arrange
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        boolean result = project.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object equals same object")
    void objectEqualsSameObject() {
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        boolean result = project.equals(project);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object equals object with same project code")
    void objectEqualsSameProjectCode() {
        ProjectDDD project1 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        ProjectDDD project2 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        boolean result = project1.equals(project2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object with different project code")
    void objectDoesNotEqualProjectWithDifferentProjectCode() {

        ProjectCode projectCodeDouble2 = mock(ProjectCode.class);

        ProjectDDD project1 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        ProjectDDD project2 = new ProjectDDD(projectCodeDouble2,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        boolean result = project1.equals(project2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);
        String fakeProject = "Wannabe project";

        //Act
        boolean result = project.equals(fakeProject);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        ProjectDDD project1 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        ProjectDDD project2 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        int hashCode1 = project1.hashCode();
        int hashCode2 = project2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Ensure different objects have different hashcode")
    void ensureDifferentObjectsHaveDifferentHashcode() {
        //Arrange
        ProjectDDD project1 = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        ProjectCode projectCodeDouble2 = mock(ProjectCode.class);
        ProjectDDD project2 = new ProjectDDD(projectCodeDouble2,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIDDouble,
                businessSectorIDDouble,
                typologyIDDouble,
                projectBudgetDouble);

        //Act
        int hashCode1 = project1.hashCode();
        int hashCode2 = project2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}
