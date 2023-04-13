package org.switch2022.project.service;

import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.UserStoryDTO;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;

import java.util.Optional;

public class UserStoryService {
    private final IUserStoryFactory userStoryFactory;
    private final Repository<UserStoryID, UserStoryDDD> userStoryRepository;
    private final Repository<ProjectCode, ProjectDDD> projectRepository;

    /**
     * Public constructor for UserStoryService.
     *
     * @param userStoryFactory    a factory that implements IUserStoryFactory
     * @param userStoryRepository a repository that implements Repository<UserStoryID, UserStory>
     * @param projectRepository   a repository that implements Repository<ProjectCode, Project>
     */
    public UserStoryService(IUserStoryFactory userStoryFactory,
                            Repository<UserStoryID, UserStoryDDD> userStoryRepository,
                            Repository<ProjectCode, ProjectDDD> projectRepository) {

        if (userStoryFactory == null) {
            throw new IllegalArgumentException("userStoryFactory must not be null.");
        }

        if (userStoryRepository == null) {
            throw new IllegalArgumentException("userStoryRepository must not be null.");
        }

        if (projectRepository == null) {
            throw new IllegalArgumentException("projectRepository must not be null.");
        }

        this.userStoryFactory = userStoryFactory;
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a UserStory and adds it to the userStoryRepository.
     *
     * @param projectCode  of the Project to which the user story belongs
     * @param userStoryDTO a DTO with the information for the UserStory
     * @param priority     the priority of the UserStory (position in the product backlog of Project)
     * @return true if UserStory was successfully created and saved, false otherwise
     */
    public boolean createUserStory(ProjectCode projectCode,
                                   UserStoryDTO userStoryDTO,
                                   UserStoryPriority priority) {

        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(projectCode);

        if (projectOptional.isEmpty()) {
            return false;
        }

        ProjectDDD project = projectOptional.get();

        UserStoryDDD userStory = this.userStoryFactory.createUserStory(userStoryDTO);

        return this.userStoryRepository.save(userStory) && project.addToProductBacklog(userStory.identity(), priority);

    }
}
