package org.switch2022.project.model;

import org.switch2022.project.mapper.SprintDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SprintList {
    private List<Sprint>sprintList;

    public SprintList() {
        this.sprintList = new ArrayList<Sprint>();
    }

    /**
     * Method createSprint with SprintDTO object as parameter
     * @param sprintDTO
     * @return true (isCreated) if the sprint is created successfully, otherwise returns false or throws exception
     */
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

    /**
     * isValidDate checks and compares the startDate and endDate in order to validate them
     * @param sprintDTO
     * @return true (isValid) if endDate is after startDate otherwise returns false
     */
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

    /**
     * Checks if a Sprint exists in the sprintList
     * @param sprint
     */
    public boolean hasSprint(Sprint sprint) {
        boolean contains = sprintList.contains(sprint);
        return contains;
    }

    /**
     * Adds a sprint to the sprintList
     * @param sprint
     * @return true if sprint is added successfully or false if already exists or is empty
     */
    public boolean add(Sprint sprint) {
        if (sprint == null) {
            throw new IllegalArgumentException("Sprint must not be empty");
        }
        boolean isAdded = false;
        if (!hasSprint(sprint)) {
            this.sprintList.add(sprint);
            isAdded = true;
        }
        return isAdded;
    }

    /**
     * Retrieves a sprint from the sprintList given a sprintNumber.
     * @param sprintNumber
     * @return true if sprint it is found, otherwise an exception is thrown.
     */
    public Sprint getSprint(int sprintNumber) {
        boolean foundSprint = false;
        Sprint sprintToReturn = null;

        for (int i = 0; i < sprintList.size() && !foundSprint; i++) {
            Sprint sprint = sprintList.get(i);
            if (sprint.getSprintNumber() == sprintNumber) {
                sprintToReturn = sprint;
                foundSprint = true;
            }
        }
        if (sprintToReturn == null) {
            throw new NullPointerException("sprint not found");
        }
        return sprintToReturn;
    }
}
