package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

public enum UserStoryStatus implements ValueObject {
        TO_DO,
        IN_PROGRESS,
        TESTING,
        DONE,
        CANCELLED
}
