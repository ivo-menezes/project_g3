package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sprints")
public class SprintJPA {

 @Id
    private SprintJpaID sprintID;
    private Date startDate;
    private Date endDate;

    protected SprintJPA() {}

    public SprintJPA(SprintJpaID sprintID, Date startDate, Date endDate) {
      this.sprintID = sprintID;
      this.startDate = startDate;
      this.endDate = endDate;
    }
    public SprintJpaID getSprintID() { return sprintID; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
}
