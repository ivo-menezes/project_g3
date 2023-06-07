package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SprintDTOMapperTest {

    @Autowired
    private SprintDTOMapper mapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ensureMapperCreatesDTOToServicesCorrectly() throws ParseException {
        //arrange
        SprintDTOUI mockDTO = mock(SprintDTOUI.class);
        ProjectCode mockCode = mock(ProjectCode.class);

        when(mockCode.toString()).thenReturn("mockedProjectCode");

        mockDTO.projectCode = "mockedProjectCode";
        mockDTO.sprintNumber = 1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mockDTO.startDate = dateFormat.parse("2023-03-21");
        mockDTO.endDate = dateFormat.parse("2023-04-21");

        //act
        SprintDTOController result = mapper.toDomainDTO(mockDTO);

        //assert
        assertInstanceOf(SprintDTOController.class, result);
    }
    @Test
    public void ensureMapperCreatesProjectCode(){
        //arrange
        SprintDTOUI mockDTO = mock(SprintDTOUI.class);
        ProjectCode mockCode = mock(ProjectCode.class);
        SprintDTOController mockController = mock(SprintDTOController.class);
        mockController.projectCode = mockCode;

        when(mockCode.toString()).thenReturn("mockedProjectCode");

        mockDTO.projectCode = "mockedProjectCode";

        //act
        SprintDTOController result = mapper.createProjectCode(mockDTO);

        //assert
        assertEquals(mockController.projectCode.toString(), result.projectCode.toString());
    }
    @Test
    public void ensureMapperConvertsToRestDTO() throws ParseException {
        //arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ProjectCode mockCode = mock(ProjectCode.class);
        SprintID mockID = mock(SprintID.class);
        SprintDTOToController mockDTOtoController = mock(SprintDTOToController.class);
        SprintNumber mockNumber = mock(SprintNumber.class);
        TimePeriod mockPeriod = mock(TimePeriod.class);
        mockDTOtoController.sprintID = mockID;
        when(mockPeriod.getStartDate()).thenReturn(dateFormat.parse("2023-03-21"));
        when(mockPeriod.getEndDate()).thenReturn(dateFormat.parse("2023-03-31"));

        when(mockNumber.getSprintNumber()).thenReturn(1);
        when(mockCode.toString()).thenReturn("P1");
        when(mockID.getProjectCode()).thenReturn(mockCode);

        when(mockDTOtoController.sprintID.getProjectCode()).thenReturn(mockCode);
        when(mockDTOtoController.sprintID.getSprintNumber()).thenReturn(mockNumber);
        mockDTOtoController.timePeriod = mockPeriod;

        //act
        SprintDTOUI result = mapper.toRestDTO(mockDTOtoController);

        //assert
        assertInstanceOf(SprintDTOUI.class, result);
    }
}