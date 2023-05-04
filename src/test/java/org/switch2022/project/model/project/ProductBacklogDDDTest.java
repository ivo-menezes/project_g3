package org.switch2022.project.model.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductBacklogDDDTest {

    @DisplayName("adding a UserStoryID to an empty backlog succeeds")
    @Test
    void addToAnEmptyBacklogSucceeds() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(0);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();
        // Act
        boolean result = productBacklog.add(userStoryIdDouble, priorityDouble);

        // Assert
        assertTrue(result);
    }

    @DisplayName("adding a UserStoryID already in the backlog fails")
    @Test
    void addSameUserStoryIDFails() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        when(priorityDouble.getValue()).thenReturn(0);

        ProductBacklogDDD productBacklog = new ProductBacklogDDD();
        // Act
        // adding for the first time (should succeed)
        productBacklog.add(userStoryIdDouble, priorityDouble);
        // adding for the second time (should fail)
        boolean result = productBacklog.add(userStoryIdDouble, priorityDouble);

        // Assert
        assertFalse(result);

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
}