package org.switch2022.project.model.role;

public class Role {

    private final String name;

    /**
     * Constructor to create a role object.
     * @param name
     */

    public Role (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
