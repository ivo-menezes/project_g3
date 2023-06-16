package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.SprintJPA;
import org.switch2022.project.datamodel.JPA.SprintJpaID;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.model.sprint.SprintBacklog;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
    public class SprintAssemblerData {

    /**
     * Inject UserStoryInSprintDataAssembler so that it is possible to create a
     * List<UserStoryInSprintJPA> sprintBacklogJPA from the sprintBacklog of a sprint.
     */
    private final UserStoryInSprintDataAssembler userStoryInSprintDataAssembler;

    public SprintAssemblerData(UserStoryInSprintDataAssembler userStoryInSprintDataAssembler) {
        this.userStoryInSprintDataAssembler = userStoryInSprintDataAssembler;
    }
    public SprintJPA toData(SprintDDD sprintDDD) {

        SprintID sprintID = sprintDDD.identity();
        String projectCode = sprintID.getProjectCode().toString();
        String sprintStatus = sprintDDD.getSprintStatus().toString();
        // Get the underlying int value
        int sprintNumber = sprintID.getSprintNumber().getSprintNumber();
        SprintJpaID sprintJpaID = new SprintJpaID(projectCode, sprintNumber);

        TimePeriod timePeriod = sprintDDD.getTimePeriod();
        Date startDate = timePeriod.getStartDate();
        Date endDate = timePeriod.getEndDate();

        SprintBacklog sprintBacklog = sprintDDD.getSprintBacklog();
        List<UserStoryInSprintJPA> sprintBacklogJPA = new ArrayList<>();


         //create a List<UserStoryInSprintJPA> sprintBacklogJPA from the sprintBacklog
         //of a sprint:


        if (sprintBacklog.getUserStoriesInSprintList().size() > 0) {
            for (int i = 0; i < sprintBacklog.getUserStoriesInSprintList().size(); i++) {

                UserStoryInSprint userStoryInSprint = sprintBacklog.getUserStoriesInSprintList().get(i);

                UserStoryInSprintJPA userStoryInSprintJPA = userStoryInSprintDataAssembler.toData(userStoryInSprint);
                sprintBacklogJPA.add(userStoryInSprintJPA);
            }
        }

        return new SprintJPA(sprintJpaID, startDate, endDate,
                sprintStatus, sprintBacklogJPA);
        }

    public SprintDDD toDomain(SprintJPA sprintJPA) {

        SprintJpaID sprintJpaID = sprintJPA.getSprintID();
        SprintNumber sprintNumber = new SprintNumber(sprintJpaID.getSprintNumber());
        ProjectCode projectCode = new ProjectCode(sprintJpaID.getProjectCode());
        SprintStatus sprintStatus = SprintStatus.valueOf(sprintJPA.getSprintStatus());
        SprintID sprintID = new SprintID(projectCode, sprintNumber);

        TimePeriod timePeriod = new TimePeriod(sprintJPA.getStartDate(), sprintJPA.getEndDate());

        return new SprintDDD(sprintID, timePeriod, sprintStatus);
        }

    /***
     * Converts the SprintID into the SprintJPAID, which is the equivalent,
     * but with primitive values.
     * @param sprintID the domain entity of our business
     * @return SprintJPAID with primitive data
     */
    public SprintJpaID convertToSprintJpaID(SprintID sprintID) {
        ProjectCode projectCode = sprintID.getProjectCode();
        String projectCodeString = projectCode.toString();
        int sprintNumber = sprintID.getSprintNumber().getSprintNumber();
        return new SprintJpaID(projectCodeString, sprintNumber);
    }
}
