package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;
import org.switch2022.project.model.sprint.UserStoryInSprint;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sprintBacklog")
public class UserStoryInSprintJPA {
    @EmbeddedId
    private UserStoryInSprintIDJpa idJpa;

    private Double userStoryEffortEstimate;

    private String userStoryInSprintStatus;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "projectCode", referencedColumnName = "projectCode", insertable = false, updatable = false),
            @JoinColumn(name = "sprintNumber", referencedColumnName = "sprintNumber", insertable = false, updatable = false)
    })
    private SprintJPA sprint;

    protected UserStoryInSprintJPA() {}

    public UserStoryInSprintJPA(@NonNull UserStoryInSprintIDJpa idJpa,
                                @NonNull Double userStoryEffortEstimate,
                                @NonNull String userStoryInSprintStatus
                                ) {
        this.idJpa = idJpa;
        this.userStoryEffortEstimate = userStoryEffortEstimate;
        this.userStoryInSprintStatus = userStoryInSprintStatus;
    }

    public UserStoryInSprintJPA(UserStoryInSprint userStoryInSprint) {
    }

    protected UserStoryInSprintIDJpa getID() {
        return idJpa;
    }


    public Double getUserStoryEffortEstimate() {
        return userStoryEffortEstimate;
    }

    public String getUserStoryInSprintStatus() {
        return userStoryInSprintStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryInSprintJPA that = (UserStoryInSprintJPA) o;
        return idJpa.equals(that.idJpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJpa);
    }
}
