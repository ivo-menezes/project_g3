package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class UserStoryAcceptanceCriteria implements ValueObject {

    private final String acceptanceCriteria;

    public UserStoryAcceptanceCriteria(String acceptanceCriteria) {

        if (acceptanceCriteria != null && !acceptanceCriteria.isBlank() && !acceptanceCriteria.isEmpty())
            this.acceptanceCriteria = acceptanceCriteria;
        else
            throw new IllegalArgumentException("UserStoryAcceptanceCriteria must not be null");
    }

    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof UserStoryAcceptanceCriteria) {
            UserStoryAcceptanceCriteria acceptanceCriteria1 = (UserStoryAcceptanceCriteria) object;

            return this.acceptanceCriteria.equals(acceptanceCriteria1.acceptanceCriteria);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.acceptanceCriteria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptanceCriteria);
    }
}


