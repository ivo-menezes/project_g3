package org.switch2022.project.model.userStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.Description;
import org.switch2022.project.model.valueobject.UserStoryAcceptanceCriteria;
import org.switch2022.project.model.valueobject.UserStoryActor;
import org.switch2022.project.model.valueobject.UserStoryID;

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


}