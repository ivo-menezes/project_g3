package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOToController;
import org.switch2022.project.model.valueobject.ProjectCode;
import org.switch2022.project.model.valueobject.SprintNumber;
import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.ArrayList;
import java.util.List;

@Component
public class SprintDTOMapper {

    /***
     * This method will convert the received DTO with primitive data to another one with Value Objects.
     * This will be consumed in the Sprint Services, to have the SprintDDD created.
     * Note that it doesn't have the Sprint Number, since it is retrieved by the Services
     * from the Repository
     * @param sprintDTOFromUI with primitive data
     * @return SprintDTOController, which is a DTO that will be sent FROM the
     * Controller and to the Services
     */
    public SprintDTOController toDomainDTO (SprintDTOUI sprintDTOFromUI){
        SprintDTOController newControllerDTO = new SprintDTOController();
        newControllerDTO.projectCode = new ProjectCode(sprintDTOFromUI.projectCode);
        newControllerDTO.sprintNumber = new SprintNumber(sprintDTOFromUI.sprintNumber);
        newControllerDTO.timePeriod = new TimePeriod(sprintDTOFromUI.startDate, sprintDTOFromUI.endDate);
        return newControllerDTO;
    }
    public SprintDTOController createProjectCode (SprintDTOUI sprintDTOFromUI){
        SprintDTOController newControllerDTO = new SprintDTOController();
        newControllerDTO.projectCode = new ProjectCode(sprintDTOFromUI.projectCode);
        return newControllerDTO;
    }

    /***
     * This one uses different DTOS, since they have the Sprint Number Value object
     * and the primitive int Sprint Number.
     * @param toControllerDTO with Value Objects
     * @return SprintDTOUI, which already has the field for the primitive Sprint Number
     */
    public SprintDTOUI toRestDTO (SprintDTOToController toControllerDTO){
        SprintDTOUI newUiDTO = new SprintDTOUI();

        newUiDTO.projectCode = toControllerDTO.sprintID.getProjectCode().toString();
        newUiDTO.sprintNumber = toControllerDTO.sprintID.getSprintNumber().getSprintNumber();
        newUiDTO.startDate = toControllerDTO.timePeriod.getStartDate();
        newUiDTO.endDate = toControllerDTO.timePeriod.getEndDate();

        return newUiDTO;
    }
    public List<SprintDTOUI> getSprintList (List<SprintDTOToController> allSprintsFromProject){
        List<SprintDTOUI> dtoList = new ArrayList<>();

        for(SprintDTOToController serviceDTO : allSprintsFromProject){
            SprintDTOUI sprintDTOUI = toRestDTO(serviceDTO);
            dtoList.add(sprintDTOUI);
        }
        return dtoList;
    }
}
