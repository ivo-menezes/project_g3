package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewProjectDTOMapperTest {

    @Test
    @DisplayName("Ensure that dto is returned")
    void ensureDtoIsReturned() {

        //Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        when(projectDDD.getProjectCode()).thenReturn(projectCode);
        ProjectName projectName = mock(ProjectName.class);
        when(projectDDD.getProjectName()).thenReturn(projectName);
        Description description = mock(Description.class);
        when(projectDDD.getDescription()).thenReturn(description);
        ProjectStatus projectStatus = mock(ProjectStatus.class);
        when(projectDDD.getProjectStatus()).thenReturn(projectStatus);
        TimePeriod timePeriod = mock(TimePeriod.class);
        when(projectDDD.getTimePeriod()).thenReturn(timePeriod);
        ProjectSprintDuration projectSprintDuration = mock(ProjectSprintDuration.class);
        when(projectDDD.getProjectSprintDuration()).thenReturn(projectSprintDuration);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = mock(ProjectNumberOfPlannedSprints.class);
        when(projectDDD.getProjectNumberOfPlannedSprints()).thenReturn(projectNumberOfPlannedSprints);
        CustomerID customerID = mock(CustomerID.class);
        when(projectDDD.getCustomerID()).thenReturn(customerID);
        BusinessSectorID businessSectorID = mock(BusinessSectorID.class);
        when(projectDDD.getBusinessSectorID()).thenReturn(businessSectorID);
        TypologyID typologyID = mock(TypologyID.class);
        when(projectDDD.getTypologyID()).thenReturn(typologyID);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        when(projectDDD.getProjectBudget()).thenReturn(projectBudget);
        List<UserStoryID> productBacklog = mock(List.class);
        when(projectDDD.getProductBacklog()).thenReturn(productBacklog);

        NewProjectDTOMapper newProjectDTOMapper = new NewProjectDTOMapper();

        //Act
        NewProjectDTO resultDto = newProjectDTOMapper.toDto(projectDDD);

        //Assert
        assertInstanceOf(NewProjectDTO.class, resultDto);
    }
}