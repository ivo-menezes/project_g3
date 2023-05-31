package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.REST.UserStoryRestDto;
import org.switch2022.project.mapper.REST.UserStoryRestDtoMapper;
import org.switch2022.project.service.UserStoryService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserStoryControllerTest {

    @MockBean
    UserStoryService userStoryServiceDouble;

    @MockBean
    UserStoryRestDtoMapper mapperDouble;

    @Autowired
    UserStoryController controllerUnderTest;

    @DisplayName("ensure creating a user story returns a correct DTO with user story info and HTTP status 201 - Created")
    @Test
    void shouldReturnCorrectDtoOfCreatedUserStoryAndStatusCreated() {
        // Arrange
        UserStoryRestDto restDtoDouble1 = mock(UserStoryRestDto.class);
        UserStoryRestDto restDtoDouble2 = mock(UserStoryRestDto.class);
        NewUserStoryInfoDTO userStoryInfoDtoDouble1 = mock(NewUserStoryInfoDTO.class);
        NewUserStoryInfoDTO userStoryInfoDtoDouble2 = mock(NewUserStoryInfoDTO.class);

        when(mapperDouble.toDomainDto(restDtoDouble1)).thenReturn(userStoryInfoDtoDouble1);
        when(userStoryServiceDouble.createUserStory(userStoryInfoDtoDouble1)).thenReturn(userStoryInfoDtoDouble2);
        when(mapperDouble.toRestDto(userStoryInfoDtoDouble2)).thenReturn(restDtoDouble2);

        // Act
        ResponseEntity<?> response = controllerUnderTest.createUserStory(restDtoDouble1);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(restDtoDouble2, response.getBody());
    }

    @DisplayName("ensure trying to create a user story returns the input DTO and HTTP status 400 - Bad Request when exception is thrown")
    @Test
    void shouldReturnInputDtoAndStatusCodeBadRequestWhenExceptionIsThrown() {
        // Arrange
        UserStoryRestDto restDtoDouble1 = mock(UserStoryRestDto.class);
        NewUserStoryInfoDTO userStoryInfoDtoDouble1 = mock(NewUserStoryInfoDTO.class);

        when(mapperDouble.toDomainDto(restDtoDouble1)).thenReturn(userStoryInfoDtoDouble1);
        when(userStoryServiceDouble.createUserStory(userStoryInfoDtoDouble1)).thenThrow(RuntimeException.class);

        // Act
        ResponseEntity<?> response = controllerUnderTest.createUserStory(restDtoDouble1);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals(restDtoDouble1, response.getBody());
    }
}