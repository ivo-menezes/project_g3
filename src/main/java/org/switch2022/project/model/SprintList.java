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
        boolean isCreated;
        if (sprintDTO == null) {
            throw new IllegalArgumentException("Sprint DTO must not be empty");
        }
        Sprint sprint = new Sprint(sprintDTO.sprintNumber, sprintDTO.startDate, sprintDTO.endDate);

        if(!isValidDate(sprintDTO)) {
            isCreated = false;
        }else{
            isCreated = add(sprint);
        }
        return isCreated;
    }

    public boolean isValidDate(SprintDTO sprintDTO){
        boolean isValid = true;

        for(int index = 0; index < this.sprintList.size();index ++) {
            Sprint sprint = this.sprintList.get(index);
            Date dateToCompare = sprint.getEndDate(), startDateDTO = sprintDTO.startDate, endDateDTO = sprintDTO.endDate;
            if (dateToCompare.after(startDateDTO) || dateToCompare.equals(startDateDTO) || dateToCompare.after(startDateDTO) && dateToCompare.before(endDateDTO)){
                isValid = false;
            } else if(startDateDTO.after(endDateDTO) || startDateDTO.equals(endDateDTO)) {
                    isValid = false;
                }
            }
            return isValid;
        }

    public boolean hasSprint(Sprint sprint){
        return sprintList.contains(sprint);
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
