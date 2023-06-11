package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.REST.SprintRestDTO;
import org.switch2022.project.mapper.REST.SprintRestDTOMapper;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.service.SprintServiceDDD;

import java.util.List;

@Controller
@RestController
@RequestMapping(path="/projects/{projectCode}/sprints")
public class SprintController {

    private final SprintServiceDDD service;

    private final SprintRestDTOMapper mapper;

    public SprintController(SprintServiceDDD service, SprintRestDTOMapper mapper) {
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
    public ResponseEntity<SprintRestDTO> createSprint(@RequestBody SprintRestDTO sprintDTOFromUI){
        try{
            NewSprintDTO newSprintDTO = mapper.createProjectCode(sprintDTOFromUI);
            sprintDTOFromUI.sprintNumber = service.getNewSprintNumber(newSprintDTO.projectCode);
            NewSprintDTO controllerDTO = mapper.toDomainDTO(sprintDTOFromUI);
            NewSprintDTO newToControllerDTO = service.createSprint(controllerDTO);
            SprintRestDTO sprintRestDTO = mapper.toRestDTO(newToControllerDTO);

            ResponseEntity<SprintRestDTO> response = new ResponseEntity<>(sprintRestDTO, HttpStatus.CREATED);
            return response;
        }catch (IllegalArgumentException e){
            ResponseEntity<SprintRestDTO> response = new ResponseEntity<>(sprintDTOFromUI, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }catch (Exception e){
            ResponseEntity<SprintRestDTO> response = new ResponseEntity<>(sprintDTOFromUI, HttpStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SprintRestDTO>> retrieveSprintList(@PathVariable("projectCode") String projectCode){
        try{
            SprintRestDTO newDTOUI = new SprintRestDTO();
            newDTOUI.projectCode = projectCode;
            NewSprintDTO newControllerDTO = mapper.createProjectCode(newDTOUI);
            List<NewSprintDTO> allSprintsFromProject = service.sprintList(newControllerDTO.projectCode);
            List<SprintRestDTO> allSprints = mapper.getSprintList(allSprintsFromProject);
            ResponseEntity<List<SprintRestDTO>> response = new ResponseEntity<>(allSprints, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
