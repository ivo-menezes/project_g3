package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.businessSector.BusinessSectorDDD;

public interface IBusinessSectorRepository {

    BusinessSectorDDD save (BusinessSectorDDD businessSector);
}
