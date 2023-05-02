package org.switch2022.project.model.project;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
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
    private final List<UserStoryID> productBacklog = new ArrayList<>();

    public ProjectDDD(ProjectCode projectCode,
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

        this.projectCode = projectCode;
        this.projectName = projectName;
        this.description = description;
        this.projectStatus = projectStatus;
        this.timePeriod = timePeriod;
        this.projectBudget = projectBudget;
        this.projectSprintDuration = projectSprintDuration;
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;

    }

    @Override
    public ProjectCode identity() {
        return this.projectCode;
    }

    public List<UserStoryID> getProductBacklog() {
        return List.copyOf(this.productBacklog);
    }

    public boolean addToProductBacklog(UserStoryID userStoryID, UserStoryPriority priority) {
        int position = priority.getValue();

        if (position < 0 || position > this.productBacklog.size()) {
            position = this.productBacklog.size();
        }

        if (productBacklog.contains(userStoryID)) {
            return false;
        } else {
            this.productBacklog.add(position, userStoryID);
            return true;
        }
    }
}
