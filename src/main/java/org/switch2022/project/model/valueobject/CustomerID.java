package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import java.io.Serializable;

public class CustomerID implements DomainId, Serializable {

    private final long id;
    public CustomerID(Long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
