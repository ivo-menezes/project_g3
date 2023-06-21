package org.switch2022.project.controller.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.switch2022.project.mapper.*;
import org.switch2022.project.mapper.REST.*;
import org.switch2022.project.mapper.REST.InputUsInSprintStatusDTO;
import org.switch2022.project.mapper.REST.SprintRestDTO;
import org.switch2022.project.mapper.REST.SprintRestDTOMapper;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.sprint.UserStoryInSprint;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.SprintServiceDDD;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping(path="/projects/{projectCode}/sprints")
public class SprintController {

    private final SprintServiceDDD service;

    private final SprintRestDTOMapper mapper;
    private final UserStoryRestDtoMapper userStoryRestDtoMapper;


    public SprintController(SprintServiceDDD service, SprintRestDTOMapper mapper, UserStoryRestDtoMapper userStoryRestDtoMapper) {
        if(service == null){
            throw new IllegalArgumentException("Sprint Service cannot be null/nonexistent");
        }
        if(mapper == null){
            throw new IllegalArgumentException("Sprint Mapper cannot be null/nonexistent");
        }
        if(userStoryRestDtoMapper == null){
            throw new IllegalArgumentException("User Story Mapper cannot be null/nonexistent");
        }
        this.service = service;
        this.mapper = mapper;
        this.userStoryRestDtoMapper = userStoryRestDtoMapper;
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
            SprintID sprintID = updateSprintDomainDTO.sprintID;
            service.updateProductBacklogAndUserStoryStatus(sprintID);

            return new ResponseEntity<>(outputDTO, HttpStatus.OK);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(updateSprintInputDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updateUsInSprint")
    public ResponseEntity<InputUsInSprintStatusDTO> updateUsInSprintStatus(@RequestBody InputUsInSprintStatusDTO inputUsInSprintStatusDTO) {

        try {
            UpdateUsInSprintDomainDTO updateUsInSprintDomainDTO = mapper.uSInSprintToDomainDTO(inputUsInSprintStatusDTO);
            UpdateUsInSprintDomainDTO updatedDomainDTO = service.updateUsInSprintStatus(updateUsInSprintDomainDTO);
            InputUsInSprintStatusDTO outputDTO = mapper.uSInSprintToDataDTO(updatedDomainDTO);
            return new ResponseEntity<>(outputDTO, HttpStatus.OK);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(inputUsInSprintStatusDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{sprintNumber}/addUserStoryToSprintBacklog")
    public ResponseEntity<AddUsInSprintToBacklogDTO> addUSToOpenSprintBacklog(@RequestBody AddUsToSprintBacklogDTO restDto) {
        try {
            NewAddUsToSprintBacklogDTO newAddUsToSprintBacklogDTO = userStoryRestDtoMapper.toSprintBacklogDomainDTO(restDto);
            UserStoryInSprintDTO userStoryInSprintDTO = service.addUsToSprintBacklog(newAddUsToSprintBacklogDTO);
            AddUsInSprintToBacklogDTO addUsInSprintToBacklogDTO = userStoryRestDtoMapper.toSprintBacklogRestDTO(userStoryInSprintDTO);
            return new ResponseEntity<>(addUsInSprintToBacklogDTO, HttpStatus.ACCEPTED);

        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping( "{sprintNumber}/getSprintBacklog")
    public ResponseEntity<List<AssembledUSRestDto>> getSprintBacklog (@PathVariable ProjectCode projectCode,
                                                                      @PathVariable SprintNumber sprintNumber ) {
        try{
            SprintID sprintID = new SprintID(projectCode,sprintNumber);

            List<UserStoryInSprint> userStoryInSprintList = service.getUserStoryInSprintList(sprintID);
            List<NewAssembledUSDTO> assembledUSDTOList = service.createListOfAssembledUS(userStoryInSprintList);

            List<AssembledUSRestDto> restDtoList = new ArrayList<>();
            for (NewAssembledUSDTO newAssembledUSDTO : assembledUSDTOList) {
                restDtoList.add(mapper.assembledUSToRestDto(newAssembledUSDTO));
            }
            return new ResponseEntity<>(restDtoList,HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
