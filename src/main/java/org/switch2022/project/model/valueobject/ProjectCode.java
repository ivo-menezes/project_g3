package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectCode implements DomainId, Serializable {

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

    @Override
    public int hashCode() {
        return Objects.hash(projectCode);
    }
}
