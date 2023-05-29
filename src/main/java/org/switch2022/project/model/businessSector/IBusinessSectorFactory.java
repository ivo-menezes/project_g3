package org.switch2022.project.model.businessSector;

import org.switch2022.project.model.valueobject.BusinessSectorDesignation;
import org.switch2022.project.model.valueobject.BusinessSectorID;

public interface IBusinessSectorFactory {

    BusinessSectorDDD createBusinessSector (BusinessSectorID businessSectorID, BusinessSectorDesignation businessSectorDesignation);
}
