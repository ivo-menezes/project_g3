package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.io.Serializable;
import java.util.Objects;

public class SprintID implements DomainId, Serializable {


    private final ProjectCode projectCode;


    private final SprintNumber sprintNumber;

    /**
     * Constructor for SprintID.
     *
     * @param projectCode  is the project code of the sprint.
     * @param sprintNumber is the number of the sprint in the project.
     */
    public SprintID(ProjectCode projectCode, SprintNumber sprintNumber) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SprintID sprintID = (SprintID) o;
        return projectCode.equals(sprintID.projectCode) && sprintNumber.equals(sprintID.sprintNumber);
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
