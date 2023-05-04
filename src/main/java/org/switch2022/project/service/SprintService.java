package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.SprintDTO_DDD;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.sprint.ISprintFactory;
import org.switch2022.project.model.sprint.SprintDDD;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintID;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.Optional;

public class SprintService {

    @Autowired
    private final ISprintFactory sprintFactory;

    @Autowired
    private final Repository<ProjectCode, ProjectDDD> projectRepository;

    @Autowired
    private final Repository<SprintID, SprintDDD> sprintRepository;

    /**
     * Public constructor for SprintService.
     * @param sprintFactory a factory that implements ISprintFactory;
     * @param projectRepository a reporitory that implements Repository<ProjectCode, Project>;
     * @param sprintRepository a repository that implements Repository<SprintID, Sprint>;
     */
    public SprintService(ISprintFactory sprintFactory,
                         Repository<ProjectCode, ProjectDDD> projectRepository,
                         Repository<SprintID, SprintDDD> sprintRepository) {
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
        this.sprintRepository = sprintRepository;
    }

    /**
     * Creates a sprint and adds it to the sprintDepository.
     * @param sprintDTO a DTO with info to create the sprint;
     * @return true if sprint was successfully created and saved, false otherwise.
     */
    public boolean createSprint(SprintDTO_DDD sprintDTO) {

        ProjectCode projectCode = new ProjectCode(sprintDTO.projectCode);

        Optional<ProjectDDD> projectOptional = this.projectRepository.getByID(projectCode);

        //System.out.println("projectOptional = " + projectOptional);

        if (projectOptional.isEmpty()) {
            return false;
        }

        //ProjectDDD project = projectOptional.get();

        SprintNumber sprintNumber = new SprintNumber(sprintDTO.sprintNumber);
        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        TimePeriod timePeriod = new TimePeriod(sprintDTO.startDate, sprintDTO.endDate);

        SprintDDD sprint = this.sprintFactory.createSprint(sprintID,timePeriod);

        return this.sprintRepository.save(sprint);

    }

}
