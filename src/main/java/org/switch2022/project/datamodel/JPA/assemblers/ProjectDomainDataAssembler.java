package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.*;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProjectDomainDataAssembler {

    public ProjectJpa toData(ProjectDDD project) {
        String projectCode = project.identity().toString();
        String projectName = project.getProjectName().toString();
        String description = project.getDescription().toString();
        String projectStatus = project.getProjectStatus().toString();

        Date startDate = project.getTimePeriod().getStartDate();
        Date endDate = project.getTimePeriod().getEndDate();

        int sprintDuration = project.getProjectSprintDuration().getValue();
        int projectNumberOfPlannedSprints = project.getProjectNumberOfPlannedSprints().getValue();

        long customerID = project.getCustomerID().getId();
        long businessSectorID = project.getBusinessSectorID().getId();
        long typologyID = project.getTypologyID().getId();

        float projectBudget = project.getProjectBudget().getValue();

        List<UserStoryID> productBacklog = project.getProductBacklog();
        List<String> productBacklogJpa = new ArrayList<>();

        for (UserStoryID userStoryID : productBacklog) {
            String userStoryNumber = userStoryID.getUserStoryNumber().toString();
            productBacklogJpa.add(userStoryNumber);
        }

        return new ProjectJpa(projectCode, projectName, description, projectStatus,
                startDate, endDate, sprintDuration, projectNumberOfPlannedSprints,
                customerID, businessSectorID, typologyID, projectBudget, productBacklogJpa);
    }

    public ProjectDDD toDomain(ProjectJpa projectJpa) {
        ProjectCode projectCode = new ProjectCode(projectJpa.getProjectCode());
        ProjectName projectName = new ProjectName(projectJpa.getProjectName());
        Description description = new Description(projectJpa.getDescription());
        ProjectStatus projectStatus = ProjectStatus.valueOf(projectJpa.getProjectStatus());
        TimePeriod timePeriod = new TimePeriod(projectJpa.getStartDate(), projectJpa.getEndDate());
        ProjectSprintDuration sprintDuration = new ProjectSprintDuration(projectJpa.getSprintDuration());
        ProjectNumberOfPlannedSprints numberOfPlannedSprints =
                new ProjectNumberOfPlannedSprints(projectJpa.getProjectNumberOfPlannedSprints());
        CustomerID customerID = new CustomerID(projectJpa.getCustomerID());
        BusinessSectorID businessSectorID = new BusinessSectorID(projectJpa.getBusinessSectorID());
        TypologyID typologyID = new TypologyID(projectJpa.getTypologyID());
        ProjectBudget projectBudget = new ProjectBudget(projectJpa.getProjectBudget());

        List<String> productBacklogJpa = projectJpa.getProductBacklog();
        List<UserStoryID> productBacklog = new ArrayList<>();
        for (String userStoryNumber : productBacklogJpa) {
            UserStoryNumber userStoryNumberVO = new UserStoryNumber(userStoryNumber);
            UserStoryID userStoryID = new UserStoryID(userStoryNumberVO, projectCode);
            productBacklog.add(userStoryID);
        }

        return new ProjectDDD(projectCode, projectName, description, projectStatus, timePeriod,
                sprintDuration, numberOfPlannedSprints, customerID, businessSectorID, typologyID,
                projectBudget, productBacklog);
    }

}


