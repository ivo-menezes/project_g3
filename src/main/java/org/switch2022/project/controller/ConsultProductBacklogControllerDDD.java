package org.switch2022.project.controller;

import org.springframework.stereotype.Controller;
import org.switch2022.project.service.UserStoryService;
@Controller
public class ConsultProductBacklogControllerDDD{

    private final UserStoryService service;

    public ConsultProductBacklogControllerDDD(UserStoryService service) {
        if (service == null) {
            throw new IllegalArgumentException("UserStoryService must not be null.");
        }
        this.service = service;
    }

    /*public Optional<List<UserStoryDTOForListDDD>> getProjectBacklog(ProjectCode projectCode) {
        if (projectCode == null) {
            throw new IllegalArgumentException("projectCode must not be null.");
        }
        Optional<List<UserStoryDTOForListDDD>> productBacklogOptional = service.getProductBacklog(projectCode);
        return productBacklogOptional;
    }*/
}
