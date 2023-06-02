package org.switch2022.project.model.resource;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.model.valueobject.*;

@Component
public class ResourceFactoryImpl implements IResourceFactory{

    @Override
    public ResourceDDD createResource(ResourceID resourceID, Email email, CostPerHour costPerHour, Role role,
                                      PercentageOfAllocation percentageOfAllocation, ProjectCode projectCode,
                                      TimePeriod timePeriod) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (costPerHour == null) {
            throw new IllegalArgumentException("CostPerHour cannot be null");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if (percentageOfAllocation == null) {
            throw new IllegalArgumentException("PercentageOfAllocation cannot be null");
        }
        if (projectCode == null) {
            throw new IllegalArgumentException("ProjectCode cannot be null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("TimePeriod cannot be null");
        }
        ResourceDDD resourceDDD = new ResourceDDD(resourceID, email, costPerHour, role, percentageOfAllocation,
                projectCode, timePeriod);
        return resourceDDD;
    }

    @Override
    public ResourceDDD createResource(NewResourceDTO newResourceDTO) {
        if (newResourceDTO == null) {
            throw new IllegalArgumentException("Dto must not be null");
        }

        ResourceDDD resource = new ResourceDDD(newResourceDTO.resourceID, newResourceDTO.email,
                newResourceDTO.costPerHour, newResourceDTO.role, newResourceDTO.percentageOfAllocation,
                newResourceDTO.projectCode, newResourceDTO.timePeriod);

        return resource;
    }
}
