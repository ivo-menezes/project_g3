package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductBacklog {
    private final List<UserStory> userStories = new ArrayList<>();
    private final List<UserStory> completedUserStories = new ArrayList<>();

    private final IFactoryUserStory factoryUserStory;

    public ProductBacklog(IFactoryUserStory factory) {
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factoryUserStory = factory;
    }

    /**
     * Checks if user story is already in product backlog (either not completed or completed).
     *
     * @param userStory to check
     * @return true if user story not already in product backlog, false otherwise
     */
    private boolean doesNotContain(UserStory userStory) {
        if (userStory == null) {
            throw new IllegalArgumentException("User Story must not be null");
        }

        boolean userStoryFound = userStories.contains(userStory) || completedUserStories.contains(userStory);
        return !userStoryFound;
    }

    /**
     * Tries to add user story to the product backlog.
     *
     * @param userStory to be added
     * @param priority  of the user story (defines position in product backlog)
     * @return true if user story successfully added, false otherwise
     */
    public boolean add(UserStory userStory, int priority) {
        if (userStory == null) {
            throw new IllegalArgumentException("User Story must not be null");
        }

        boolean userStoryAdded = false;

        int index = priority;
        if (index < 0 || index > this.userStories.size()) {
            index = this.userStories.size();
        }

        if (doesNotContain(userStory)) {
            this.userStories.add(index, userStory);
            userStoryAdded = true;
        }
        return userStoryAdded;
    }

    /**
     * Creates a new user story and adds it to the product backlog
     *
     * @param userStoryDTO containing user story information
     * @param priority     of user story
     * @return true if successfully created and added, false otherwise
     */
    public boolean createAndAddUserStory(UserStoryDTO userStoryDTO, int priority) {
        if (userStoryDTO == null) {
            throw new IllegalArgumentException("User Story DTO must not be null");
        }

        UserStory userStory = createUserStoryFromDTO(userStoryDTO);

        return add(userStory, priority);
    }

    /**
     * Creates user story from DTO.
     *
     * @param userStoryDTO to unpack
     * @return userStory
     */
    private UserStory createUserStoryFromDTO(UserStoryDTO userStoryDTO) {

        UserStory userStory = factoryUserStory.createUserStory(userStoryDTO.id,
                userStoryDTO.actor,
                userStoryDTO.text,
                userStoryDTO.acceptanceCriteria);

        return userStory;
    }

    /**
     * Retrieves a user story from the product backlog, given a userStoryID.
     * Only works for not completed user stories.
     * @param userStoryID is a string provided by the user.
     * @return user story if it is found, otherwise thrown an exception.
     */
    public UserStory getUserStory(String userStoryID) {
        boolean foundUserStory = false;
        UserStory userStoryToReturn = null;

        for (int i = 0; i < userStories.size() && !foundUserStory; i++) {
            UserStory userStory = userStories.get(i);
            if (Objects.equals(userStory.getId(), userStoryID)) {
                userStoryToReturn = userStory;
            }
            if (userStoryToReturn == null) {
                throw new NullPointerException("User story not found");
            }
        }
        return userStoryToReturn;
    }
}
