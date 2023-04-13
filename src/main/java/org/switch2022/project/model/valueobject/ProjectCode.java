package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

public class ProjectCode implements DomainId {

    private String projectCode;

    public ProjectCode(String projectCode) {

        if (projectCode != null && !projectCode.isBlank() && !projectCode.isEmpty()) {
            this.projectCode = projectCode;
        } else {
            throw new IllegalArgumentException("Project code must not be null");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof ProjectCode) {
            ProjectCode projectCode = (ProjectCode) object;

            if (this.projectCode.equals(projectCode.projectCode))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return projectCode;
    }
}
