package org.switch2022.project.service;

import org.springframework.stereotype.Service;
import org.switch2022.project.mapper.SprintDTOController;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.service.irepositories.ISprintRepository;

import java.util.List;

@Service
public class SprintServiceDDD {

    private final ISprintFactory sprintFactory;

    private final ISprintRepository iSprintRepository;

    /**
     * Public constructor for SprintService.
     * @param sprintFactory a factory that implements ISprintFactory;
     * @param sprintRepository a repository that implements RepositoryJPA;
     */
    public SprintServiceDDD(ISprintFactory sprintFactory,
                              ISprintRepository sprintRepository){
        if (sprintFactory == null) {
            throw new IllegalArgumentException("sprintFactory cannot be null.");
        }
        if (sprintRepository == null) {
            throw new IllegalArgumentException("sprintRepository cannot be null.");
        }

        this.sprintFactory = sprintFactory;
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

        return this.iSprintRepository.save(sprint);
    }

    public List<SprintDDD> sprintList (ProjectCode projectCode){

        return iSprintRepository.findByProjectCode(projectCode);
    }

}
