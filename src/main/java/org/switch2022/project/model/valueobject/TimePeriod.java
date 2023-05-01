package org.switch2022.project.model.valueobject;

import org.switch2022.project.ddd.DomainId;
import org.switch2022.project.ddd.ValueObject;

import java.util.Date;
import java.util.Objects;

public class TimePeriod implements ValueObject {

    private Date startDate;

    private Date endDate;


    public TimePeriod(Date startDate, Date endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date must not be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date must not be null");
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date must be sooner than end date");
        }

        this.startDate = startDate;
        this.endDate = endDate;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePeriod that = (TimePeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

}
