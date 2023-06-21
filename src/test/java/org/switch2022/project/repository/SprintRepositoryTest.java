package org.switch2022.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;
import org.switch2022.project.datamodel.JPA.assemblers.SprintAssemblerData;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.repository.JPA.SprintJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintRepositoryTest {

    @Mock
    private SprintJPARepository sprintJPARepository;
    @Mock
    private SprintAssemblerData sprintAssemblerData;
    @InjectMocks
    private SprintRepository sprintRepository;


    @Test
    public void ensureThatRepositoryIsInstantiated(){
        //arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSavedSprint = mock(SprintDDD.class);
        SprintJPA mockJPA = mock(SprintJPA.class);
        SprintJPA mockSavedJPA = mock(SprintJPA.class);
        SprintID mockID = mock(SprintID.class);
        when(mockSprint.identity()).thenReturn(mockID);

        when(sprintAssemblerData.toData(mockSprint)).thenReturn(mockJPA);
        when(sprintJPARepository.save(mockJPA)).thenReturn(mockSavedJPA);
        when(sprintAssemblerData.toDomain(mockSavedJPA)).thenReturn(mockSavedSprint);

        //act
        SprintDDD result = sprintRepository.save(mockSprint);

        //assert
        assertEquals(mockSavedSprint, result);
    }
    @Test
    public void ensureThatRepositoryContainsID(){
        //arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintID mockID = mock(SprintID.class);
        when(mockSprint.identity()).thenReturn(mockID);
        SprintJpaID mockJpaId = mock(SprintJpaID.class);

        when(sprintAssemblerData.convertToSprintJpaID(mockID)).thenReturn(mockJpaId);
        when(sprintJPARepository.existsById(mockJpaId)).thenReturn(true);

        String expectedMessage = "Sprint already exists with this ID";
        //act

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
            sprintRepository.save(mockSprint);
        });

        String resultMessage = result.getMessage();

        //assert
        assertEquals(expectedMessage, resultMessage);
    }
    @Test
    public void ensureThatRepositoryDoesNotContainID(){
        //arrange
        SprintID sprintIDMock = mock(SprintID.class);

        //act
        boolean result = sprintRepository.containsID(sprintIDMock);

        //assert
        assertFalse(result);
    }
    @Test
    public void checkIfRepositoryContainsID(){
        //arrange
        SprintID mockID = mock(SprintID.class);
        SprintJpaID mockJpaId = mock(SprintJpaID.class);

        when(sprintJPARepository.existsById(mockJpaId)).thenReturn(true);
        when(sprintAssemblerData.convertToSprintJpaID(mockID)).thenReturn(mockJpaId);

        //act
        boolean result = sprintRepository.containsID(mockID);

        //assert
        assertTrue(result);
    }
    @Test
    public void ensureRepositoryIsReturningANonEmptyIterableCollection() {
        //arrange
        //created three mock SprintJPAs and SprintDDD
        SprintJPA mockJPA = mock(SprintJPA.class);
        SprintJPA mockJPATwo = mock(SprintJPA.class);
        SprintJPA mockJPAThree = mock(SprintJPA.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        SprintDDD mockSprintThree = mock(SprintDDD.class);

        //created list for the mock sprintJPARepository
        List<SprintJPA> mockJPAList = new ArrayList<>();

        mockJPAList.add(mockJPA);
        mockJPAList.add(mockJPATwo);
        mockJPAList.add(mockJPAThree);

        //Injecting the created list in the repository
        when(sprintJPARepository.findAll()).thenReturn(mockJPAList);
        when(sprintAssemblerData.toDomain(mockJPA)).thenReturn(mockSprint);
        when(sprintAssemblerData.toDomain(mockJPATwo)).thenReturn(mockSprintTwo);
        when(sprintAssemblerData.toDomain(mockJPAThree)).thenReturn(mockSprintThree);

        //created expected list to compare
        List<SprintDDD> expectedDDDList = new ArrayList<>();
        expectedDDDList.add(mockSprint);
        expectedDDDList.add(mockSprintTwo);
        expectedDDDList.add(mockSprintThree);

        //act
        Iterable<SprintDDD> result = sprintRepository.findAll();

        //assert
        assertEquals(expectedDDDList, result);
    }

    @Test
    public void ensureRepositoryShouldNotReturnEmptyList() {
        //arrange
        //created three mock Sprints and mock IDs
        SprintJPA mockJPA = mock(SprintJPA.class);
        SprintJPA mockJPATwo = mock(SprintJPA.class);
        SprintJPA mockJPAThree = mock(SprintJPA.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        SprintDDD mockSprintThree = mock(SprintDDD.class);

        //created list for the mock sprintJPARepository
        List<SprintJPA> mockJPAList = new ArrayList<>();

        mockJPAList.add(mockJPA);
        mockJPAList.add(mockJPATwo);
        mockJPAList.add(mockJPAThree);

        //Injecting the created list in the repository
        when(sprintJPARepository.findAll()).thenReturn(mockJPAList);
        when(sprintAssemblerData.toDomain(mockJPA)).thenReturn(mockSprint);
        when(sprintAssemblerData.toDomain(mockJPATwo)).thenReturn(mockSprintTwo);
        when(sprintAssemblerData.toDomain(mockJPAThree)).thenReturn(mockSprintThree);

        //created expected list to compare
        List<SprintDDD> expectedDDDList = new ArrayList<>();

        //act
        Iterable<SprintDDD> result = sprintRepository.findAll();

        //assert
        assertNotEquals(expectedDDDList, result);
    }
    @Test
    public void ensureRepositoryDoesNotHaveSprintWithID(){
        //arrange
        SprintID mockID = mock(SprintID.class);
        SprintJpaID mockJpaId = mock(SprintJpaID.class);

        when(sprintJPARepository.findById(mockJpaId)).thenReturn(Optional.empty());
        when(sprintAssemblerData.convertToSprintJpaID(mockID)).thenReturn(mockJpaId);

        //act
        Optional expected = Optional.empty();
        Optional result = sprintRepository.getByID(mockID);

        //assert
        assertEquals(expected, result);
    }
    @Test
    public void ensureRepositoryGetsSprintByID(){
        //arrange
        // created mock SprintID and SprintJPA
        SprintID mockID = mock(SprintID.class);

        SprintJPA mockJPA = mock(SprintJPA.class);
        SprintJpaID mockJpaId = mock(SprintJpaID.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        Optional<SprintDDD> mockSprintOptional = Optional.of(mockSprint);

        when(sprintAssemblerData.convertToSprintJpaID(mockID)).thenReturn(mockJpaId);
        when(sprintJPARepository.findById(mockJpaId)).thenReturn(Optional.of(mockJPA));
        when(sprintAssemblerData.toDomain(mockJPA)).thenReturn(mockSprint);

        //act
        Optional result = sprintRepository.getByID(mockID);

        //assert
        assertEquals(mockSprintOptional, result);
    }
    @Test
    public void ensureRepositoryGetsListByProjectCode(){
        // Arrange
        SprintJPA mockJPA = mock(SprintJPA.class);
        SprintJPA mockJPATwo = mock(SprintJPA.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");

        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);

        List<SprintJPA> mockList = new ArrayList<>();
        mockList.add(mockJPA);
        mockList.add(mockJPATwo);

        //when calling the method below, it should give the list created above, the
        //mockList with the two mockSprints
        when(sprintJPARepository.findAllByProjectCode("P1")).thenReturn(mockList);

        when(sprintAssemblerData.toDomain(mockJPA)).thenReturn(mockSprint);
        when(sprintAssemblerData.toDomain(mockJPATwo)).thenReturn(mockSprintTwo);

        List<SprintDDD> expectedDDDList = new ArrayList<>();
        expectedDDDList.add(mockSprint);
        expectedDDDList.add(mockSprintTwo);

        // Act
        List<SprintDDD> result = sprintRepository.findByProjectCode(mockCode);

        // Assert
        assertEquals(expectedDDDList, result);
    }
   @Test
    public void ensureRepositoryDoesNotGetsList(){
        // Arrange
        ProjectCode mockCode = mock(ProjectCode.class);
        when(mockCode.toString()).thenReturn("P1");

        List<SprintJPA> mockList = new ArrayList<>();

        lenient().when(sprintJPARepository.findAll()).thenReturn(mockList);
        List<SprintDDD> expectedDDDList = new ArrayList<>();

        // Act
        List<SprintDDD> result = sprintRepository.findByProjectCode(mockCode);

        // Assert
        assertEquals(expectedDDDList, result);
    }

    @Test
    @DisplayName("Ensure sprint is successfully replaced")
    void ensureProjectIsReplaced(){
        //Arrange
        SprintDDD sprint = mock(SprintDDD.class);
        SprintDDD savedSprint = mock(SprintDDD.class);

        SprintJPA sprintJPA = mock(SprintJPA.class);
        SprintJPA savedSprintJpa = mock(SprintJPA.class);

        when(sprintAssemblerData.toData(sprint)).thenReturn(sprintJPA);
        when(sprintJPARepository.save(sprintJPA)).thenReturn(savedSprintJpa);
        when(sprintAssemblerData.toDomain(savedSprintJpa)).thenReturn(savedSprint);

        //Act
        SprintDDD result = sprintRepository.replace(sprint);

        //Assert
        assertEquals(savedSprint, result);
    }

}