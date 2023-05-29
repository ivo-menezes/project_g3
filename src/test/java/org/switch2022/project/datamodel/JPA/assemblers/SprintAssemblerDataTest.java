package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintAssemblerDataTest {


    @DisplayName("ensure toData method returns a correct SprintJPA")
    @Test
    void shouldReturnCorrectSprintJPA() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        // Arrange
        SprintDDD sprintDouble = mock(SprintDDD.class);
        SprintID sprintIdDouble = mock(SprintID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        SprintNumber sprintNumberDouble = mock(SprintNumber.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);

        when(sprintDouble.identity()).thenReturn(sprintIdDouble);
        when(sprintIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.toString()).thenReturn("PJ1");
        when(sprintIdDouble.getSprintNumber()).thenReturn(sprintNumberDouble);
        when(sprintNumberDouble.toString()).thenReturn("1");
        when(sprintDouble.getTimePeriod()).thenReturn(timePeriodDouble);
        when(timePeriodDouble.getStartDate()).thenReturn(formatter.parse("01/01/2023"));
        when(timePeriodDouble.getEndDate()).thenReturn(formatter.parse("31/01/2023"));

        SprintJpaID expectedSprintJpaId = new SprintJpaID("PJ1", 1);
        SprintJPA expectedSprintJpa = new SprintJPA(expectedSprintJpaId, formatter.parse("01/01/2023"), formatter.parse("31/01/2023"));

        SprintAssemblerData assembler = new SprintAssemblerData();

        // Act
        SprintJPA resultingSprintJpa = assembler.toData(sprintDouble);

        // Assert
        assertThat(resultingSprintJpa).usingRecursiveComparison().isEqualTo(expectedSprintJpa);
    }

    @DisplayName("ensure toDomain method returns a correct SprintDDD")
    @Test
    void shouldReturnCorrectSprintDDD() {
        // Arrange
        SprintJPA sprintJpaMock = mock(SprintJPA.class);
        SprintJpaID sprintJpaIdMock = mock(SprintJpaID.class);
        Date startDateMock = mock(Date.class);
        Date endDateMock = mock(Date.class);

        when(sprintJpaMock.getSprintID()).thenReturn(sprintJpaIdMock);
        when(sprintJpaIdMock.getSprintNumber()).thenReturn(1);
        when(sprintJpaIdMock.getProjectCode()).thenReturn("XPTO");
        when(sprintJpaMock.getStartDate()).thenReturn(startDateMock);
        when(sprintJpaMock.getEndDate()).thenReturn(endDateMock);

        SprintAssemblerData assembler = new SprintAssemblerData();

        // Act
        SprintDDD resultingSprintDdd = assembler.toDomain(sprintJpaMock);

        // Assert
        ProjectCode expectedProjectCode = new ProjectCode("XPTO");
        SprintNumber expectedSprintNumber = new SprintNumber(1);
        SprintID expectedSprintId = new SprintID(expectedProjectCode, expectedSprintNumber);
        TimePeriod expectedTimePeriod = new TimePeriod(startDateMock, endDateMock);
        SprintDDD expectedSprintDdd = new SprintDDD(expectedSprintId, expectedTimePeriod);

        assertEquals(expectedSprintDdd, resultingSprintDdd);
    }

}


