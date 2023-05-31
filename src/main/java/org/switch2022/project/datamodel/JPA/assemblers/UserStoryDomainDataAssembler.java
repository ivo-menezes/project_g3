package org.switch2022.project.datamodel.JPA.assemblers;

import org.springframework.stereotype.Component;
import org.switch2022.project.datamodel.JPA.UserStoryJpa;
import org.switch2022.project.datamodel.JPA.UserStoryJpaId;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;

@Component
public class UserStoryDomainDataAssembler {

    public UserStoryJpa toData(UserStoryDDD userStory) {

        UserStoryID userStoryID = userStory.identity();
        String projectCode = userStoryID.getProjectCode().toString();
        String userStoryNumber = userStoryID.getUserStoryNumber().toString();
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(projectCode, userStoryNumber);

        String actor = userStory.getActor().toString();
        String description = userStory.getDescription().toString();
        String criteria = userStory.getAcceptanceCriteria().toString();
        String status = userStory.getStatus().toString();

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryJpaId, actor, description, criteria, status);

        return userStoryJpa;
    }

    public UserStoryDDD toDomain(UserStoryJpa userStoryJpa) {

        UserStoryJpaId userStoryJpaId = userStoryJpa.getId();
        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryJpaId.getUserStoryNumber());
        ProjectCode projectCode = new ProjectCode(userStoryJpaId.getProjectCode());
        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);

        UserStoryActor userStoryActor = new UserStoryActor(userStoryJpa.getActor());
        Description description = new Description(userStoryJpa.getDescription());
        UserStoryAcceptanceCriteria acceptanceCriteria = new UserStoryAcceptanceCriteria(userStoryJpa.getAcceptanceCriteria());
        UserStoryStatus status = UserStoryStatus.valueOf(userStoryJpa.getStatus());

        UserStoryDDD userStory = new UserStoryDDD(userStoryID, userStoryActor, description, acceptanceCriteria, status);

        return userStory;
    }

    public UserStoryJpaId convertToJpaId(UserStoryID domainId) {
        String projectCode = domainId.getProjectCode().toString();
        String userStoryNumber = domainId.getUserStoryNumber().toString();
        UserStoryJpaId userStoryJpaId = new UserStoryJpaId(projectCode, userStoryNumber);
        return userStoryJpaId;
    }
}
