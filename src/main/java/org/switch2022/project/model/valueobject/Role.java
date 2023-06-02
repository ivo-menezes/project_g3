package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class Role implements ValueObject {

    private final String role;

    public Role(String role) {
        if (role == null || role.isBlank() || role.isEmpty())
            throw new IllegalArgumentException(("roleName cannot be null/blank/empty"));
        if (!role.equals("Product Owner") && !role.equals("Scrum Master") && !role.equals("Team Member")
                && role.equals("Project Manager")) {
            throw new IllegalArgumentException(("Role is not valid"));
        }
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role that = (Role) o;
        return Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() { return Objects.hash(role);}

}