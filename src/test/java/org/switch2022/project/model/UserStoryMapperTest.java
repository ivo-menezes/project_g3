package org.switch2022.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.userStory.old.IFactoryUserStory;
import org.switch2022.project.model.userStory.old.UserStory;

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

    @DisplayName("successfully create a UserStory from a UserStoryDTO")
    @Test
    void fromDTO() {
        // Arrange
        String id = "US007";
        String actor = "Product Owner";
        String text = "As a Product Owner, I want to communicate telepathically with the application.";
        String accept = "We don't do that here.";

        // mocked DTO with attributes
        UserStoryDTO dto = mock(UserStoryDTO.class);
        dto.id = id;
        dto.actor = actor;
        dto.text = text;
        dto.acceptanceCriteria = accept;

        // mocked UserStory and factory
        UserStory userStoryDouble = mock(UserStory.class);
        IFactoryUserStory factoryDouble = mock(IFactoryUserStory.class);
        // mocked factory will return mocked user story only if dto attributes above are used
        when(factoryDouble.createUserStory(id, actor, text, accept)).thenReturn(userStoryDouble);

        // a real mapper
        UserStoryMapper mapper = new UserStoryMapper();

        // Act
        UserStory result = mapper.fromDTO(dto, factoryDouble);

        // Assert
        // if fromDTO() is working, the mocked user story had to be returned
        assertEquals(userStoryDouble, result);
    }
}