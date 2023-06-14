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

}
