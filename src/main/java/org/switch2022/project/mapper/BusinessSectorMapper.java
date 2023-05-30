package org.switch2022.project.mapper;

import org.springframework.stereotype.Component;

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
}
