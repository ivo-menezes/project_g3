package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryDomainDataAssemblerTest {

    @DisplayName("ensure toData method returns a correct UserStoryJpa - using doubles")
    @Test
    void shouldReturnCorrectUserStoryJpa() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryActor userStoryActorDouble = mock(UserStoryActor.class);
        Description userStoryDescriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus userStoryStatusDouble = mock(UserStoryStatus.class);

        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryDouble.getActor()).thenReturn(userStoryActorDouble);
        when(userStoryDouble.getDescription()).thenReturn(userStoryDescriptionDouble);
        when(userStoryDouble.getAcceptanceCriteria()).thenReturn(userStoryAcceptanceCriteriaDouble);
        when(userStoryDouble.getStatus()).thenReturn(userStoryStatusDouble);

        UserStoryJpa expectedUserStoryJpa = new UserStoryJpa(userStoryIdDouble, userStoryActorDouble, userStoryDescriptionDouble, userStoryAcceptanceCriteriaDouble, userStoryStatusDouble);

        UserStoryDomainDataAssembler assembler = new UserStoryDomainDataAssembler();

        // Act
        UserStoryJpa resultingUserStoryJpa = assembler.toData(userStoryDouble);

        // Assert
        assertEquals(expectedUserStoryJpa, resultingUserStoryJpa);
    }

    @DisplayName("ensure toDomain method returns a correct UserStory - using doubles")
    @Test
    void shouldReturnCorrectUserStory() {
        // Arrange
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryActor userStoryActorDouble = mock(UserStoryActor.class);
        Description userStoryDescriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria userStoryAcceptanceCriteriaDouble = mock(UserStoryAcceptanceCriteria.class);

        UserStoryJpa userStoryJpaDouble = mock(UserStoryJpa.class);
        when(userStoryJpaDouble.getUserStoryID()).thenReturn(userStoryIdDouble);
        when(userStoryJpaDouble.getUserStoryActor()).thenReturn(userStoryActorDouble);
        when(userStoryJpaDouble.getUserStoryDescription()).thenReturn(userStoryDescriptionDouble);
        when(userStoryJpaDouble.getUserStoryAcceptanceCriteria()).thenReturn(userStoryAcceptanceCriteriaDouble);

        UserStoryDDD expectedUserStory = new UserStoryDDD(userStoryIdDouble, userStoryActorDouble, userStoryDescriptionDouble, userStoryAcceptanceCriteriaDouble);

        UserStoryDomainDataAssembler assembler = new UserStoryDomainDataAssembler();

        // Act
        UserStoryDDD resultingUserStory = assembler.toDomain(userStoryJpaDouble);

        // Assert
        assertEquals(expectedUserStory, resultingUserStory);
    }
}