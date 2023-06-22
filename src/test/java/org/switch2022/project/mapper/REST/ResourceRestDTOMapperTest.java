package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewResourceDTO;
import org.switch2022.project.mapper.ResourceDTOOutput;
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
        //Arrange
        ResourceRestDTO restDTO = new ResourceRestDTO();
        restDTO.resourceID = 2L;
        restDTO.email = "ricardo@hotmail.com";
        restDTO.costPerHour = 10.2;
        restDTO.role = "Project Manager";
        restDTO.percentageOfAllocation = 10.5;
        restDTO.projectCode = "xxx";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        restDTO.startDate = dateFormat.parse("2023-02-21");

        restDTO.endDate = dateFormat.parse("2023-03-21");

        AccountID accountID = new AccountID(1);
        NewResourceDTO domainDTO = new NewResourceDTO();
        domainDTO.resourceID = new ResourceID(restDTO.resourceID);
        domainDTO.accountID = accountID;
        domainDTO.costPerHour = new CostPerHour(restDTO.costPerHour);
        domainDTO.role = new Role(restDTO.role);
        domainDTO.percentageOfAllocation = new PercentageOfAllocation(restDTO.percentageOfAllocation);
        domainDTO.projectCode = new ProjectCode(restDTO.projectCode);
        domainDTO.timePeriod = new TimePeriod(restDTO.startDate, restDTO.endDate);

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        //Act
        NewResourceDTO result = mapper.toDomainDto(restDTO, accountID);

        //Assert
        assertInstanceOf(NewResourceDTO.class, result);
    }

    @Test
    void toRestDto() throws ParseException {
        //Arrange
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

        Email email = new Email("ricardo@hotmail.com");
        ResourceRestDTO restDTO = new ResourceRestDTO();
        restDTO.resourceID = domainDTO.resourceID.getId();
        restDTO.email = email.toString();
        restDTO.costPerHour = domainDTO.costPerHour.getValue();
        restDTO.role = domainDTO.role.toString();
        restDTO.percentageOfAllocation = domainDTO.percentageOfAllocation.getValue();
        restDTO.projectCode = domainDTO.projectCode.toString();
        restDTO.startDate = domainDTO.timePeriod.getStartDate();
        restDTO.endDate = domainDTO.timePeriod.getEndDate();

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        //Act
        ResourceRestDTO result = mapper.toRestDto(domainDTO, email);

        //Assert
        assertInstanceOf(ResourceRestDTO.class, result);
    }

    @Test
    void toRestDtoOutput() {
        //Arrange
        ResourceDTOOutput resourceDTOOutput = mock(ResourceDTOOutput.class);

        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod1 = mock(TimePeriod.class);

        resourceDTOOutput.resourceID = resourceID;
        resourceDTOOutput.email = email;
        resourceDTOOutput.costPerHour = costPerHour;
        resourceDTOOutput.role = role;
        resourceDTOOutput.percentageOfAllocation = percentageOfAllocation;
        resourceDTOOutput.projectCode = projectCode;
        resourceDTOOutput.timePeriod = timePeriod1;

        ResourceRestDTO resourceRestDTO = mock(ResourceRestDTO.class);

        resourceRestDTO.resourceID = resourceDTOOutput.resourceID.getId();
        resourceRestDTO.email = resourceDTOOutput.email.toString();
        resourceRestDTO.costPerHour = resourceDTOOutput.costPerHour.getValue();
        resourceRestDTO.role = resourceDTOOutput.role.toString();
        resourceRestDTO.percentageOfAllocation = resourceDTOOutput.percentageOfAllocation.getValue();
        resourceRestDTO.projectCode = resourceDTOOutput.projectCode.toString();

        TimePeriod timePeriod = resourceDTOOutput.timePeriod;
        resourceRestDTO.startDate = timePeriod.getStartDate();
        resourceRestDTO.endDate = timePeriod.getEndDate();

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        //Act
        ResourceRestDTO result = mapper.toRestDtoOutput(resourceDTOOutput);

        //Assert
        assertInstanceOf(ResourceRestDTO.class, result);
    }

   @Test
    void toRestDTOList() {
        //Arrange
        ResourceDTOOutput dto = mock(ResourceDTOOutput.class);

        ResourceID resourceID = mock(ResourceID.class);
        Email email = mock(Email.class);
        CostPerHour costPerHour = mock(CostPerHour.class);
        Role role = mock(Role.class);
        PercentageOfAllocation percentageOfAllocation = mock(PercentageOfAllocation.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        TimePeriod timePeriod = mock(TimePeriod.class);

        dto.resourceID = resourceID;
        dto.email = email;
        dto.costPerHour = costPerHour;
        dto.role = role;
        dto.percentageOfAllocation = percentageOfAllocation;
        dto.projectCode = projectCode;
        dto.timePeriod = timePeriod;

        List<ResourceDTOOutput> list = new ArrayList<>();
        list.add(dto);

        ResourceRestDTOMapper mapper = new ResourceRestDTOMapper();

        //Act
        List<ResourceRestDTO> resultList = mapper.toRestDTOList(list);

        //Assert
        assertInstanceOf(ResourceRestDTO.class, resultList.get(0));
    }
}