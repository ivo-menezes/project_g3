package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewAddUsToSprintBacklogDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

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
        NewAddUsToSprintBacklogDTO dtoDouble = mock(NewAddUsToSprintBacklogDTO.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        SprintNumber sprintNumberDouble = mock(SprintNumber.class);
        UserStoryNumber userStoryNumberDouble = mock(UserStoryNumber.class);
        UserStoryEffortEstimate userStoryEffortEstimateDouble = mock(UserStoryEffortEstimate.class);

        dtoDouble.projectCode = projectCodeDouble;
        dtoDouble.sprintNumber = sprintNumberDouble;
        dtoDouble.userStoryNumber = userStoryNumberDouble;
        dtoDouble.userStoryEffortEstimate = userStoryEffortEstimateDouble;

        // Act
        AddUsToSprintBacklogDTO result = mapper.toSprintBacklogRestDTO(dtoDouble);

        // Assert
        assertInstanceOf(AddUsToSprintBacklogDTO.class, result);
    }
}