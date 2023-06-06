package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.io.Serializable;
import java.util.Objects;

public class SprintID implements DomainId, Serializable {



    private ProjectCode projectCode;



    private SprintNumber sprintNumber;

    /**
     * Constructor for SprintID.
     * @param projectCode is the project code of the sprint.
     * @param sprintNumber is the number of the sprint in the project.
     */
    public SprintID (ProjectCode projectCode, SprintNumber sprintNumber){
        if (projectCode == null) {
            throw new IllegalArgumentException("Project code must not be null");
        }

        if (sprintNumber == null) {
            throw new IllegalArgumentException("Sprint number must not be null");
        }

        this.projectCode = projectCode;
        this.sprintNumber = sprintNumber;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof SprintID) {
            SprintID sprintID = (SprintID) object;

            if (this.projectCode.equals(sprintID.projectCode) && this.sprintNumber.equals(sprintID.sprintNumber))
                return true;
        }
        return false;
    }
    public ProjectCode getProjectCode() {
        return projectCode;
    }
    public SprintNumber getSprintNumber() {

        return sprintNumber;
    }
    @Override
    public int hashCode() {
        return Objects.hash(projectCode, sprintNumber);
    }
}
