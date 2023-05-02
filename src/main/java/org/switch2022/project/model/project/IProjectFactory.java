package org.switch2022.project.model.project;

import org.switch2022.project.model.valueobject.*;

public interface IProjectFactory {

    ProjectDDD createProject (ProjectCode projectCode,
                              ProjectName projectName,
                              Description description,
                              ProjectStatus projectStatus,
                              TimePeriod timePeriod,
                              ProjectBudget projectBudget,
                              ProjectSprintDuration projectSprintDuration,
                              ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints
                              );
}
