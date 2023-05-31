package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.ValueObject;

import java.io.Serializable;
import java.util.Objects;

public class CostPerHour implements ValueObject, Serializable {

    private final double costPerHour;

    public CostPerHour (double costPerHour) {
        if (costPerHour < 0) {
            throw new IllegalArgumentException("CostPerHour cannot be negative");
        }
        this.costPerHour = costPerHour;
    }

    public double getValue() {
        return this.costPerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostPerHour)) return false;
        CostPerHour that = (CostPerHour) o;
        return costPerHour == that.costPerHour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(costPerHour);
    }

}

