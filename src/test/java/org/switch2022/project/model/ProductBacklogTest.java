package org.switch2022.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.UserStoryDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        assertInstanceOf(ProductBacklog.class, productBacklog);
    }

    @Test
    @DisplayName("successfully create and add user story to empty product backlog")
    void createUserStoryAndAddToEmptuBacklog() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStoryDTO dtoDouble = mock(UserStoryDTO.class);
        UserStory userStoryDouble = mock(UserStory.class);

        when(factoryUserStoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);

        // Act
        boolean result = productBacklog.createAndAddUserStory(dtoDouble);

        // Assert
        assertTrue(result);
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

    @Test
    @DisplayName("add two different user stories and ensure the second one is at the end of the list")
    void ensureUserStoryIsAddedAtTheEnd() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble1 = mock(UserStory.class);
        UserStory userStoryDouble2 = mock(UserStory.class);

        // Act
        productBacklog.add(userStoryDouble1);
        productBacklog.add(userStoryDouble2);

        // Assert
        List<UserStory> userStories = productBacklog.getUserStoryList();
        assertEquals(userStoryDouble2, userStories.get(1));
    }

    @Test
    @DisplayName("add US to non-empty backlog at priority 2 and length of backlog is 3")
    void ensureUserStoryIsAddedWithPriority2AndBacklogLength3() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble1 = mock(UserStory.class);
        UserStory userStoryDouble2 = mock(UserStory.class);
        UserStory userStoryDouble3 = mock(UserStory.class);
        UserStory userStoryDouble4 = mock(UserStory.class);
        int priority = 2;

        // Act
        productBacklog.add(userStoryDouble1);
        productBacklog.add(userStoryDouble2);
        productBacklog.add(userStoryDouble3);
        productBacklog.add(userStoryDouble4, priority);

        // Assert
        List<UserStory> userStories = productBacklog.getUserStoryList();
        assertEquals(userStoryDouble4, userStories.get(2));
    }

    @Test
    @DisplayName("add US to non-empty backlog at priority 2 and length of backlog is 1")
    void ensureUserStoryIsAddedWithPriority2AndBacklogLength1() {
        // Arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble1 = mock(UserStory.class);
        UserStory userStoryDouble2 = mock(UserStory.class);
        int priority = 2;

        // Act
        productBacklog.add(userStoryDouble1);
        productBacklog.add(userStoryDouble2, priority);

        // Assert
        List<UserStory> userStories = productBacklog.getUserStoryList();
        assertEquals(userStoryDouble2, userStories.get(1));
    }

    // not sure how it is possible to test this case with isolation
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
    @DisplayName("ensure user story is returned")
    void ensureUserStoryIsReturned() {
        //arrange
        IFactoryUserStory factoryUserStory = mock(IFactoryUserStory.class);
        UserStory userStory = mock(UserStory.class);
        when(userStory.getId()).thenReturn("2");
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);
        productBacklog.add(userStory, 0);
        //act
        UserStory userStoryToReturn = productBacklog.getUserStory("2");
        //assert
        assertEquals(userStory, userStoryToReturn);
    }
    @Test
    @DisplayName("ensure an exception is returned when the user story is not found")
    void ensureGetUserStoryException() {
        //arrange
        IFactoryUserStory factoryUserStory = mock(IFactoryUserStory.class);
        UserStory userStory = mock(UserStory.class);
        when(userStory.getId()).thenReturn("2");
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStory);
        productBacklog.add(userStory, 0);

        //Mockito.when(productBacklog.getUserStory("2")).thenThrow(NullPointerException.class);

        //act and assert
        Assertions.assertThrows(NullPointerException.class, () -> productBacklog.getUserStory("3"));
    }

    @Test
    @DisplayName("ensure userStory list is retrieved")
    void ensureUserStoryListIsRetrieved() {
        //arrange
        IFactoryUserStory factoryUserStoryDouble = mock(IFactoryUserStory.class);
        ProductBacklog productBacklog = new ProductBacklog(factoryUserStoryDouble);

        UserStory userStoryDouble = mock(UserStory.class);
        UserStory userStoryDoubleTwo = mock(UserStory.class);

        List<UserStory> expectedList = new ArrayList<>();
        expectedList.add(userStoryDouble);
        expectedList.add(userStoryDoubleTwo);

        productBacklog.add(userStoryDouble, 1);
        productBacklog.add(userStoryDoubleTwo, 2);

        //act
        List<UserStory> resultList = productBacklog.getUserStoryList();

        //assert
        assertEquals(expectedList, resultList);
    }
}