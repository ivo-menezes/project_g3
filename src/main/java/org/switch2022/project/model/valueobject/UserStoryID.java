package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

public class UserStoryID implements DomainId {

    private String id;

    public UserStoryID (String id) {
        if (id != null && !id.isBlank() && !id.isEmpty()) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID must not be null");
        }
    }

    @Override

    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof UserStoryID) {
            UserStoryID userStoryID = (UserStoryID) object;

            if (this.id.equals(userStoryID.id))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return id;
    }
}
