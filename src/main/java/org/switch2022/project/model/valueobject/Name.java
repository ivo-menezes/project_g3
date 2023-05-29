package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class Name implements ValueObject, Serializable {

    private final String name;

    public Name(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null/blank/empty");
        }
        this.name = name;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if(!(o instanceof Name)) return false;
        Name that = (Name) o;
        return Objects.equals(name,that.name);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(name);
    }
}
