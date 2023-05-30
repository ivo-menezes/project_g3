package org.switch2022.project.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.assemblers.SprintAssemblerData;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.repository.JPA.SprintJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintRepositoryJPATest {

    @Mock
    private SprintJPARepository sprintJPARepository;
    @Mock
    private SprintAssemblerData sprintAssemblerData;
    @InjectMocks
    private SprintRepositoryJPA sprintRepositoryJPA;


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
        SprintDDD result = sprintRepositoryJPA.save(mockSprint);

        //assert
        assertEquals(mockSavedSprint, result);
    }
    @Test
    public void ensureThatRepositoryDoesNotContainID(){
        //arrange
        SprintID sprintIDMock = mock(SprintID.class);

        //act
        boolean result = sprintRepositoryJPA.containsID(sprintIDMock);

        //assert
        assertFalse(result);
    }
    @Test
    public void checkIfRepositoryContainsID(){
        //arrange
        SprintID mockID = mock(SprintID.class);
        when(sprintJPARepository.existsById(mockID)).thenReturn(true);

        //act
        boolean result = sprintRepositoryJPA.containsID(mockID);

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
        Iterable<SprintDDD> result = sprintRepositoryJPA.findAll();

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
        Iterable<SprintDDD> result = sprintRepositoryJPA.findAll();

        //assert
        assertNotEquals(expectedDDDList, result);
    }
    @Test
    public void ensureRepositoryDoesNotHaveSprintWithID(){
        //arrange
        SprintID mockID = mock(SprintID.class);

        when(sprintJPARepository.findById(mockID)).thenReturn(Optional.empty());

        //act
        Optional expected = Optional.empty();
        Optional result = sprintRepositoryJPA.getByID(mockID);

        //assert
        assertEquals(expected, result);
    }
    @Test
    public void ensureRepositoryGetsSprintByID(){
        //arrange
        // created mock SprintID and SprintJPA
        SprintID mockID = mock(SprintID.class);

        SprintJPA mockJPA = mock(SprintJPA.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        Optional<SprintDDD> mockSprintOptional = Optional.of(mockSprint);


        when(sprintJPARepository.findById(mockID)).thenReturn(Optional.of(mockJPA));
        when(sprintAssemblerData.toDomain(mockJPA)).thenReturn(mockSprint);

        //act
        Optional result = sprintRepositoryJPA.getByID(mockID);

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
        List<SprintDDD> result = sprintRepositoryJPA.findByProjectCode(mockCode);

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
        List<SprintDDD> result = sprintRepositoryJPA.findByProjectCode(mockCode);

        // Assert
        assertEquals(expectedDDDList, result);
    }
}