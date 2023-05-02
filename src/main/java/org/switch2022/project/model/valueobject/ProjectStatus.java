package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

public enum ProjectStatus implements ValueObject {
    Planned,
    Inception,
    Elaboration,
    Construction,
    Transition,
    Warranty,
    Closed
}
