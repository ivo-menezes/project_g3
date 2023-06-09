package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.model.valueobject.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

class ResourceRestDTOMapperTest {

    @Test
    void toDomainDto() throws ParseException {
        ResourceRestDTO restDTO = new ResourceRestDTO();
        restDTO.resourceID = 2L;
        restDTO.accountID = 1L;
        restDTO.costPerHour = 10.2;
        restDTO.role = "Project Manager";
        restDTO.percentageOfAllocation = 10.5;
        restDTO.projectCode = "xxx";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        restDTO.startDate = dateFormat.parse("2023-02-21");

        restDTO.endDate = dateFormat.parse("2023-03-21");

        NewResourceDTO domainDTO = new NewResourceDTO();
        domainDTO.resourceID = new ResourceID(restDTO.resourceID);
        domainDTO.accountID = new AccountID(restDTO.accountID);
        domainDTO.costPerHour = new CostPerHour(restDTO.costPerHour);
        domainDTO.role = new Role(restDTO.role);
        domainDTO.percentageOfAllocation = new PercentageOfAllocation(restDTO.percentageOfAllocation);
        domainDTO.projectCode = new ProjectCode(restDTO.projectCode);
        domainDTO.timePeriod = new TimePeriod(restDTO.startDate, restDTO.endDate);

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        NewResourceDTO result = mapper.toDomainDto(restDTO);

        assertInstanceOf(NewResourceDTO.class, result);
    }

    @Test
    void toRestDto() throws ParseException {
        NewResourceDTO domainDTO = new NewResourceDTO();
        domainDTO.resourceID = new ResourceID(1L);
        domainDTO.accountID = new AccountID(2L);
        domainDTO.costPerHour = new CostPerHour(5.5);
        domainDTO.role = new Role("Scrum Master");
        domainDTO.percentageOfAllocation = new PercentageOfAllocation(15.6);
        domainDTO.projectCode = new ProjectCode("xxx");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2023-10-10");
        Date endDate = dateFormat.parse("2023-11-11");
        domainDTO.timePeriod = new TimePeriod(startDate, endDate);

        ResourceRestDTO restDTO = new ResourceRestDTO();
        restDTO.resourceID = domainDTO.resourceID.getId();
        restDTO.accountID = domainDTO.accountID.getId();
        restDTO.costPerHour = domainDTO.costPerHour.getValue();
        restDTO.role = domainDTO.role.toString();
        restDTO.percentageOfAllocation = domainDTO.percentageOfAllocation.getValue();
        restDTO.projectCode = domainDTO.projectCode.toString();
        restDTO.startDate = domainDTO.timePeriod.getStartDate();
        restDTO.endDate = domainDTO.timePeriod.getEndDate();

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();
        ResourceRestDTO result = mapper.toRestDto(domainDTO);

        assertInstanceOf(ResourceRestDTO.class, result);
    }

    @Test
    void toRestDTOList() {
        //Arrange
        NewResourceDTO dto = mock(NewResourceDTO.class);

        ResourceID resourceID = mock(ResourceID.class);
        AccountID accountID = mock(AccountID.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        dto.resourceID = resourceID;
        dto.accountID = accountID;
        dto.costPerHour = costPerHour;
        dto.role = role;
        dto.percentageOfAllocation = percentageOfAllocation;
        dto.projectCode = projectCode;
        dto.timePeriod = timePeriod;

        List<NewResourceDTO> list = new ArrayList<>();
        list.add(dto);

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        //Act
        List<ResourceRestDTO> resultList = mapper.toRestDTOList(list);

        //Assert
        assertInstanceOf(ResourceRestDTO.class, resultList.get(0));
    }
}