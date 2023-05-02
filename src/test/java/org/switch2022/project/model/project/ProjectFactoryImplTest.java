package org.switch2022.project.model.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.userStory.UserStoryFactoryImpl;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectFactoryImplTest {

    @DisplayName("assert that creating Project succeeds")
    @Test
    void createProjectSucceeds() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble =  mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        // act
        ProjectDDD project = factory.createProject(projectCodeDouble,
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
    @DisplayName("assert that trying to create project with null code throws Exception")
    @Test
    void createProjectNullCodeThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = null;
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectCode cannot be null";

        // act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null name throws Exception")
    @Test
    void createProjectNullNameThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = null;
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectName cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null description throws Exception")
    @Test
    void createProjectNullDescriptionThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = null;
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "description cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null status throws Exception")
    @Test
    void createProjectNullStatusThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = null;
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectStatus cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null timePeriod throws Exception")
    @Test
    void createProjectNullTimePeriodThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = null;
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "timePeriod cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
    @DisplayName("assert that trying to create project with null budget throws Exception")
    @Test
    void createProjectNullBudgetThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = null;
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectBudget cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null SprintDuration throws Exception")
    @Test
    void createProjectNullSprintDurationThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = null;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectSprintDuration cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create project with null NumberOfPlannedSprints throws Exception")
    @Test
    void createProjectNullNumberOfPlannedSprintsThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprintsDouble = null;

        ProjectFactoryImpl factory = new ProjectFactoryImpl();

        String expectedMessage = "projectNumberOfPlannedSprints cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createProject(projectCodeDouble,
                    projectNameDouble,
                    descriptionDouble,
                    projectStatusDouble,
                    timePeriodDouble,
                    projectBudgetDouble,
                    projectSprintDurationDouble,
                    projectNumberOfPlannedSprintsDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


}