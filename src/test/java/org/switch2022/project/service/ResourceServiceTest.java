package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.NewResourceDTOMapper;
import org.switch2022.project.model.account.AccountDDD;
import org.switch2022.project.model.profile.Profile;
import org.switch2022.project.model.resource.IResourceFactory;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.model.valueobject.Email;
import org.switch2022.project.model.valueobject.ProfileName;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.Role;
import org.switch2022.project.repository.ProjectRepositoryForJpa;
import org.switch2022.project.service.irepositories.IAccountRepository;
import org.switch2022.project.service.irepositories.IResourceRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class ResourceServiceTest {

    @MockBean
    IResourceFactory resourceFactory;

    @MockBean
    IResourceRepository resourceRepository;

    @MockBean
    IAccountRepository accountRepository;

    @MockBean
    ProjectRepositoryForJpa projectRepository;

    @MockBean
    NewResourceDTOMapper resourceDTOMapper;

    @Autowired
    ResourceService resourceService;

    @DisplayName("assert that creating a resource succeeds when role is product owner")
    @Test
    void createResourceSucceedsWhenRoleIsProductOwner() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProfileName profile = mock(ProfileName.class);

        AccountDDD account = mock(AccountDDD.class);
        ResourceDDD resource = mock(ResourceDDD.class);
        ResourceDDD savedResource = mock(ResourceDDD.class);
        NewResourceDTO resourceDTO = mock(NewResourceDTO.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.of(account));
        when(account.getProfile()).thenReturn(profile);
        when(account.isUser(profile)).thenReturn(true);
        when(projectRepository.existsByProjectCode(projectCode.toString())).thenReturn(true);
        when(role.toString()).thenReturn("Product Owner");

        when(resourceFactory.createResource(newResourceDTO)).thenReturn(resource);
        when(resourceRepository.save(resource)).thenReturn(savedResource);
        when(resourceDTOMapper.toDTO(savedResource)).thenReturn(resourceDTO);

        // Act
        NewResourceDTO result = resourceService.createResource(newResourceDTO);

        // Assert
        assertEquals(resourceDTO, result);
    }

    @DisplayName("assert that creating a resource succeeds when role is scrum master")
    @Test
    void createResourceSucceedsWhenRoleIsScrumMaster() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProfileName profile = mock(ProfileName.class);

        AccountDDD account = mock(AccountDDD.class);
        ResourceDDD resource = mock(ResourceDDD.class);
        ResourceDDD savedResource = mock(ResourceDDD.class);
        NewResourceDTO resourceDTO = mock(NewResourceDTO.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.of(account));
        when(account.getProfile()).thenReturn(profile);
        when(account.isUser(profile)).thenReturn(true);
        when(projectRepository.existsByProjectCode(projectCode.toString())).thenReturn(true);
        when(role.toString()).thenReturn("Scrum Master");

        when(resourceFactory.createResource(newResourceDTO)).thenReturn(resource);
        when(resourceRepository.save(resource)).thenReturn(savedResource);
        when(resourceDTOMapper.toDTO(savedResource)).thenReturn(resourceDTO);

        // Act
        NewResourceDTO result = resourceService.createResource(newResourceDTO);

        // Assert
        assertEquals(resourceDTO, result);
    }

    @DisplayName("assert that creating a resource succeeds when role is team member")
    @Test
    void createResourceSucceedsWhenRoleIsTeamMember() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProfileName profile = mock(ProfileName.class);

        AccountDDD account = mock(AccountDDD.class);
        ResourceDDD resource = mock(ResourceDDD.class);
        ResourceDDD savedResource = mock(ResourceDDD.class);
        NewResourceDTO resourceDTO = mock(NewResourceDTO.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.of(account));
        when(account.getProfile()).thenReturn(profile);
        when(account.isUser(profile)).thenReturn(true);
        when(projectRepository.existsByProjectCode(projectCode.toString())).thenReturn(true);
        when(role.toString()).thenReturn("Team Member");

        when(resourceFactory.createResource(newResourceDTO)).thenReturn(resource);
        when(resourceRepository.save(resource)).thenReturn(savedResource);
        when(resourceDTOMapper.toDTO(savedResource)).thenReturn(resourceDTO);

        // Act
        NewResourceDTO result = resourceService.createResource(newResourceDTO);

        // Assert
        assertEquals(resourceDTO, result);
    }

    @DisplayName("assert that creating a resource fails when account doesn't exist")
    @Test
    void createResourceFailsWhenAccountDoesntExist() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.empty());

        String expected = "Account with given email doesn't exist";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            resourceService.createResource(newResourceDTO);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expected, resultMessage);
    }

    @DisplayName("assert that creating a resource fails when profile is not user")
    @Test
    void createResourceFailsWhenProfileIsNotUser() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProfileName profile = mock(ProfileName.class);

        AccountDDD account = mock(AccountDDD.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.of(account));
        when(account.getProfile()).thenReturn(profile);
        when(account.isUser(profile)).thenReturn(false);

        String expected = "This account doesn't have an user profile";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            resourceService.createResource(newResourceDTO);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expected, resultMessage);
    }

    @DisplayName("assert that creating a resource fails when project code doesn't exist")
    @Test
    void createResourceFailsWhenProjectCodeDoesntExist() {
        // Arrange
        NewResourceDTO newResourceDTO = mock(NewResourceDTO.class);
        Email email = mock(Email.class);
        Role role = mock(Role.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProfileName profile = mock(ProfileName.class);

        AccountDDD account = mock(AccountDDD.class);

        newResourceDTO.email = email;
        newResourceDTO.role = role;
        newResourceDTO.projectCode = projectCode;

        when(accountRepository.getByEmail(email)).thenReturn(Optional.of(account));
        when(account.getProfile()).thenReturn(profile);
        when(account.isUser(profile)).thenReturn(true);
        when(projectRepository.existsByProjectCode(projectCode.toString())).thenReturn(false);

        String expected = "This project doesn't exist";

        // Act
        RuntimeException result = assertThrows(RuntimeException.class, () -> {
            resourceService.createResource(newResourceDTO);
        });
        String resultMessage = result.getMessage();

        // Assert
        assertEquals(expected, resultMessage);
    }
}