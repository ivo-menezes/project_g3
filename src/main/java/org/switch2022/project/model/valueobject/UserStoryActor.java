package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class UserStoryActor implements ValueObject {

    private final String actor;

    public UserStoryActor (String actor) {
        if (actor == null || actor.isBlank()) {
            throw new IllegalArgumentException("User Story actor must not be empty");
        }
        this.actor = actor;
    }

    @Override
    public String toString() {
        return this.actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStoryActor that = (UserStoryActor) o;
        return Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actor);
    }
}
