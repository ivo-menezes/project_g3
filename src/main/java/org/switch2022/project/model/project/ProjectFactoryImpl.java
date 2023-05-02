package org.switch2022.project.model.project;

import org.switch2022.project.model.valueobject.*;

public class ProjectFactoryImpl implements IProjectFactory {
    @Override
    public ProjectDDD createProject(ProjectCode projectCode,
                                    ProjectName projectName,
                                    Description description,
                                    ProjectStatus projectStatus,
                                    TimePeriod timePeriod,
                                    ProjectBudget projectBudget,
                                    ProjectSprintDuration projectSprintDuration,
                                    ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints) {

        if (projectCode == null) {
            throw new IllegalArgumentException("projectCode cannot be null");
        }

        if (projectName == null) {
            throw new IllegalArgumentException("projectName cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (projectStatus == null) {
            throw new IllegalArgumentException("projectStatus cannot be null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("timePeriod cannot be null");
        }
        if (projectBudget == null) {
            throw new IllegalArgumentException("projectBudget cannot be null");
        }
        if (projectSprintDuration == null) {
            throw new IllegalArgumentException("projectSprintDuration cannot be null");
        }
        if (projectNumberOfPlannedSprints == null) {
            throw new IllegalArgumentException("projectNumberOfPlannedSprints cannot be null");
        }

        ProjectDDD project = new ProjectDDD(projectCode,projectName,description,projectStatus,timePeriod,projectBudget,projectSprintDuration,projectNumberOfPlannedSprints);

        return project;
    }
}
