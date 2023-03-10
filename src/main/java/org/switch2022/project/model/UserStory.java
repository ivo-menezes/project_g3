package org.switch2022.project.model;

import java.util.Objects;

public class UserStory {

    /**
     * Acceptable user story statuses.
     */
    public enum Status {TODO, IN_PROGRESS, TESTING, DONE}

    private final String id;
    private final String actor;
    private final String text;
    private final String acceptanceCriteria;
    private Status status;
    /**
     * Public constructor to instantiate a user story.
     *
     * @param id                 alphanumeric user story id
     * @param actor              of user story
     * @param text               of user story
     * @param acceptanceCriteria of user story
     */
    public UserStory(String id, String actor, String text, String acceptanceCriteria) {
        if (id == null || id.isBlank() || id.isEmpty()) {
            throw new IllegalArgumentException("User Story ID must not be empty");
        }

        if (actor == null || actor.isBlank() || actor.isEmpty()) {
            throw new IllegalArgumentException("User Story actor must not be empty");
        }

        if (text == null || text.isBlank() || text.isEmpty()) {
            throw new IllegalArgumentException("User Story text must not be empty");
        }

        if (acceptanceCriteria == null || acceptanceCriteria.isBlank() || acceptanceCriteria.isEmpty()) {
            throw new IllegalArgumentException("User Story acceptance criteria must not be empty");
        }

        this.id = id;
        this.actor = actor;
        this.text = text;
        this.acceptanceCriteria = acceptanceCriteria;
        this.status = Status.TODO;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserStory userStory = (UserStory) o;
        return id.equals(userStory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
