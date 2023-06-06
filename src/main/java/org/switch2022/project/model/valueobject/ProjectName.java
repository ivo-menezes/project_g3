package org.switch2022.project.model.valueobject;


import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class ProjectName implements ValueObject {

    private final String projectName;


    public ProjectName (String projectName) {
        if (projectName==null || projectName.isBlank() || projectName.isEmpty()) {
            throw new IllegalArgumentException("projectName cannot be null/blank/empty");
        }

        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return this.projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectName)) {
            return false;
        }
        ProjectName that = (ProjectName) o;
        return Objects.equals(projectName, that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName);
    }
}
