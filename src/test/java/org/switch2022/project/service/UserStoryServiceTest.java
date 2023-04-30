package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryServiceTest {

    @DisplayName("assert that creating a UserStoryService with null UserStoryFactory throws Exception")
    @Test
    void createUserStoryNullFactoryThrowsException() {
        // arrange
        IUserStoryFactory factory = null;
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);

        String expectedMessage = "userStoryFactory must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factory, usRepositoryDouble, projectRepositoryDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a UserStoryService with null UserStoryRepository throws Exception")
    @Test
    void createUserStoryNullUSRepositoryThrowsException() {
        // arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        Repository<UserStoryID, UserStoryDDD> usRepository = null;
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);

        String expectedMessage = "userStoryRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factoryDouble, usRepository, projectRepositoryDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a UserStoryService with null ProjectRepository throws Exception")
    @Test
    void createUserStoryNullProjectRepositoryThrowsException() {
        // arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        Repository<ProjectCode, ProjectDDD> projectRepository = null;

        String expectedMessage = "projectRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factoryDouble, usRepositoryDouble, projectRepository);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a user story succeeds with total isolation")
    @Test
    void createUserStorySuccessWithTotalIsolation() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        userStoryDTODouble.id = "US017";
        userStoryDTODouble.actor = "Administrator";
        userStoryDTODouble.text = "blah blah";
        userStoryDTODouble.acceptanceCriteria = "none";
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        UserStoryID userStoryIDDouble = mock(UserStoryID.class);

        // projectRepository has to be trained to return a projectDouble
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.ofNullable(projectDouble));

        // factoryDouble has to be trained to return a userStoryDouble
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        when(factoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);

        // usRepositoryDouble has to be trained to respond with true when asked to save a UserStory
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        when(usRepositoryDouble.save(userStoryDouble)).thenReturn(true);

        // userStoryDouble has to be trained to return userStoryIDDouble when asked with identity()
        when(userStoryDouble.identity()).thenReturn(userStoryIDDouble);

        // projectDouble has to be trained to return true when asked to add a userStoryID to the backlog
        when(projectDouble.addToProductBacklog(userStoryIDDouble, priorityDouble)).thenReturn(true);

        // a real UserStoryService
        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble);

        // act
        boolean result = service.createUserStory(projectCodeDouble, userStoryDTODouble, priorityDouble);

        // assert
        assertTrue(result);
    }
}