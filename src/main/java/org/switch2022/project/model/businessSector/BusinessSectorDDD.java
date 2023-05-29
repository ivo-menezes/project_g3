package org.switch2022.project.model.businessSector;

import org.switch2022.project.ddd.AggregateRoot;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

public class BusinessSectorDDD implements AggregateRoot<BusinessSectorID> {

    private final BusinessSectorID businessSectorID;
    private final BusinessSectorDesignation businessSectorDesignation;

    /**
     * Public constructor to instantiate a businessSector.
     * @param businessSectorID
     * @param businessSectorDesignation
     */
    public BusinessSectorDDD(BusinessSectorID businessSectorID,
                             BusinessSectorDesignation businessSectorDesignation) {

        if (businessSectorDesignation == null) {
            throw new IllegalArgumentException("businessSectorDesignation, cannot be null");
        }
        this.businessSectorID = businessSectorID;
        this.businessSectorDesignation = businessSectorDesignation;
    }

    @Override
    public BusinessSectorID identity() {
        return this.businessSectorID;
    }

    public BusinessSectorDesignation getBusinessSectorDesignation() {
        return businessSectorDesignation;
    }
}
