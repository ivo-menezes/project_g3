package org.switch2022.project.controller.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.REST.UserStoryRestDto;
import org.switch2022.project.mapper.REST.UserStoryRestDtoMapper;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.service.UserStoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
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

    @DisplayName("ensure consulting backlog returns correct list of DTOs and HTTP status 200 - OK")
    @Test
    void shouldReturnListOfDtoAndStatusOk() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        NewUserStoryInfoDTO domainDtoDouble1 = mock(NewUserStoryInfoDTO.class);
        NewUserStoryInfoDTO domainDtoDouble2 = mock(NewUserStoryInfoDTO.class);
        List<NewUserStoryInfoDTO> domainDtoDoubleList = new ArrayList<>();
        domainDtoDoubleList.add(domainDtoDouble1);
        domainDtoDoubleList.add(domainDtoDouble2);

        UserStoryRestDto restDtoDouble1 = mock(UserStoryRestDto.class);
        UserStoryRestDto restDtoDouble2 = mock(UserStoryRestDto.class);
        List<UserStoryRestDto> restDtoDoubleList = new ArrayList<>();
        restDtoDoubleList.add(restDtoDouble1);
        restDtoDoubleList.add(restDtoDouble2);

        when(userStoryServiceDouble.getProductBacklog(projectCodeDouble)).thenReturn(domainDtoDoubleList);
        when(mapperDouble.toRestDtoList(domainDtoDoubleList)).thenReturn(restDtoDoubleList);

        // Act
        ResponseEntity<?> response = controllerUnderTest.consultBacklog(projectCodeDouble);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(restDtoDoubleList, response.getBody());
    }

    @DisplayName("ensure consulting backlog returns HTTP status 404 - Not Found when service throws exception")
    @Test
    void shouldReturnStatusNotFound() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(userStoryServiceDouble.getProductBacklog(projectCodeDouble)).thenThrow(RuntimeException.class);

        // Act
        ResponseEntity<?> response = controllerUnderTest.consultBacklog(projectCodeDouble);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
    }
}