package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.util.Objects;

public class ProjectCode implements DomainId {

    private final String projectCode;

    public ProjectCode(String projectCode) {

        if (projectCode == null || projectCode.isBlank()) {
            throw new IllegalArgumentException("Project code must not be null");
        } else {
            this.projectCode = projectCode;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        ProjectCode that = (ProjectCode) o;
        return projectCode.equals(that.projectCode);
    }

    @Override
    public String toString() {
        return projectCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }


}
