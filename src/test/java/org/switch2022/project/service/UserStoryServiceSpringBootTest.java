package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

    @Autowired
    UserStoryService userStoryService;

    @DisplayName("assert that creating a user story succeeds with total isolation")
    @Test
    void createUserStorySucceedsWithTotalIsolation() {
        // Arrange
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

        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(userStoryFactoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIDDouble);
        when(projectDouble.addToProductBacklog(userStoryIDDouble, priorityDouble)).thenReturn(true);

        // Act
        boolean result = userStoryService.createUserStory(projectCodeDouble, userStoryDTODouble, priorityDouble);

        // Assert
        assertTrue(result);
    }

    @DisplayName("assert that creating a user story fails with total isolation when project code doesn't exist")
    @Test
    void createUserStorySucceeds() {
        // Arrange
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

        // when project code doesn't exist, projectRepository returns empty optional
        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.empty());
        when(userStoryFactoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIDDouble);
        when(projectDouble.addToProductBacklog(userStoryIDDouble, priorityDouble)).thenReturn(true);

        // Act
        boolean result = userStoryService.createUserStory(projectCodeDouble, userStoryDTODouble, priorityDouble);

        // Assert
        assertFalse(result);
    }

    @DisplayName("assert that creating a user story fails with total isolation when saving in repository fails")
    @Test
    void createUserStoryFailsWhenSavingInRepoFails() {
        // Arrange
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

        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(userStoryFactoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);
        // when saving user story fails, userStoryRepository returns false
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(null);
        when(userStoryDouble.identity()).thenReturn(userStoryIDDouble);
        when(projectDouble.addToProductBacklog(userStoryIDDouble, priorityDouble)).thenReturn(true);

        // Act
        boolean result = userStoryService.createUserStory(projectCodeDouble, userStoryDTODouble, priorityDouble);

        // Assert
        assertFalse(result);
    }

    @DisplayName("assert that creating a user story fails with total isolation when saving in product backlog fails")
    @Test
    void createUserStoryFailsWhenSavingInBacklogFails() {
        // Arrange
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

        when(projectRepositoryDouble.getByID(projectCodeDouble)).thenReturn(Optional.of(projectDouble));
        when(userStoryFactoryDouble.createUserStory(any(), any(), any(), any())).thenReturn(userStoryDouble);
        when(userStoryRepositoryDouble.save(userStoryDouble)).thenReturn(userStoryDouble);
        when(userStoryDouble.identity()).thenReturn(userStoryIDDouble);
        // when saving to backlog fails, project.addToProductBacklog returns false
        when(projectDouble.addToProductBacklog(userStoryIDDouble, priorityDouble)).thenReturn(false);

        // Act
        boolean result = userStoryService.createUserStory(projectCodeDouble, userStoryDTODouble, priorityDouble);

        // Assert
        assertFalse(result);
    }
}
