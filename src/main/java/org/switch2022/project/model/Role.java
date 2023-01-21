package org.switch2022.project.model;

public class Role {

    private int code;
    private String description;

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

}
