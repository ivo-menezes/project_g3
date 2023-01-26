package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class BusinessSectorList {

    private List<BusinessSector> listBusinessSector;

    /***
     * Constructor to initialise a new list of business sectors.
     */
    public BusinessSectorList() {
        listBusinessSector = new ArrayList<>();
    }

    /***
     * The method will create the new business sector, if the validation passes
     * @return true, if the following method, which is addBusinessSector does put the newly
     * created business sector is added to the list.
     */
    public boolean createBusinessSector(String designation) {

        BusinessSector newBusinessSector = new BusinessSector(designation);

        return addBusiness(newBusinessSector);
    }

    /***
     * The method will use the Arraylist option of .contain to verify whether the object exists in the
     * list, before adding it to the list itself.
     * @param newBusinessSector
     * @return a boolean true or false, accordingly to whether it exists or not
     */
    public boolean listDoesNotContainSector(BusinessSector newBusinessSector){
    return !listBusinessSector.contains(newBusinessSector);
    }

    public boolean addBusiness(BusinessSector newBusinessSector){
        boolean addedDesignation = true;
        if(listDoesNotContainSector(newBusinessSector) && newBusinessSector != null) {
            this.listBusinessSector.add(newBusinessSector);
        }else{
            addedDesignation = false;
        }
        return addedDesignation;
    }
}
