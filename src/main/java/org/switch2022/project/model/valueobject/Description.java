package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


public class Description implements ValueObject, Serializable {

    private String description;

    public Description (String description) {

        if (description != null && !description.isBlank() && !description.isEmpty())
            this.description = description;
        else
            throw new IllegalArgumentException("Description must not be null");
    }

    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof Description) {
            Description description1 = (Description) object;

            if (this.description.equals(description1.description))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
