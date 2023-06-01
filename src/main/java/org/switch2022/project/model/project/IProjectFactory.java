package org.switch2022.project.model.project;

import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

public interface IProjectFactory {

    ProjectDDD createProject (ProjectCode projectCode,
                              ProjectName projectName,
                              Description description,
                              TimePeriod timePeriod,
                              ProjectSprintDuration projectSprintDuration,
                              ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints,
                              CustomerID customerID,
                              BusinessSectorID businessSectorID,
                              TypologyID typologyID,
                              ProjectBudget projectBudget);

    ProjectDDD createProject(NewProjectDTO projectDto);
}
