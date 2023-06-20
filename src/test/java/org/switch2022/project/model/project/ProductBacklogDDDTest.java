package org.switch2022.project.model.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductBacklogDDDTest {

    @DisplayName("ensure instantiating a ProductBacklog with a list of UserStoryID succeeds")
    @Test
    void instantiateProductBacklogWithListOfUserStoryIdSucceeds() {
        UserStoryID userStoryIdDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryIdDouble2 = mock(UserStoryID.class);
        List<UserStoryID> userStoryIDList = new ArrayList<>();
        userStoryIDList.add(userStoryIdDouble1);
        userStoryIDList.add(userStoryIdDouble2);
        ProductBacklogDDD productBacklog = new ProductBacklogDDD(userStoryIDList);

        assertInstanceOf(ProductBacklogDDD.class, productBacklog);
    }

    @DisplayName("ensure adding a UserStoryID to an empty backlog with priority 1 returns priority 1")
    @Test
    void addToAnEmptyBacklogPriority1ReturnsPriority1() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(1);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();

        int expectedValue = 1;

        // Act
        UserStoryPriority result = productBacklog.add(userStoryIdDouble, priorityDouble);
        int resultValue = result.getValue();

        // Assert
        assertEquals(expectedValue, resultValue);
    }

    @DisplayName("ensure adding a UserStoryID to an empty backlog with priority 0 returns priority 1")
    @Test
    void addToAnEmptyBacklogPriority0ReturnsPriority1() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(0);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();

        int expectedValue = 1;

        // Act
        UserStoryPriority result = productBacklog.add(userStoryIdDouble, priorityDouble);
        int resultValue = result.getValue();

        // Assert
        assertEquals(expectedValue, resultValue);
    }

    @DisplayName("ensure adding a UserStoryID to a backlog of size 1 with priority 0 returns priority 2")
    @Test
    void addToABacklogOfSizeOnePriority0ReturnsPriority2() {
        // Arrange
        UserStoryID userStoryIdDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryIdDouble2 = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(0);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();

        int expectedValue = 2;

        // Act
        productBacklog.add(userStoryIdDouble1, priorityDouble);
        UserStoryPriority result = productBacklog.add(userStoryIdDouble2, priorityDouble);
        int resultValue = result.getValue();

        // Assert
        assertEquals(expectedValue, resultValue);
    }

    @DisplayName("ensure adding a UserStoryID to a backlog of size 1 with priority 1 returns priority 1")
    @Test
    void addToABacklogOfSizeOnePriority1ReturnsPriority1() {
        // Arrange
        UserStoryID userStoryIdDouble1 = mock(UserStoryID.class);
        UserStoryID userStoryIdDouble2 = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(1);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();

        int expectedValue = 1;

        // Act
        productBacklog.add(userStoryIdDouble1, priorityDouble);
        UserStoryPriority result = productBacklog.add(userStoryIdDouble2, priorityDouble);
        int resultValue = result.getValue();

        // Assert
        assertEquals(expectedValue, resultValue);
    }

    @DisplayName("adding a UserStoryID already in the backlog fails")
    @Test
    void addSameUserStoryIDFails() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(0);

        String expectedMessage = "UserStoryID already in project";

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();
        // Act
        // adding for the first time (should succeed)
        productBacklog.add(userStoryIdDouble, priorityDouble);
        // adding for the second time (should fail)
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.add(userStoryIdDouble, priorityDouble);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);

    }

    @DisplayName("getting open user stories from an empty backlog returns an empty list")
    @Test
    void getOpenUserStoriesFromEmptyBacklogReturnsEmptyList() {
        // Arrange
        ProductBacklogDDD productBacklog = new ProductBacklogDDD();
        int expectedSize = 0;

        // Act
        List<UserStoryID> result = productBacklog.getOpenUserStories();
        int resultSize = result.size();

        // Assert
        assertEquals(expectedSize, resultSize);
    }

    @DisplayName("check if remove userStoryID")
    @Test
    void ensureReturnsCorrectProductBacklog() {
        //Arrange
        UserStoryID userStoryID1 = mock(UserStoryID.class);
        UserStoryID userStoryID2 = mock(UserStoryID.class);
        UserStoryID userStoryID3 = mock(UserStoryID.class);
        UserStoryID userStoryID4 = mock(UserStoryID.class);

        List<UserStoryID> openUserStories = new ArrayList<>();
        openUserStories.add(userStoryID1);
        openUserStories.add(userStoryID2);
        openUserStories.add(userStoryID3);
        openUserStories.add(userStoryID4);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD(openUserStories);
        productBacklog.removeUserStoryID(userStoryID1);

        ProjectDDD project = mock(ProjectDDD.class);
        when(project.getProductBacklog()).thenReturn(productBacklog.getOpenUserStories());

        //Act
        List<UserStoryID> result = project.getProductBacklog();

        //Assert
        assertEquals(productBacklog.getOpenUserStories(),result);
    }
}