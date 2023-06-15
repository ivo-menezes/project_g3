package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    private String sprintStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "projectCode", referencedColumnName = "projectCode"),
            @JoinColumn(name = "sprintNumber", referencedColumnName = "sprintNumber")
    })
    private List<UserStoryInSprintJPA> sprintBacklogJPA;

    protected SprintJPA() {
    }

    public SprintJPA(SprintJpaID sprintID, Date startDate, Date endDate, String sprintStatus,
                     List<UserStoryInSprintJPA> sprintBacklogJPA) {
        this.sprintID = sprintID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintStatus = sprintStatus;
        this.sprintBacklogJPA = sprintBacklogJPA;
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

    public String getSprintStatus() {
        return sprintStatus;
    }

    public List<UserStoryInSprintJPA> getSprintBacklogJPA() {
        return sprintBacklogJPA;
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
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(sprintStatus, that.sprintStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sprintID, startDate, endDate, sprintStatus);
    }
}

