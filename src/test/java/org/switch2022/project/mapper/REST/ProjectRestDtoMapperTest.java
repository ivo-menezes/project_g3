
package org.switch2022.project.mapper.REST;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectRestDtoMapperTest {

    @Test
    @DisplayName("Ensure toDomainDto returns the expected object type")
    void ensureToDomainDtoReturnsTheExpectedObjectType() {
        //Arrange
        ProjectRestDto projectRestDto = mock(ProjectRestDto.class);
        projectRestDto.projectCode = "P001";
        projectRestDto.projectName = "My Project";
        projectRestDto.description = "This is a test";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        projectRestDto.startDate = calendar.getTime();
        calendar.set(2023, Calendar.MAY, 1);
        projectRestDto.endDate = calendar.getTime();
        projectRestDto.sprintDuration = 3;
        projectRestDto.projectNumberOfPlannedSprints = 3;
        projectRestDto.customerID = 1L;
        projectRestDto.businessSectorID = 1L;
        projectRestDto.typologyID = 1L;
        projectRestDto.projectBudget = 3000;

        ProjectRestDtoMapper mapper = new ProjectRestDtoMapper();

        //Act
        NewProjectDTO resultDto = mapper.toDomainDto(projectRestDto);

        //Assert
        assertInstanceOf(NewProjectDTO.class, resultDto);
    }

    @Test
    @DisplayName("Ensure toRestDto returns the expected object type")
    void ensureToRestDtoReturnsTheExpectedObjectType() {
        //Arrange
        NewProjectDTO newProjectDtoDouble = mock(NewProjectDTO.class);
        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        ProjectName projectNameDouble = mock(ProjectName.class);
        Description descriptionDouble = mock(Description.class);
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        ProjectSprintDuration sprintDurationDouble = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints numberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        CustomerID customerIdDouble = mock(CustomerID.class);
        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        TypologyID typologyIDDouble = mock(TypologyID.class);
        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        UserStoryID userStoryIdDouble = mock(UserStoryID.class);

        newProjectDtoDouble.projectCode = projectCodeDouble;
        newProjectDtoDouble.projectName = projectNameDouble;
        newProjectDtoDouble.description = descriptionDouble;
        newProjectDtoDouble.projectStatus = projectStatusDouble;
        newProjectDtoDouble.timePeriod = timePeriodDouble;
        newProjectDtoDouble.projectSprintDuration = sprintDurationDouble;
        newProjectDtoDouble.projectNumberOfPlannedSprints = numberOfPlannedSprintsDouble;
        newProjectDtoDouble.customerID = customerIdDouble;
        newProjectDtoDouble.businessSectorID = businessSectorIDDouble;
        newProjectDtoDouble.typologyID = typologyIDDouble;
        newProjectDtoDouble.projectBudget = projectBudgetDouble;

        List<UserStoryID> productBacklogDouble = new ArrayList<>();
        when(userStoryIdDouble.getUserStoryNumber()).thenReturn(mock(UserStoryNumber.class));

        productBacklogDouble.add(userStoryIdDouble);
        newProjectDtoDouble.productBacklog = productBacklogDouble;

        ProjectRestDtoMapper mapper = new ProjectRestDtoMapper();

        //Act
        ProjectRestDto resultDto = mapper.toRestDto(newProjectDtoDouble);

        //Assert
        assertInstanceOf(ProjectRestDto.class, resultDto);
    }
}
