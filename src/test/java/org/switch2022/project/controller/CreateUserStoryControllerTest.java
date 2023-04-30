package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.userStory.UserStoryFactoryImpl;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryNumber;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.repository.ProjectRepository;
import org.switch2022.project.repository.UserStoryRepository;
import org.switch2022.project.service.UserStoryService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CreateUserStoryControllerTest {

    @DisplayName("creating a controller with null UserStoryService throws exception")
    @Test
    void createControllerWithNullProjectListThrowsException() {
        // Arrange
        String expectedMessage = "UserStoryService must not be null.";
        UserStoryService service = null;
        // Act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new CreateUserStoryController(service);
        });
        String resultMessage = result.getMessage();
        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("creating a controller succeeds")
    @Test
    void createControllerSucceeds() {
        // Arrange
        UserStoryService serviceDouble = mock(UserStoryService.class);
        // Act
        CreateUserStoryController controller = new CreateUserStoryController(serviceDouble);
        // Assert
        assertInstanceOf(CreateUserStoryController.class, controller);
    }

    // ACCEPTANCE TESTS
    @DisplayName("Fails to create a US due to insuficient/invalid information: null projectCode")
    @Test
    void createUserStoryWithNullProjectCodeThrowsException() {
        // arrange
        ProjectCode projectCode = null;
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);

        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);
        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble);

        CreateUserStoryController controller = new CreateUserStoryController(service);

        String expectedMessage = "projectCode must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            controller.createUserStory(projectCode, userStoryDTODouble, priorityDouble);
        });
        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Fails to create a US due to insufficient/invalid information: null userStoryDTO")
    @Test
    void createUserStoryWithNullUserStoryDTOThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryDTO userStoryDTO = null;
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);

        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);
        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble);

        CreateUserStoryController controller = new CreateUserStoryController(service);

        String expectedMessage = "userStoryDTO must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            controller.createUserStory(projectCodeDouble, userStoryDTO, priorityDouble);
        });
        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("Fails to create a US due to insufficient/invalid information: null priority")
    @Test
    void createUserStoryWithNullPriorityThrowsException() {
        // arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryDTO userStoryDTODouble = mock(UserStoryDTO.class);
        UserStoryPriority priority = null;

        IUserStoryFactory factoryDouble = mock(IUserStoryFactory.class);
        Repository<UserStoryID, UserStoryDDD> usRepositoryDouble = mock(Repository.class);
        Repository<ProjectCode, ProjectDDD> projectRepositoryDouble = mock(Repository.class);
        UserStoryService service = new UserStoryService(factoryDouble, usRepositoryDouble, projectRepositoryDouble);

        CreateUserStoryController controller = new CreateUserStoryController(service);

        String expectedMessage = "priority must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            controller.createUserStory(projectCodeDouble, userStoryDTODouble, priority);
        });
        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("Create US and add it to an empty backlog.")
    @Test
    void createUserStorySucceedsWithEmptyBacklog() {
        // Arrange
        // create new project and save it a projectRepository
        ProjectCode projectCode = new ProjectCode("XPTO");
        ProjectDDD project = new ProjectDDD(projectCode);
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.save(project);

        // remaining info needed for creating a user story
        UserStoryDTO dto = new UserStoryDTO();
        dto.id = "US017";
        dto.actor = "Product Owner";
        dto.text = "As Product Owner, I want to create a user story.";
        dto.acceptanceCriteria = "None";

        UserStoryPriority priority = new UserStoryPriority(0);

        // user story factory, repository and service
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryService service = new UserStoryService(factory, userStoryRepository, projectRepository);

        // the controller to be tested
        CreateUserStoryController controller = new CreateUserStoryController(service);

        // Act
        boolean result = controller.createUserStory(projectCode, dto, priority);

        // Assert
        assertTrue(result);
    }

    @DisplayName("Create US and add it to a non-empty backlog (at the end or begining, according to the group's decision).")
    @Test
    void createUserStorySucceedsWithNonEmptyBacklog() {
        // Arrange
        // create new project and save it a projectRepository
        ProjectCode projectCode = new ProjectCode("XPTO");
        ProjectDDD project = new ProjectDDD(projectCode);
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.save(project);

        // remaining info needed for creating a user story (x2)
        UserStoryDTO dto1 = new UserStoryDTO();
        dto1.id = "US017";
        dto1.actor = "Product Owner";
        dto1.text = "As Product Owner, I want to create a user story.";
        dto1.acceptanceCriteria = "None";

        UserStoryPriority priority1 = new UserStoryPriority(0);

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US018";
        dto2.actor = "Product Owner";
        dto2.text = "As Product Owner, I want to consult the product backlog.";
        dto2.acceptanceCriteria = "None";

        UserStoryPriority priority2 = new UserStoryPriority(1);

        // user story factory, repository and service
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryService service = new UserStoryService(factory, userStoryRepository, projectRepository);

        // the controller to be tested
        CreateUserStoryController controller = new CreateUserStoryController(service);

        // Act
        controller.createUserStory(projectCode, dto1, priority1);
        boolean result = controller.createUserStory(projectCode, dto2, priority2);

        // Assert
        assertTrue(result);

    }

    @DisplayName("Fails to create a US because it has the same number of another in a non-empty backlog.")
    @Test
    void createUserStoryFailsWithSameUSNumber() {
        // Arrange
        // create new project and save it a projectRepository
        ProjectCode projectCode = new ProjectCode("XPTO");
        ProjectDDD project = new ProjectDDD(projectCode);
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.save(project);

        // remaining info needed for creating a user story (x2)
        UserStoryDTO dto1 = new UserStoryDTO();
        dto1.id = "US017";
        dto1.actor = "Product Owner";
        dto1.text = "As Product Owner, I want to create a user story.";
        dto1.acceptanceCriteria = "None";

        UserStoryPriority priority1 = new UserStoryPriority(0);

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US017";
        dto2.actor = "Product Owner";
        dto2.text = "As Product Owner, I want to consult the product backlog.";
        dto2.acceptanceCriteria = "None";

        UserStoryPriority priority2 = new UserStoryPriority(1);

        // user story factory, repository and service
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryService service = new UserStoryService(factory, userStoryRepository, projectRepository);

        // the controller to be tested
        CreateUserStoryController controller = new CreateUserStoryController(service);

        // Act
        controller.createUserStory(projectCode, dto1, priority1);
        boolean result = controller.createUserStory(projectCode, dto2, priority2);

        // Assert
        assertFalse(result);
    }

    @DisplayName("Add US to a non-empty backlog at the specified priority n (and the length of the backlog is at least n).")
    @Test
    void createUserStorySucceedsWithPriorityLessThanSizeOfBacklog() {
        // Arrange
        // create new project and save it a projectRepository
        ProjectCode projectCode = new ProjectCode("XPTO");
        ProjectDDD project = new ProjectDDD(projectCode);
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.save(project);

        // remaining info needed for creating a user story (x2)
        UserStoryDTO dto1 = new UserStoryDTO();
        dto1.id = "US017";
        dto1.actor = "Product Owner";
        dto1.text = "As Product Owner, I want to create a user story.";
        dto1.acceptanceCriteria = "None";

        UserStoryPriority priority1 = new UserStoryPriority(0);

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US018";
        dto2.actor = "Product Owner";
        dto2.text = "As Product Owner, I want to consult the product backlog.";
        dto2.acceptanceCriteria = "None";

        UserStoryPriority priority2 = new UserStoryPriority(1);

        UserStoryDTO dto3 = new UserStoryDTO();
        dto3.id = "US019";
        dto3.actor = "Product Owner";
        dto3.text = "As Product Owner, I want to erase the product backlog.";
        dto3.acceptanceCriteria = "None";

        UserStoryPriority priority3 = new UserStoryPriority(1);

        // user story factory, repository and service
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryService service = new UserStoryService(factory, userStoryRepository, projectRepository);

        // the controller to be tested
        CreateUserStoryController controller = new CreateUserStoryController(service);

        UserStoryNumber userStoryNumber = new UserStoryNumber("US019");
        UserStoryID expectedID = new UserStoryID(userStoryNumber, projectCode);

        // Act
        controller.createUserStory(projectCode, dto1, priority1);
        controller.createUserStory(projectCode, dto2, priority2);
        controller.createUserStory(projectCode, dto3, priority3);

        List<UserStoryID> backlog = project.getProductBacklog();
        UserStoryID retrievedID = backlog.get(1);

        // Assert
        assertEquals(expectedID, retrievedID);

    }

    @DisplayName("Add US with specified priority n at the end of a non-empty backlog (the length of the backlog is shorter than n).")
    @Test
    void createUserStorySucceedsWithPriorityMoreThanSizeOfBacklog() {
        // Arrange
        // create new project and save it a projectRepository
        ProjectCode projectCode = new ProjectCode("XPTO");
        ProjectDDD project = new ProjectDDD(projectCode);
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.save(project);

        // remaining info needed for creating a user story (x2)
        UserStoryDTO dto1 = new UserStoryDTO();
        dto1.id = "US017";
        dto1.actor = "Product Owner";
        dto1.text = "As Product Owner, I want to create a user story.";
        dto1.acceptanceCriteria = "None";

        UserStoryPriority priority1 = new UserStoryPriority(0);

        UserStoryDTO dto2 = new UserStoryDTO();
        dto2.id = "US018";
        dto2.actor = "Product Owner";
        dto2.text = "As Product Owner, I want to consult the product backlog.";
        dto2.acceptanceCriteria = "None";

        UserStoryPriority priority2 = new UserStoryPriority(1);

        UserStoryDTO dto3 = new UserStoryDTO();
        dto3.id = "US019";
        dto3.actor = "Product Owner";
        dto3.text = "As Product Owner, I want to erase the product backlog.";
        dto3.acceptanceCriteria = "None";

        UserStoryPriority priority3 = new UserStoryPriority(5);

        // user story factory, repository and service
        UserStoryFactoryImpl factory = new UserStoryFactoryImpl();
        UserStoryRepository userStoryRepository = new UserStoryRepository();
        UserStoryService service = new UserStoryService(factory, userStoryRepository, projectRepository);

        // the controller to be tested
        CreateUserStoryController controller = new CreateUserStoryController(service);

        UserStoryNumber userStoryNumber = new UserStoryNumber("US019");
        UserStoryID expectedID = new UserStoryID(userStoryNumber, projectCode);

        // Act
        controller.createUserStory(projectCode, dto1, priority1);
        controller.createUserStory(projectCode, dto2, priority2);
        controller.createUserStory(projectCode, dto3, priority3);

        List<UserStoryID> backlog = project.getProductBacklog();
        UserStoryID retrievedID = backlog.get(backlog.size() - 1);

        // Assert
        assertEquals(expectedID, retrievedID);
    }
}