package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.model.valueobject.ProjectCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SprintDTOMapperTest {

    SprintDTOMapper mapper = new SprintDTOMapper();

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
}