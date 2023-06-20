package org.switch2022.project.model.businessSector;

import org.springframework.stereotype.Component;
import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

@Component
public class BusinessSectorFactoryImpl implements IBusinessSectorFactory{
    /**
     * Method to create the businessSector object.
     * @param businessSectorID
     * @param businessSectorDesignation
     * @return a businessSector object
     */
    @Override
    public BusinessSectorDDD createBusinessSector(BusinessSectorID businessSectorID,
                                                  BusinessSectorDesignation businessSectorDesignation) {

        if (businessSectorDesignation == null) {
            throw new IllegalArgumentException("businessSectorDesignation cannot be null");
        }

        BusinessSectorDDD businessSector = new BusinessSectorDDD(businessSectorID,businessSectorDesignation);
        return businessSector;
    }
}
