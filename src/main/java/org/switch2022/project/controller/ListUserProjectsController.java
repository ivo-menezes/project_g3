package org.switch2022.project.controller;

import org.switch2022.project.model.*;

import java.util.ArrayList;
import java.util.List;

public class ListUserProjectsController {

    private ProjectList projectList;

    public ListUserProjectsController(ProjectList projectList) {
        if(projectList.listSize()<=0){
            throw new IllegalArgumentException("List is empty");
        }
        this.projectList = projectList;
    }

    /***
     * The methods receives an projectList and his recourses and will create a list of userProjectsDTOs to send.
     * containing only code and name of each project in the list.
     * @param currentAccount
     * @return UserProjectsDTOList
     */
    public List listUserProjectsDTO (Account currentAccount) {
        List<UserProjectsDTO> userProjectsDTOList = new ArrayList<>();
        List<Integer> projectCodes = new ArrayList<>();
        int listProjectSize = this.projectList.listSize();
        String emailCurrentAccount = currentAccount.getEmail();

        for (int i = 0; i < listProjectSize; i++) {
            Project project = projectList.getProjectIndex(i);
            int listResourceSize = project.getList().listResourceSize();

            for (int j = 0; j < listResourceSize; j++ ) {
                Resource resource = project.getList().getResourceIndex(j);
                boolean emailsMatch = emailCurrentAccount.equals(resource.getAccount().getEmail());
                boolean endDateNull = resource.isActive();

                if (emailsMatch && endDateNull && !projectCodes.contains(project.getCode())){
                    userProjectsDTOList.add(projectList.createUserProjectsDTO(project));
                    projectCodes.add(project.getCode());
                }
            }
        } return userProjectsDTOList;
    }
}
