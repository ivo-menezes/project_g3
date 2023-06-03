package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.datamodel.JPA.ProjectJpa;
import org.switch2022.project.datamodel.JPA.assemblers.ProjectDomainDataAssembler;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.repository.JPA.ProjectJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryForJpaTest {

    @Mock
    ProjectJpaRepository projectJpaRepository;

    @Mock
    ProjectDomainDataAssembler projectAssembler;

    @InjectMocks
    ProjectRepositoryForJpa projectRepository;


    @Test
    @DisplayName("Ensure project is successfully saved")
    void ensureProjectIsSaved() {
        //Arrange
        ProjectDDD project = mock(ProjectDDD.class);
        ProjectDDD savedProject = mock(ProjectDDD.class);

        ProjectJpa projectJpa = mock(ProjectJpa.class);
        ProjectJpa savedProjectJpa = mock(ProjectJpa.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(project.identity()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.toString()).thenReturn("P001");

        when(projectRepository.existsByProjectCode("P001")).thenReturn(false);
        when(projectAssembler.toData(project)).thenReturn(projectJpa);
        when(projectJpaRepository.save(projectJpa)).thenReturn(savedProjectJpa);
        when(projectAssembler.toDomain(savedProjectJpa)).thenReturn(savedProject);

        //Act
        ProjectDDD resultingProject = projectRepository.save(project);

        //Assert
        assertEquals(savedProject, resultingProject);
    }

    @DisplayName("ensure getByID returns optional of project that JPA returns")
    @Test
    void shouldReturnOptionalOfProject() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectJpa projectJpaDouble = mock(ProjectJpa.class);
        Optional<ProjectJpa> projectJpaDoubleOptional = Optional.of(projectJpaDouble);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        Optional<ProjectDDD> projectDoubleOptional = Optional.of(projectDouble);

        when(projectCodeDouble.toString()).thenReturn("PROJ001");
        when(projectJpaRepository.findById("PROJ001")).thenReturn(projectJpaDoubleOptional);
        when(projectAssembler.toDomain(projectJpaDouble)).thenReturn(projectDouble);

        // Act
        Optional<ProjectDDD> result = projectRepository.getByID(projectCodeDouble);

        // Assert
        assertEquals(projectDoubleOptional, result);
    }

    @DisplayName("ensure getByID returns empty optional if JPA returns empty optional")
    @Test
    void shouldReturnEmptyOptional() {
        // Arrange
        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(projectCodeDouble.toString()).thenReturn("PROJ001");
        when(projectJpaRepository.findById("PROJ001")).thenReturn(Optional.empty());

        // Act
        Optional<ProjectDDD> result = projectRepository.getByID(projectCodeDouble);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    @DisplayName("Ensure project is successfully replaced")
    void ensureProjectIsReplaced(){
        //Arrange
        ProjectDDD project = mock(ProjectDDD.class);
        ProjectDDD savedProject = mock(ProjectDDD.class);

        ProjectJpa projectJpa = mock(ProjectJpa.class);
        ProjectJpa savedProjectJpa = mock(ProjectJpa.class);

        when(projectAssembler.toData(project)).thenReturn(projectJpa);
        when(projectJpaRepository.save(projectJpa)).thenReturn(savedProjectJpa);
        when(projectAssembler.toDomain(savedProjectJpa)).thenReturn(savedProject);

        //Act
        ProjectDDD resultingProject = projectRepository.replace(project);

        //Assert
        assertEquals(savedProject, resultingProject);
    }

    @Test
    @DisplayName("Ensure project list is returned")
    void ensureProjectListIsReturned() {
        //Arrange
        ProjectDDD projectDouble1 = mock(ProjectDDD.class);
        ProjectDDD projectDouble2 = mock(ProjectDDD.class);
        ProjectDDD projectDouble3 = mock(ProjectDDD.class);
        ProjectJpa projectJpaDouble1 = mock(ProjectJpa.class);
        ProjectJpa projectJpaDouble2 = mock(ProjectJpa.class);
        ProjectJpa projectJpaDouble3 = mock(ProjectJpa.class);

        List <ProjectJpa> projectJpaListDouble = new ArrayList<>();
        projectJpaListDouble.add(projectJpaDouble1);
        projectJpaListDouble.add(projectJpaDouble2);
        projectJpaListDouble.add(projectJpaDouble3);

        when(projectJpaRepository.findAll()).thenReturn(projectJpaListDouble);

        when(projectAssembler.toDomain(projectJpaDouble1)).thenReturn(projectDouble1);
        when(projectAssembler.toDomain(projectJpaDouble2)).thenReturn(projectDouble2);
        when(projectAssembler.toDomain(projectJpaDouble3)).thenReturn(projectDouble3);

        List<ProjectDDD> expectedProjectList = new ArrayList<>();
        expectedProjectList.add(projectDouble1);
        expectedProjectList.add(projectDouble2);
        expectedProjectList.add(projectDouble3);

        //Act
        List<ProjectDDD> resultProjectList = projectRepository.getAllProjects();

        //Assert
        assertEquals(expectedProjectList, resultProjectList);
    }
}