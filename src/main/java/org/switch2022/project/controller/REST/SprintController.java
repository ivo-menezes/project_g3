package org.switch2022.project.controller.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.switch2022.project.mapper.REST.SprintDTOMapper;
import org.switch2022.project.mapper.REST.SprintDTOUI;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
import org.switch2022.project.service.SprintServiceDDD;

@Controller
@RestController
public class SprintController {

    private final SprintServiceDDD service;

    private final SprintDTOMapper mapper;

    public SprintController(SprintServiceDDD service, SprintDTOMapper mapper) {
        if(service == null){
            throw new IllegalArgumentException("Sprint Service cannot be null/nonexistent");
        }
        if(mapper == null){
            throw new IllegalArgumentException("Sprint Mapper cannot be null/nonexistent");
        }
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(path="/sprints")
    public ResponseEntity<SprintDTOUI> createSprint(@RequestBody SprintDTOUI sprintDTOFromUI){
        try{
            SprintDTOController toController = mapper.createProjectCode(sprintDTOFromUI);
            sprintDTOFromUI.sprintNumber = generateSprintNumber(toController);
            SprintDTOController controllerDTO = mapper.toDomainDTO(sprintDTOFromUI);
            SprintDTOToController newToControllerDTO = service.createSprint(controllerDTO);
            SprintDTOUI sprintDTOUI = mapper.toRestDTO(newToControllerDTO);

            ResponseEntity<SprintDTOUI> response = new ResponseEntity<>(sprintDTOUI, HttpStatus.CREATED);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            ResponseEntity<SprintDTOUI> response = new ResponseEntity<>(sprintDTOFromUI, HttpStatus.BAD_REQUEST);
            return response;
        }
    }
    private int generateSprintNumber(SprintDTOController controllerDTO){
        return service.generateSprintNumber(controllerDTO.projectCode);
    }
}
