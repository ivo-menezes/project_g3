package org.switch2022.project.mapper;

import java.util.Objects;

public class BusinessSectorOutputDTO {
    public Long businessSectorId;
    public String businessSectorDesignation;

    public BusinessSectorOutputDTO(Long businessSectorId, String businessSectorDesignation) {
        this.businessSectorId = businessSectorId;
        this.businessSectorDesignation = businessSectorDesignation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSectorOutputDTO)) return false;
        BusinessSectorOutputDTO that = (BusinessSectorOutputDTO) o;
        return Objects.equals(businessSectorId, that.businessSectorId) && Objects.equals(businessSectorDesignation, that.businessSectorDesignation);
    }

}
