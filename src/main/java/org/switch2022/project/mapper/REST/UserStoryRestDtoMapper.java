package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewAddUsToSprintBacklogDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserStoryRestDtoMapper {
    public NewUserStoryInfoDTO toDomainDto(UserStoryRestDto restDto) {

        NewUserStoryInfoDTO domainDto = new NewUserStoryInfoDTO();
        domainDto.userStoryNumber = new UserStoryNumber(restDto.userStoryNumber);
        domainDto.projectCode = new ProjectCode(restDto.projectCode);
        domainDto.actor = new UserStoryActor(restDto.actor);
        domainDto.description = new Description(restDto.description);
        domainDto.acceptanceCriteria = new UserStoryAcceptanceCriteria(restDto.acceptanceCriteria);
        domainDto.priority = new UserStoryPriority(restDto.priority);

        return domainDto;
    }

    public UserStoryRestDto toRestDto(NewUserStoryInfoDTO domainDto) {

        UserStoryRestDto restDto = new UserStoryRestDto();
        restDto.userStoryNumber = domainDto.userStoryNumber.toString();
        restDto.projectCode = domainDto.projectCode.toString();
        restDto.actor = domainDto.actor.toString();
        restDto.description = domainDto.description.toString();
        restDto.acceptanceCriteria = domainDto.acceptanceCriteria.toString();
        restDto.status = domainDto.status.toString();
        restDto.priority = domainDto.priority.getValue();

        return restDto;
    }

    public List<UserStoryRestDto> toRestDtoList(List<NewUserStoryInfoDTO> domainDtoList) {
        List<UserStoryRestDto> restDtoList = new ArrayList<>();

        for (NewUserStoryInfoDTO domainDto : domainDtoList) {
            UserStoryRestDto restDto = toRestDto(domainDto);
            restDtoList.add(restDto);
        }

        return restDtoList;
    }

    public NewAddUsToSprintBacklogDTO toSprintBacklogDomainDTO(AddUsToSprintBacklogDTO addUsToSprintBacklogDTO) {

        NewAddUsToSprintBacklogDTO sprintBacklogDomainDto = new NewAddUsToSprintBacklogDTO();
        sprintBacklogDomainDto.projectCode = new ProjectCode(addUsToSprintBacklogDTO.projectCode);
        sprintBacklogDomainDto.sprintNumber = new SprintNumber(addUsToSprintBacklogDTO.sprintNumber);
        sprintBacklogDomainDto.userStoryNumber = new UserStoryNumber(addUsToSprintBacklogDTO.userStoryNumber);
        sprintBacklogDomainDto.userStoryEffortEstimate = new UserStoryEffortEstimate(addUsToSprintBacklogDTO.userStoryEffortEstimate);

        return sprintBacklogDomainDto;
    }

    public AddUsToSprintBacklogDTO toSprintBacklogRestDTO(NewAddUsToSprintBacklogDTO newAddUsToSprintBacklogDTO) {

        AddUsToSprintBacklogDTO sprintBacklogRestDto = new AddUsToSprintBacklogDTO();
        sprintBacklogRestDto.projectCode = newAddUsToSprintBacklogDTO.projectCode.toString();
        sprintBacklogRestDto.sprintNumber = newAddUsToSprintBacklogDTO.sprintNumber.getValue();
        sprintBacklogRestDto.userStoryNumber = newAddUsToSprintBacklogDTO.userStoryNumber.toString();
        sprintBacklogRestDto.userStoryEffortEstimate = newAddUsToSprintBacklogDTO.userStoryEffortEstimate.getValue();

        return sprintBacklogRestDto;
    }
}
