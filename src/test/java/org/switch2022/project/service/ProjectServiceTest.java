package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.NewProjectDTOMapper;
import org.switch2022.project.mapper.ProjectDTOForListDDD;
import org.switch2022.project.mapper.ProjectMapperDDD;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;
import org.switch2022.project.service.irepositories.ICustomerRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@SpringBootTest
class ProjectServiceTest {

    @MockBean
    ICustomerRepository customerRepository;
    @MockBean
    IBusinessSectorRepository businessSectorRepository;
    @MockBean
    ITypologyRepository typologyRepository;
    @MockBean
    IProjectFactory projectFactory;
    @MockBean
    IProjectRepository projectNewRepository;
    @MockBean
    NewProjectDTOMapper newProjectDTOMapper;
    @MockBean
    Repository<ProjectCode, ProjectDDD> projectRepository;
    @MockBean
    ProjectMapperDDD projectMapper;

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Ensure exception is returned when CustomerRepository is null")
    void ensureExceptionWhenCustomerRepositoryNull() {
        // arrange
        String expectedMessage = "Customer Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(null, businessSectorRepository,
                    typologyRepository, projectFactory, projectNewRepository,
                    newProjectDTOMapper, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("Ensure exception is returned when BusinessSectorRepository is null")
    void ensureExceptionWhenBusinessSectorRepositoryNull() {
        // arrange
        String expectedMessage = "Business Sector Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, null,
                    typologyRepository, projectFactory, projectNewRepository,
                    newProjectDTOMapper, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when TypologyRepository is null")
    void ensureExceptionWhenTypologyRepositoryNull() {
        // arrange
        String expectedMessage = "Typology Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    null, projectFactory, projectNewRepository,
                    newProjectDTOMapper, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


    @DisplayName("assert that creating a ProjectService with null ProjectFactory throws Exception")
    @Test
    void createProjectNullFactoryThrowsException() {
        // arrange
        String expectedMessage = "Project Factory must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    typologyRepository, null, projectNewRepository,
                    newProjectDTOMapper, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when Project New Repository is null")
    void ensureExceptionWhenProjectNewRepositoryNull() {
        // arrange
        String expectedMessage = "Project New Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    typologyRepository, projectFactory, null,
                    newProjectDTOMapper, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when NewProjectDTOMapper is null")
    void ensureExceptionWhenNewProjectDtoMapperNull() {
        // arrange
        String expectedMessage = "New Project DTO Mapper must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    typologyRepository, projectFactory, projectNewRepository,
                    null, projectRepository, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @DisplayName("assert that creating a ProjectService with null ProjectRepository throws Exception")
    @Test
    void createProjectNullRepositoryThrowsException() {
        // arrange
        String expectedMessage = "Project Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    typologyRepository, projectFactory, projectNewRepository,
                    newProjectDTOMapper, null, projectMapper);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("Ensure exception is returned when Project Mapper is null")
    void ensureExceptionWhenProjectMapperNull() {
        // arrange
        String expectedMessage = "Project Mapper must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            new ProjectService(customerRepository, businessSectorRepository,
                    typologyRepository, projectFactory, projectNewRepository,
                    newProjectDTOMapper, projectRepository, null);
        });

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("assert that creating a project succeeds")
    void createProjectSuccess() {
        //Arrange
        NewProjectDTO projectDtoDouble = mock(NewProjectDTO.class);
        NewProjectDTO projectDtoDouble2 = mock(NewProjectDTO.class);
        ProjectDDD projectDouble = mock(ProjectDDD.class);
        ProjectDDD savedProjectDouble = mock(ProjectDDD.class);

        when(projectFactory.createProject(projectDtoDouble)).thenReturn(projectDouble);
        when(projectNewRepository.save(projectDouble)).thenReturn(savedProjectDouble);
        when(newProjectDTOMapper.toDto(savedProjectDouble)).thenReturn(projectDtoDouble2);

        //Act
        NewProjectDTO resultDto = projectService.createProject(projectDtoDouble);

        //Assert
        assertEquals(projectDtoDouble2, resultDto);
    }


        @DisplayName("ensure that return a list of ProjectDTOForListDDD")
        @Test
        void returnAListOfProjectDTOForListDD() {

            ProjectCode projectCode = mock(ProjectCode.class);
            ProjectName projectName = mock(ProjectName.class);
            Description description = mock(Description.class);
            TimePeriod timePeriod = mock(TimePeriod.class);
            ProjectSprintDuration projectSprintDuration = mock(ProjectSprintDuration.class);
            ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = mock(ProjectNumberOfPlannedSprints.class);
            CustomerID customerID = mock(CustomerID.class);
            BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
            TypologyID typologyID = mock(TypologyID.class);
            ProjectBudget projectBudget = mock(ProjectBudget.class);

            ProjectDDD projectDDD = mock(ProjectDDD.class);
            ProjectDDD projectDDD1 = mock(ProjectDDD.class);
            List<ProjectDDD> projectDDDList = new ArrayList<>();
            projectDDDList.add(projectDDD);
            projectDDDList.add(projectDDD1);

            ProjectDTOForListDDD dto1 = new ProjectDTOForListDDD();
            ProjectDTOForListDDD dto2 = new ProjectDTOForListDDD();

            List<ProjectDTOForListDDD> expectedDTOs = Arrays.asList(dto1, dto2);

            when(projectRepository.findAll()).thenReturn(projectDDDList);
            when(projectMapper.toDTOList(projectDDDList)).thenReturn(expectedDTOs);

            when(projectFactory.createProject(eq(projectCode), eq(projectName), eq(description), eq(timePeriod), eq(projectSprintDuration), eq(projectNumberOfPlannedSprints), eq(customerID),  eq(businessSectorID), eq(typologyID),eq(projectBudget))).thenReturn(projectDDDList.get(0));

            List<ProjectDTOForListDDD> actualDTOs = projectService.listProjects();

            assertEquals(expectedDTOs, actualDTOs);
        }


    }



