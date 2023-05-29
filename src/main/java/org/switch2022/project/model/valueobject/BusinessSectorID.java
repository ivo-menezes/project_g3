package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BusinessSectorID implements DomainId, Serializable {
    private long id;

    public BusinessSectorID(long id) {
        this.id = id;
    }
    protected BusinessSectorID (){}
}
