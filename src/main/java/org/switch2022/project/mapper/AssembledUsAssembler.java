package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.sprint.AssembledUS;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

@Component
public class AssembledUsAssembler {

    public AssembledUS assembledUserStory(UserStoryInSprint userStoryInSprint, UserStoryDDD userStoryDDD) {

        UserStoryNumber userStoryNumber = userStoryDDD.identity().getUserStoryNumber();

        ProjectCode projectCode = userStoryDDD.identity().getProjectCode();

        SprintNumber sprintNumber = userStoryInSprint.identity().getSprintID().getSprintNumber();

        UserStoryActor userStoryActor = userStoryDDD.getActor();

        Description userStoryDescription = userStoryDDD.getDescription();

        UserStoryAcceptanceCriteria userStoryAcceptanceCriteria = userStoryDDD.getAcceptanceCriteria();

        UserStoryStatus userStoryStatus = userStoryInSprint.getUserStoryInSprintStatus();

        UserStoryEffortEstimate userStoryEffortEstimate = userStoryInSprint.getUserStoryEffortEstimate();

        return new AssembledUS(userStoryNumber, projectCode, sprintNumber,
                userStoryActor, userStoryDescription, userStoryAcceptanceCriteria,
                userStoryStatus, userStoryEffortEstimate);
    }

}