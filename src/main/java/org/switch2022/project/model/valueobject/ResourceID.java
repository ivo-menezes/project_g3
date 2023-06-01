package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import java.io.Serializable;
import java.util.Objects;


public class ResourceID implements DomainId, Serializable {

    private Long id;

    public ResourceID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
