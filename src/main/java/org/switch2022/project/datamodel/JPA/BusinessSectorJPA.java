package org.switch2022.project.datamodel.JPA;

import javax.persistence.*;

@Entity
@Table(name = "businessSector")
public class BusinessSectorJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String businessSectorDesignation;

    public BusinessSectorJPA (String businessSectorDesignation) {
        if (businessSectorDesignation == null) {
            throw new IllegalArgumentException("businessSectorDesignation cannot be null");
        }
        this.businessSectorDesignation = businessSectorDesignation;
    }

    protected BusinessSectorJPA (){}

    public long getId() {
        return id;
    }

    public String getBusinessSectorDesignation() {
        return businessSectorDesignation;
    }
}
