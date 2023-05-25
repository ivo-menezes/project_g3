package org.switch2022.project.datamodel.JPA;

import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;
import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sprints")
public class SprintJPA {

 @Id
    private SprintID sprintID;

    private Date startDate;

    private Date endDate;

    public SprintJPA() {}

    public SprintJPA(SprintID sprintID, Date startDate, Date endDate) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SprintID getSprintID() {
        return sprintID;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public SprintJPA(SprintID sprintID, TimePeriod timePeriod) {
        this.sprintID = sprintID;
        this.startDate = (Date) timePeriod.getStartDate();
        this.endDate = (Date) timePeriod.getEndDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintJPA)) return false;
        SprintJPA sprintJPA = (SprintJPA) o;
        return Objects.equals(sprintID, sprintJPA.sprintID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }
}
