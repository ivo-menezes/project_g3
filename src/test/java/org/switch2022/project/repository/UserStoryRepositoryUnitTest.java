package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.datamodel.JPA.UserStoryJpaId;
import org.switch2022.project.datamodel.JPA.assemblers.UserStoryDomainDataAssembler;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryNumber;
import org.switch2022.project.repository.JPA.UserStoryJpaRepository;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoryRepositoryUnitTest {

    @Mock
    UserStoryJpaRepository userStoryJpaRepositoryDouble;

    @Mock
    UserStoryDomainDataAssembler userStoryDomainDataAssemblerDouble;

    @InjectMocks
    UserStoryRepository userStoryRepository;


    @DisplayName("ensure containsID returns false when JPA repo returns false")
    @Test
    void shouldNotContainIDEmptyRepo() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");

        // Act
        boolean result = userStoryRepository.containsID(userStoryIdDouble);

        // Assert
        assertFalse(result);
    }

    @DisplayName("ensure containsID returns true when JPA repo returns true")
    @Test
    void shouldContainID() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");

        UserStoryJpaId jpaId = new UserStoryJpaId("XPTO", "US001");

        when(userStoryJpaRepositoryDouble.existsById(jpaId)).thenReturn(true);

        // Act
        boolean result = userStoryRepository.containsID(userStoryIdDouble);

        // Assert
        assertTrue(result);
    }

    @DisplayName("ensure saving a UserStory returns the saved object when JPA returns the saved object")
    @Test
    void shouldSaveAUserStory() {
        // Arrange
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        UserStoryJpa savedUserStoryJpaDouble = mock(UserStoryJpa.class);
        UserStoryDDD savedUserStoryDouble = mock(UserStoryDDD.class);

        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");
        UserStoryJpaId jpaId = new UserStoryJpaId("XPTO", "US001");

        when(userStoryJpaRepositoryDouble.existsById(jpaId)).thenReturn(false);

        when(userStoryDomainDataAssemblerDouble.toData(userStoryDouble)).thenReturn(userStoryJpaDouble);
        when(userStoryJpaRepositoryDouble.save(userStoryJpaDouble)).thenReturn(savedUserStoryJpaDouble);
        when(userStoryDomainDataAssemblerDouble.toDomain(savedUserStoryJpaDouble)).thenReturn(savedUserStoryDouble);

        // Act
        UserStoryDDD result = userStoryRepository.save(userStoryDouble);

        // Assert
        assertEquals(savedUserStoryDouble, result);
    }

    @DisplayName("ensure saving a UserStory throws exception if JPA detects ID already exists")
    @Test
    void shouldNotSaveUserStoryBecauseIdExists() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");

        UserStoryJpaId jpaId = new UserStoryJpaId("XPTO", "US001");

        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryJpaRepositoryDouble.existsById(jpaId)).thenReturn(true);

        String expectedMessage = "UserStoryID already exists";

        // Act
        KeyAlreadyExistsException result = assertThrows(KeyAlreadyExistsException.class, () -> {
            userStoryRepository.save(userStoryDouble);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("ensure findAll user stories returns a collection of user stories")
    @Test
    void shouldReturnAListOfUserStories() {
        // Arrange
        UserStoryJpa userStoryJpaDouble1 = mock(UserStoryJpa.class);
        UserStoryJpa userStoryJpaDouble2 = mock(UserStoryJpa.class);

        List<UserStoryJpa> jpaList = new ArrayList<>();
        jpaList.add(userStoryJpaDouble1);
        jpaList.add(userStoryJpaDouble2);

        UserStoryDDD userStoryDouble1 = mock(UserStoryDDD.class);
        UserStoryDDD userStoryDouble2 = mock(UserStoryDDD.class);

        when(userStoryJpaRepositoryDouble.findAll()).thenReturn(jpaList);
        when(userStoryDomainDataAssemblerDouble.toDomain(userStoryJpaDouble1)).thenReturn(userStoryDouble1);
        when(userStoryDomainDataAssemblerDouble.toDomain(userStoryJpaDouble2)).thenReturn(userStoryDouble2);

        List<UserStoryDDD> expectedList = new ArrayList<>();
        expectedList.add(userStoryDouble1);
        expectedList.add(userStoryDouble2);

        // Act
        Iterable<UserStoryDDD> resultList = userStoryRepository.findAll();

        // Assert
        assertEquals(expectedList, resultList);
    }

    @DisplayName("ensure getByID returns optional of object that JPA returns")
    @Test
    void shouldReturnOptionalOfUserStory() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);

        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        Optional<UserStoryJpa> userStoryJpaDoubleOptional = Optional.of(userStoryJpaDouble);

        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        Optional<UserStoryDDD> userStoryDoubleOptional = Optional.of(userStoryDouble);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");

        UserStoryJpaId jpaId = new UserStoryJpaId("XPTO", "US001");

        when(userStoryJpaRepositoryDouble.findById(jpaId)).thenReturn(userStoryJpaDoubleOptional);
        when(userStoryDomainDataAssemblerDouble.toDomain(userStoryJpaDouble)).thenReturn(userStoryDouble);

        // Act
        Optional<UserStoryDDD> resultOptional = userStoryRepository.getByID(userStoryIdDouble);

        // Assert
        assertEquals(userStoryDoubleOptional, resultOptional);
    }

    @DisplayName("ensure getByID returns empty optional when JPA also returns empty optional")
    @Test
    void shouldReturnEmptyOptional() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);

        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryNumberDouble.toString()).thenReturn("US001");

        UserStoryJpaId jpaId = new UserStoryJpaId("XPTO", "US001");

        when(userStoryJpaRepositoryDouble.findById(jpaId)).thenReturn(Optional.empty());

        // Act
        Optional<UserStoryDDD> resultOptional = userStoryRepository.getByID(userStoryIdDouble);

        // Assert
        assertEquals(Optional.empty(), resultOptional);
    }
}