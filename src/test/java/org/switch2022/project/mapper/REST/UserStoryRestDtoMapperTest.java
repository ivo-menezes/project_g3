package org.switch2022.project.mapper.REST;


import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewAddUsToSprintBacklogDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.UserStoryInSprintDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserStoryRestDtoMapperTest {

    UserStoryRestDtoMapper mapper = new UserStoryRestDtoMapper();

    @Test
    void toDomainDto() {
        // Arrange
        UserStoryRestDto dtoDouble = mock(UserStoryRestDto.class);
        dtoDouble.userStoryNumber = "US001";
        dtoDouble.projectCode = "XPTO";
        dtoDouble.actor = "Administrator";
        dtoDouble.description = "This is a description";
        dtoDouble.acceptanceCriteria = "None";
        dtoDouble.priority = 0;

        // Act
        NewUserStoryInfoDTO result = mapper.toDomainDto(dtoDouble);

        // Assert
        assertInstanceOf(NewUserStoryInfoDTO.class, result);
    }

    @Test
    void toRestDto() {
        // Arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);

        dtoDouble.userStoryNumber = userStoryNumberDouble;
        dtoDouble.projectCode = projectCodeDouble;
        dtoDouble.actor = actorDouble;
        dtoDouble.description = descriptionDouble;
        dtoDouble.acceptanceCriteria = criteriaDouble;
        dtoDouble.status = statusDouble;
        dtoDouble.priority = priorityDouble;

        // Act
        UserStoryRestDto result = mapper.toRestDto(dtoDouble);

        // Assert
        assertInstanceOf(UserStoryRestDto.class, result);
    }

    @Test
    void toRestDtoList() {
        // Arrange
        NewUserStoryInfoDTO dtoDouble = mock(NewUserStoryInfoDTO.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        UserStoryActor actorDouble = mock(UserStoryActor.class);
        Description descriptionDouble = mock(Description.class);
        UserStoryAcceptanceCriteria criteriaDouble = mock(UserStoryAcceptanceCriteria.class);
        UserStoryStatus statusDouble = mock(UserStoryStatus.class);
        UserStoryPriority priorityDouble = mock(UserStoryPriority.class);

        dtoDouble.userStoryNumber = userStoryNumberDouble;
        dtoDouble.projectCode = projectCodeDouble;
        dtoDouble.actor = actorDouble;
        dtoDouble.description = descriptionDouble;
        dtoDouble.acceptanceCriteria = criteriaDouble;
        dtoDouble.status = statusDouble;
        dtoDouble.priority = priorityDouble;

        List<NewUserStoryInfoDTO> dtoList = new ArrayList<>();
        dtoList.add(dtoDouble);

        // Act
        List<UserStoryRestDto> resultList = mapper.toRestDtoList(dtoList);

        // Assert
        assertInstanceOf(UserStoryRestDto.class, resultList.get(0));
    }

    @Test
    void toSprintBacklogDomainDTO() {
        // Arrange
        AddUsToSprintBacklogDTO addUsToSprintBacklogDTO = mock(AddUsToSprintBacklogDTO.class);
        addUsToSprintBacklogDTO.userStoryNumber = "US003";
        addUsToSprintBacklogDTO.projectCode = "Test";
        addUsToSprintBacklogDTO.sprintNumber = 2;
        addUsToSprintBacklogDTO.userStoryEffortEstimate = 3;

        // Act
        NewAddUsToSprintBacklogDTO result = mapper.toSprintBacklogDomainDTO(addUsToSprintBacklogDTO);

        // Assert
        assertInstanceOf(NewAddUsToSprintBacklogDTO.class, result);
    }

    @Test
    void toSprintBacklogRestDto() {
        // Arrange
        UserStoryInSprintDTO userStoryInSprintDTO = new UserStoryInSprintDTO();
        UserStoryInSprintID userStoryInSprintID = mock(UserStoryInSprintID.class);
        UserStoryEffortEstimate userStoryEffortEstimate = mock(UserStoryEffortEstimate.class);
        UserStoryStatus userStoryStatus = mock(UserStoryStatus.class);
        userStoryInSprintDTO.userStoryInSprintID = userStoryInSprintID;
        userStoryInSprintDTO.userStoryEffortEstimate = userStoryEffortEstimate;
        userStoryInSprintDTO.userStoryStatus = userStoryStatus;

        SprintID sprintID = mock(SprintID.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        when(userStoryInSprintDTO.userStoryInSprintID.getSprintID()).thenReturn(sprintID);
        when(userStoryInSprintDTO.userStoryInSprintID.getUserStoryID()).thenReturn(userStoryID);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        when(sprintID.getProjectCode()).thenReturn(projectCodeDouble);
        SprintNumber sprintNumberDouble = mock(SprintNumber.class);
        when(sprintID.getSprintNumber()).thenReturn(sprintNumberDouble);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        when(userStoryID.getUserStoryNumber()).thenReturn(userStoryNumberDouble);

        // Act
        AddUsInSprintToBacklogDTO result = mapper.toSprintBacklogRestDTO(userStoryInSprintDTO);

        // Assert
        assertInstanceOf(AddUsInSprintToBacklogDTO.class, result);
    }
}