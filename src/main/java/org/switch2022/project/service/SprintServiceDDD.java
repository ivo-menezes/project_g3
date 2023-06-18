package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTOMapper;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.userStory.UserStoryDDD;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.TimePeriod;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ISprintRepository;
import org.switch2022.project.service.irepositories.IUserStoryRepository;
import org.switch2022.project.utils.TimePeriodUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceDDD {

    private final ISprintFactory sprintFactory;

    private final ISprintRepository iSprintRepository;
    private final NewSprintDTOMapper newSprintDTOMapper;
    private final IProjectRepository projectRepository;
    private final IUserStoryRepository userStoryRepository;

    /**
     * Public constructor for SprintService.
     *
     * @param sprintFactory    a factory that implements ISprintFactory;
     * @param sprintRepository a repository that implements RepositoryJPA;
     */
    public SprintServiceDDD(ISprintFactory sprintFactory,
                            ISprintRepository sprintRepository,
                            NewSprintDTOMapper newSprintDTOMapper,
                            IProjectRepository projectRepository,
                            IUserStoryRepository userStoryRepository) {
        if (sprintFactory == null) {
            throw new IllegalArgumentException("SprintFactory cannot be null.");
        }
        if (sprintRepository == null) {
            throw new IllegalArgumentException("SprintRepository cannot be null.");
        }
        if (newSprintDTOMapper == null) {
            throw new IllegalArgumentException("NewSprintDTOMapper cannot be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("ProjectRepository cannot be null.");
        }
        if (userStoryRepository == null) {
            throw new IllegalArgumentException("UserStoryRepository cannot be null.");
        }
        this.sprintFactory = sprintFactory;
        this.iSprintRepository = sprintRepository;
        this.newSprintDTOMapper = newSprintDTOMapper;
        this.projectRepository = projectRepository;
        this.userStoryRepository = userStoryRepository;
    }

    public int getNewSprintNumber(ProjectCode projectCode) {

        if (!projectRepository.existsByProjectCode(projectCode.toString())) {
            throw new RuntimeException("There is no project with this code.");
        }
        List<SprintDDD> allSprintsInProject = iSprintRepository.findByProjectCode(projectCode);

        int newSprintNumber = allSprintsInProject.size();
        newSprintNumber = newSprintNumber + 1;
        return newSprintNumber;
    }

    /**
     * Creates a sprint and adds it to the sprintRepository, unless there is an overlap with the previous sprint
     *
     * @param sprintDTO a DTO with info to create the sprint with VOs, received from
     *                  the controller;
     *                  This method contemplates using a DTO carrying a status, meaning it can be
     *                  used for the DataLoader and other.
     * @return a savedSprint object, unless there is an error with the projectCode,
     * or an overlap with the previous sprint
     */
    @Transactional
    public NewSprintDTO createSprint(NewSprintDTO sprintDTO) {
        SprintDDD sprint;
        sprintDTO.sprintID = sprintFactory.newSprintID(
                sprintDTO.projectCode, sprintDTO.sprintNumber);

        if (sprintDTO.status == null) {
            sprint = sprintFactory.createSprint(sprintDTO);
        } else {
            sprint = sprintFactory.createSprint(
                    sprintDTO.sprintID,
                    sprintDTO.timePeriod,
                    sprintDTO.status);
        }

        //checking if there is a time period overlap between the project's last saved sprint, and the one being created
        if (hasTimePeriodOverlap(sprintDTO.projectCode, sprintDTO.timePeriod)) {
            throw new IllegalArgumentException("The time period of the new sprint overlaps with the last sprint");
        }
        //checking if the time period of the sprint being created is contained in respective project's time period
        if (!sprintIsContainedInProjectTimePeriod(sprintDTO.projectCode, sprintDTO.timePeriod)) {
            throw new IllegalArgumentException("The time period of the new sprint is not contained" +
                    " within the project's time period");
        }


        SprintDDD savedSprint = this.iSprintRepository.save(sprint);
        return newSprintDTOMapper.convertToDTO(savedSprint);
    }

    public List<NewSprintDTO> sprintList(ProjectCode projectCode) {
        List<NewSprintDTO> newDTOList = new ArrayList<>();
        List<SprintDDD> allSprints = iSprintRepository.findByProjectCode(projectCode);

        for (SprintDDD sprintDDD : allSprints) {
            newDTOList.add(newSprintDTOMapper.convertToDTO(sprintDDD));
        }
        return newDTOList;
    }

    public UpdateSprintDomainDTO updateStatusSprint(UpdateSprintDomainDTO updateSprintDomainDTO) {
        Optional<SprintDDD> sprintOptional = iSprintRepository.getByID(updateSprintDomainDTO.sprintID);
        if (sprintOptional.isEmpty()) {
            throw new IllegalArgumentException("Sprint id does not exist");
        }

        SprintDDD sprint = sprintOptional.get();
        sprint.setStatus(updateSprintDomainDTO.sprintStatus);
        iSprintRepository.replace(sprint);

        return updateSprintDomainDTO;
    }

    /**
     * Checks if a new sprint's time period overlaps with the time period of the last sprint in a project.
     *
     * @param projectCode         the project code to check for the last sprint
     * @param newSprintTimePeriod the time period of the new sprint to be checked for overlap
     * @return true if there is an overlap between the time periods, false otherwise
     */

    private boolean hasTimePeriodOverlap(ProjectCode projectCode, TimePeriod newSprintTimePeriod) {
        Optional<SprintDDD> lastSprintOptional = iSprintRepository.findLastSprintByProjectCode(projectCode);
        if (lastSprintOptional.isPresent()) {
            SprintDDD lastSprint = lastSprintOptional.get();
            TimePeriod lastSprintTimePeriod = lastSprint.getTimePeriod();

            return TimePeriodUtils.timePeriodsOverlap(lastSprintTimePeriod, newSprintTimePeriod);
        }

        return false;
    }

    /**
     * Checks if the specified sprint time period is contained within the project's time period.
     *
     * @param projectCode         the code of the project
     * @param newSprintTimePeriod the time period of the new sprint
     * @return true if the sprint time period is contained within the project's time period, false otherwise
     */
    private boolean sprintIsContainedInProjectTimePeriod(ProjectCode projectCode, TimePeriod newSprintTimePeriod) {
        Optional<ProjectDDD> projectOptional = projectRepository.getByID(projectCode);
        if (projectOptional.isPresent()) {
            ProjectDDD project = projectOptional.get();
            TimePeriod projectTimePeriod = project.getTimePeriod();

            return TimePeriodUtils.timePeriodContainsTimePeriod(projectTimePeriod, newSprintTimePeriod);
        }

        return false;
    }

    public boolean updateProductBacklogAndUserStoryStatus(SprintID sprintID) {
        ProjectCode projectCode = sprintID.getProjectCode();
        Optional<ProjectDDD> projectOptional = projectRepository.getByID(projectCode);
        Optional<SprintDDD> sprintOptional = iSprintRepository.getByID(sprintID);

        if (projectOptional.isPresent() && sprintOptional.isPresent()) {
            ProjectDDD project = projectOptional.get();
            SprintDDD sprint = sprintOptional.get();
            List<UserStoryID> listOfUserStoryWithStatusDone = sprint.listOfUserStoriesInSprintWithStatusDone();

            for (UserStoryID userStoryID : listOfUserStoryWithStatusDone) {
                Optional<UserStoryDDD> userStoryOptional = userStoryRepository.getByID(userStoryID);
                if (userStoryOptional.isPresent()) {
                    UserStoryDDD userStory = userStoryOptional.get();
                    userStory.setUserStoryStatus(UserStoryStatus.DONE);
                    userStoryRepository.replace(userStory);
                }
            }
            project.removeUserStoryIDs(listOfUserStoryWithStatusDone);
            projectRepository.replace(project);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a list of UserStoryInSprint of a given sprint in a given project.
     * @param sprintID contains projectCode and SprintNumber to retrieve the list
     * @return the list userStoryInSprintList.
     */
    public List<UserStoryInSprint> getUserStoryInSprintList(SprintID sprintID){
        Optional<SprintDDD> sprintOptional = iSprintRepository.findSprintBySprintID(sprintID);

        if (sprintOptional.isEmpty()) {
            throw new RuntimeException("Sprint with given number and projectCode does not exist.");
        }

        SprintDDD sprint = sprintOptional.get();

        List<UserStoryInSprint> userStoryInSprintList = sprint.getUserStoriesInSprintList();

        return userStoryInSprintList;
    }
}
