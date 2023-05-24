package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public enum UserStoryStatus implements ValueObject, Serializable {
        TO_DO,
        IN_PROGRESS,
        TESTING,
        DONE,
        CANCELLED
}
