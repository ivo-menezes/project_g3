package org.switch2022.project.model.resource;

import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.model.valueobject.*;


public interface IResourceFactory {

    ResourceDDD createResource (ResourceID resourceID, AccountID accountID, CostPerHour costPerHour, Role role,
                                PercentageOfAllocation percentageOfAllocation, ProjectCode projectCode,
                                TimePeriod timePeriod);

    ResourceDDD createResource (NewResourceDTO newResourceDTO);
}
