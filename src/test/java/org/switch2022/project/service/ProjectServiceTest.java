package org.switch2022.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.mapper.NewProjectDTOMapper;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.BusinessSectorID;
import org.switch2022.project.model.valueobject.CustomerID;
import org.switch2022.project.model.valueobject.TypologyID;
import org.switch2022.project.service.irepositories.IBusinessSectorRepository;
import org.switch2022.project.service.irepositories.ICustomerRepository;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ITypologyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Ensure exception is returned when CustomerRepository is null")
    void ensureExceptionWhenCustomerRepositoryNull() {
        // arrange
        String expectedMessage = "Customer Repository must not be null.";

        // act
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(null, businessSectorRepository,
                typologyRepository, projectFactory, projectNewRepository,
                newProjectDTOMapper));

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
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(customerRepository, null,
                typologyRepository, projectFactory, projectNewRepository,
                newProjectDTOMapper));

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
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(customerRepository, businessSectorRepository,
                null, projectFactory, projectNewRepository,
                newProjectDTOMapper));

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
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(customerRepository, businessSectorRepository,
                typologyRepository, null, projectNewRepository,
                newProjectDTOMapper));

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
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(customerRepository, businessSectorRepository,
                typologyRepository, projectFactory, null,
                newProjectDTOMapper));

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
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new ProjectService(customerRepository, businessSectorRepository,
                typologyRepository, projectFactory, projectNewRepository,
                null));

        String resultMessage = result.getMessage();

        // assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("assert that creating a project succeeds")
    void createProjectSuccess() {
        //Arrange
        NewProjectDTO projectDtoDouble = mock(NewProjectDTO.class);
        CustomerID customerID = mock(CustomerID.class);
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        TypologyID typologyID = mock(TypologyID.class);

        projectDtoDouble.customerID = customerID;
        projectDtoDouble.businessSectorID = businessSectorID;
        projectDtoDouble.typologyID = typologyID;

        when(customerRepository.containsID(customerID)).thenReturn(true);
        when(businessSectorRepository.containsID(businessSectorID)).thenReturn(true);
        when(typologyRepository.containsID(typologyID)).thenReturn(true);

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

    @Test
    @DisplayName("assert that creating a project fails when customerID does not exist")
    void createProjectFailsWhenCustomerIDDoesNotExist() {
        //Arrange
        NewProjectDTO projectDtoDouble = mock(NewProjectDTO.class);
        CustomerID customerID = mock(CustomerID.class);
        projectDtoDouble.customerID = customerID;

        when(customerRepository.containsID(customerID)).thenReturn(false);
        String expectedMessage = "There is no Customer with that ID.";

        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> projectService.createProject(projectDtoDouble));
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }


    @Test
    @DisplayName("assert that creating a project fails when businessSectorID does not exist")
    void createProjectFailsWhenBusinessSectorIDDoesNotExist() {
        //Arrange
        NewProjectDTO projectDtoDouble = mock(NewProjectDTO.class);
        CustomerID customerID = mock(CustomerID.class);
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);

        projectDtoDouble.customerID = customerID;
        projectDtoDouble.businessSectorID = businessSectorID;

        when(customerRepository.containsID(customerID)).thenReturn(true);
        when(businessSectorRepository.containsID(businessSectorID)).thenReturn(false);
        String expectedMessage = "There is no Business sector with that ID.";

        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> projectService.createProject(projectDtoDouble));
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }

    @Test
    @DisplayName("assert that creating a project fails when typologyID does not exist")
    void createProjectFailsWhenTypologyIDDoesNotExist() {
        //Arrange
        NewProjectDTO projectDtoDouble = mock(NewProjectDTO.class);
        CustomerID customerID = mock(CustomerID.class);
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        TypologyID typologyID = mock(TypologyID.class);

        projectDtoDouble.customerID = customerID;
        projectDtoDouble.businessSectorID = businessSectorID;
        projectDtoDouble.typologyID = typologyID;

        when(customerRepository.containsID(customerID)).thenReturn(true);
        when(businessSectorRepository.containsID(businessSectorID)).thenReturn(true);
        when(typologyRepository.containsID(typologyID)).thenReturn(false);
        String expectedMessage = "There is no Typology with that ID.";

        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> projectService.createProject(projectDtoDouble));
        String resultMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, resultMessage);
    }
}



