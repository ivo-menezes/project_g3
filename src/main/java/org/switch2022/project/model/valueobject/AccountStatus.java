package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;

public enum AccountStatus implements ValueObject, Serializable {
    ACTIVE,
    INACTIVE
}
