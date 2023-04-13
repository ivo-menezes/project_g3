package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

public class UserStoryPriority implements ValueObject {

    final private int priority;

    public UserStoryPriority(int priority){
        if(priority >= 0){
            this.priority = priority;
        }else{
            throw new IllegalArgumentException("Priority must not be below 0.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserStoryPriority)) {
            return false;
        }
        UserStoryPriority that = (UserStoryPriority) o;
        return priority == that.priority;
    }
}
