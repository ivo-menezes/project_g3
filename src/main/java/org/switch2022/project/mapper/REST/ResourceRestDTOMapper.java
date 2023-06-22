package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.ResourceDTOOutput;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ResourceRestDTOMapper {

    public NewResourceDTO toDomainDto(ResourceRestDTO restResourceDto, AccountID accountID) {
        NewResourceDTO domainDTO = new NewResourceDTO();

        domainDTO.resourceID = new ResourceID(restResourceDto.resourceID);
        domainDTO.accountID = new AccountID(accountID.getId());
        domainDTO.costPerHour = new CostPerHour(restResourceDto.costPerHour);
        domainDTO.role = new Role(restResourceDto.role);
        domainDTO.percentageOfAllocation = new PercentageOfAllocation(restResourceDto.percentageOfAllocation);
        domainDTO.projectCode = new ProjectCode(restResourceDto.projectCode);

        Date startDate = restResourceDto.startDate;
        Date endDate = restResourceDto.endDate;
        domainDTO.timePeriod = new TimePeriod(startDate, endDate);

        return domainDTO;
    }

    public ResourceRestDTO toRestDto(NewResourceDTO domainDto, Email email) {

        ResourceRestDTO resourceRestDTO = new ResourceRestDTO();

        resourceRestDTO.resourceID = domainDto.resourceID.getId();
        resourceRestDTO.email = email.toString();
        resourceRestDTO.costPerHour = domainDto.costPerHour.getValue();
        resourceRestDTO.role = domainDto.role.toString();
        resourceRestDTO.percentageOfAllocation = domainDto.percentageOfAllocation.getValue();
        resourceRestDTO.projectCode = domainDto.projectCode.toString();

        TimePeriod timePeriod = domainDto.timePeriod;
        resourceRestDTO.startDate = timePeriod.getStartDate();
        resourceRestDTO.endDate = timePeriod.getEndDate();

        return resourceRestDTO;
    }

    public ResourceRestDTO toRestDtoOutput(ResourceDTOOutput resourceDTOOutput) {

        ResourceRestDTO resourceRestDTO = new ResourceRestDTO();

        resourceRestDTO.resourceID = resourceDTOOutput.resourceID.getId();
        resourceRestDTO.email = resourceDTOOutput.email.toString();
        resourceRestDTO.costPerHour = resourceDTOOutput.costPerHour.getValue();
        resourceRestDTO.role = resourceDTOOutput.role.toString();
        resourceRestDTO.percentageOfAllocation = resourceDTOOutput.percentageOfAllocation.getValue();
        resourceRestDTO.projectCode = resourceDTOOutput.projectCode.toString();

        TimePeriod timePeriod = resourceDTOOutput.timePeriod;
        resourceRestDTO.startDate = timePeriod.getStartDate();
        resourceRestDTO.endDate = timePeriod.getEndDate();

        return resourceRestDTO;
    }

    public List<ResourceRestDTO> toRestDTOList(List<ResourceDTOOutput> domainList) {
        List<ResourceRestDTO> restList = new ArrayList<>();

        for (ResourceDTOOutput domainDTO : domainList) {
            ResourceRestDTO restDTO = toRestDtoOutput(domainDTO);
            restList.add(restDTO);
        }
        return restList;
    }


}
