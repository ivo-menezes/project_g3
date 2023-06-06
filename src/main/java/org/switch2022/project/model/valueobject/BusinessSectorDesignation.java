package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;
import java.util.Objects;


public class BusinessSectorDesignation implements ValueObject {

    private String businessSectorDesignation;

    public BusinessSectorDesignation (String businessSectorDesignation) {
        if (businessSectorDesignation == null || businessSectorDesignation.isEmpty() || businessSectorDesignation.isBlank()) {
            throw new IllegalArgumentException("businessSectorDesignation cannot be null/blank/empty");
        }
        this.businessSectorDesignation = businessSectorDesignation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessSectorDesignation)) {
            return false;
        }
        BusinessSectorDesignation that = (BusinessSectorDesignation) o;
        return businessSectorDesignation.equals(that.businessSectorDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessSectorDesignation);
    }
    @Override
    public String toString() {
        return this.businessSectorDesignation;
    }
}
