package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTOController;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SprintServiceDDD {

    private final ISprintFactory sprintFactory;


    private final ISprintRepository iSprintRepository;


    private final Repository<ProjectCode, ProjectDDD> projectRepository;


    /**
     * Public constructor for SprintService.
     * @param sprintFactory a factory that implements ISprintFactory;
     * @param projectRepository a repository that implements Repository<ProjectCode, Project>;
     * @param sprintRepository a repository that implements RepositoryJPA;
     */
    public SprintServiceDDD(ISprintFactory sprintFactory,
                              ISprintRepository sprintRepository,
                              Repository<ProjectCode, ProjectDDD> projectRepository){
        if (sprintFactory == null) {
            throw new IllegalArgumentException("sprintFactory cannot be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("projectRepository cannot be null.");
        }
        if (sprintRepository == null) {
            throw new IllegalArgumentException("sprintRepository cannot be null.");
        }

        this.sprintFactory = sprintFactory;
        this.projectRepository = projectRepository;
        this.iSprintRepository = sprintRepository;
    }

    private SprintID newSprintID (SprintDTOController sprintDTO){
        int newNumber = iSprintRepository.findLastSprintNumber(sprintDTO.projectCode);
        SprintNumber number = new SprintNumber(newNumber+1);
        return new SprintID(sprintDTO.projectCode, number);
    }
    /**
     * Creates a sprint and adds it to the sprintRepository.
     * @param sprintDTO a DTO with info to create the sprint with VOs, received from
     * the controller;
     * @return a savedSprint object, unless there is an error with the projectCode
     */
    public SprintDDD createSprint(SprintDTOController sprintDTO) {

        SprintID newID = newSprintID(sprintDTO);
        SprintDDD sprint = sprintFactory.createSprint(newID, sprintDTO.timePeriod);

        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(sprintDTO.projectCode);

        if (projectOptional.isEmpty()) {
           throw new RuntimeException("ProjectCode is not valid!");
        }
        return this.iSprintRepository.save(sprint);
    }

    public List<SprintDDD> sprintList (ProjectCode projectCode){

        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(projectCode);

        if (projectOptional.isEmpty()) {
            throw new RuntimeException("ProjectCode is not valid!");
        }

        return iSprintRepository.findByProjectCode(projectCode);
    }

}
