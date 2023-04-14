package org.switch2022.project.model.userStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.valueobject.ProjectCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserStoryFactoryImplTest {

    @DisplayName("assert that creating UserStory succeeds")
    @Test
    void createUserStorySucceeds() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        dtoDouble.id = "US017";
        dtoDouble.actor = "Product Owner";
        dtoDouble.text = "As a Product Owner, I want to create a user story.";
        dtoDouble.acceptanceCriteria = "None";

        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();

        // act
        UserStoryDDD userStory = factory.createUserStory(dtoDouble, projectCodeDouble);

        // assert
        assertInstanceOf(UserStoryDDD.class, userStory);
    }

    @DisplayName("assert that trying to create UserStory with null DTO throws Exception")
    @Test
    void createUserStoryNullDTOThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryDTO dto = null;
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        String expectedMessage = "UserStoryDTO must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            factory.createUserStory(dto, projectCodeDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }
}