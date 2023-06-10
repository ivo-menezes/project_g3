package org.switch2022.project.mapper;

public class BusinessSectorOutputDTO {
    public Long businessSectorId;
    public String businessSectorDesignation;

    public BusinessSectorOutputDTO(Long businessSectorId, String businessSectorDesignation) {
        this.businessSectorId = businessSectorId;
        this.businessSectorDesignation = businessSectorDesignation;
    }
}
