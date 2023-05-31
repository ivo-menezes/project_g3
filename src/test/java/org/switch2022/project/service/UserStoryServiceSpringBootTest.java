package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTOMapper;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserStoryServiceSpringBootTest {

    @MockBean
    IUserStoryFactory userStoryFactoryDouble;

    @MockBean
    IUserStoryRepository userStoryRepositoryDouble;

    @MockBean
    Repository<ProjectCode, ProjectDDD> projectRepositoryDouble;

    @MockBean
    NewUserStoryInfoDTOMapper mapperDouble;

    @Autowired
    UserStoryService userStoryService;

    @DisplayName("assert that creating a user story succeeds with total isolation")
    @Test
    void createUserStorySucceedsWithTotalIsolation() {
        // Arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        dtoDouble.priority = mock(UserStoryPriority.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        NewUserStoryInfoDTO dtoDouble2 = mock(NewUserStoryInfoDTO.class);

        when(userStoryFactoryDouble.createUserStory(dtoDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(projectDouble.addToProductBacklog(userStoryIdDouble, dtoDouble.priority)).thenReturn(true);
        when(projectRepositoryDouble.save(projectDouble)).thenReturn(true);
        when(mapperDouble.toDto(userStoryDouble)).thenReturn(dtoDouble2);

        // act
        NewUserStoryInfoDTO result = userStoryService.createUserStory(dtoDouble);

        // assert
        assertEquals(dtoDouble2, result);

    }

    @DisplayName("assert that creating a user story fails with total isolation when project code doesn't exist")
    @Test
    void createUserStorySucceeds() {
        // Arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        dtoDouble.priority = mock(UserStoryPriority.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(userStoryFactoryDouble.createUserStory(dtoDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.empty());

        String expectedMessage = "project with given projectCode does not exist";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            userStoryService.createUserStory(dtoDouble);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a user story throws exception if saving to backlog fails")
    @Test
    void createUserStoryFailsIfSavingToBacklogFails() {
        // Arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        dtoDouble.priority = mock(UserStoryPriority.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);

        when(userStoryFactoryDouble.createUserStory(dtoDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(projectDouble.addToProductBacklog(userStoryIdDouble, dtoDouble.priority)).thenReturn(false);
        when(projectRepositoryDouble.save(projectDouble)).thenReturn(true);

        String expectedMessage = "UserStoryID not added to ProductBacklog";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            userStoryService.createUserStory(dtoDouble);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }
}
