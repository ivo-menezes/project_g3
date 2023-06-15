package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintIDJpa;
import org.switch2022.project.datamodel.JPA.UserStoryInSprintJPA;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.UserStoryInSprintID;

@Component
public class UserStoryInSprintDataAssembler {

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

}
