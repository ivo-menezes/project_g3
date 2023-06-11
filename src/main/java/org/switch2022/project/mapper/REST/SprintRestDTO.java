package org.switch2022.project.mapper.REST;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SprintRestDTO {
    /***
     * This DTO has all the primitive values for the construction of the SPRINTDDD.
     * However, we don't receive the sprintnumber from UI, so this field will come empty.
     * It will be used when sending the newly created object back to the UI
     */
    public String projectCode;
    public int sprintNumber;
    public Date startDate;
    public Date endDate;
    public String status;
}
