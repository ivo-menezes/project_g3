package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ResourceRestDTOMapper {

    public NewResourceDTO toDomainDto(ResourceRestDTO restResourceDto) {
        NewResourceDTO domainDTO = new NewResourceDTO();

        domainDTO.resourceID = new ResourceID(restResourceDto.resourceID);
        domainDTO.email = new Email(restResourceDto.email);
        domainDTO.costPerHour = new CostPerHour(restResourceDto.costPerHour);
        domainDTO.role = new Role(restResourceDto.role);
        domainDTO.percentageOfAllocation = new PercentageOfAllocation(restResourceDto.percentageOfAllocation);
        domainDTO.projectCode = new ProjectCode(restResourceDto.projectCode);

        Date startDate = restResourceDto.startDate;
        Date endDate = restResourceDto.endDate;
        domainDTO.timePeriod = new TimePeriod(startDate, endDate);

        return domainDTO;
    }

    public ResourceRestDTO toRestDto(NewResourceDTO domainDto) {

        ResourceRestDTO resourceRestDTO = new ResourceRestDTO();

        resourceRestDTO.resourceID = domainDto.resourceID.getId();
        resourceRestDTO.email = domainDto.email.toString();
        resourceRestDTO.costPerHour = domainDto.costPerHour.getValue();
        resourceRestDTO.role = domainDto.role.toString();
        resourceRestDTO.percentageOfAllocation = domainDto.percentageOfAllocation.getValue();
        resourceRestDTO.projectCode = domainDto.projectCode.toString();

        TimePeriod timePeriod = domainDto.timePeriod;
        resourceRestDTO.startDate = timePeriod.getStartDate();
        resourceRestDTO.endDate = timePeriod.getEndDate();

        return resourceRestDTO;
    }

    public List<ResourceRestDTO> toRestDTOList(List<NewResourceDTO> domainList) {
        List<ResourceRestDTO> restList = new ArrayList<>();

        for (NewResourceDTO domainDTO : domainList) {
            ResourceRestDTO restDTO = toRestDto(domainDTO);
            restList.add(restDTO);
        }
        return restList;
    }


}
