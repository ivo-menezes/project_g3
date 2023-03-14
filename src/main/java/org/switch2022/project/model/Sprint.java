package org.switch2022.project.model;


import java.util.*;

public class Sprint {
    private int sprintNumber;
    private Date startDate;
    private Date endDate;
    private List <UserStory> sprintBacklog = new ArrayList<>();

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
    public List<UserStoryDTO> createScrumBoardList(){
        List<UserStoryDTO> scrumBoard = new ArrayList<>();

        for(int index = 0; index < this.sprintBacklog.size(); index++){
            UserStory userStoryAtIndex = getUSAtIndex(index);
            String iD = userStoryAtIndex.getId();
            UserStory.Status status = userStoryAtIndex.getStatus();
            scrumBoard.add(create(iD, status));
        }
        return scrumBoard;
    }

    /***
     *Getting the US at the indicated index for the Scrum Board List
     * @param index
     * @return US
     */
    private UserStory getUSAtIndex(int index){
        UserStory userStoryAtIndex =this.sprintBacklog.get(index);
        return userStoryAtIndex;
    }
    private UserStoryDTO create (String iD, UserStory.Status status){
        UserStoryDTO usDTO = new UserStoryDTO();
        usDTO.id = iD;
        usDTO.status = status;
        return usDTO;
    }
}
