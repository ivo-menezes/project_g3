package org.switch2022.project.model.userStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryDDDTest {
    @Test
    @DisplayName("create a user story successfully")
    void createUserStoryDDD() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        //Act
        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor, userStoryDescription, userStoryAcceptanceCriteria);
        //Assert
        assertInstanceOf(UserStoryDDD.class, userStory);
    }

    @Test
    @DisplayName("US constructor throws exception with null id")
    void nullIdThrowsException() {
        //Arrange
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        String expectedMessage = "User Story ID must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryDDD(null,
                            userStoryActor,
                            userStoryDescription,
                            userStoryAcceptanceCriteria);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("US constructor throws exception with null actor")
    void nullActorThrowsException() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        String expectedMessage = "User Story actor must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryDDD(userStoryID, null,
                            userStoryDescription,
                            userStoryAcceptanceCriteria);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("US constructor throws exception with null description")
    void nullDescriptionThrowsException() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = mock(UserStoryAcceptanceCriteria.class);
        String expectedMessage = "User Story text must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryDDD(userStoryID, userStoryActor, null,
                            userStoryAcceptanceCriteria);
                }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("US constructor throws exception with null acceptance criteria")
    void nullAcceptanceCriteriaThrowsException() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        String expectedMessage = "User Story acceptance criteria must not be null";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    new UserStoryDDD(userStoryID, userStoryActor,
                            userStoryDescription, null ); }
        );
        //Act
        String resultMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    @DisplayName("object equals itself")
    void testEqualsWithItself() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD us = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryDDD us2 = us;
        //Act
        boolean result = us.equals(us2);
        //Assert
        assertTrue(result);
    }
    @Test
    @DisplayName("object does not equal null")
    void testEqualsWithNull() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD us = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        //Act
        boolean result = us.equals(null);
        //Assert
        assertFalse(result);
    }
    @Test
    @DisplayName("object does not equal object of another class")
    void testEqualsWithAnotherClass() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD us = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);

        String fakeUserStory = "US001";
        boolean result = us.equals(fakeUserStory);
        assertFalse(result);
    }
    @Test
    @DisplayName("object equals object with same id")
    void testEqualsWithEqualId() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryDDD anotherUserStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        //Act
        boolean result = userStory.equals(anotherUserStory);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("object does not equal object with different id")
    void testEqualsWithDifferentId() {
        //Arrange
        UserStoryID userStoryID_1 = mock(UserStoryID.class);
        UserStoryID userStoryID_2 = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID_1, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryDDD anotherUserStory = new UserStoryDDD(userStoryID_2, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        //Act
        boolean result = userStory.equals(anotherUserStory);
        //Assert
        assertFalse(result);
    }
    @Test
    @DisplayName("equal objects have same hash code")
    void testSameHashCode() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor_1 = mock(UserStoryActor.class);
        UserStoryActor userStoryActor_2 = mock(UserStoryActor.class);
        Description userStoryDescription_1 = mock(Description.class);
        Description userStoryDescription_2 = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria_1 =
                mock(UserStoryAcceptanceCriteria.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria_2 =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor_1,
                userStoryDescription_1, userStoryAcceptanceCriteria_1);
        UserStoryDDD anotherUserStory = new UserStoryDDD(userStoryID, userStoryActor_2,
                userStoryDescription_2, userStoryAcceptanceCriteria_2);
        //Act
        int userStoryHashCode = userStory.hashCode();
        int anotherUserStoryHashCode = anotherUserStory.hashCode();
        //Assert
        assertEquals(userStoryHashCode, anotherUserStoryHashCode);
    }

    @Test
    @DisplayName("different objects have different hash code")
    void testDifferentHashCode() {
        //Arrange
        UserStoryID userStoryID_1 = mock(UserStoryID.class);
        UserStoryID userStoryID_2 = mock(UserStoryID.class);
        UserStoryActor userStoryActor_1 = mock(UserStoryActor.class);
        UserStoryActor userStoryActor_2 = mock(UserStoryActor.class);
        Description userStoryDescription_1 = mock(Description.class);
        Description userStoryDescription_2 = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria_1 =
                mock(UserStoryAcceptanceCriteria.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria_2 =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID_1, userStoryActor_1,
                userStoryDescription_1, userStoryAcceptanceCriteria_1);
        UserStoryDDD anotherUserStory = new UserStoryDDD(userStoryID_2, userStoryActor_2,
                userStoryDescription_2, userStoryAcceptanceCriteria_2);
        //Act
        int userStoryHashCode = userStory.hashCode();
        int anotherUserStoryHashCode = anotherUserStory.hashCode();
        //Assert
        assertNotEquals(userStoryHashCode, anotherUserStoryHashCode);
    }
    @Test
    @DisplayName("ensure user story ID is returned")
    void ensureUserStoryIsReturned() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryID expected = userStoryID;

        //Act
        UserStoryID result = userStory.identity();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure user story actor is returned")
    void ensureUserStoryActorIsReturned() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryActor expected = userStoryActor;

        //Act
        UserStoryActor result = userStory.getActor();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure user story description is returned")
    void ensureUserStoryDescriptionIsReturned() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        Description expected = userStoryDescription;

        //Act
        Description result = userStory.getDescription();

        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure user story acceptance criteria are returned")
    void ensureUserStoryAcceptanceCriteriaAreReturned() {
        //Arrange
        UserStoryID userStoryID = mock(UserStoryID.class);
        UserStoryActor userStoryActor = mock(UserStoryActor.class);
        Description userStoryDescription = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria =
                mock(UserStoryAcceptanceCriteria.class);

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor,
                userStoryDescription, userStoryAcceptanceCriteria);
        UserStoryAcceptanceCriteria expected = userStoryAcceptanceCriteria;

        //Act
       UserStoryAcceptanceCriteria result = userStory.getAcceptanceCriteria();

        //Assert
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("ensure user story status is returned")
    void ensureUserStoryStatusIsReturned(){
        //Arrange
        UserStoryDDD userStory = mock(UserStoryDDD.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        Mockito.when(userStory.getStatus()).thenReturn(userStoryStatus);
        //Act
        UserStoryStatus result = userStory.getStatus();
        //Assert
        assertNotNull(result);
    }
}