package org.switch2022.project.service.irepositories;

import org.switch2022.project.model.businessSector.BusinessSectorDDD;
import org.switch2022.project.model.valueobject.BusinessSectorID;

import java.util.ArrayList;

public interface IBusinessSectorRepository {

    BusinessSectorDDD save (BusinessSectorDDD businessSector);
    ArrayList<BusinessSectorDDD> getAll();

    boolean containsID (BusinessSectorID businessSectorID);
}
