package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTOMapper;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.service.irepositories.IProjectRepository;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SprintServiceDDD {

    private final ISprintFactory sprintFactory;

    private final ISprintRepository iSprintRepository;
    private final NewSprintDTOMapper newSprintDTOMapper;
    private final IProjectRepository projectRepository;

    /**
     * Public constructor for SprintService.
     * @param sprintFactory a factory that implements ISprintFactory;
     * @param sprintRepository a repository that implements RepositoryJPA;
     */
    public SprintServiceDDD(ISprintFactory sprintFactory,
                            ISprintRepository sprintRepository,
                            NewSprintDTOMapper newSprintDTOMapper,
                            IProjectRepository projectRepository){
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
        this.sprintFactory = sprintFactory;
        this.iSprintRepository = sprintRepository;
        this.newSprintDTOMapper = newSprintDTOMapper;
        this.projectRepository = projectRepository;
    }

    public int getNewSprintNumber(ProjectCode projectCode) {

        if(!projectRepository.existsByProjectCode(projectCode.toString())){
            throw new RuntimeException("There is no project with this code.");
        }
        List<SprintDDD> allSprintsInProject = iSprintRepository.findByProjectCode(projectCode);

        int newSprintNumber = allSprintsInProject.size();
        newSprintNumber = newSprintNumber + 1;
        return newSprintNumber;
    }

    /**
     * Creates a sprint and adds it to the sprintRepository.
     * @param sprintDTO a DTO with info to create the sprint with VOs, received from
     * the controller;
     * This method contemplates using a DTO carrying a status, meaning it can be
     * used for the DataLoader and other.
     * @return a savedSprint object, unless there is an error with the projectCode
     */
    public NewSprintDTO createSprint(NewSprintDTO sprintDTO) {
        SprintDDD sprint;
        sprintDTO.sprintID = sprintFactory.newSprintID(
                sprintDTO.projectCode, sprintDTO.sprintNumber);

        if(sprintDTO.status == null) {
            sprint = sprintFactory.createSprint(sprintDTO);
        }else {
            sprint = sprintFactory.createSprint(
                    sprintDTO.sprintID,
                    sprintDTO.timePeriod,
                    sprintDTO.status);
        }
         SprintDDD savedSprint = this.iSprintRepository.save(sprint);
        return newSprintDTOMapper.convertToDTO(savedSprint);
    }

    public List<NewSprintDTO> sprintList (ProjectCode projectCode){
        List<NewSprintDTO> newDTOList = new ArrayList<>();
         List<SprintDDD> allSprints = iSprintRepository.findByProjectCode(projectCode);

        for(SprintDDD sprintDDD : allSprints){
            newDTOList.add(newSprintDTOMapper.convertToDTO(sprintDDD));
        }
        return newDTOList;
    }

}
