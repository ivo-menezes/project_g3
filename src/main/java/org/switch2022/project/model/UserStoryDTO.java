package org.switch2022.project.model;

import java.util.Objects;

public class UserStoryDTO {
    public String id;
    public String actor;
    public String text;
    public String acceptanceCriteria;
    public UserStory.Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryDTO that = (UserStoryDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
