package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintIDJpa;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryInSprintDataAssemblerTest {
    @Test
    @DisplayName("ensure toData method returns correct UserStoryInSprintJPA")
    void ensureCorrectUserStoryInSprintJPAReturned() {
        // Arrange
        UserStoryInSprint userStoryInSprint = mock(UserStoryInSprint.class);
        UserStoryInSprintID userStoryInSprintID = mock(UserStoryInSprintID.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintID sprintID = mock(SprintID.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);
        UserStoryStatus userStoryInSprintStatus = mock(UserStoryStatus.class);

        when(userStoryInSprint.identity()).thenReturn(userStoryInSprintID);
        when(userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectCode()).thenReturn(projectCode);
        when(projectCode.toString()).thenReturn("P01");
        when(userStoryInSprintID.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumber);
        when(sprintNumber.getValue()).thenReturn(1);
        when(userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryNumber.toString()).thenReturn("US01");
        when(userStoryInSprint.getUserStoryEffortEstimate()).thenReturn(userStoryEffortEstimate);
        when(userStoryEffortEstimate.getUserStoryEffortEstimate()).thenReturn(2.0);
        when(userStoryInSprint.getUserStoryInSprintStatus()).thenReturn(userStoryInSprintStatus);
        when(String.valueOf(userStoryInSprintStatus)).thenReturn("TO_DO");

        UserStoryInSprintIDJpa idJpa = new UserStoryInSprintIDJpa("P01", 1, "US01");
        UserStoryInSprintJPA expected = new UserStoryInSprintJPA(idJpa, 2.0, "TO_DO");

        UserStoryInSprintDataAssembler assembler = new UserStoryInSprintDataAssembler();

        // Act
        UserStoryInSprintJPA result = assembler.toData(userStoryInSprint);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("ensure toDomain method returns a UserStoryInSprint")
    void ensureToDomainReturnsStoryInSprint() {
        // Arrange
        UserStoryInSprintJPA userStoryInSprintJPA = mock(UserStoryInSprintJPA.class);
        UserStoryInSprintIDJpa userStoryInSprintIDJpa = mock(UserStoryInSprintIDJpa.class);
        when(userStoryInSprintJPA.getID()).thenReturn(userStoryInSprintIDJpa);
        when(userStoryInSprintIDJpa.getProjectCode()).thenReturn("P01");
        when(userStoryInSprintIDJpa.getSprintNumber()).thenReturn(1);
        when(userStoryInSprintIDJpa.getUserStoryNumber()).thenReturn("US01");
        when(userStoryInSprintJPA.getUserStoryEffortEstimate()).thenReturn(2.0);
        when(userStoryInSprintJPA.getUserStoryInSprintStatus()).thenReturn("TO_DO");

        UserStoryInSprintDataAssembler assembler = new UserStoryInSprintDataAssembler();

        // Act
        UserStoryInSprint result = assembler.toDomain(userStoryInSprintJPA);

        // Assert
        assertInstanceOf(UserStoryInSprint.class, result);
    }

    @Test
    @DisplayName("ensure convertToJpaId returns a correct UserStoryInSprintIDJpa")
    void shouldReturnCorrectUserStoryInSprintIDJpa() {
        UserStoryInSprintID userStoryInSprintID = mock(UserStoryInSprintID.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        SprintID sprintID = mock(SprintID.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        SprintNumber sprintNumber = mock(SprintNumber.class);
        UserStoryNumber userStoryNumber = mock(UserStoryNumber.class);

        when(userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectCode()).thenReturn(projectCode);
        when(projectCode.toString()).thenReturn("P01");
        when(userStoryInSprintID.getSprintID()).thenReturn(sprintID);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumber);
        when(sprintNumber.getValue()).thenReturn(1);
        when(userStoryID.getUserStoryNumber()).thenReturn(userStoryNumber);
        when(userStoryNumber.toString()).thenReturn("US01");

        UserStoryInSprintIDJpa expected = new UserStoryInSprintIDJpa("P01", 1, "US01");

        UserStoryInSprintDataAssembler assembler = new UserStoryInSprintDataAssembler();

        // Act
        UserStoryInSprintIDJpa result = assembler.convertToJpaId(userStoryInSprintID);

        // Assert
        assertEquals(expected, result);
    }

}