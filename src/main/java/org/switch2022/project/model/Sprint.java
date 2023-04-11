package org.switch2022.project.model;


import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryScrumBoardMapper;
import org.switch2022.project.model.userStory.UserStory;

import java.util.*;

public class Sprint {
    private int sprintNumber;
    private Date startDate;
    private Date endDate;
    private List <UserStory> sprintBacklog = new ArrayList<>();

    private Map<String, Double> effortEstimates = new HashMap<>();

    public Sprint(int sprintNumber, Date startDate, Date endDate){
        if(sprintNumber <= 0 || startDate == null || endDate == null){
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        this.sprintNumber = sprintNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return sprintNumber == sprint.sprintNumber && startDate.equals(sprint.startDate) && endDate.equals(sprint.endDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(sprintNumber, startDate, endDate);
    }

    /**
     * Get StartDate and EndDate methods
     * @return startDate and endDate respectively
     */
    public Date getStartDate(){return this.startDate;}
    public Date getEndDate(){return this.endDate;}

    /**
     * Add a user story to the sprint backlog.
     * @param userStory
     * @return true if the user story was added to the Sprint Backlog, or false otherwise.
     */
    public boolean addUserStoryToSprintBacklog (UserStory userStory) {
        boolean added=false;

        if(!this.existsUserStory(userStory)) {
            this.sprintBacklog.add(userStory);
            added=true;
        }
        return added;
    }

    /**
     * Checks if it contains the user story in the sprint backlog.
     * @param userStory
     * @return returns false if the user story does not exist, or true otherwise.
     */
    private boolean existsUserStory (UserStory userStory) {
        boolean exists=false;

        if (userStory == null) {
            throw new IllegalArgumentException("User Story must not be null");
        }
        if (sprintBacklog.contains(userStory)) {
            exists = true;
        }
        return exists;
    }
    public int getSprintNumber() {
        return this.sprintNumber;
    }

    /***
     * The method will create a list of UserStoryDTOs, where the only data required will be the US ID and respective status.
     * @return a list, containing the UserStoryDTOs in the SprintBacklog belonging to the current sprint.
     */
    public List<UserStoryDTO> viewScrumBoardList(){
        List<UserStoryDTO> scrumBoard = new ArrayList<>();
        UserStoryScrumBoardMapper userStoryScrumBoardMapper = new UserStoryScrumBoardMapper();

         for (UserStory userStory : sprintBacklog) {
            scrumBoard.add(userStoryScrumBoardMapper.toDTO(userStory));
        }
        return scrumBoard;
    }

    /**
     * Check if the effort value are in the Fibonacci sequence.
     * @param effort of user story in a sprint
     * @return true if effort value are in the Fibonacci sequence.
     */
    public boolean validEffortEstimate (double effort) {

        List<Double> validateEffortValues = new ArrayList<>(Arrays.asList(0.0, 0.5, 1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 20.0, 40.0));
        return validateEffortValues.contains(effort);
    }

    /**
     * Estimate effort of a user story in a sprint.
     *  @param userStory
     *  @param effort of user story in a sprint
     *  @return a HasMap with a user story id and respective effort value.
     */
    public boolean estimateEffortForUserStory ( UserStory userStory, double effort) {
        boolean effortSaved = false;
        if (!validEffortEstimate(effort)) {
            throw new IllegalArgumentException("This effort value is invalid");
        }
        if (existsUserStory(userStory)) {
            this.effortEstimates.put(userStory.getId(), effort);
            effortSaved = true;
        }

        return effortSaved;}
}
