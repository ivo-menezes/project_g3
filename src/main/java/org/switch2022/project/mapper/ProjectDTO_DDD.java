package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.ProjectStatus;

import java.util.Date;

public class ProjectDTO_DDD {

        public String code;
        public String name;
        public String description;
        public ProjectStatus Status;
        public Date StartDate;
        public Date endDate;
        public float budget;
        public int sprintDuration;
        public int numberOfPlannedSprints;
}
