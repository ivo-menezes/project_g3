package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.*;

import java.util.List;

public class NewProjectDTO {

    public ProjectCode projectCode;
    public ProjectName projectName;
    public Description description;
    public ProjectStatus projectStatus;
    public TimePeriod timePeriod;
    public ProjectSprintDuration projectSprintDuration;
    public ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    public CustomerID customerID;
    public BusinessSectorID businessSectorID;
    public TypologyID typologyID;
    public ProjectBudget projectBudget;
    public List<UserStoryID> productBacklog;
}
