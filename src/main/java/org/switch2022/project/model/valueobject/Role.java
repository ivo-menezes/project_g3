package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

public enum Role implements ValueObject {

    TEAM_MEMBER,
    PRODUCT_OWNER,
    SCRUM_MASTER,
    PROJECT_MANAGER
}