package org.switch2022.project.model;


import java.util.Date;
import java.util.Objects;

public class Sprint {
    private int sprintNumber;
    private Date startDate;
    private Date endDate;

    public Sprint(int sprintNumber, Date startDate, Date endDate){
        if(sprintNumber <= 0 || startDate == null || endDate == null){
            throw new IllegalArgumentException("Missing value, please try again.");
        }
        this.sprintNumber = sprintNumber;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return sprintNumber == sprint.sprintNumber && startDate.equals(sprint.startDate) && endDate.equals(sprint.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintNumber, startDate, endDate);
    }
}
