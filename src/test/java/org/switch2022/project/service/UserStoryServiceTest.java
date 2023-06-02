package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTOMapper;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryServiceTest {

    @DisplayName("assert that creating a UserStoryService with null UserStoryFactory throws Exception")
    @Test
    void createUserStoryNullFactoryThrowsException() {
        // arrange
        IUserStoryFactory factory = null;
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper mapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        String expectedMessage = "userStoryFactory must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factory, usRepositoryDouble, projectRepositoryDouble, mapperDouble);
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
        IUserStoryRepository usRepository = null;
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper mapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        String expectedMessage = "userStoryRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factoryDouble, usRepository, projectRepositoryDouble, mapperDouble);
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
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepository = null;
        NewUserStoryInfoDTOMapper mapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        String expectedMessage = "projectRepository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new UserStoryService(factoryDouble, usRepositoryDouble, projectRepository, mapperDouble);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a user story succeeds with total isolation")
    @Test
    void createUserStorySuccessWithTotalIsolation() {
        // Arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper mapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble, mapperDouble);

        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);
        dtoDouble.priority = priorityDouble;
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        NewUserStoryInfoDTO dtoDouble2 = mock(NewUserStoryInfoDTO.class);

        when(factoryDouble.createUserStory(dtoDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(usRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(projectDouble.addToProductBacklog(userStoryIdDouble, dtoDouble.priority)).thenReturn(priorityDouble);
        when(projectRepositoryDouble.replace(projectDouble)).thenReturn(projectDouble);
        when(mapperDouble.toDto(userStoryDouble)).thenReturn(dtoDouble2);

        // act
        NewUserStoryInfoDTO result = service.createUserStory(dtoDouble);

        // assert
        assertEquals(dtoDouble2, result);
    }

    @DisplayName("assert that creating a user story with a non existing project throws exception")
    @Test
    void createUserStoryFailsEmptyProjectWithIsolation() {
        // Arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper mapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble, mapperDouble);

        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        dtoDouble.priority = mock(UserStoryPriority.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(factoryDouble.createUserStory(dtoDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.empty());

        String expectedMessage = "project with given projectCode does not exist";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            service.createUserStory(dtoDouble);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("ensure a projectBacklog is retrieved")
    void returnsProjectBacklog(){

        //Arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper infoMapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        UserStoryDDD userStoryDouble1 = mock(UserStoryDDD.class);
        UserStoryID userStoryIDDouble1 = mock(UserStoryID.class);

        //defining projectRepositoryDouble behavior to return a projectDouble
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));

        //defining projectDouble behavior to return openUserStoryIDs
        List<UserStoryID> openUserStoryIDs = new ArrayList<>();
        openUserStoryIDs.add(userStoryIDDouble1);
        when(projectDouble.getProductBacklog()).thenReturn(openUserStoryIDs);

        //defining usRepositoryDouble behavior to return a userStoryDouble
        when(usRepositoryDouble.getByID(userStoryIDDouble1)).thenReturn(Optional.of(userStoryDouble1));

        //defining mapperDouble behavior to return a mockDTOList
        List<UserStoryDDD> openUserStoryList = new ArrayList<>();
        openUserStoryList.add(userStoryDouble1);
        List<NewUserStoryInfoDTO> mockDTOList = new ArrayList<>();
        NewUserStoryInfoDTO userStoryDTODouble1 = mock(NewUserStoryInfoDTO.class);
        mockDTOList.add(userStoryDTODouble1);
        when(infoMapperDouble.toDtoList(openUserStoryList)).thenReturn(mockDTOList);

        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble, infoMapperDouble);

        //Act
        List<NewUserStoryInfoDTO> result = service.getProductBacklog(projectCodeDouble);

        //Assert
        assertEquals(mockDTOList, result);
    }

    @Test
    @DisplayName("Ensure exception is thrown when project is not found")
    void returnsEmptyOptionalWhenProjectNotFound() {

        //Arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper infoMapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        //defining projectRepositoryDouble behavior to return empty optional
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.empty());

        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble, infoMapperDouble);

        String expectedMessage = "project with given projectCode does not exist";
        //Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            service.getProductBacklog(projectCodeDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("Ensure empty optional is returned when userStory id is not found")
    void returnsEmptyOptionalIfUserStoryIdDoesNotExist(){

        //Arrange
        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        IUserStoryRepository usRepositoryDouble = mock(IUserStoryRepository.class);
        IProjectRepository projectRepositoryDouble = mock(IProjectRepository.class);
        NewUserStoryInfoDTOMapper infoMapperDouble = mock(NewUserStoryInfoDTOMapper.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        UserStoryID userStoryIDDouble1 = mock(UserStoryID.class);

        //defining projectRepositoryDouble behavior to return a projectDouble
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));

        //defining projectDouble behavior to return openUserStoryIDs
        List<UserStoryID> openUserStoryIDs = new ArrayList<>();
        openUserStoryIDs.add(userStoryIDDouble1);
        when(projectDouble.getProductBacklog()).thenReturn(openUserStoryIDs);

        when(usRepositoryDouble.getByID(userStoryIDDouble1)).thenReturn(Optional.empty());

        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble, infoMapperDouble);

        String expectedMessage = "user story with given ID does not exist";
        //Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            service.getProductBacklog(projectCodeDouble);
        });
        String resultMessage = result.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}