package org.switch2022.project.mapper.REST;

import java.util.Date;
import java.util.List;

public class ProjectRestDto {

    public String projectCode;
    public String projectName;
    public String description;
    public String projectStatus;
    public Date startDate;
    public Date endDate;
    public int sprintDuration;
    public int projectNumberOfPlannedSprints;
    public long customerID;
    public long businessSectorID;
    public long typologyID;
    public float projectBudget;
    public List<String> productBacklog;
}
