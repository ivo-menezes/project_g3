package org.switch2022.project.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.sprint.AssembledUS;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssembledUsAssemblerTest {


    @Test
    public void testAssembledUserStory() {

        //Arrange

        UserStoryNumber userStoryNumber = new UserStoryNumber("US1");
        ProjectCode projectCode = new ProjectCode("PROJECT");
        SprintNumber sprintNumber = new SprintNumber(1);
        UserStoryActor userStoryActor = new UserStoryActor("Actor");
        Description userStoryDescription = new Description("Description");
        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria("Criteria 1");
        UserStoryStatus userStoryStatus = UserStoryStatus.TO_DO;
        UserStoryEffortEstimate effortEstimate = new UserStoryEffortEstimate(5.0);

        UserStoryID userStoryID = new UserStoryID(userStoryNumber,projectCode);
        SprintID sprintID = new SprintID(projectCode,sprintNumber);


        UserStoryInSprintID userStoryInSprintID=new UserStoryInSprintID(sprintID,userStoryID);

        UserStoryInSprint userStoryInSprint = new UserStoryInSprint(userStoryInSprintID,effortEstimate,userStoryStatus);

        UserStoryDDD userStoryDDD = new UserStoryDDD(userStoryID,userStoryActor,userStoryDescription,acceptanceCriteria,userStoryStatus);

        AssembledUsAssembler assembler = new AssembledUsAssembler();

        // Act
        AssembledUS assembledUS = assembler.assembledUserStory(userStoryInSprint,userStoryDDD);


        // Act & Assert
        assertEquals(userStoryNumber, assembledUS.getUserStoryNumber());
        assertEquals(projectCode, assembledUS.getProjectCode());
        assertEquals(sprintNumber, assembledUS.getSprintNumber());
        assertEquals(userStoryActor, assembledUS.getUserStoryActor());
        assertEquals(userStoryDescription, assembledUS.getUserStoryDescription());
        assertEquals(acceptanceCriteria, assembledUS.getUserStoryAcceptanceCriteria());
        assertEquals(userStoryStatus, assembledUS.getUserStoryStatus());
        assertEquals(effortEstimate, assembledUS.getUserStoryEffortEstimate());
    }
}