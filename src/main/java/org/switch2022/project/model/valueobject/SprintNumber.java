package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;


public class SprintNumber implements ValueObject, Serializable {

    final private int sprintNumber;

    public SprintNumber(int sprintNumber) {

        if (sprintNumber > 0 ) {
            this.sprintNumber = sprintNumber;
        } else {
            throw new IllegalArgumentException("Sprint number has to be higher than 0.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintNumber that = (SprintNumber) o;
        return sprintNumber == that.sprintNumber;
    }

    public int getValue() {
        return this.sprintNumber;
    }


}
