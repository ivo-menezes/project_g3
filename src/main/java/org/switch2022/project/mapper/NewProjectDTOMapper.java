package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.project.ProjectDDD;

@Component
public class NewProjectDTOMapper {

    public NewProjectDTO toDto(ProjectDDD project){

        NewProjectDTO dto = new NewProjectDTO();
        dto.projectCode = project.getProjectCode();
        dto.projectName = project.getProjectName();
        dto.description = project.getDescription();
        dto.projectStatus = project.getProjectStatus();
        dto.timePeriod = project.getTimePeriod();
        dto.projectSprintDuration = project.getProjectSprintDuration();
        dto.projectNumberOfPlannedSprints = project.getProjectNumberOfPlannedSprints();
        dto.customerID = project.getCustomerID();
        dto.businessSectorID = project.getBusinessSectorID();
        dto.typologyID = project.getTypologyID();
        dto.projectBudget = project.getProjectBudget();
        dto.productBacklog = project.getProductBacklog();

        return dto;

    }
}
