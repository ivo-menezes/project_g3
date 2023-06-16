package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.REST.SprintRestDTO;
import org.switch2022.project.mapper.REST.SprintRestDTOMapper;
import org.switch2022.project.mapper.UpdateSprintDTO;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
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

            return new ResponseEntity<>(sprintRestDTO, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(sprintDTOFromUI, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(sprintDTOFromUI, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /***
     * GET Request for a list of the sprints available for a specified project
     * @param projectCode of the project
     * @return list of sprints of the requested project
     */
    @GetMapping("")
    public ResponseEntity<List<SprintRestDTO>> retrieveSprintList(@PathVariable("projectCode") String projectCode){
        try{
            SprintRestDTO newDTOUI = new SprintRestDTO();
            newDTOUI.projectCode = projectCode;
            NewSprintDTO newControllerDTO = mapper.createProjectCode(newDTOUI);
            List<NewSprintDTO> allSprintsFromProject = service.sprintList(newControllerDTO.projectCode);
            List<SprintRestDTO> allSprints = mapper.getSprintList(allSprintsFromProject);
            return new ResponseEntity<>(allSprints, HttpStatus.OK);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("")
    public ResponseEntity<UpdateSprintDTO> updateStatusSprint(@RequestBody UpdateSprintDTO updateSprintInputDTO) {

        try {
            UpdateSprintDomainDTO updateSprintDomainDTO = mapper.toDomainDTO(updateSprintInputDTO);
            UpdateSprintDomainDTO changedDomainDTO = service.updateStatusSprint(updateSprintDomainDTO);
            UpdateSprintDTO outputDTO = mapper.toDataDTO(changedDomainDTO);

            return new ResponseEntity<>(outputDTO, HttpStatus.OK);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(updateSprintInputDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
