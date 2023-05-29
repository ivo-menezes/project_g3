package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BusinessSectorDesignation implements ValueObject {

    private String businessSectorDesignation;

    public BusinessSectorDesignation (String businessSectorDesignation) {
        if (businessSectorDesignation == null || businessSectorDesignation.isEmpty() || businessSectorDesignation.isBlank()) {
            throw new IllegalArgumentException("businessSectorDesignation cannot be null/blank/empty");
        }
        this.businessSectorDesignation = businessSectorDesignation;
    }

    protected BusinessSectorDesignation (){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSectorDesignation)) return false;
        BusinessSectorDesignation that = (BusinessSectorDesignation) o;
        return businessSectorDesignation.equals(that.businessSectorDesignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessSectorDesignation);
    }
}
