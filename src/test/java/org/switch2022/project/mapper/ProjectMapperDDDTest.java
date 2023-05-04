package org.switch2022.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectMapperDDDTest {

    @Test
    @DisplayName("ensure a list of projectDTOs is successfully created with a single element")
    void ensureUserStoryDTOListIsCreatedOneElement () {

        // Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectName projectName = mock(ProjectName.class);
        Description description = mock(Description.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        ProjectStatus projectStatus = mock(ProjectStatus.class);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDuration = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = mock(ProjectNumberOfPlannedSprints.class);
        when(projectDDD.getProjectCode()).thenReturn(projectCode);
        when(projectDDD.getProjectName()).thenReturn(projectName);
        when(projectDDD.getDescription()).thenReturn(description);
        when(projectDDD.getTimePeriod()).thenReturn(timePeriod);
        when(projectDDD.getProjectStatus()).thenReturn(projectStatus);
        when(projectDDD.getProjectBudget()).thenReturn(projectBudget);
        when(projectDDD.getProjectSprintDuration()).thenReturn(projectSprintDuration);
        when(projectDDD.getProjectNumberOfPlannedSprints()).thenReturn(projectNumberOfPlannedSprints);

        List<ProjectDDD> projectDDDList = new ArrayList<>();
        projectDDDList.add(projectDDD);

        ProjectMapperDDD mapper = new ProjectMapperDDD();

        // Act
        List<ProjectDTOForListDDD> result = mapper.toDTOList(projectDDDList);

        //Assert
        assertEquals(1, result.size());
        assertSame(projectCode, result.get(0).projectCode);
        assertSame(projectName, result.get(0).projectName);
        assertSame(description, result.get(0).description);
        assertSame(timePeriod, result.get(0).timePeriod);
        assertSame(projectStatus, result.get(0).projectStatus);
        assertSame(projectBudget, result.get(0).projectBudget);
        assertSame(projectSprintDuration, result.get(0).projectSprintDuration);
        assertSame(projectNumberOfPlannedSprints, result.get(0).projectNumberOfPlannedSprints);
    }

    @Test
    @DisplayName("ensure a list of projectDTOs is successfully created with two elements")
    void ensureUserStoryDTOListIsCreatedTwoElements() {

        // Arrange
        ProjectDDD projectDDD = mock(ProjectDDD.class);
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectName projectName = mock(ProjectName.class);
        Description description = mock(Description.class);
        TimePeriod timePeriod = mock(TimePeriod.class);
        ProjectStatus projectStatus = mock(ProjectStatus.class);
        ProjectBudget projectBudget = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDuration = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = mock(ProjectNumberOfPlannedSprints.class);
        when(projectDDD.getProjectCode()).thenReturn(projectCode);
        when(projectDDD.getProjectName()).thenReturn(projectName);
        when(projectDDD.getDescription()).thenReturn(description);
        when(projectDDD.getTimePeriod()).thenReturn(timePeriod);
        when(projectDDD.getProjectStatus()).thenReturn(projectStatus);
        when(projectDDD.getProjectBudget()).thenReturn(projectBudget);
        when(projectDDD.getProjectSprintDuration()).thenReturn(projectSprintDuration);
        when(projectDDD.getProjectNumberOfPlannedSprints()).thenReturn(projectNumberOfPlannedSprints);

        ProjectDDD projectDDD2 = mock(ProjectDDD.class);
        ProjectCode projectCode2 = mock(ProjectCode.class);
        ProjectName projectName2 = mock(ProjectName.class);
        Description description2 = mock(Description.class);
        TimePeriod timePeriod2 = mock(TimePeriod.class);
        ProjectStatus projectStatus2 = mock(ProjectStatus.class);
        ProjectBudget projectBudget2 = mock(ProjectBudget.class);
        ProjectSprintDuration projectSprintDuration2 = mock(ProjectSprintDuration.class);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints2 = mock(ProjectNumberOfPlannedSprints.class);
        when(projectDDD2.getProjectCode()).thenReturn(projectCode2);
        when(projectDDD2.getProjectName()).thenReturn(projectName2);
        when(projectDDD2.getDescription()).thenReturn(description2);
        when(projectDDD2.getTimePeriod()).thenReturn(timePeriod2);
        when(projectDDD2.getProjectStatus()).thenReturn(projectStatus2);
        when(projectDDD2.getProjectBudget()).thenReturn(projectBudget2);
        when(projectDDD2.getProjectSprintDuration()).thenReturn(projectSprintDuration2);
        when(projectDDD2.getProjectNumberOfPlannedSprints()).thenReturn(projectNumberOfPlannedSprints2);

        List<ProjectDDD> projectDDDList = new ArrayList<>();
        projectDDDList.add(projectDDD);
        projectDDDList.add(projectDDD2);


        ProjectMapperDDD mapper = new ProjectMapperDDD();

        // Act
        List<ProjectDTOForListDDD> result = mapper.toDTOList(projectDDDList);

        //Assert
        assertEquals(2, result.size());
        assertSame(projectCode, result.get(0).projectCode);
        assertSame(projectName, result.get(0).projectName);
        assertSame(description, result.get(0).description);
        assertSame(timePeriod, result.get(0).timePeriod);
        assertSame(projectStatus, result.get(0).projectStatus);
        assertSame(projectBudget, result.get(0).projectBudget);
        assertSame(projectSprintDuration, result.get(0).projectSprintDuration);
        assertSame(projectNumberOfPlannedSprints, result.get(0).projectNumberOfPlannedSprints);
        assertSame(projectCode2, result.get(1).projectCode);
        assertSame(projectName2, result.get(1).projectName);
        assertSame(description2, result.get(1).description);
        assertSame(timePeriod2, result.get(1).timePeriod);
        assertSame(projectStatus2, result.get(1).projectStatus);
        assertSame(projectBudget2, result.get(1).projectBudget);
        assertSame(projectSprintDuration2, result.get(1).projectSprintDuration);
        assertSame(projectNumberOfPlannedSprints2, result.get(1).projectNumberOfPlannedSprints);
    }

}