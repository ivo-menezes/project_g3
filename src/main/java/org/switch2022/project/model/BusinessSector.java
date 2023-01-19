package org.switch2022.project.model;

import java.util.Objects;

public class BusinessSector {
    private String designation;

    public BusinessSector(String designation){
        if(designation == null){
            throw new IllegalArgumentException("Designation cannot be null.");
        }
        this.designation = designation;
    }

    public String getDesignation(){
        return designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessSector)) return false;
        BusinessSector that = (BusinessSector) o;
        return designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }
}
