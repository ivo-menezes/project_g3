package org.switch2022.project.mapper;

import org.switch2022.project.model.project.ProjectDDD;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapperDDD {

    /**
     * This method converts a projectDDD in a projectDTOForList_DDD
     * @param projectDDD
     * @return
     */
    private ProjectDTOForListDDD toDTO (ProjectDDD projectDDD) {

        ProjectDTOForListDDD projectDTOForList_DDD = new ProjectDTOForListDDD();
        projectDTOForList_DDD.projectCode = projectDDD.getProjectCode();
        projectDTOForList_DDD.projectName = projectDDD.getProjectName();
        projectDTOForList_DDD.description = projectDDD.getDescription();
        projectDTOForList_DDD.timePeriod = projectDDD.getTimePeriod();
        projectDTOForList_DDD.projectStatus = projectDDD.getProjectStatus();
        projectDTOForList_DDD.projectBudget = projectDDD.getProjectBudget();
        projectDTOForList_DDD.projectSprintDuration = projectDDD.getProjectSprintDuration();
        projectDTOForList_DDD.projectNumberOfPlannedSprints = projectDDD.getProjectNumberOfPlannedSprints();

        return projectDTOForList_DDD;
    }

    /**
     * This method accepts a project list which is converted in a list of ProjectDTO ("ProjectDTOForList_DDD") list
     * @param lsProject list of projects
     * @return a list of projects DTOs
     */
    public List<ProjectDTOForListDDD> toDTOList(List<ProjectDDD> lsProject) {

        List<ProjectDTOForListDDD> DTOList = new ArrayList<>();
        for (ProjectDDD projectDDD : lsProject) {
            ProjectDTOForListDDD dto = toDTO(projectDDD);
            DTOList.add(dto);
        }
        return DTOList;
    }

}
