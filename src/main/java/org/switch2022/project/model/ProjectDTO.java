package org.switch2022.project.model;

import java.util.Date;

public class ProjectDTO {

    public int code;
    public String name;
    public String customer;
    public Date startDate;
    public Date endDate;
    public String projectStatus;


    public ProjectDTO(int code,String name, String customer, Date startDate, Date endDate, String projectStatus) {
    }
}
