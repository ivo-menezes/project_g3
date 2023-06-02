package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

import org.switch2022.project.model.resource.ResourceDDD;

@Component
public class NewResourceDTOMapper {

    public NewResourceDTO toDTO (ResourceDDD resource) {
        NewResourceDTO resourceDTO = new NewResourceDTO();

        resourceDTO.resourceID = resource.identity();
        resourceDTO.email = resource.getEmail();
        resourceDTO.costPerHour = resource.getCostPerHour();
        resourceDTO.role = resource.getRole();
        resourceDTO.percentageOfAllocation = resource.getPercentageOfAllocation();
        resourceDTO.projectCode = resource.getProjectCode();
        resourceDTO.timePeriod = resource.getTimePeriod();

        return resourceDTO;
    }
}

