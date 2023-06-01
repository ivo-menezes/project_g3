package org.switch2022.project.model.project;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectDDD implements AggregateRoot<ProjectCode> {

    private ProjectCode projectCode;
    private ProjectName projectName;
    private Description description;
    private ProjectStatus projectStatus;
    private TimePeriod timePeriod;
    private ProjectSprintDuration projectSprintDuration;
    private ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    private CustomerID customerID;
    private BusinessSectorID businessSectorID;
    private TypologyID typologyID;
    private ProjectBudget projectBudget;
    private ProductBacklogDDD productBacklog;


    public ProjectDDD(ProjectCode projectCode,
                      ProjectName projectName,
                      Description description,
                      TimePeriod timePeriod,
                      ProjectSprintDuration projectSprintDuration,
                      ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints,
                      CustomerID customerID,
                      BusinessSectorID businessSectorID,
                      TypologyID typologyID,
                      ProjectBudget projectBudget
    ) {

        this(projectCode, projectName, description, ProjectStatus.Planned, timePeriod, projectSprintDuration, projectNumberOfPlannedSprints, customerID, businessSectorID, typologyID, projectBudget, new ArrayList<>());

    }

    public ProjectDDD(ProjectCode projectCode,
                      ProjectName projectName,
                      Description description,
                      ProjectStatus projectStatus,
                      TimePeriod timePeriod,
                      ProjectSprintDuration projectSprintDuration,
                      ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints,
                      CustomerID customerID,
                      BusinessSectorID businessSectorID,
                      TypologyID typologyID,
                      ProjectBudget projectBudget,
                      List<UserStoryID> userStoryIDs
    ) {
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
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.projectStatus = projectStatus;
        this.timePeriod = timePeriod;
        this.projectSprintDuration = projectSprintDuration;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
        this.customerID = customerID;
        this.businessSectorID = businessSectorID;
        this.typologyID = typologyID;
        this.projectBudget = projectBudget;
        this.productBacklog = new ProductBacklogDDD(userStoryIDs);
    }

    @Override
    public ProjectCode identity() {
        return this.projectCode;
    }

    /**
     * Returns the IDs of the open user stories of the project (i.e. the product backlog).
     *
     * @return list of UserStoryIDs
     */
    public List<UserStoryID> getProductBacklog() {
        return this.productBacklog.getOpenUserStories();
    }

    /**
     * Adds the ID of a user story to the product backlog.
     *
     * @param userStoryID of the user story to be added to the backlog
     * @param priority    of the user story; index at which it will be added to the list
     * @return the priority at which it was saved
     */
    public UserStoryPriority addToProductBacklog(UserStoryID userStoryID, UserStoryPriority priority) {
        return this.productBacklog.add(userStoryID, priority);
    }

    public ProjectCode getProjectCode() {
        return projectCode;
    }

    public ProjectName getProjectName() {
        return projectName;
    }

    public Description getDescription() {
        return description;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public ProjectSprintDuration getProjectSprintDuration() {
        return projectSprintDuration;
    }

    public ProjectNumberOfPlannedSprints getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }

    public CustomerID getCustomerID() {
        return customerID;
    }

    public BusinessSectorID getBusinessSectorID() {
        return businessSectorID;
    }

    public TypologyID getTypologyID() {
        return typologyID;
    }

    public ProjectBudget getProjectBudget() {
        return projectBudget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDDD that = (ProjectDDD) o;
        return projectCode.equals(that.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }
}
