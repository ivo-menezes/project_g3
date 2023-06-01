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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryForJpaTest {

    @Mock
    ProjectJpaRepository projectJpaRepository;

    @Mock
    ProjectDomainDataAssembler projectAssembler;

    @InjectMocks
    ProjectRepositoryForJpa projectRepositoryForJpaRepository;


    @Test
    @DisplayName("Ensure project is successfully saved")
    void ensureProjectIsSaved(){
        //Arrange
        ProjectDDD project = mock(ProjectDDD.class);
        ProjectDDD savedProject = mock(ProjectDDD.class);

        ProjectJpa projectJpa = mock(ProjectJpa.class);
        ProjectJpa savedProjectJpa = mock(ProjectJpa.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);

        when(project.identity()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.toString()).thenReturn("P001");

        when(projectRepositoryForJpaRepository.existsByProjectCode("P001")).thenReturn(false);
        when(projectAssembler.toData(project)).thenReturn(projectJpa);
        when(projectJpaRepository.save(projectJpa)).thenReturn(savedProjectJpa);
        when(projectAssembler.toDomain(savedProjectJpa)).thenReturn(savedProject);

        //Act
        ProjectDDD resultingProject = projectRepositoryForJpaRepository.save(project);

        //Assert
        assertEquals(savedProject, resultingProject);
    }
}