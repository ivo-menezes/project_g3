package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

public class UserStoryActor implements ValueObject {

    private String userStoryActor;

    public UserStoryActor (String userStoryActor) {
        if (userStoryActor == null || userStoryActor.isBlank() || userStoryActor.isEmpty()) {
            throw new IllegalArgumentException("User Story actor must not be empty");
        }
        this.userStoryActor = userStoryActor;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;}

        if (object instanceof UserStoryActor) {
            UserStoryActor userStoryActor1 = (UserStoryActor) object;

            if (this.userStoryActor.equals(userStoryActor1.userStoryActor)) {
                return true;}
        }
        return false;
    }

    @Override
    public String toString() {
        return this.userStoryActor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryActor);
    }
}
