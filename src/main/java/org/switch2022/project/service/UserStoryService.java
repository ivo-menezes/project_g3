package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryDTOForListDDD;
import org.switch2022.project.mapper.UserStoryMapperDDD;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserStoryService {

    private final IUserStoryFactory userStoryFactory;

    private final IUserStoryRepository userStoryRepository;

    private final Repository<ProjectCode, ProjectDDD> projectRepository;


    private UserStoryMapperDDD userStoryMapper;



    /**
     * Public constructor for UserStoryService.
     *
     * @param userStoryFactory    a factory that implements IUserStoryFactory
     * @param userStoryRepository a repository that implements Repository<UserStoryID, UserStory>
     * @param projectRepository   a repository that implements Repository<ProjectCode, Project>
     */
    public UserStoryService(IUserStoryFactory userStoryFactory,
                            IUserStoryRepository userStoryRepository,
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

        UserStoryNumber userStoryNumber = new UserStoryNumber(userStoryDTO.id);
        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);

        UserStoryActor actor = new UserStoryActor(userStoryDTO.actor);
        Description description = new Description(userStoryDTO.text);
        UserStoryAcceptanceCriteria criteria = new UserStoryAcceptanceCriteria(userStoryDTO.acceptanceCriteria);

        UserStoryDDD userStory = this.userStoryFactory.createUserStory(userStoryID, actor, description, criteria);

        UserStoryDDD savedUserStory = this.userStoryRepository.save(userStory);
        boolean savedUserStoryIsNotNull = savedUserStory != null;

        return savedUserStoryIsNotNull && project.addToProductBacklog(userStory.identity(), priority); // TODO: should return savedUserStory

    }

    /**
     * Sets the UserStoryMapperDDD for this UserStoryServer instance
     * @param userStoryMapper the UserStoryMapperDDD implementation to set
     */

    @Autowired
    public void setUserStoryMapper(UserStoryMapperDDD userStoryMapper) {
        this.userStoryMapper = userStoryMapper;
    }


    /**
     * Retrieves the productBacklog of a project
     * @param projectCode the code of the project to retrieve the productBacklog for
     * @return an Optional containing the product backlog as a list of UserStoryDTOs (named "UserStoryDTOForListDDD")
     */
    public Optional<List<UserStoryDTOForListDDD>> getProductBacklog(ProjectCode projectCode) {

        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(projectCode);

        if (projectOptional.isEmpty()) {
            return Optional.empty();
        }

        ProjectDDD project = projectOptional.get();

        List<UserStoryID> openUserStoryIDs = project.getProductBacklog();

        List<UserStoryDDD> openUserStoryList = new ArrayList<>();
        for (UserStoryID id : openUserStoryIDs) {
            if (this.userStoryRepository.containsID(id)) {
                Optional<UserStoryDDD> userStoryOptional = this.userStoryRepository.getByID(id);
                if (userStoryOptional.isEmpty()) {
                    return Optional.empty();
                }
                UserStoryDDD userStory = userStoryOptional.get();
                openUserStoryList.add(userStory);
            }
        }
        List<UserStoryDTOForListDDD> productBacklog = this.userStoryMapper.toDTOList(openUserStoryList);

        return Optional.of(productBacklog);
    }
}

