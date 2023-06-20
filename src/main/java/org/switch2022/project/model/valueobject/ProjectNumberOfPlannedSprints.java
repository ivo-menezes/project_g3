package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class ProjectNumberOfPlannedSprints implements ValueObject {

    private final int projectNumberOfPlannedSprints;

    public ProjectNumberOfPlannedSprints(int projectNumberOfPlannedSprints) {
        if (projectNumberOfPlannedSprints < 0) {
            throw new IllegalArgumentException("numberOfPlannedSprints cannot be negative.");
        }
        this.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
    }

    public int getValue() {
        return this.projectNumberOfPlannedSprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectNumberOfPlannedSprints)) {
            return false;
        }
        ProjectNumberOfPlannedSprints that = (ProjectNumberOfPlannedSprints) o;
        return projectNumberOfPlannedSprints == that.projectNumberOfPlannedSprints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectNumberOfPlannedSprints);
    }

}
