package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.io.Serializable;


public class TypologyID implements DomainId, Serializable {

    private Long id;


    public TypologyID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}