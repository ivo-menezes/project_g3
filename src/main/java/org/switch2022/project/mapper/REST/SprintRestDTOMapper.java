package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.NewAssembledUSDTO;
import org.switch2022.project.mapper.UpdateSprintDTO;
import org.switch2022.project.mapper.UpdateSprintDomainDTO;
import org.switch2022.project.mapper.UpdateUsInSprintDomainDTO;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
import org.switch2022.project.model.valueobject.*;

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
        sprintRestDTO.sprintNumber = toControllerDTO.sprintID.getSprintNumber().getValue();
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

    /**
     * Method responsible for converting a primitive data DTO to a domain DTO
     * @param updateSprintInputDTO
     * @return domain DTO
     */
    public UpdateSprintDomainDTO toDomainDTO(UpdateSprintDTO updateSprintInputDTO) {
        ProjectCode projectCode = new ProjectCode(updateSprintInputDTO.projectCode);
        SprintNumber sprintNumber =  new SprintNumber(updateSprintInputDTO.sprintNumber);

        SprintID sprintID = new SprintID(projectCode,sprintNumber);
        SprintStatus sprintStatus = SprintStatus.valueOf(updateSprintInputDTO.sprintStatus);

        UpdateSprintDomainDTO updateSprintDomainDTO = new UpdateSprintDomainDTO();
        updateSprintDomainDTO.sprintID = sprintID;
        updateSprintDomainDTO.sprintStatus = sprintStatus;

        return updateSprintDomainDTO;
    }

    /**
     * Method responsible for converting a domain DTO into a primitive data DTO.
     * @param updateSprintDomainDTO
     * @return primitive data DTO
     */
    public UpdateSprintDTO toDataDTO(UpdateSprintDomainDTO updateSprintDomainDTO){
        UpdateSprintDTO updateSprintOutputDTO = new UpdateSprintDTO();
        updateSprintOutputDTO.sprintNumber = updateSprintDomainDTO.sprintID.getSprintNumber().getValue();
        updateSprintOutputDTO.projectCode = updateSprintDomainDTO.sprintID.getProjectCode().toString();
        updateSprintOutputDTO.sprintStatus = updateSprintDomainDTO.sprintStatus.toString();

        return updateSprintOutputDTO;
    }

    /**
     * Method responsible for converting a primitive data DTO to a domain DTO
     * @param inputUsSprintInputDTO
     * @return updateUsInSprintDomainDTO
     */
    public UpdateUsInSprintDomainDTO uSInSprintToDomainDTO(InputUsInSprintStatusDTO inputUsSprintInputDTO) {
        ProjectCode projectCode = new ProjectCode(inputUsSprintInputDTO.projectCode);
        UserStoryNumber userStoryNumber =  new UserStoryNumber(inputUsSprintInputDTO.userStoryNumber);
        SprintNumber sprintNumber =  new SprintNumber(inputUsSprintInputDTO.sprintNumber);

        SprintID sprintID = new SprintID(projectCode, sprintNumber);
        UserStoryID userStoryID = new UserStoryID(userStoryNumber, projectCode);
        UserStoryInSprintID userStoryInSprintID = new UserStoryInSprintID(sprintID, userStoryID);

        UserStoryStatus userStoryStatus = UserStoryStatus.valueOf(inputUsSprintInputDTO.userStoryStatus);

        UpdateUsInSprintDomainDTO updateUsInSprintDomainDTO = new UpdateUsInSprintDomainDTO();
        updateUsInSprintDomainDTO.userStoryInSprintID = userStoryInSprintID;
        updateUsInSprintDomainDTO.sprintNumber = sprintNumber;
        updateUsInSprintDomainDTO.userStoryInSprintStatus = userStoryStatus;

        return updateUsInSprintDomainDTO;
    }

    public InputUsInSprintStatusDTO uSInSprintToDataDTO(UpdateUsInSprintDomainDTO updateUsInSprintDomainDTO){
        InputUsInSprintStatusDTO inputUsInSprintStatusDTO = new InputUsInSprintStatusDTO();
        inputUsInSprintStatusDTO.sprintNumber = updateUsInSprintDomainDTO.userStoryInSprintID.getSprintID().getSprintNumber().getValue();
        inputUsInSprintStatusDTO.projectCode = updateUsInSprintDomainDTO.userStoryInSprintID.getSprintID().getProjectCode().toString();
        inputUsInSprintStatusDTO.userStoryNumber = updateUsInSprintDomainDTO.userStoryInSprintID.getUserStoryID().getUserStoryNumber().toString();
        inputUsInSprintStatusDTO.userStoryStatus = updateUsInSprintDomainDTO.userStoryInSprintStatus.toString();

        return inputUsInSprintStatusDTO;
    }

    public AssembledUSRestDto assembledUSToRestDto (NewAssembledUSDTO domainDto ) {

        AssembledUSRestDto restDto = new AssembledUSRestDto();
        restDto.userStoryNumber = domainDto.userStoryNumber.toString();
        restDto.projectCode = domainDto.projectCode.toString();
        restDto.sprintNumber = domainDto.sprintNumber.toString();
        restDto.userStoryActor = domainDto.userStoryActor.toString();
        restDto.userStoryDescription = domainDto.userStoryDescription.toString();
        restDto.userStoryAcceptanceCriteria = domainDto.userStoryAcceptanceCriteria.toString();
        restDto.userStoryStatus = domainDto.userStoryStatus.toString();
        restDto.userStoryEffortEstimate = domainDto.userStoryEffortEstimate.toString();

        return restDto;
    }

}
