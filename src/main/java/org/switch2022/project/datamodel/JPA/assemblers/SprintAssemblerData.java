package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.*;
import java.util.Date;

@Component
    public class SprintAssemblerData {

    public SprintJPA toData(SprintDDD sprintDDD) {

        SprintID sprintID = sprintDDD.identity();
        String projectCode = sprintID.getProjectCode().toString();
        int sprintNumber = Integer.parseInt(sprintID.getSprintNumber().toString());
        SprintJpaID sprintJpaID = new SprintJpaID(projectCode, sprintNumber);

        TimePeriod timePeriod = sprintDDD.getTimePeriod();
        Date startDate = timePeriod.getStartDate();
        Date endDate = timePeriod.getEndDate();

        SprintJPA sprintJPA = new SprintJPA(sprintJpaID, startDate, endDate);

        return sprintJPA;
        }

    public SprintDDD toDomain(SprintJPA sprintJPA) {

        SprintJpaID sprintJpaID = sprintJPA.getSprintID();
        SprintNumber sprintNumber = new SprintNumber(sprintJpaID.getSprintNumber());
        ProjectCode projectCode = new ProjectCode(sprintJpaID.getProjectCode());
        SprintID sprintID = new SprintID(projectCode, sprintNumber);

        TimePeriod timePeriod = new TimePeriod(sprintJPA.getStartDate(), sprintJPA.getEndDate());

        SprintDDD sprint = new SprintDDD(sprintID, timePeriod);

        return sprint;
        }
}
