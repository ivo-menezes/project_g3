package org.switch2022.project.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SprintList {
    private List<Sprint> sprintList;

    public SprintList() {
        this.sprintList = new ArrayList<Sprint>();
    }

    public boolean createSprint(SprintDTO sprintDTO) {
        if (sprintDTO == null) {
            throw new IllegalArgumentException("Sprint DTO must not be empty");
        }
        Sprint sprint = new Sprint(sprintDTO.sprintNumber, sprintDTO.startDate, sprintDTO.endDate);
        return add(sprint);
    }

    public boolean hasSprint(Sprint sprint){
        boolean sprintExists = sprintList.contains(sprint);
        return sprintExists;
    }

    public boolean add(Sprint sprint){
        if (sprint == null) {
            throw new IllegalArgumentException("Sprint must not be empty");
        }
        boolean isAdded = false;
        if (!hasSprint(sprint)){
            this.sprintList.add(sprint);
            isAdded = true;
        }
        return isAdded;
    }
}
