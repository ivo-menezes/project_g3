package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.REST.SprintDTOMapper;
import org.switch2022.project.mapper.REST.SprintDTOUI;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
import org.switch2022.project.service.SprintServiceDDD;

import java.util.List;

@Controller
@RestController
@RequestMapping(path="/projects/{projectCode}/sprints")
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

    /***
     * Receives a POST request with data to create a sprint
     * @param sprintDTOFromUI with the data to create a sprint
     * @return a SprintDTO to send to the UI
     */
    @PostMapping("")
    public ResponseEntity<SprintDTOUI> createSprint(@RequestBody SprintDTOUI sprintDTOFromUI){
        try{
            SprintDTOController toController = mapper.createProjectCode(sprintDTOFromUI);
            sprintDTOFromUI.sprintNumber = generateSprintNumber(toController);
            SprintDTOController controllerDTO = mapper.toDomainDTO(sprintDTOFromUI);
            SprintDTOToController newToControllerDTO = service.createSprint(controllerDTO);
            SprintDTOUI sprintDTOUI = mapper.toRestDTO(newToControllerDTO);

            ResponseEntity<SprintDTOUI> response = new ResponseEntity<>(sprintDTOUI, HttpStatus.CREATED);
            return response;
        }catch (IllegalArgumentException e){
            ResponseEntity<SprintDTOUI> response = new ResponseEntity<>(sprintDTOFromUI, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }catch (Exception e){
            ResponseEntity<SprintDTOUI> response = new ResponseEntity<>(sprintDTOFromUI, HttpStatus.BAD_REQUEST);
            return response;
        }
    }
    private int generateSprintNumber(SprintDTOController controllerDTO){
        return service.generateSprintNumber(controllerDTO.projectCode);
    }
    @GetMapping("")
    public ResponseEntity<List<SprintDTOUI>> retrieveSprintList(@PathVariable("projectCode") String projectCode){
        try{
            SprintDTOUI newDTOUI = new SprintDTOUI();
            newDTOUI.projectCode = projectCode;
            SprintDTOController newControllerDTO = mapper.createProjectCode(newDTOUI);
            List<SprintDTOToController> allSprintsFromProject = service.sprintList(newControllerDTO.projectCode);
            List<SprintDTOUI> allSprints = mapper.getSprintList(allSprintsFromProject);
            ResponseEntity<List<SprintDTOUI>> response = new ResponseEntity<>(allSprints, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
