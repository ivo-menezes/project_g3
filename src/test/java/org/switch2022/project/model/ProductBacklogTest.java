package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductBacklogTest {

    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority 0")
    void createUserStoryWithPriorityZero() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = 0;

        ProductBacklog productBacklog = new ProductBacklog();

        // act
        boolean result = productBacklog.createUserStory(dto, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority 5")
    void createUserStoryWithPriorityFive() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = 5;

        ProductBacklog productBacklog = new ProductBacklog();

        // act
        boolean result = productBacklog.createUserStory(dto, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority 5")
    void createUserStoryWithPriorityMinusTwo() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = -2;

        ProductBacklog productBacklog = new ProductBacklog();

        // act
        boolean result = productBacklog.createUserStory(dto, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("create and add user story to product backlog already containing equal user story fails")
    void createUserStoryWithSameIdFails() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US001";
        dto2.actor = "Administrator";
        dto2.text = "As Administrator, I want to delete all accounts";
        dto2.acceptanceCriteria = "None";

        int priority = 0;

        ProductBacklog productBacklog = new ProductBacklog();

        // act
        productBacklog.createUserStory(dto, priority);
        boolean result = productBacklog.createUserStory(dto2, priority);

        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("create and successfully add two user stories to product backlog")
    void createTwoUserStories() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US002";
        dto2.actor = "Administrator";
        dto2.text = "As Administrator, I want to delete all accounts";
        dto2.acceptanceCriteria = "None";

        int priority = 0;

        ProductBacklog productBacklog = new ProductBacklog();

        // act
        productBacklog.createUserStory(dto, priority);
        boolean result = productBacklog.createUserStory(dto2, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("creating user story with null DTO throws exception")
    void createUserStoryWithNullDTOThrowsException() {
        // arrange
        UserStoryDTO dto = null;
        int priority = 0;
        ProductBacklog productBacklog = new ProductBacklog();
        String expectedMessage = "User Story DTO must not be null";
        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.createUserStory(dto, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("trying to add null user story throws exception")
    void addNullUserStoryThrowsException() {
        // arrange
        UserStory userStory = null;
        int priority = 5;
        String expectedMessage = "User Story must not be null";
        ProductBacklog productBacklog = new ProductBacklog();
        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.add(userStory, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }
}