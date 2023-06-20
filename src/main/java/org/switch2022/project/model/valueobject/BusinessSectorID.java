package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import java.io.Serializable;

public class BusinessSectorID implements DomainId, Serializable {
    private final long id;
    public BusinessSectorID(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
