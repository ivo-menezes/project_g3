package org.switch2022.project.model;

public class Role {

    private final String description;

    /**
     * Constructor to create a role object.
     * @param description
     */

    public Role (String description) {
        this.description= description;
    }

    public String getDescription() {
        return this.description;
    }
}
