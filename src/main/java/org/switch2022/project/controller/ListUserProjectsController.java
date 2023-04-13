package org.switch2022.project.controller;

import org.switch2022.project.mapper.UserProjectsDTO;
import org.switch2022.project.model.*;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.project.Project;

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
    public List listUserProjects (Account currentAccount) {
        List<UserProjectsDTO> userProjectsList = new ArrayList<>();
        List<Integer> projectCodes = new ArrayList<>();
        int listProjectSize = this.projectList.listSize();
        String emailCurrentAccount = currentAccount.getEmail();

        for (int i = 0; i < listProjectSize; i++) {
            Project project = projectList.getProjectByIndex(i);
            int listResourceSize = project.getList().listResourceSize();

            for (int j = 0; j < listResourceSize; j++ ) {
                Resource resource = project.getList().getResourceIndex(j);
                boolean emailsMatch = emailCurrentAccount.equals(resource.getEmailOfResource());
                boolean endDateNull = resource.isActive();
                boolean projectCodeInProjectCodeList = projectCodes.contains(project.getCode());

                if (emailsMatch && endDateNull && !projectCodeInProjectCodeList){
                    userProjectsList.add(project.createUserProjectsDTO(project));
                    projectCodes.add(project.getCode());
                }
            }
        } return userProjectsList;
    }
}
