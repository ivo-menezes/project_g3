package org.switch2022.project.controller.old;

import org.springframework.stereotype.Controller;
import org.switch2022.project.mapper.NewUserStoryInfoDTO;
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

    public NewUserStoryInfoDTO createUserStory(NewUserStoryInfoDTO infoDTO) throws Exception {

        if (infoDTO == null) {
            throw new IllegalArgumentException("NewUserStoryDTO must not be null.");
        }

        return service.createUserStory(infoDTO);
    }
}
