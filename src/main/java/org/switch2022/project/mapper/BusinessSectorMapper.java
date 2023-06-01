package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BusinessSectorMapper {
    /**
     * Method responsible for converting DTO in a DTO with primitive properties.
     * @param businessSector
     * @return BusinessSectorOutputDTO object.
     */
    public BusinessSectorOutputDTO toOutputDTO(BusinessSectorDTO businessSector) {
        if (businessSector.businessSectorID != null)
            return new BusinessSectorOutputDTO(businessSector.businessSectorID.getId(), businessSector.businessSectorDesignation.toString());
        else
            return new BusinessSectorOutputDTO(null, businessSector.businessSectorDesignation.toString());
    }

    /**
     * Method responsible for converting ArrayList of DTO in a ArrayList of DTO with primitive properties.
     * @return BusinessSectorOutputDTO object.
     */
    public ArrayList<BusinessSectorOutputDTO> toOutputDTO(ArrayList<BusinessSectorDTO> BusinessSectors) {
        ArrayList<BusinessSectorOutputDTO> businessSectorsOutput = new ArrayList();

        for (BusinessSectorDTO businessSectorDTO : BusinessSectors) {

            BusinessSectorOutputDTO businessSectorOutPut = new BusinessSectorOutputDTO(businessSectorDTO.businessSectorID.getId(), businessSectorDTO.businessSectorDesignation.toString());

            businessSectorsOutput.add(businessSectorOutPut);
        }

        return businessSectorsOutput;
    }
}
