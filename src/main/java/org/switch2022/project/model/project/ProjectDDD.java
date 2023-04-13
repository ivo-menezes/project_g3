package org.switch2022.project.model.project;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import java.util.ArrayList;
import java.util.List;

public class ProjectDDD implements AggregateRoot<ProjectCode> {

    private final ProjectCode projectCode;

    private final List<UserStoryID> productBacklog = new ArrayList<>();

    public ProjectDDD(ProjectCode projectCode) {
        this.projectCode = projectCode;
    }

    @Override
    public ProjectCode identity() {
        return this.projectCode;
    }

    public List<UserStoryID> getProductBacklog() {
        return List.copyOf(this.productBacklog);
    }

    public boolean addToProductBacklog(UserStoryID userStoryID, UserStoryPriority priority) {
        int position = priority.getValue();

        if (position < 0 || position > this.productBacklog.size()) {
            position = this.productBacklog.size();
        }

        if (productBacklog.contains(userStoryID)) {
            return false;
        } else {
            this.productBacklog.add(position, userStoryID);
            return true;
        }
    }
}
