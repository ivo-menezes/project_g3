package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class ProjectSprintDuration implements ValueObject {

    private final int sprintDuration;

    public ProjectSprintDuration(int sprintDuration) {
        if (sprintDuration < 0 || sprintDuration > 4) {
            throw new IllegalArgumentException("sprintDuration cannot have negative values and last longer than four weeks.");
        }
        this.sprintDuration = sprintDuration;
    }

    public int getValue() {
        return this.sprintDuration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectSprintDuration)) {
            return false;
        }
        ProjectSprintDuration that = (ProjectSprintDuration) o;
        return sprintDuration == that.sprintDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintDuration);
    }


}
