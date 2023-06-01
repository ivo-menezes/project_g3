package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
import org.switch2022.project.mapper.NewUserStoryInfoDTOMapper;
import org.switch2022.project.mapper.UserStoryDTOForListDDD;
import org.switch2022.project.mapper.UserStoryMapperDDD;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.userStory.IUserStoryFactory;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryID;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.service.irepositories.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserStoryService {

    private final IUserStoryFactory userStoryFactory;

    private final IUserStoryRepository userStoryRepository;

    private final Repository<ProjectCode, ProjectDDD> projectRepository;

    private final NewUserStoryInfoDTOMapper userStoryInfoDTOMapper;
    private UserStoryMapperDDD userStoryMapper;


    /**
     * Public constructor for UserStoryService.
     *
     * @param userStoryFactory    a factory that implements IUserStoryFactory
     * @param userStoryRepository a repository that implements IUserStoryRepository
     * @param projectRepository   a repository that implements Repository<ProjectCode, Project>
     */
    public UserStoryService(IUserStoryFactory userStoryFactory,
                            IUserStoryRepository userStoryRepository,
                            Repository<ProjectCode, ProjectDDD> projectRepository,
                            NewUserStoryInfoDTOMapper userStoryInfoDTOMapper) {

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
        this.userStoryInfoDTOMapper = userStoryInfoDTOMapper;
    }

    /**
     * Creates a UserStory and adds it to the userStoryRepository and the ProductBacklog of the respective Project.
     *
     * @param infoDTO a NewUserStoryInfoDTO with the information for the new UserStory
     * @return the saved UserStory entity
     */
    public NewUserStoryInfoDTO createUserStory(NewUserStoryInfoDTO infoDTO) {

        // create the new UserStory
        UserStoryDDD newUserStory = userStoryFactory.createUserStory(infoDTO);

        // get corresponding project, throw exception if it does not exist
        ProjectCode projectCode = newUserStory.identity().getProjectCode();
        System.out.println(projectCode.toString());
        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(projectCode);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("project with given projectCode does not exist");
        }

        ProjectDDD project = projectOptional.get();

        // save UserStory to repository and its ID to the product backlog of project
        UserStoryDDD savedUserStory = this.userStoryRepository.save(newUserStory);
        UserStoryPriority attributedPriority = project.addToProductBacklog(newUserStory.identity(), infoDTO.priority);

        // save the updated project
        projectRepository.save(project);

        NewUserStoryInfoDTO outboundDto = userStoryInfoDTOMapper.toDto(savedUserStory);
        outboundDto.priority = attributedPriority;
        return outboundDto;
    }

    /**
     * Sets the UserStoryMapperDDD for this UserStoryServer instance
     *
     * @param userStoryMapper the UserStoryMapperDDD implementation to set
     */

    @Autowired
    public void setUserStoryMapper(UserStoryMapperDDD userStoryMapper) {
        this.userStoryMapper = userStoryMapper;
    }


    /**
     * Retrieves the productBacklog of a project
     *
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

