package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

public enum ProjectStatus implements ValueObject {
    PLANNED,
    INCEPTION,
    ELABORATION,
    CONSTRUCTION,
    TRANSITION,
    WARRANTY,
    CLOSED,
}
