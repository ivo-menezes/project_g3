package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Service;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.model.sprint.Sprint;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.TimePeriod;

@Service
    public class SprintAssemblerData {

        public SprintJPA toData(SprintDDD sprint) {
            return new SprintJPA(sprint.identity(), sprint.getTimePeriod());
        }

        public SprintDDD toDomain( SprintJPA sprintJpa) {
            return new SprintDDD(sprintJpa.getSprintID(), new TimePeriod(sprintJpa.getStartDate(), sprintJpa.getEndDate()));
        }
}
