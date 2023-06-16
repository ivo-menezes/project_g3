package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintIDJpa;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.*;

@Component
public class UserStoryInSprintDataAssembler {

    /**
     * Coverts UserStoryInSprint to UserStoryInSprintJPA
     * @param userStoryInSprint to be converted
     * @return the converted UserStoryInSprintJPA
     */
    public UserStoryInSprintJPA toData(UserStoryInSprint userStoryInSprint) {

        UserStoryInSprintID userStoryInSprintID = userStoryInSprint.identity();

        String projectCode = userStoryInSprintID.getUserStoryID().
                getProjectCode().toString();

        int sprintNumber = userStoryInSprintID.getSprintID().
                getSprintNumber().getSprintNumber();

        String userStoryNumber = userStoryInSprintID.getUserStoryID().
                getUserStoryNumber().toString();

        UserStoryInSprintIDJpa userStoryInSprintIDJpa = new UserStoryInSprintIDJpa(
                projectCode, sprintNumber, userStoryNumber);

        Double userStoryEffortEstimate = userStoryInSprint.getUserStoryEffortEstimate().
                getUserStoryEffortEstimate();

        String userStoryInSprintStatus = String.valueOf(userStoryInSprint.
                getUserStoryInSprintStatus());

        return new UserStoryInSprintJPA(userStoryInSprintIDJpa,
                userStoryEffortEstimate, userStoryInSprintStatus);

    }

    public UserStoryInSprint toDomain(UserStoryInSprintJPA userStoryInSprintJPA) {

        UserStoryInSprintIDJpa userStoryInSprintIDJPA = userStoryInSprintJPA.getID();
        ProjectCode projectCode = new ProjectCode(userStoryInSprintIDJPA.getProjectCode());
        SprintNumber sprintNumber = new SprintNumber(userStoryInSprintIDJPA.getSprintNumber());
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryInSprintIDJPA.getUserStoryNumber());
        SprintID sprintID = new SprintID(projectCode,sprintNumber);
        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);
        UserStoryInSprintID userStoryInSprintID = new UserStoryInSprintID(sprintID, userStoryID);
        UserStoryEffortEstimate userStoryEffortEstimate = new UserStoryEffortEstimate(
                userStoryInSprintJPA.getUserStoryEffortEstimate());
        UserStoryStatus userStoryInSprintStatus = UserStoryStatus.valueOf(
                userStoryInSprintJPA.getUserStoryInSprintStatus());


        return new UserStoryInSprint(userStoryInSprintID, userStoryEffortEstimate,
                userStoryInSprintStatus);
    }

}
