package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sprints")
public class SprintJPA {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "projectCode", column = @Column(name = "ProjectCode")),
            @AttributeOverride(name = "sprintNumber", column = @Column(name = "SprintNumber")),
    })
    private SprintJpaID sprintID;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    protected SprintJPA() {
    }

    public SprintJPA(SprintJpaID sprintID, Date startDate, Date endDate) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SprintJpaID getSprintID() {
        return sprintID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SprintJPA that = (SprintJPA) o;
        return Objects.equals(sprintID, that.sprintID) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sprintID, startDate, endDate);
    }
}

