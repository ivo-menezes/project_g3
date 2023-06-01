package org.switch2022.project.model.project;

import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * The class ProductBacklog stores a list of UserStoryID
 * representing the set of open (not cancelled or concluded) user stories
 * ordered by priority.
 */
class ProductBacklogDDD {

    private List<UserStoryID> openUserStories;

    protected ProductBacklogDDD() {
        this.openUserStories = new ArrayList<>();
    }

    protected ProductBacklogDDD(List<UserStoryID> userStoryIDs) {
        this.openUserStories = new ArrayList<>();
        this.openUserStories.addAll(userStoryIDs);
    }

    /**
     * Adds a UserStoryID to the list of open user stories with the given priority.
     * The priority is simply the position in the list.
     *
     * @param id       UserStoryID to store
     * @param priority UserStoryPriority that defines the position in the list
     * @return a new UserStoryPriority (position in which it was actually saved)
     */
    protected UserStoryPriority add(UserStoryID id, UserStoryPriority priority) {
        int priorityValue = priority.getValue();

        // priorityValue (given by user) : final 0-based index (position) in the backlog
        // 0 : initial size (last position)
        // 1 : 0 (first position)
        // n : n-1
        // initial size : initial size - 1 (penultimate position)
        // out-of-bounds : initial size (last position)

        int index = this.openUserStories.size(); // last position by default
        if (priorityValue > 0 && priorityValue <= this.openUserStories.size()) {
            index = priorityValue - 1;
        }

        if (openUserStories.contains(id)) {
            throw new IllegalArgumentException("UserStoryID already in project");
        }

        this.openUserStories.add(index, id);

        return new UserStoryPriority(index + 1);
    }

    /**
     * Returns a copy of the list of open user stories.
     *
     * @return List<UserStoryID> with IDs of open user stories
     */
    protected List<UserStoryID> getOpenUserStories() {
        return List.copyOf(this.openUserStories);
    }
}
