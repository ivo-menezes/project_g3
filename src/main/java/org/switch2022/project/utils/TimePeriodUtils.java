package org.switch2022.project.utils;

import org.switch2022.project.model.valueobject.TimePeriod;

import java.util.Date;

public class TimePeriodUtils {

    /**
     * Checks if two time periods overlap
     * @param firstTimePeriod the first time period
     * @param secondTimePeriod the second time period
     * @return true if the time periods overlap, false otherwise.
     */
    public static boolean timePeriodsOverlap(TimePeriod firstTimePeriod, TimePeriod secondTimePeriod){
        Date firstStartDate = firstTimePeriod.getStartDate();
        Date firstEndDate = firstTimePeriod.getEndDate();

        Date secondStartDate = secondTimePeriod.getStartDate();
        Date secondEndDate = secondTimePeriod.getEndDate();

        return firstStartDate.before(secondEndDate) && firstEndDate.after(secondStartDate);
    }

    /**
     * Checks if a given time period is contained within another time period.
     * @param largestTimePeriod the largest time period
     * @param smallestSprintTimePeriod the smallest time period
     * @return true if the smaller time period is fully contained within the larger time period, false otherwise.
     */

    public static boolean timePeriodContainsTimePeriod(TimePeriod largestTimePeriod, TimePeriod smallestSprintTimePeriod) {
        Date firstStartDate = largestTimePeriod.getStartDate();
        Date firstEndDate = largestTimePeriod.getEndDate();

        Date secondStartDate = smallestSprintTimePeriod.getStartDate();
        Date secondEndDate = smallestSprintTimePeriod.getEndDate();

        return (firstStartDate.equals(secondStartDate) && firstEndDate.after(secondEndDate)) ||
                (firstStartDate.before(secondStartDate) && (firstEndDate.equals(secondEndDate) || firstEndDate.after(secondEndDate)));
    }

}
