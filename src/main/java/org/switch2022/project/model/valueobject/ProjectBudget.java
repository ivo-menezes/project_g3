package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class ProjectBudget implements ValueObject {

    private final float projectBudget;

    public ProjectBudget (float projectBudget) {
        if (projectBudget < 0) {
            throw new IllegalArgumentException("projectBudget cannot be negative");
        }
        this.projectBudget = projectBudget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectBudget)) return false;
        ProjectBudget that = (ProjectBudget) o;
        return projectBudget == that.projectBudget;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectBudget);
    }
}
