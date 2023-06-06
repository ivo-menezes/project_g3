package org.switch2022.project.model.userStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryFactoryImplTest {

    @DisplayName("assert that creating UserStory succeeds")
    @Test
    void createUserStorySucceeds() {
        // arrange
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        // act
        UserStoryDDD userStory = factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);

        // assert
        assertInstanceOf(UserStoryDDD.class, userStory);
    }

    @DisplayName("assert that trying to create UserStory with null UserStoryID throws Exception")
    @Test
    void createUserStoryNullIDThrowsException() {
        // arrange
        UserStoryID idDouble = null;
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        String expectedMessage = "userStoryID cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create UserStory with null actor throws Exception")
    @Test
    void createUserStoryNullActorThrowsException() {
        // arrange
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = null;
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        String expectedMessage = "actor cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create UserStory with null description throws Exception")
    @Test
    void createUserStoryNullDescriptionThrowsException() {
        // arrange
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = null;
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        String expectedMessage = "description cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create UserStory with null acceptance criteria throws Exception")
    @Test
    void createUserStoryNullCriteriaThrowsException() {
        // arrange
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = null;

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        String expectedMessage = "acceptanceCriteria cannot be null";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that trying to create UserStory with null Dto throws exception")
    @Test
    void createUserStoryNullDtoThrowsException() {
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        String expectedMessage = "dto must not be null";

        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(null);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating UserStory from DTO succeeds")
    @Test
    void createUserStoryFromDtoSucceeds() {
        // arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        dtoDouble.userStoryNumber = userStoryNumberDouble;
        dtoDouble.projectCode = projectCodeDouble;
        dtoDouble.actor = actorDouble;
        dtoDouble.description = descriptionDouble;
        dtoDouble.acceptanceCriteria = criteriaDouble;

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        // act
        UserStoryDDD userStory = factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);

        // assert
        assertInstanceOf(UserStoryDDD.class, userStory);
    }

    @DisplayName("assert that creating UserStory from DTO is not null")
    @Test
    void createUserStoryFromDtoNotNull() {
        // arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryID idDouble = mock(UserStoryID.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        dtoDouble.userStoryNumber = userStoryNumberDouble;
        dtoDouble.projectCode = projectCodeDouble;
        dtoDouble.actor = actorDouble;
        dtoDouble.description = descriptionDouble;
        dtoDouble.acceptanceCriteria = criteriaDouble;

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        // act
        UserStoryDDD userStory = factory.createUserStory(idDouble, actorDouble, descriptionDouble, criteriaDouble);

        // assert
        assertNotNull(userStory);
    }
}