package org.switch2022.project.datamodel.JPA;

import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public
class UserStoryInSprintIDJpa implements Serializable {

    private String projectCode;

    private int sprintNumber;

    private String userStoryNumber;

    protected UserStoryInSprintIDJpa() {}

    public UserStoryInSprintIDJpa(@NonNull String projectCode,
                                  @NonNull int sprintNumber,
                                  @NonNull String userStoryNumber) {
        this.projectCode = projectCode;
        this.sprintNumber = sprintNumber;
        this.userStoryNumber = userStoryNumber;
    }


    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public void setUserStoryNumber(String userStoryNumber) {
        this.userStoryNumber = userStoryNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryInSprintIDJpa that = (UserStoryInSprintIDJpa) o;
        return sprintNumber == that.sprintNumber && projectCode.equals(that.projectCode) && Objects.equals(userStoryNumber, that.userStoryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, sprintNumber, userStoryNumber);
    }
}
