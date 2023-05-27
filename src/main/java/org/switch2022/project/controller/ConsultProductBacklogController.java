package org.switch2022.project.controller;

import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.mapper.UserStoryMapper;
import org.switch2022.project.model.*;
import org.switch2022.project.model.project.Project;
import org.switch2022.project.model.userStory.old.UserStory;

import java.util.List;

public class ConsultProductBacklogController {

    private final ProjectList projectList;

    private final UserStoryMapper userStoryMapper;

    /**
     * Constructor that accepts a project list and a mapper
     * @param projectList  to retrieve the intended project
     * @param userStoryMapper to convert a list of user stories in a list of user story DTOs.
     */
    public ConsultProductBacklogController(ProjectList projectList, UserStoryMapper userStoryMapper) {

        if(projectList == null) {
            throw new IllegalArgumentException("Project List must not be null");
        }

        if(userStoryMapper == null) {
            throw new IllegalArgumentException("Mapper must not be null");
        }

        this.projectList = projectList;
        this.userStoryMapper = userStoryMapper;
    }

    /**
     * This method returns the product backlog (User stories yet to be implemented) of project.
     * @param projectCode to identify the intended project
     * @return the product backlog which is a list of user story DTOs
     */

    public List<UserStoryDTO> getProductBacklog (int projectCode){

        Project project = projectList.getProject(projectCode);
        ProductBacklog productBacklog = project.getProductBacklog();
        List<UserStory> userStoryList = productBacklog.getUserStoryList();

        return userStoryMapper.toDTOList(userStoryList);
    }
}
