package org.switch2022.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.switch2022.project.ddd.Repository;
import org.switch2022.project.mapper.ProjectDTO_DDD;
import org.switch2022.project.model.project.IProjectFactory;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;

@Service
public class ProjectService {
    @Autowired
    private final IProjectFactory projectFactory;
    @Autowired
    private final Repository<ProjectCode, ProjectDDD> projectRepository;

    public ProjectService(IProjectFactory projectFactory, Repository<ProjectCode, ProjectDDD> projectRepository) {

        if (projectFactory == null) {
            throw new IllegalArgumentException("projectFactory must not be null.");
        }
        if (projectRepository == null) {
            throw new IllegalArgumentException("projectRepository must not be null.");
        }

        this.projectFactory = projectFactory;
        this.projectRepository = projectRepository;
    }

    public boolean createProject (ProjectDTO_DDD projectDTO_ddd) {

        ProjectCode projectCode =  new ProjectCode(projectDTO_ddd.code);
        ProjectName projectName = new ProjectName(projectDTO_ddd.name);
        Description description = new Description(projectDTO_ddd.description);
        ProjectStatus projectStatus = ProjectStatus.Planned;
        TimePeriod timePeriod = new TimePeriod(projectDTO_ddd.StartDate, projectDTO_ddd.endDate);
        ProjectBudget projectBudget = new ProjectBudget(projectDTO_ddd.budget);
        ProjectSprintDuration projectSprintDuration = new ProjectSprintDuration(projectDTO_ddd.sprintDuration);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = new ProjectNumberOfPlannedSprints(projectDTO_ddd.numberOfPlannedSprints);

        ProjectDDD project = this.projectFactory.createProject(projectCode,
                                                               projectName,
                                                               description,
                                                               projectStatus,
                                                               timePeriod,
                                                               projectBudget,
                                                               projectSprintDuration,
                                                               projectNumberOfPlannedSprints);
        return this.projectRepository.save(project);
    }
}
