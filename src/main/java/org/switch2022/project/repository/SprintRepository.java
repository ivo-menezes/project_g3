package org.switch2022.project.repository;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.SprintID;

import java.util.*;

@org.springframework.stereotype.Repository
public class SprintRepository implements Repository<SprintID, SprintDDD> {

    /***
     * This creates the necessary hashmap, called sprintData, to link SprintID and Sprint.
     */
    private static final Map<SprintID, SprintDDD> sprintData = new HashMap<>();

    /***
     * This will check whether the HashMap already has a sprint with the id we pass
     * into this method.
     * @param id As SprintID object
     * @return boolean value of whether it contains the ID or not
     */
    public boolean containsID(SprintID id) {
        return sprintData.containsKey(id);
    }

    /***
     * This method will save the sprint to the Hashmap if there is none containing the
     * sprintID we have
     * @param sprint Object to save into the Repository
     * @return true if process is successful, false if the process fails.
     */
    public boolean save(SprintDDD sprint) {
        SprintID sprintID = sprint.identity();

        if(!containsID(sprintID)){
            sprintData.put(sprintID, sprint);
            return true;
        }
        return false;
    }

    /***
     * This method returns the collection of values of the hashmap,
     * by using the values() method, as an iterable object
     * @return iterable collection of Sprint values in the hashmap
     */
    public Iterable<SprintDDD> findAll() {
        return sprintData.values();
    }

    /***
     * This method will return a sprint if there is one in the hashmap
     * with the SprintID passed by the user. Otherwise, it will return optional
     * @param id of the SprintID Object Type
     * @return sprint optional object or empty optional object
     */
    public Optional<SprintDDD> getByID(SprintID id) {
        if (!containsID(id)) {
            return Optional.empty();
        } else {
            return Optional.of(sprintData.get(id));
        }
    }
    /**
     * Used to clear the memory store
     */
    public void clearRepository(){
        sprintData.clear();
    }
}
