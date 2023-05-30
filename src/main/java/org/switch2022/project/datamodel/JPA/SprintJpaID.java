package org.switch2022.project.datamodel.JPA;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
    public class SprintJpaID implements Serializable {

        private String projectCode;

        private int sprintNumber;

        public SprintJpaID(String projectCode, int sprintNumber) {
            this.projectCode = projectCode;
            this.sprintNumber = sprintNumber;
        }

    protected SprintJpaID() {

    }

    public String getProjectCode() {
            return projectCode;
        }

        public int getSprintNumber() { return sprintNumber; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SprintJpaID that = (SprintJpaID) o;
            return Objects.equals(projectCode, that.projectCode) && Objects.equals(sprintNumber, that.sprintNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(projectCode, sprintNumber);
        }
    }

