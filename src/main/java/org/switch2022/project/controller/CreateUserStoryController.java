package org.switch2022.project.controller;

import org.springframework.stereotype.Controller;
import org.switch2022.project.mapper.old.UserStoryDTO;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.UserStoryPriority;
import org.switch2022.project.service.UserStoryService;

@Controller
public class CreateUserStoryController {
    private final UserStoryService service;

    public CreateUserStoryController(UserStoryService service) {
        if (service == null) {
            throw new IllegalArgumentException("UserStoryService must not be null.");
        }
        this.service = service;
    }

    public boolean createUserStory(ProjectCode projectCode, UserStoryDTO userStoryDTO, UserStoryPriority priority) {
        if (projectCode == null) {
            throw new IllegalArgumentException("projectCode must not be null.");
        }

        if (userStoryDTO == null) {
            throw new IllegalArgumentException("userStoryDTO must not be null.");
        }

        if (priority == null) {
            throw new IllegalArgumentException("priority must not be null.");
        }


        return service.createUserStory(projectCode, userStoryDTO, priority);
    }
}
