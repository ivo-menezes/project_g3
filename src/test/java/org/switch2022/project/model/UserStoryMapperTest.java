package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryMapperTest {

    @Test
    @DisplayName("ensure a list of user stories DTO is created")
    void createUserStoryListDTO() {

        //Arrange
        //setting up mocked User Stories
        UserStory userStoryDouble1 = mock(UserStory.class);
        when(userStoryDouble1.getId()).thenReturn("US001");
        UserStory userStoryDouble2 = mock(UserStory.class);
        when(userStoryDouble2.getId()).thenReturn("US002");
        //adding to a list for the toDTOList method
        List<UserStory> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble1);
        userStoryList.add(userStoryDouble2);

        //setting up expected DTOs
        UserStoryDTO userStoryDTO1 = new UserStoryDTO();
        userStoryDTO1.id = "US001";
        UserStoryDTO userStoryDTO2 = new UserStoryDTO();
        userStoryDTO2.id = "US002";
        //adding to a list for comparison
        List<UserStoryDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(userStoryDTO1);
        expectedDTOList.add(userStoryDTO2);

        //creating a real UserStoryMapper object
        UserStoryMapper mapper = new UserStoryMapper();

        //act
        List<UserStoryDTO> resultDTOList = mapper.toDTOList(userStoryList);

        //assert
        assertEquals(expectedDTOList, resultDTOList);
    }

}