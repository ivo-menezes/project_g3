package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.UserStoryID;

import java.util.*;

@org.springframework.stereotype.Repository
public class UserStoryRepository implements Repository<UserStoryID, UserStoryDDD> {

    /**
     * Create DATA hashmap to link UserStoryID and UserStory.
     */
    private static final Map<UserStoryID, UserStoryDDD> DATA = new HashMap<>();

    /**
     *
     * @param id
     * @return
     */
    public boolean containsID(UserStoryID id) {
        return DATA.containsKey(id);
    }

    /**
     * Saves userStory if userStoryID is not yet in the DATA hashmap.
     * @param userStory
     * @return true if operation is successful, otherwise return false.
     */
    public boolean save(UserStoryDDD userStory) {
        UserStoryID userStoryID = userStory.identity();

        if (!containsID(userStoryID)) {
            DATA.put(userStoryID, userStory);
            return true;
        }
       return false;
    }

    /**
     * Creats an iterable with all values contained in the DATA hashmap.
     * @return
     */
    public Iterable<UserStoryDDD> findAll() {
        return DATA.values();
    }

    /**
     * Returns userStory if UserStoryID specified by user is contained in the DATA
     * hashmap. Else, return optional.
     * @param id
     * @return userStory or optional.
     */
    public Optional<UserStoryDDD> getByID(UserStoryID id) {
        if( !containsID(id) )
            return Optional.empty();
        else
            return Optional.of( DATA.get(id) );
    }

    /**
     * This method receives a list of UserStoryIDs (user Stories not done nor cancelled). Then it iterates over the input IDs and checks whether the DATA map contains a UserStoryDDD object for each ID. If true, then it adds the UserStoryDDD to the newly created openUserStoryList.
     * @param openUserStoryIDs which is a list of User Story IDs
     * @return a list of open User Stories
     */

    public List<UserStoryDDD> getOpenUserStories(List<UserStoryID> openUserStoryIDs) {

        List<UserStoryDDD> openUserStoryList = new ArrayList<>();
        for (UserStoryID id : openUserStoryIDs) {
            if (containsID(id)) {
                openUserStoryList.add(DATA.get(id));
            }
        }
        return openUserStoryList;
    }

}
