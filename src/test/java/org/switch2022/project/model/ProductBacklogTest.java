package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductBacklogTest {

    // Unit tests (with mock)

    @Test
    @DisplayName("test ProductBacklog constructor with null factory throws exception")
    void createProductBacklogWithNullFactoryThrowsException(){
        String expectedMessage = "Factory must not be null";
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProductBacklog(null);
        });
        String resultMessage = result.getMessage();
        assertEquals(expectedMessage, resultMessage);

    }

    @Test
    @DisplayName("test successfull ProductBacklog constructor")
    void createProductBacklogSucceeds() {
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);
    }


    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority 0")
    void createUserStoryWithPriorityZeroWithIsolation() {
        // arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        UserStory userStoryDouble = mock(UserStory.class);

        when(factoryUserStoryDouble.createUserStory(null, null, null, null)).thenReturn(userStoryDouble);

        int priority = 0;

        // act
        boolean result = productBacklog.createAndAddUserStory(dtoDouble, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority 5")
    void createUserStoryWithPriorityFiveWithIsolation() {
        // arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        UserStory userStoryDouble = mock(UserStory.class);

        when(factoryUserStoryDouble.createUserStory(null, null, null, null)).thenReturn(userStoryDouble);

        int priority = 5;

        // act
        boolean result = productBacklog.createAndAddUserStory(dtoDouble, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story to empty product backlog with priority -2")
    void createUserStoryWithPriorityMinusTwoWithIsolation() {
        // arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        UserStory userStoryDouble = mock(UserStory.class);

        when(factoryUserStoryDouble.createUserStory(null, null, null, null)).thenReturn(userStoryDouble);

        int priority = -2;

        // act
        boolean result = productBacklog.createAndAddUserStory(dtoDouble, priority);

        // assert
        assertTrue(result);
    }

    // falta aqui teste unitário (com mock) para verificar que 2 user stories iguais não podem ser adicionadas

    @Test
    @DisplayName("create and successfully add two different user stories to product backlog")
    void createTwoUserStoriesWithIsolation() {
        int priority = 0;

        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        UserStoryDTO dtoDouble2 = mock(UserStoryDTO.class);

        UserStory userStoryDouble = mock(UserStory.class);
        when(factoryUserStoryDouble.createUserStory(null, null, null, null)).thenReturn(userStoryDouble);
        productBacklog.createAndAddUserStory(dtoDouble, priority);

        UserStory userStoryDouble2 = mock(UserStory.class);
        when(factoryUserStoryDouble.createUserStory(null, null, null, null)).thenReturn(userStoryDouble2);
        boolean result = productBacklog.createAndAddUserStory(dtoDouble2, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("creating user story with null DTO throws exception")
    void createUserStoryWithNullDTOThrowsExceptionWithIsolation() {
        // arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dto = null;
        int priority = 0;

        String expectedMessage = "User Story DTO must not be null";
        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.createAndAddUserStory(dto, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("trying to add null user story throws exception")
    void addNullUserStoryThrowsExceptionWithIsolation() {
        // arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStory = null;
        int priority = 5;
        String expectedMessage = "User Story must not be null";

        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.add(userStory, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("successfully add a user story with priority 0")
    void addUserStoryWithPriorityZeroSucceedsWithIsolation(){
        // arrange
        int priority = 0;

        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        // act
        boolean result = productBacklog.add(userStoryDouble, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully add a user story with priority 5")
    void addUserStoryWithPriorityFiveSucceedsWithIsolation(){
        // arrange
        int priority = 5;

        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        // act
        boolean result = productBacklog.add(userStoryDouble, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully add a user story with priority -2")
    void addUserStoryWithPriorityMinusTwoSucceedsWithIsolation(){
        // arrange
        int priority = -2;

        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);

        // act
        boolean result = productBacklog.add(userStoryDouble, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully add two different user stories")
    void addTwoDifferentUserStoriesSucceedsWithIsolation() {
        // arrange
        int priority = -2;

        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        UserStory userStoryDouble2 = mock(UserStory.class);

        // act
        productBacklog.add(userStoryDouble, priority);
        boolean result = productBacklog.add(userStoryDouble2, priority);

        // assert
        assertTrue(result);
    }

    // falta aqui teste para verificar que não é possível adicionar 2 user stories com o mesmo id (usando mock)

    // "Integration tests" (tests without mock)
    @Test
    @DisplayName("successfully create and add user story from DTO to empty product backlog with priority 0")
    void createUserStoryWithPriorityZero() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = 0;

        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        // act
        boolean result = productBacklog.createAndAddUserStory(dto, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story from DTO to empty product backlog with priority 5")
    void createUserStoryWithPriorityFive() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = 5;

        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        // act
        boolean result = productBacklog.createAndAddUserStory(dto, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("successfully create and add user story from DTO to empty product backlog with priority -2")
    void createUserStoryWithPriorityMinusTwo() {
        // arrange
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US001";
        dto.actor = "Manager";
        dto.text = "As Manager, I want to give everybody a break";
        dto.acceptanceCriteria = "None";

        int priority = -2;

        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        // act
        boolean result = productBacklog.createAndAddUserStory(dto, priority);

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

        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        // act
        productBacklog.createAndAddUserStory(dto, priority);
        boolean result = productBacklog.createAndAddUserStory(dto2, priority);

        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("create and successfully add two different user stories to product backlog")
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

        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        // act
        productBacklog.createAndAddUserStory(dto, priority);
        boolean result = productBacklog.createAndAddUserStory(dto2, priority);

        // assert
        assertTrue(result);
    }

    @Test
    @DisplayName("creating user story with null DTO throws exception")
    void createUserStoryWithNullDTOThrowsException() {
        // arrange
        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        UserStoryDTO dto = null;
        int priority = 0;

        String expectedMessage = "User Story DTO must not be null";
        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.createAndAddUserStory(dto, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("trying to add null user story throws exception")
    void addNullUserStoryThrowsException() {
        // arrange
        IFactoryUserStory factoryUserStory = new FactoryUserStoryImpl();
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);

        UserStory userStory = null;
        int priority = 5;

        String expectedMessage = "User Story must not be null";
        // act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productBacklog.add(userStory, priority);
        });
        String resultMessage = exception.getMessage();
        // assert
        assertEquals(expectedMessage, resultMessage);
    }
}