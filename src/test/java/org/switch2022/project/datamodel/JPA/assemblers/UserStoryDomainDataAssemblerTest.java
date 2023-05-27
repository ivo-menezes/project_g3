package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.datamodel.JPA.UserStoryJpaId;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryDomainDataAssemblerTest {

    @DisplayName("ensure toData method returns a correct UserStoryJpa - using doubles")
    @Test
    void shouldReturnCorrectUserStoryJpa() {
        // Arrange
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        UserStoryActor userStoryActorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);

        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.toString()).thenReturn("XPTO");
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(userStoryNumberDouble.toString()).thenReturn("US001");
        when(userStoryDouble.getActor()).thenReturn(userStoryActorDouble);
        when(userStoryActorDouble.toString()).thenReturn("actor");
        when(userStoryDouble.getDescription()).thenReturn(descriptionDouble);
        when(descriptionDouble.toString()).thenReturn("description");
        when(userStoryDouble.getAcceptanceCriteria()).thenReturn(criteriaDouble);
        when(criteriaDouble.toString()).thenReturn("criteria");
        when(userStoryDouble.getStatus()).thenReturn(statusDouble);
        when(statusDouble.toString()).thenReturn("TO_DO");

        UserStoryJpaId userStoryJpaId = new UserStoryJpaId("XPTO", "US001");
        UserStoryJpa expectedUserStoryJpa = new UserStoryJpa(userStoryJpaId, "actor", "description", "criteria", "TO_DO");

        UserStoryDomainDataAssembler assembler = new UserStoryDomainDataAssembler();

        // Act
        UserStoryJpa resultingUserStoryJpa = assembler.toData(userStoryDouble);

        // Assert
        assertEquals(expectedUserStoryJpa, resultingUserStoryJpa);
    }

    @DisplayName("ensure toDomain method returns a UserStory - using doubles")
    @Test
    void shouldReturnUserStory() {
        // Arrange
        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        UserStoryJpaId userStoryJpaIdDouble = mock(UserStoryJpaId.class);
        when(userStoryJpaDouble.getId()).thenReturn(userStoryJpaIdDouble);
        when(userStoryJpaIdDouble.getUserStoryNumber()).thenReturn("US001");
        when(userStoryJpaIdDouble.getProjectCode()).thenReturn("XPTO");
        when(userStoryJpaDouble.getActor()).thenReturn("actor");
        when(userStoryJpaDouble.getDescription()).thenReturn("description");
        when(userStoryJpaDouble.getAcceptanceCriteria()).thenReturn("criteria");
        when(userStoryJpaDouble.getStatus()).thenReturn("TO_DO");

        UserStoryDomainDataAssembler assembler = new UserStoryDomainDataAssembler();

        // Act
        UserStoryDDD resultingUserStory = assembler.toDomain(userStoryJpaDouble);

        // Assert
        assertInstanceOf(UserStoryDDD.class, resultingUserStory);
    }
}