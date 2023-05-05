package org.switch2022.project.controller;

import org.springframework.stereotype.Controller;
import org.switch2022.project.mapper.SprintDTO_DDD;
import org.switch2022.project.service.SprintService;

@Controller
public class CreateSprintController {

    private final SprintService service;

    public CreateSprintController(SprintService service) {
        if (service == null) {
            throw new IllegalArgumentException("SprintService must not be null.");
        }
        this.service = service;
    }

    /**
     * Generate a sprint object from a sprintDTO as parameter.
     *
     * @param sprintDTO contains projectCode, sprintNumber, startDare, endDate.
     * @return boolean if sprint is successfully created.
     */
    public boolean createSprint(SprintDTO_DDD sprintDTO) {
        if (sprintDTO == null) {
            throw new IllegalArgumentException("SprintDTO must not be null.");
        }

        return service.createSprint(sprintDTO);
    }
}

