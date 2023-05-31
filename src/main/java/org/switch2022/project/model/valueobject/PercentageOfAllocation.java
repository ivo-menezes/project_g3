package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class PercentageOfAllocation implements ValueObject, Serializable {

    private final double percentageOfAllocation;

    public PercentageOfAllocation (double percentageOfAllocation) {
        if (percentageOfAllocation < 0) {
            throw new IllegalArgumentException("PercentageOfAllocation cannot be negative");
        }
        if (percentageOfAllocation > 100) {
            throw new IllegalArgumentException("PercentageOfAllocation cannot be greater than 100");
        }
        this.percentageOfAllocation = percentageOfAllocation;
    }

    public double getValue() {
        return this.percentageOfAllocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PercentageOfAllocation)) return false;
        PercentageOfAllocation that = (PercentageOfAllocation) o;
        return percentageOfAllocation == that.percentageOfAllocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentageOfAllocation);
    }

}

