package org.switch2022.project.model.project;


import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

@Component
public class ProjectFactoryImpl implements IProjectFactory {
    @Override
    public ProjectDDD createProject(ProjectCode projectCode,
                                    ProjectName projectName,
                                    Description description,
                                    TimePeriod timePeriod,
                                    ProjectSprintDuration projectSprintDuration,
                                    ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints,
                                    CustomerID customerID,
                                    BusinessSectorID businessSectorID,
                                    TypologyID typologyID,
                                    ProjectBudget projectBudget) {

        if (projectCode == null) {
            throw new IllegalArgumentException("Project code must not be null");
        }
        if (projectName == null) {
            throw new IllegalArgumentException("Project name must not be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
        if (customerID == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        if (businessSectorID == null) {
            throw new IllegalArgumentException("Business sector ID must not be null");
        }
        if (typologyID == null) {
            throw new IllegalArgumentException("Typology ID must not be null");
        }

        ProjectDDD project = new ProjectDDD(projectCode,
                projectName, description, timePeriod, projectSprintDuration,
                projectNumberOfPlannedSprints, customerID, businessSectorID,
                typologyID, projectBudget);

        return project;
    }

    @Override
    public ProjectDDD createProject(NewProjectDTO projectDto) {

        ProjectDDD project = new ProjectDDD(
                projectDto.projectCode,
                projectDto.projectName,
                projectDto.description,
                projectDto.timePeriod,
                projectDto.projectSprintDuration,
                projectDto.projectNumberOfPlannedSprints,
                projectDto.customerID,
                projectDto.businessSectorID,
                projectDto.typologyID,
                projectDto.projectBudget);

        return project;
    }
}

