package org.switch2022.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.switch2022.project.mapper.ProjectDTO_DDD;
import org.switch2022.project.service.ProjectService;

@Controller
public class CreateProjectControllerDDD {

    @Autowired
    private final ProjectService service;

    /**
     * Public constructor for CreateProjectController.
     * @param service
     */
    public CreateProjectControllerDDD(ProjectService service) {
        if (service == null) {
            throw new IllegalArgumentException("ProjectService must not be null.");
        }
        this.service = service;
    }

    /**
     * Creates a Project.
     * @param projectDTO_ddd a DTO with the information for the Project.
     * @return true if Project was successfully created, false otherwise.
     */
    public boolean createProject(ProjectDTO_DDD projectDTO_ddd) {
        if (projectDTO_ddd == null) {
            throw new IllegalArgumentException("ProjectDTO must not be null.");
        }

        return service.createProject(projectDTO_ddd);
    }
}
