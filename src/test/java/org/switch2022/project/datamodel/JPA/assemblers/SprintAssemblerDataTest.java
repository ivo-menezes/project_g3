package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.*;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SprintAssemblerDataTest {

    @DisplayName("ensure toData method returns a correct SprintJpa - using doubles")
    @Test
    void shouldReturnCorrectSprintJPA() {
        // Arrange
        SprintID sprintIdDouble = mock(SprintID.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        SprintDDD sprintDouble = mock(SprintDDD.class);

        when(sprintDouble.identity()).thenReturn(sprintIdDouble);
        when(sprintDouble.getTimePeriod()).thenReturn(timePeriodDouble);

        SprintJPA expectedSprintJPA = new SprintJPA(sprintIdDouble, timePeriodDouble);
        SprintAssemblerData assembler = new SprintAssemblerData();

        // Act
        SprintJPA resultingSprintJPA = assembler.toData(sprintDouble);

        // Assert
        assertEquals(expectedSprintJPA, resultingSprintJPA);
    }

    @DisplayName("ensure toDomain method returns a correct SprintDDD - using doubles")
    @Test
    void shouldReturnCorrectSprintDDD() throws ParseException {
        // Arrange
        SprintID sprintIdDouble = mock(SprintID.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        SprintJPA sprintJpaDouble = mock(SprintJPA.class);

        when(sprintJpaDouble.getSprintID()).thenReturn(sprintIdDouble);
        when(sprintJpaDouble.getStartDate()).thenReturn(Date.valueOf("2023-01-01"));
        when(sprintJpaDouble.getEndDate()).thenReturn(Date.valueOf("2023-01-07"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date startDate = dateFormat.parse("2023-01-01");
        java.util.Date endDate = dateFormat.parse("2023-01-07");

        SprintDDD expectedSprintDDD = new SprintDDD(sprintIdDouble, new TimePeriod(startDate, endDate));

        SprintAssemblerData assembler = new SprintAssemblerData();

        // Act
        SprintDDD resultingSprintDDD = assembler.toDomain(sprintJpaDouble);

        // Assert
        assertEquals(expectedSprintDDD, resultingSprintDDD);
    }
}


