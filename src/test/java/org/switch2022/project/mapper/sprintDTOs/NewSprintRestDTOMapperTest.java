package org.switch2022.project.mapper.sprintDTOs;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintStatus;
import org.switch2022.project.model.valueobject.TimePeriod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewSprintRestDTOMapperTest {

    NewSprintDTOMapper mapper = new NewSprintDTOMapper();

    @Test
    public void ensureThatMapperCreatesInstanceOfDTOClass(){
        //arrange
        SprintDDD mockSprint = mock(SprintDDD.class);
        SprintID mockID = mock(SprintID.class);
        TimePeriod mockPeriod = mock(TimePeriod.class);
        SprintStatus status = SprintStatus.Planned;

        NewSprintDTO mockDTOToController = mock(NewSprintDTO.class);
        mockDTOToController.sprintID = mockID;
        mockDTOToController.timePeriod = mockPeriod;
        mockDTOToController.status = status;
        when(mockSprint.identity()).thenReturn(mockID);
        when(mockSprint.getTimePeriod()).thenReturn(mockPeriod);
        when(mockSprint.getSprintStatus()).thenReturn(status);

        //act
        NewSprintDTO result = mapper.convertToDTO(mockSprint);

        //assert
        assertInstanceOf(NewSprintDTO.class, result);
    }
}