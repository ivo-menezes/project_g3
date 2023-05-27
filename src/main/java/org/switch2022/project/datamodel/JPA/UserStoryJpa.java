package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "userstories")
public class UserStoryJpa {

    @EmbeddedId
    private final UserStoryJpaId id;

    private final String actor;

    private final String description;

    private final String acceptanceCriteria;

    private final String status;

    public UserStoryJpa(@NonNull UserStoryJpaId id,
                        @NonNull String actor,
                        @NonNull String description,
                        @NonNull String acceptanceCriteria,
                        @NonNull String status) {

        this.id = id;
        this.actor = actor;
        this.description = description;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = status;
    }


    public UserStoryJpaId getId() {
        return id;
    }

    public String getActor() {
        return actor;
    }

    public String getDescription() {
        return description;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryJpa that = (UserStoryJpa) o;
        return Objects.equals(id, that.id) && Objects.equals(actor, that.actor) && Objects.equals(description, that.description) && Objects.equals(acceptanceCriteria, that.acceptanceCriteria) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actor, description, acceptanceCriteria, status);
    }
}
