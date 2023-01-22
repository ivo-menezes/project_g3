package org.switch2022.project.controller;

import org.switch2022.project.model.BusinessSectorList;

public class CreateBusinessSectorController {

    private BusinessSectorList newBusinessList;
    /***
     * This controller will be used to create the business sector.
     * The constructor doesn't receive anything, since it will be only pulled for the
     */
    public CreateBusinessSectorController(BusinessSectorList newBusinessList) {
        this.newBusinessList = newBusinessList;
    }

    public boolean createBusinessSector(String designation) {
        boolean result = true;
        newBusinessList = new BusinessSectorList();
        if (!newBusinessList.validateBusinessSector(designation)) {
            result = false;
        } else {
            newBusinessList.createBusinessSector(designation);
        }
        return result;
    }
}
