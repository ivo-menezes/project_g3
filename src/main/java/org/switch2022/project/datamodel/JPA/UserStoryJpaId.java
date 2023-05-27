package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserStoryJpaId implements Serializable {

    private final String projectCode;

    private final String userStoryNumber;

    public UserStoryJpaId(@NonNull String projectCode, @NonNull String userStoryNumber) {
        this.projectCode = projectCode;
        this.userStoryNumber = userStoryNumber;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryJpaId that = (UserStoryJpaId) o;
        return Objects.equals(projectCode, that.projectCode) && Objects.equals(userStoryNumber, that.userStoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, userStoryNumber);
    }
}
