package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectRestDtoMapper {

    public NewProjectDTO toDomainDto(ProjectRestDto projectRestDto){

        NewProjectDTO domainDto = new NewProjectDTO();

        ProjectCode projectCode = new ProjectCode(projectRestDto.projectCode);
        domainDto.projectCode = projectCode;
        domainDto.projectName = new ProjectName(projectRestDto.projectName);
        domainDto.description = new Description(projectRestDto.description);
        domainDto.timePeriod = new TimePeriod(projectRestDto.startDate, projectRestDto.endDate);
        domainDto.projectSprintDuration = new ProjectSprintDuration(projectRestDto.sprintDuration);
        domainDto.projectNumberOfPlannedSprints =
                new ProjectNumberOfPlannedSprints(projectRestDto.projectNumberOfPlannedSprints);
        domainDto.customerID = new CustomerID(projectRestDto.customerID);
        domainDto.businessSectorID = new BusinessSectorID(projectRestDto.businessSectorID);
        domainDto.typologyID = new TypologyID(projectRestDto.typologyID);
        domainDto.projectBudget = new ProjectBudget(projectRestDto.projectBudget);

        return domainDto;
    }


    public ProjectRestDto toRestDto(NewProjectDTO domainDto){

        ProjectRestDto restDto = new ProjectRestDto();
        restDto.projectCode = domainDto.projectCode.toString();
        restDto.projectName = domainDto.projectName.toString();
        restDto.description = domainDto.description.toString();
        restDto.projectStatus = domainDto.projectStatus.toString();
        restDto.startDate = domainDto.timePeriod.getStartDate();
        restDto.endDate = domainDto.timePeriod.getEndDate();
        restDto.sprintDuration = domainDto.projectSprintDuration.getValue();
        restDto.projectNumberOfPlannedSprints = domainDto.projectNumberOfPlannedSprints.getValue();
        restDto.customerID = domainDto.customerID.getId();
        restDto.businessSectorID = domainDto.businessSectorID.getId();
        restDto.typologyID = domainDto.typologyID.getId();
        restDto.projectBudget = domainDto.projectBudget.getValue();

        List<String> productBacklog = new ArrayList<>();
        for (UserStoryID userStoryID : domainDto.productBacklog) {
            productBacklog.add(userStoryID.getUserStoryNumber().toString());
        }
        restDto.productBacklog = productBacklog;

        return restDto;
    }
}
