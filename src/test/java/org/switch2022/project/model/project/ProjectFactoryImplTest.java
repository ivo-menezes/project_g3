package org.switch2022.project.model.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectFactoryImplTest {

    @Mock
    private ProjectCode projectCodeDouble;
    @Mock
    private ProjectName projectNameDouble;
    @Mock
    private Description descriptionDouble;
    @Mock
    private TimePeriod timePeriodDouble;
    @Mock
    private ProjectSprintDuration projectSprintDurationDouble;
    @Mock
    private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble;
    @Mock
    private CustomerID customerIdDouble;
    @Mock
    private BusinessSectorID businessSectorIdDouble;
    @Mock
    private TypologyID typologyIdDouble;
    @Mock
    private ProjectBudget projectBudgetDouble;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("assert that creating Project succeeds")
    @Test
    void createProjectSucceeds() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        // act
        ProjectDDD project = factory.createProject(projectCodeDouble,
                                                   projectNameDouble,
                                                   descriptionDouble,
                                                   timePeriodDouble,
                                                   projectSprintDurationDouble,
                                                   projectNumberOfPlannedSprintsDouble,
                                                   customerIdDouble,
                                                   businessSectorIdDouble,
                                                   typologyIdDouble,
                                                   projectBudgetDouble
                );

        // assert
        assertInstanceOf(ProjectDDD.class, project);
    }

    @Test
    @DisplayName("Ensure creating a Project from a projectDto succeeds")
    void ensureProjectIsCreatedFromDto(){
        //Arrange
        NewProjectDTO projectDto = mock(NewProjectDTO.class);

        projectDto.projectCode = projectCodeDouble;
        projectDto.projectName = projectNameDouble;
        projectDto.description = descriptionDouble;
        projectDto.timePeriod = timePeriodDouble;
        projectDto.projectSprintDuration = projectSprintDurationDouble;
        projectDto.projectNumberOfPlannedSprints = projectNumberOfPlannedSprintsDouble;
        projectDto.customerID = customerIdDouble;
        projectDto.businessSectorID = businessSectorIdDouble;
        projectDto.typologyID = typologyIdDouble;
        projectDto.projectBudget = projectBudgetDouble;


        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        //Act
        ProjectDDD resultingProject = factory.createProject(projectDto);

        //Assert
        assertInstanceOf(ProjectDDD.class, resultingProject);
    }


    @DisplayName("assert that trying to create project with null code throws Exception")
    @Test
    void createProjectNullCodeThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "Code must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(null,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIdDouble,
                businessSectorIdDouble,
                typologyIdDouble,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null name throws Exception")
    @Test
    void createProjectNullNameThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "Name must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(projectCodeDouble,
                null,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIdDouble,
                businessSectorIdDouble,
                typologyIdDouble,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null description throws Exception")
    @Test
    void createProjectNullDescriptionThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "Description must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(projectCodeDouble,
                projectNameDouble,
                null,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIdDouble,
                businessSectorIdDouble,
                typologyIdDouble,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("assert that trying to create project with null customer ID throws Exception")
    @Test
    void createProjectNullTimePeriodThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "CustomerId must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                null,
                businessSectorIdDouble,
                typologyIdDouble,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("assert that trying to create project with null business sector ID throws Exception")
    @Test
    void createProjectNullBudgetThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "BusinessSectorId must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIdDouble,
                null,
                typologyIdDouble,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null typology ID throws Exception")
    @Test
    void createProjectNullSprintDurationThrowsException() {
        // arrange
        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "TypologyId must not be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                factory.createProject(projectCodeDouble,
                projectNameDouble,
                descriptionDouble,
                timePeriodDouble,
                projectSprintDurationDouble,
                projectNumberOfPlannedSprintsDouble,
                customerIdDouble,
                businessSectorIdDouble,
                null,
                projectBudgetDouble));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
}

