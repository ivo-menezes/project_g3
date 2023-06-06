package org.switch2022.project.mapper;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewUserStoryInfoDTOMapperTest {

    @Test
    void toDto() {
        // Arrange
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);

        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryDouble.getActor()).thenReturn(actorDouble);
        when(userStoryDouble.getDescription()).thenReturn(descriptionDouble);
        when(userStoryDouble.getAcceptanceCriteria()).thenReturn(criteriaDouble);
        when(userStoryDouble.getStatus()).thenReturn(statusDouble);

        // Act
        NewUserStoryInfoDTOMapper mapper = new NewUserStoryInfoDTOMapper();
        NewUserStoryInfoDTO resultDto = mapper.toDto(userStoryDouble);

        // Assert
        assertInstanceOf(NewUserStoryInfoDTO.class, resultDto);

    }

    @Test
    void toDtoList() {
        // Arrange
        UserStoryDDD userStoryDouble = mock(UserStoryDDD.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);

        when(userStoryDouble.identity()).thenReturn(userStoryIdDouble);
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(userStoryNumberDouble);
        when(userStoryIdDouble.getProjectCode()).thenReturn(projectCodeDouble);
        when(userStoryDouble.getActor()).thenReturn(actorDouble);
        when(userStoryDouble.getDescription()).thenReturn(descriptionDouble);
        when(userStoryDouble.getAcceptanceCriteria()).thenReturn(criteriaDouble);
        when(userStoryDouble.getStatus()).thenReturn(statusDouble);

        List<UserStoryDDD> userStoryList = new ArrayList<>();
        userStoryList.add(userStoryDouble);

        // Act
        NewUserStoryInfoDTOMapper mapper = new NewUserStoryInfoDTOMapper();
        List<NewUserStoryInfoDTO> resultList = mapper.toDtoList(userStoryList);

        // Assert
        assertInstanceOf(NewUserStoryInfoDTO.class, resultList.get(0));
    }
}