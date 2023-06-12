package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.ArrayList;
import java.util.List;

@Component
public class SprintRestDTOMapper {

    /***
     * This method will convert the received DTO with primitive data to another one with Value Objects.
     * This will be consumed in the Sprint Services, to have the SprintDDD created.
     * Note that it doesn't have the Sprint Number, since it is retrieved by the Services
     * from the Repository
     * @param sprintRestDTO with primitive data
     * @return NewSprintDTO, which is a DTO that will be sent FROM the
     * Controller and to the Services
     */
    public NewSprintDTO toDomainDTO (SprintRestDTO sprintRestDTO){
        NewSprintDTO newControllerDTO = new NewSprintDTO();
        newControllerDTO.projectCode = new ProjectCode(sprintRestDTO.projectCode);
        newControllerDTO.sprintNumber = new SprintNumber(sprintRestDTO.sprintNumber);
        newControllerDTO.timePeriod = new TimePeriod(sprintRestDTO.startDate, sprintRestDTO.endDate);
        return newControllerDTO;
    }

    /***
     * Method to convert a primitive String projectCode to the value object
     * @param sprintRestDTO with the primitive value for ProjectCode
     * @return DTO with the ProjectCode value object
     */
    public NewSprintDTO createProjectCode (SprintRestDTO sprintRestDTO){
        NewSprintDTO newControllerDTO = new NewSprintDTO();
        newControllerDTO.projectCode = new ProjectCode(sprintRestDTO.projectCode);
        return newControllerDTO;
    }

    /***
     * This one uses different DTOS, since they have the Sprint Number Value object
     * and the primitive int Sprint Number.
     * @param toControllerDTO with Value Objects
     * @return SprintRestDTO, which already has the field for the primitive Sprint Number
     */
    public SprintRestDTO toRestDTO (NewSprintDTO toControllerDTO){
        SprintRestDTO sprintRestDTO = new SprintRestDTO();

        sprintRestDTO.projectCode = toControllerDTO.sprintID.getProjectCode().toString();
        sprintRestDTO.sprintNumber = toControllerDTO.sprintID.getSprintNumber().getSprintNumber();
        sprintRestDTO.startDate = toControllerDTO.timePeriod.getStartDate();
        sprintRestDTO.endDate = toControllerDTO.timePeriod.getEndDate();
        sprintRestDTO.status = toControllerDTO.status.toString();

        return sprintRestDTO;
    }

    /***
     * Creates a list of SprintRestDTO, in order to be able to successfully
     * create the JSON file when the GET request comes.
     * @param allSprintsFromProject list
     * @return dtoList with data in primitive values for a correct conversion to JSON
     */
    public List<SprintRestDTO> getSprintList (List<NewSprintDTO> allSprintsFromProject){
        List<SprintRestDTO> dtoList = new ArrayList<>();

        for(NewSprintDTO serviceDTO : allSprintsFromProject){
            SprintRestDTO sprintRestDTO = toRestDTO(serviceDTO);
            dtoList.add(sprintRestDTO);
        }
        return dtoList;
    }
}