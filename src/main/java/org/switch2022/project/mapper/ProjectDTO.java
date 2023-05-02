package org.switch2022.project.mapper;

import java.util.Date;

public class ProjectDTO {

    public int code;
    public String name;
    public String customer;
    public Date startDate;
    public Date endDate;
    public String projectStatus; // nao tem status pq vem como planned


    public ProjectDTO(int code,String name, String customer, Date startDate, Date endDate, String projectStatus) {
    }
}
