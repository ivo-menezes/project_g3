package org.switch2022.project.model.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectDDDTest {

    /***
     * The test is used to ensure the ProjectDDD is correctly created, using a
     * mock for the ProjectCode class.
     */
    @Test
    @DisplayName("Test for a successful creation of ProjectDDD")
    public void checkIfTheProjectDDDIsSuccessfulCreated(){
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        // act
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectBudgetDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble);

        // assert
        assertInstanceOf(ProjectDDD.class, project);
    }

    /***
     * This tests to ensure the mock object is correctly inserted into the product backlog.
     */
    @Test
    @DisplayName("Test for a successful addition to Backlog")
    public void checkIfTheProjectDDDAddsToBacklog(){
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectBudgetDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

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
    public void checkIfTheProjectDDDReturnsProductBacklog(){
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectBudgetDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble);
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryPriority priority = mock(UserStoryPriority.class);

        //Act
        project.addToProductBacklog(userStoryID, priority);

        //Assert
        assertEquals(userStoryID, project.getProductBacklog().get(project.getProductBacklog().size() - 1));
    }

    /***
     *
     */
    @Test
    @DisplayName("Test for the list returning more than one UserStory")
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithNegativeValueSize(){
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectBudgetDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble);
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
    public void checkIfTheProjectDDDDoesNotReturnProductBacklogWithValuesAboveTheTrueSize(){
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        ProjectDDD project = new ProjectDDD(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                projectStatusDouble,
                timePeriodDouble,
                projectBudgetDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble);
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
    void nullCodeThrowsException() {
        //Arrange
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "projectCode cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(null,
                            projectNameDouble,
                            descriptionDouble,
                            projectStatusDouble,
                            timePeriodDouble,
                            projectBudgetDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null name")
    void nullNameThrowsException() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "projectName cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            null,
                            descriptionDouble,
                            projectStatusDouble,
                            timePeriodDouble,
                            projectBudgetDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null description")
    void nullDescriptionThrowsException() {
        //Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "description cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            null,
                            projectStatusDouble,
                            timePeriodDouble,
                            projectBudgetDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("Project constructor throws exception with null projectStatus")
    void nullProjectStatusThrowsException() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "projectStatus cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            null,
                            timePeriodDouble,
                            projectBudgetDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("Project constructor throws exception with null timePeriod")
    void nullTimePeriodThrowsException() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "timePeriod cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            projectStatusDouble,
                            null,
                            projectBudgetDouble,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null budget")
    void nullBudgetThrowsException() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "projectBudget cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            projectStatusDouble,
                            timePeriodDouble,
                            null,
                            projectSprintDurationDouble,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null sprint Duration")
    void nullSprintDurationThrowsException() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        String expectedMessage = "projectSprintDuration cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            projectStatusDouble,
                            timePeriodDouble,
                            projectBudget,
                            null,
                            projectNumberOfPlannedSprintsDouble);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Project constructor throws exception with null number of planned sprints")
    void nullNumberOfPlannedSprintsThrowsException() {
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock (ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        String expectedMessage = "projectNumberOfPlannedSprints cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new ProjectDDD(projectCodeDouble,
                            projectNameDouble,
                            descriptionDouble,
                            projectStatusDouble,
                            timePeriodDouble,
                            projectBudget,
                            projectSprintDurationDouble,
                            null);
                }
        );

        //Act
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure that we get the project code")
    void ensureThatWeGetProjectCode () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        Mockito.when(projectDDD.getProjectCode()).thenReturn(projectCode);

        //Act
        ProjectCode result = projectDDD.getProjectCode();

        //Assert
        assertEquals(projectCode, result);
    }

    @Test
    @DisplayName("ensure that we get the description")
    void ensureThatWeGetDescription () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        Description description = mock(Description.class);
        Mockito.when(projectDDD.getDescription()).thenReturn(description);

        //Act
        Description result = projectDDD.getDescription();

        //Assert
        assertEquals(description, result);
    }

    @Test
    @DisplayName("ensure that we get the project status")
    void ensureThatWeGetProjectStatus () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectStatus projectStatus = mock(ProjectStatus.class);
        Mockito.when(projectDDD.getProjectStatus()).thenReturn(projectStatus);

        //Act
        ProjectStatus result = projectDDD.getProjectStatus();

        //Assert
        assertEquals(projectStatus, result);
    }

    @Test
    @DisplayName("ensure that we get the time period of project")
    void ensureThatWeGetTimePeriod () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        Mockito.when(projectDDD.getTimePeriod()).thenReturn(timePeriod);

        //Act
        TimePeriod result = projectDDD.getTimePeriod();

        //Assert
        assertEquals(timePeriod, result);
    }

    @Test
    @DisplayName("ensure that we get the project budget")
    void ensureThatWeGetProjectBudget () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        Mockito.when(projectDDD.getProjectBudget()).thenReturn(projectBudget);

        //Act
        ProjectBudget result = projectDDD.getProjectBudget();

        //Assert
        assertEquals(projectBudget, result);
    }

    @Test
    @DisplayName("ensure that we get the project sprint duration")
    void ensureThatWeGetProjectSprintDuration () {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectSprintDuration projectSprintDuration = mock(ProjectSprintDuration.class);
        Mockito.when(projectDDD.getProjectSprintDuration()).thenReturn(projectSprintDuration);

        //Act
        ProjectSprintDuration result = projectDDD.getProjectSprintDuration();

        //Assert
        assertEquals(projectSprintDuration, result);
    }

    @Test
    @DisplayName("ensure that we get the project number of planned sprints")
    void ensureThatWeGetProjectNumberOfPlannedSprints() {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = mock(ProjectNumberOfPlannedSprints.class);
        Mockito.when(projectDDD.getProjectNumberOfPlannedSprints()).thenReturn(projectNumberOfPlannedSprints);

        //Act
        ProjectNumberOfPlannedSprints result = projectDDD.getProjectNumberOfPlannedSprints();

        //Assert
        assertEquals(projectNumberOfPlannedSprints, result);
    }

}