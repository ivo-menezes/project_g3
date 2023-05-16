package org.switch2022.project.repository;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintRepositoryTest {


    @Test
    public void ensureThatRepositoryIsInstantiated(){
        new SprintRepository();
    }

    @Test
    public void ensureThatRepositoryAddsSpringCorrectly(){
        //arrange
        SprintRepository sprintData = new SprintRepository();
        SprintDDD sprintMock = mock(SprintDDD.class);

        //act
        boolean result = sprintData.save(sprintMock);
        sprintData.clearRepository();

        //assert
        assertTrue(result);
    }

    @Test
    public void checkRepositoryDoesNotContainID(){
        //arrange
        SprintRepository sprintData = new SprintRepository();
        SprintID sprintIDMock = mock(SprintID.class);

        //act
        boolean result = sprintData.containsID(sprintIDMock);

        //assert
        assertFalse(result);
    }
    @Test
    public void checkIfRepositoryContainsID(){
        //arrange
        SprintRepository sprintData = new SprintRepository();

        SprintID mockID = mock(SprintID.class);

        SprintDDD mockSprint = mock(SprintDDD.class);
        when(mockSprint.identity()).thenReturn(mockID);

        //act
        sprintData.save(mockSprint);
        boolean result = sprintData.containsID(mockID);
        sprintData.clearRepository();

        //assert
        assertTrue(result);
    }

    /***
     * Method necessary to test the findAll repository method, since it creates
     * an iterable collection
     * @param iterable Object
     * @return int
     */
    private int countIterable(Iterable<?> iterable) {
        int count = 0;
        for (Object item : iterable) {
            count++;
        }
        return count;
    }
    @Test
    public void checkIfRepositoryIsEmpty(){
        //arrange
        SprintRepository sprintData = new SprintRepository();

        //act
        Iterable<SprintDDD> result = sprintData.findAll();

        //assert
        assertEquals(0, countIterable(result));
    }
    @Test
    public void ensureRepositoryIsReturningANonEmptyIterableCollection() {
        //arrange

        SprintRepository sprintDataNew = new SprintRepository();

        //created three mock SprintIDs
        SprintID mockID = mock(SprintID.class);
        SprintID mockIDTwo = mock(SprintID.class);
        SprintID mockIDThree = mock(SprintID.class);

        //created three SprintDDD mocks and trained them
        SprintDDD mockSprint = mock(SprintDDD.class);
        when(mockSprint.identity()).thenReturn(mockID);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        when(mockSprintTwo.identity()).thenReturn(mockIDTwo);
        SprintDDD mockSprintThree = mock(SprintDDD.class);
        when(mockSprintThree.identity()).thenReturn(mockIDThree);

        //saved the SprintDDD mocks to the repository
        sprintDataNew.save(mockSprint);
        sprintDataNew.save(mockSprintTwo);
        sprintDataNew.save(mockSprintThree);

        //act
        Iterable<SprintDDD> result = sprintDataNew.findAll();

        //assert
        assertEquals(3, countIterable(result));
    }

    @Test
    public void ensureRepositoryHasSprintWithID() {
        //arrange
        SprintRepository sprintData = new SprintRepository();

        //created two mock SprintIDs
        SprintID mockID = mock(SprintID.class);
        SprintID mockIDTwo = mock(SprintID.class);

        //created two SprintDDD mocks and trained them
        SprintDDD mockSprint = mock(SprintDDD.class);
        when(mockSprint.identity()).thenReturn(mockID);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        when(mockSprintTwo.identity()).thenReturn(mockIDTwo);

        //act
        sprintData.save(mockSprint);
        sprintData.save(mockSprintTwo);

        Optional expected = Optional.of(mockSprint);
        Optional result = sprintData.getByID(mockID);
        sprintData.clearRepository();

        //assert
        assertEquals(expected, result);
    }

    @Test
    public void ensureRepositoryDoesNotHaveSprintWithID() {
        //arrange
        SprintRepository sprintData = new SprintRepository();

        //created two mock SprintIDs
        SprintID mockID = mock(SprintID.class);
        SprintID mockIDTwo = mock(SprintID.class);
        SprintID mockIDThree = mock(SprintID.class);

        //created two SprintDDD mocks and trained them
        SprintDDD mockSprint = mock(SprintDDD.class);
        when(mockSprint.identity()).thenReturn(mockID);
        SprintDDD mockSprintTwo = mock(SprintDDD.class);
        when(mockSprintTwo.identity()).thenReturn(mockIDTwo);

        //act
        sprintData.save(mockSprint);
        sprintData.save(mockSprintTwo);

        Optional expected = Optional.empty();
        Optional result = sprintData.getByID(mockIDThree);
        sprintData.clearRepository();

        //assert
        assertEquals(expected, result);
    }
}