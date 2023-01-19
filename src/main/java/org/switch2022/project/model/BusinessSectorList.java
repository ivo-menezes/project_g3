package org.switch2022.project.model;

import java.util.ArrayList;
import java.util.List;

public class BusinessSectorList {

    private List<BusinessSector> businessSectorList;

    /***
     * Constructor to initialise a new list of business sectors.
     */
    public BusinessSectorList() {
        businessSectorList = new ArrayList<>();
    }

    /***
     * This method will validate whether there is a business sector in the list with
     * the input designation.
     * @param designation this is the data for the validation
     * @return a boolean, according to whether the designation already exists or not
     */
    public boolean validateBusinessSector(String designation){
        boolean isDesignationValid = null != designation;
        for (int index = 0; index < this.businessSectorList.size() && isDesignationValid; index++) {
            BusinessSector business = this.businessSectorList.get(index);
            String bDesign = business.getDesignation();
            if (bDesign.equals(designation)) {
                isDesignationValid = false;
                break;
            }
        }
        return isDesignationValid;
    }

    /***
     * The method will create the new business sector, if the validation passes
     * @return true, if the following method, which is addBusinessSector does put the newly
     * created business sector is added to the list.
     */
    public boolean createBusinessSector(String designation) {
        if(designation == null){
            throw new IllegalArgumentException("Designation cannot be null.");
        }
        else if (!validateBusinessSector(designation)) {
            throw new IllegalArgumentException("Designation already exists.");
        }

        BusinessSector businessSector = new BusinessSector(designation);

        return addBusiness(businessSector);
    }

    public boolean addBusiness(BusinessSector businessSector){
        boolean addedDesignation = true;
        if(businessSector == null){
            addedDesignation = false;
        }
        this.businessSectorList.add(businessSector);
        return addedDesignation;
    }
}
