package org.switch2022.project.mapper.sprintDTOs;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SprintDTOToControllerMapperTest {

    SprintDTOToControllerMapper mapper = new SprintDTOToControllerMapper();

    @Test
    public void ensureThatMapperCreatesInstanceOfDTOClass(){
        //arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintID mockID = mock(SprintID.class);
        TimePeriod mockPeriod = mock(TimePeriod.class);

        SprintDTOToController mockDTOToController = mock(SprintDTOToController.class);
        mockDTOToController.sprintID = mockID;
        mockDTOToController.timePeriod = mockPeriod;
        when(mockSprint.identity()).thenReturn(mockID);
        when(mockSprint.getTimePeriod()).thenReturn(mockPeriod);

        //act
        SprintDTOToController result = mapper.convertToDTO(mockSprint);

        //assert
        assertInstanceOf(SprintDTOToController.class, result);
    }
}