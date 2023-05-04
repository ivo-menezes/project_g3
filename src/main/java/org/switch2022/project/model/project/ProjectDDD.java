package org.switch2022.project.model.project;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.List;

public class ProjectDDD implements AggregateRoot<ProjectCode> {

    private final ProjectCode projectCode;
    private final ProjectName projectName;
    private final Description description;
    private final ProjectStatus projectStatus;
    private final TimePeriod timePeriod;
    private final ProjectBudget projectBudget;
    private final ProjectSprintDuration projectSprintDuration;
    private final ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    private final ProductBacklogDDD productBacklog;

    public ProjectDDD(ProjectCode projectCode,
                      ProjectName projectName,
                      Description description,
                      ProjectStatus projectStatus,
                      TimePeriod timePeriod,
                      ProjectBudget projectBudget,
                      ProjectSprintDuration projectSprintDuration,
                      ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints
    ) {

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

        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.projectStatus = projectStatus;
        this.timePeriod = timePeriod;
        this.projectBudget = projectBudget;
        this.projectSprintDuration = projectSprintDuration;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;

        this.productBacklog = new ProductBacklogDDD();
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
     * @return true if ID was added, false otherwise
     */
    public boolean addToProductBacklog(UserStoryID userStoryID, UserStoryPriority priority) {
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

    public ProjectBudget getProjectBudget() {
        return projectBudget;
    }

    public ProjectSprintDuration getProjectSprintDuration() {
        return projectSprintDuration;
    }

    public ProjectNumberOfPlannedSprints getProjectNumberOfPlannedSprints() {
        return projectNumberOfPlannedSprints;
    }
}
