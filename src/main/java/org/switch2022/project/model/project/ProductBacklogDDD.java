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
     * The priority is simply the position (index) in the list.
     *
     * @param id       UserStoryID to store
     * @param priority UserStoryPriority that defines the position in the list
     * @return true if UserStoryID successfully added, false otherwise
     */
    protected boolean add(UserStoryID id, UserStoryPriority priority) {
        int position = priority.getValue();

        if (position < 0 || position > this.openUserStories.size()) {
            position = this.openUserStories.size();
        }

        if (openUserStories.contains(id)) {
            return false;
        } else {
            this.openUserStories.add(position, id);
            return true;
        }
    }

    /**
     * Returns a copy of the list of open user stories.
     *
     * @return List<UserStoryID> with IDs of open user stories
     */
    public List<UserStoryID> getOpenUserStories() {
        return List.copyOf(this.openUserStories);
    }


    public void setOpenUserStories(List<UserStoryID> openUserStories) {
        this.openUserStories = openUserStories;
    }
}
