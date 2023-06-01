package org.switch2022.project.mapper;

import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

import java.util.Objects;

public class BusinessSectorDTO {

    public BusinessSectorID businessSectorID;
    public BusinessSectorDesignation businessSectorDesignation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSectorDTO)) return false;
        BusinessSectorDTO that = (BusinessSectorDTO) o;
        return Objects.equals(businessSectorID, that.businessSectorID) && Objects.equals(businessSectorDesignation, that.businessSectorDesignation);
    }
}
