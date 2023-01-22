package org.switch2022.project.model;

public class Role {

    private final int code;
    private final String description;

    /**
     * Constructor to create a role object.
     * @param code
     * @param description
     */

    public Role (int code, String description) {
        this.code= code;
        this.description= description;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
