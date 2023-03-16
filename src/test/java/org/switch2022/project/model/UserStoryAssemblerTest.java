package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryAssemblerTest {

    @Test
    @DisplayName("ensure the DTO is created")
    void checkIfUserStoryDTOIsCreated() {

        //Arrange
        UserStory userStoryMock = mock(UserStory.class);
        when(userStoryMock.getId()).thenReturn("US001");
        when(userStoryMock.getStatus()).thenReturn(UserStory.Status.TODO);
        UserStory userStoryMockTwo = mock(UserStory.class);
        when(userStoryMockTwo.getId()).thenReturn("US002");
        when(userStoryMockTwo.getStatus()).thenReturn(UserStory.Status.IN_PROGRESS);


        UserStoryDTO userStoryDTO = new UserStoryDTO();
        userStoryDTO.id = "US001";
        userStoryDTO.status = UserStory.Status.TODO;
        UserStoryDTO userStoryDTOTwo = new UserStoryDTO();
        userStoryDTOTwo.id = "US002";
        userStoryDTOTwo.status = UserStory.Status.IN_PROGRESS;

        UserStoryAssembler userStoryAssembler = new UserStoryAssembler();
        UserStoryDTO result = userStoryAssembler.toDTO(userStoryMock);
        userStoryAssembler.toDTO(userStoryMockTwo);

        //assert
        assertEquals(userStoryDTO, result);
    }
}