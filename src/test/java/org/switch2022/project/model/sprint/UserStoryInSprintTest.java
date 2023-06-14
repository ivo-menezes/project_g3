package org.switch2022.project.model.sprint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.switch2022.project.model.valueobject.UserStoryEffortEstimate;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;
import org.switch2022.project.model.valueobject.UserStoryStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryInSprintTest {

    @Mock
    private UserStoryInSprintID userStoryInSprintID;
    @Mock
    private UserStoryEffortEstimate userStoryEffortEstimate;
    @Mock
    private UserStoryStatus userStoryInSprintStatus;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This test ensures that UserStoryInSprint is successfully created,
     * using mocks for its attributes.
     */
    @Test
    @DisplayName("ensure UserStoryInSprint is successfully created")
    public void ensureUSInSprintCreated() {
        // Act
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        // Assert
        assertInstanceOf(UserStoryInSprint.class, userStoryInSprint);
    }

    /**
     * This test ensures that passing a null UserStoryInSprintID throwns an exception.
     */
    @Test
    @DisplayName("ensure UserStoryInSprintID is not null")
    void ensureUSInSprintNotNull() {
        //Arrange
        String expectedMessage = "ID for User Story in Sprint cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new UserStoryInSprint(null, userStoryEffortEstimate,
                        userStoryInSprintStatus)
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * This test ensures that a userStoryInSprintID is successfully returned.
     */
    @Test
    @DisplayName("ensure userStoryInSprintID is returned")
    void ensureUSInSprintIDIsReturned() {
        //Arrange
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprintID,
                userStoryEffortEstimate, userStoryInSprintStatus);
        UserStoryInSprintID expected = userStoryInSprintID;
        //Act
        UserStoryInSprintID result = userStoryInSprint.identity();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test ensures that passing a null UserStoryEffortEstimate throwns an exception.
     */
    @Test
    @DisplayName("ensure UserStoryEffortEstimate is not null")
    void ensureUSEfforteEstimateNotNull() {
        //Arrange
        String expectedMessage = "User Story effort estimate in Sprint cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new UserStoryInSprint(userStoryInSprintID, null,
                        userStoryInSprintStatus)
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * This test ensures that a UserStoryEffortEstimate is successfully returned.
     */
    @Test
    @DisplayName("ensure UserStoryEffortEstimate is returned")
    void ensureUSEffortEstimateIsReturned() {
        //Arrange
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprintID,
                userStoryEffortEstimate, userStoryInSprintStatus);
        UserStoryEffortEstimate expected = userStoryEffortEstimate;
        //Act
        UserStoryEffortEstimate result = userStoryInSprint.getUserStoryEffortEstimate();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test ensures that passing a null UserStoryStatus throwns an exception.
     */
    @Test
    @DisplayName("ensure UserStoryStatus is not null")
    void ensureUSStatusNotNull() {
        //Arrange
        String expectedMessage = "Status for User Story in Sprint cannot be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new UserStoryInSprint(userStoryInSprintID, userStoryEffortEstimate,
                        null)
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * This test ensures that a UserStoryStatus is successfully returned.
     */
    @Test
    @DisplayName("ensure UserStoryStatus is returned")
    void ensureUSStatusIsReturned() {
        //Arrange
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprintID,
                userStoryEffortEstimate, userStoryInSprintStatus);
        UserStoryStatus expected = userStoryInSprintStatus;
        //Act
        UserStoryStatus result = userStoryInSprint.getUserStoryInSprintStatus();
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
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);
        //Act
        boolean result = userStoryInSprint.equals(null);
        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("ensure object equals itself")
    void ensureObjectEqualsItself() {
        //Arrange
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        //Act
        boolean result = userStoryInSprint.equals(userStoryInSprint);

        //Assert
        assertTrue(result);
    }


    /**
     * This test ensures that 2 objects with the same userStoryInSprintID are equal.
     */
    @Test
    @DisplayName("ensure object equals object with same userStoryInSprintID")
    void objectEqualsSameUserStoryInSprintID() {
        //Arrange
        UserStoryInSprint userStoryInSprint1 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        UserStoryEffortEstimate userStoryEffortEstimate2 = mock(UserStoryEffortEstimate.class);
        UserStoryStatus userStoryInSprintStatus2 = mock(UserStoryStatus.class);
        UserStoryInSprint userStoryInSprint2 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate2,
                userStoryInSprintStatus2);

        //Act
        boolean result = userStoryInSprint1.equals(userStoryInSprint2);

        //Assert
        assertTrue(result);
    }

    /**
     * test ensures that 2 objects with the different userStoryInSprintID are not equal.
     */
    @Test
    @DisplayName("ensure object does not equal object with different userStoryInSprintID")
    void objectDoesNotEqualUserStoryInSprintWithDifferentID() {
        //Arrange
        UserStoryInSprint userStoryInSprint1 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        UserStoryInSprintID userStoryInSprintID2 = mock(UserStoryInSprintID.class);
        UserStoryInSprint userStoryInSprint2 = new UserStoryInSprint(
                userStoryInSprintID2,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        //Act
        boolean result = userStoryInSprint1.equals(userStoryInSprint2);

        //Assert
        assertFalse(result);
    }

    /**
     * This test ensure that objects from different classes are not equal.
     */
    @Test
    @DisplayName("ensure object does not equal object of different class")
    void objectDoesNotEqualProjectOfDifferentClass() {
        //Arrange
        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);
        String fakeUserStoryInSprint = "I'm a fake!";

        //Act
        boolean result = userStoryInSprint.equals(fakeUserStoryInSprint);

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
        UserStoryInSprint userStoryInSprint1 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        UserStoryInSprint userStoryInSprint2 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        //Act
        int hashCode1 = userStoryInSprint1.hashCode();
        int hashCode2 = userStoryInSprint2.hashCode();

        //Assert
        assertEquals(hashCode1, hashCode2);
    }

    /**
     * This test ensures that 2 different objects have different hashcodes.
     */
    @Test
    @DisplayName("ensure different objects have different hashcodes")
    void ensureDifferentObjectsHaveDifferentHashcodes() {
        //Arrange
        UserStoryInSprint userStoryInSprint1 = new UserStoryInSprint(
                userStoryInSprintID,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        UserStoryInSprintID userStoryInSprintID2 = mock(UserStoryInSprintID.class);
        UserStoryInSprint userStoryInSprint2 = new UserStoryInSprint(
                userStoryInSprintID2,
                userStoryEffortEstimate,
                userStoryInSprintStatus);

        //Act
        int hashCode1 = userStoryInSprint1.hashCode();
        int hashCode2 = userStoryInSprint2.hashCode();

        //Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}