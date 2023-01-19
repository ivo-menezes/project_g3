package org.switch2022.project.controller;

import org.switch2022.project.model.BusinessSectorList;

public class CreateBusinessSectorController {

    public CreateBusinessSectorController() {
    }

    public boolean createBusinessSector(String designation) {
        boolean result = true;
        BusinessSectorList newList = new BusinessSectorList();
        if (!newList.validateBusinessSector(designation)) {
            result = false;
        } else {
            newList.createBusinessSector(designation);
        }
        return result;
    }
}
