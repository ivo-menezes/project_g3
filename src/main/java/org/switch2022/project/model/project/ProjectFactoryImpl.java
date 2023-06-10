package org.switch2022.project.model.project;


import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewProjectDTO;
import org.switch2022.project.model.valueobject.*;

@Component
public class ProjectFactoryImpl implements IProjectFactory {
    @Override
    public ProjectDDD createProject(ProjectCode code,
                                    ProjectName name,
                                    Description description,
                                    TimePeriod timePeriod,
                                    ProjectSprintDuration sprintDuration,
                                    ProjectNumberOfPlannedSprints numberOfPlannedSprints,
                                    CustomerID customerId,
                                    BusinessSectorID businessSectorId,
                                    TypologyID typologyId,
                                    ProjectBudget projectBudget) {

        if (code == null) {
            throw new IllegalArgumentException("Code must not be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
        if (customerId == null) {
            throw new IllegalArgumentException("CustomerId must not be null");
        }
        if (businessSectorId == null) {
            throw new IllegalArgumentException("BusinessSectorId must not be null");
        }
        if (typologyId == null) {
            throw new IllegalArgumentException("TypologyId must not be null");
        }

        return new ProjectDDD(code,
                name, description, timePeriod, sprintDuration,
                numberOfPlannedSprints, customerId, businessSectorId,
                typologyId, projectBudget);
    }

    @Override
    public ProjectDDD createProject(NewProjectDTO projectDto) {

        return new ProjectDDD(
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
    }
}

