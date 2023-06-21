package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AssembledUSTest {

    @Mock
    private UserStoryNumber userStoryNumber;
    @Mock
    private ProjectCode projectCode;
    @Mock
    private SprintNumber sprintNumber;
    @Mock
    private UserStoryActor userStoryActor;
    @Mock
    private Description userStoryDescription;
    @Mock
    private UserStoryAcceptanceCriteria userStoryAcceptanceCriteria;
    @Mock
    private UserStoryStatus userStoryStatus;
    @Mock
    private UserStoryEffortEstimate userStoryEffortEstimate;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }





    /**
     * This test ensures that AssembledUS is successfully created,
     * using mocks for its attributes.
     */
    @Test
    @DisplayName("ensure AssembledUS is successfully created")
    public void ensureAssembledUSisCreated() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        // Assert
        assertInstanceOf(AssembledUS.class, assembledUS);
    }

    @Test
    @DisplayName("ensure userStoryNumber is not null")
    void ensureUSNumberNotNull() {
        //Arrange
        String expectedMessage = "User story number cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        null,
                        projectCode,
                        sprintNumber,
                        userStoryActor,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure projectCode is not null")
    void ensureProjectCodeNotNull() {
        //Arrange
        String expectedMessage = "Project code cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        null,
                        sprintNumber,
                        userStoryActor,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure sprintNumber is not null")
    void ensureSprintNumberNotNull() {
        //Arrange
        String expectedMessage = "Sprint Number code cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        null,
                        userStoryActor,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryActor is not null")
    void ensureUserSToryActorNotNull() {
        //Arrange
        String expectedMessage = "User story actor code cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        sprintNumber,
                        null,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryDescription is not null")
    void ensureUserSToryDescriptionNotNull() {
        //Arrange
        String expectedMessage = "User story description cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        sprintNumber,
                        userStoryActor,
                        null,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryAcceptanceCriteria is not null")
    void ensureUserSToryAcceptanceCriteriaNotNull() {
        //Arrange
        String expectedMessage = "User story acceptance criteria cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        sprintNumber,
                        userStoryActor,
                        userStoryDescription,
                        null,
                        userStoryStatus,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryStatus is not null")
    void ensureUserSToryStatusNotNull() {
        //Arrange
        String expectedMessage = "User story status cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        sprintNumber,
                        userStoryActor,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        null,
                        userStoryEffortEstimate
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure userStoryEffortEstimate is not null")
    void ensureUserSToryEffortEstimateNotNull() {
        //Arrange
        String expectedMessage = "User story effort estimate cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new AssembledUS(
                        userStoryNumber,
                        projectCode,
                        sprintNumber,
                        userStoryActor,
                        userStoryDescription,
                        userStoryAcceptanceCriteria,
                        userStoryStatus,
                        null
                )
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("ensure UserStoryStatus is returned")
    void ensureUSNumberIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        UserStoryNumber expected = userStoryNumber;
        //Act
        UserStoryNumber result = assembledUS.getUserStoryNumber();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure projectCode is returned")
    void ensureProjectCodeIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        ProjectCode expected = projectCode;
        //Act
        ProjectCode result = assembledUS.getProjectCode();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure sprintNumber is returned")
    void ensureSprintNumberIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        SprintNumber expected = sprintNumber;
        //Act
        SprintNumber result = assembledUS.getSprintNumber();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure userStoryActor is returned")
    void ensureUserStoryActorIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        UserStoryActor expected = userStoryActor;
        //Act
        UserStoryActor result = assembledUS.getUserStoryActor();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure userStoryDescription is returned")
    void ensureUserStoryDescriptionIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        Description expected = userStoryDescription;
        //Act
        Description result = assembledUS.getUserStoryDescription();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure userStoryAcceptanceCriteria is returned")
    void ensureUserStoryAcceptanceCriteriaIsReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        UserStoryAcceptanceCriteria expected = userStoryAcceptanceCriteria;
        //Act
        UserStoryAcceptanceCriteria result = assembledUS.getUserStoryAcceptanceCriteria();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure userStoryStatus is returned")
    void ensureUserStoryStatusReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        UserStoryStatus expected = userStoryStatus;
        //Act
        UserStoryStatus result = assembledUS.getUserStoryStatus();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure userStoryEffortEstimate is returned")
    void ensureUserStoryEffortEstimateReturned() {
        //Arrange
        // Act
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        UserStoryEffortEstimate expected = userStoryEffortEstimate;
        //Act
        UserStoryEffortEstimate result = assembledUS.getUserStoryEffortEstimate();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * This tests ensures that a created UserStoryInSprint is not null.
     */
    @Test
    @DisplayName("ensure object does not equal null")
    void ensureObjectNotEqualNull() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        //Act
        boolean result = assembledUS.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object equals itself")
    void ensureObjectEqualsItself() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUS);

        //Assert
        assertTrue(result);
    }

    /**
     * This test ensure that objects from different classes are not equal.
     */
    @Test
    @DisplayName("ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );
        String fakeAssembledUS = "I'm a fake!";

        //Act
        boolean result = assembledUS.equals(fakeAssembledUS);

        //Assert
        assertFalse(result);
    }
    /**
     * test ensures that 2 objects with the different userStoryNumber are not equal.
     */
    @Test
    @DisplayName("ensure object does not equal object with different userStoryNumber")
    void objectDoesNotEqualWithDifferentUserStoryNumberValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryNumber userStoryNumberTwo = mock(UserStoryNumber.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumberTwo,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different projectCode")
    void objectDoesNotEqualWithDifferentProjectCodeValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        ProjectCode projectCodeTwo = mock(ProjectCode.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCodeTwo,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different SprintNumber")
    void objectDoesNotEqualWithDifferentSprintNumberValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        SprintNumber sprintNumberTwo = mock(SprintNumber.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumberTwo,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different UserStoryActor")
    void objectDoesNotEqualWithDifferentUserStoryActorValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryActor userStoryActorTwo = mock(UserStoryActor.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActorTwo,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different Description")
    void objectDoesNotEqualWithDifferentUserStoryDescriptionValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        Description userStoryDescriptionTwo = mock(Description.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescriptionTwo,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different UserStoryAcceptanceCriteria")
    void objectDoesNotEqualWithDifferentUserStoryAcceptanceCriteriaValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryAcceptanceCriteria userStoryAcceptanceCriteriaTwo = mock(UserStoryAcceptanceCriteria.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteriaTwo,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }
    @Test
    @DisplayName("ensure object does not equal object with different UserStoryStatus")
    void objectDoesNotEqualWithDifferentUserStoryStatusValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryStatus userStoryStatusTwo = mock(UserStoryStatus.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatusTwo,
                userStoryEffortEstimate
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object does not equal object with different UserStoryEffortEstimate")
    void objectDoesNotEqualWithDifferentUserStoryEffortEstimateValues() {
        //Arrange
        AssembledUS assembledUS = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryEffortEstimate userStoryEffortEstimateTwo = mock(UserStoryEffortEstimate.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimateTwo
        );

        //Act
        boolean result = assembledUS.equals(assembledUSTwo);

        //Assert
        assertFalse(result);
    }


    /**
     * This test ensures that 2 equal objects have the same hashcode.
     */
    @Test
    @DisplayName("ensure equal objects have same hashcode")
    void ensureEqualObjectsHaveSameHashcode() {
        //Arrange
        AssembledUS assembledUSOne = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        AssembledUS assembledUSTwo = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        int hashCode1 = assembledUSOne.hashCode();
        int hashCode2 = assembledUSTwo.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    /**
     * This test ensures that 2 different objects have different hashcodes.
     */
    @Test
    @DisplayName("ensure different objects have different hashcodes")
    void ensureDifferentObjectsHaveDifferentHashCodes() {
        //Arrange
        AssembledUS assembledUSOne = new AssembledUS(
                userStoryNumber,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        UserStoryNumber userStoryNumberTwo = mock(UserStoryNumber.class);
        AssembledUS assembledUSTwo = new AssembledUS(

                userStoryNumberTwo,
                projectCode,
                sprintNumber,
                userStoryActor,
                userStoryDescription,
                userStoryAcceptanceCriteria,
                userStoryStatus,
                userStoryEffortEstimate
        );

        //Act
        int hashCode1 = assembledUSOne.hashCode();
        int hashCode2 = assembledUSTwo.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }

}