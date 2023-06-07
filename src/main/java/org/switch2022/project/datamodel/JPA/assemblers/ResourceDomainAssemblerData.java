package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.ResourceJPA;
import org.switch2022.project.model.resource.ResourceDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.Date;

@Component
public class ResourceDomainAssemblerData {

    public ResourceJPA toData(ResourceDDD resourceDDD) {

        long accountID = resourceDDD.getAccountID().getId();
        double costPerHour = resourceDDD.getCostPerHour().getValue();
        String role = resourceDDD.getRole().toString();
        double percentageOfAllocation = resourceDDD.getPercentageOfAllocation().getValue();
        String projectCode = resourceDDD.getProjectCode().toString();
        TimePeriod timePeriod = resourceDDD.getTimePeriod();
        Date startDate = timePeriod.getStartDate();
        Date endDate = timePeriod.getEndDate();

        return new ResourceJPA(accountID, costPerHour, role, percentageOfAllocation, projectCode,startDate, endDate);
    }

    public ResourceDDD toDomain(ResourceJPA resourceJPA) {
        Long id = resourceJPA.getResourceID();
        ResourceID resourceID = new ResourceID(id);
        long resourceAccountID = resourceJPA.getAccountID();
        AccountID accountID = new AccountID(resourceAccountID);
        double resourceCostPerHour = resourceJPA.getCostPerHour();
        CostPerHour costPerHour = new CostPerHour(resourceCostPerHour);
        String resourceRole = resourceJPA.getRole();
        Role role = new Role(resourceRole);
        double resourcePercentageOfAllocation = resourceJPA.getPercentageOfAllocation();
        PercentageOfAllocation percentageOfAllocation = new PercentageOfAllocation(resourcePercentageOfAllocation);
        String resourceProjectCode = resourceJPA.getProjectCode();
        ProjectCode projectCode = new ProjectCode(resourceProjectCode);
        Date resourceStartDate = resourceJPA.getStartDate();
        Date resourceEndDate = resourceJPA.getEndDate();
        TimePeriod timePeriod = new TimePeriod(resourceStartDate, resourceEndDate);

        return new ResourceDDD(resourceID, accountID, costPerHour, role, percentageOfAllocation, projectCode, timePeriod);
    }


}
