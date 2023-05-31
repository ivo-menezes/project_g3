package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;

import java.io.Serializable;

public class AccountID implements DomainId, Serializable {

    private long id;

    public AccountID(long id) {
        this.id = id;
    }

}
