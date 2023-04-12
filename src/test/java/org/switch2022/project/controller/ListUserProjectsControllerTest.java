package org.switch2022.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.mapper.ResourceDTO;
import org.switch2022.project.mapper.UserProjectsDTO;
import org.switch2022.project.model.*;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.profile.Profile;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListUserProjectsControllerTest {

    @Test
    @DisplayName("ensure that a valid list of user projects is returned")
    public void ensureValidListOfUserProjectsIsReturned() {
        // arrange
        // create all necessary mock objects
        Account currentAccount = mock(Account.class);
        ProjectList projectList = mock(ProjectList.class);
        Project project1 = mock(Project.class);
        Project project2 = mock(Project.class);
        ResourceList resourceList1 = mock(ResourceList.class);
        ResourceList resourceList2 = mock(ResourceList.class);

        Resource resourceA1 = mock(Resource.class);
        Resource resourceA2 = mock(Resource.class);
        Resource resourceB1 = mock(Resource.class);
        Resource resourceB2 = mock(Resource.class);

        UserProjectsDTO userProjectsDTO_B1 = mock(UserProjectsDTO.class);

        // train the mock objects
        when(projectList.listSize()).thenReturn(2);
        when(currentAccount.getEmail()).thenReturn("xxxxx@gmail.com");
        when(projectList.getProjectByIndex(0)).thenReturn(project1);
        when(projectList.getProjectByIndex(1)).thenReturn(project2);
        when(project1.getList()).thenReturn(resourceList1);
        when(project2.getList()).thenReturn(resourceList2);
        when(project1.getList().listResourceSize()).thenReturn(2);
        when(project2.getList().listResourceSize()).thenReturn(2);
        when(project1.getList().getResourceIndex(0)).thenReturn(resourceA1);
        when(project1.getList().getResourceIndex(1)).thenReturn(resourceA2);
        when(project2.getList().getResourceIndex(0)).thenReturn(resourceB1);
        when(project2.getList().getResourceIndex(1)).thenReturn(resourceB2);
        when(resourceA1.getEmailOfResource()).thenReturn("yyyyy@gmail.com");
        when(resourceA2.getEmailOfResource()).thenReturn("zzzzz@gmail.com");
        when(resourceB1.getEmailOfResource()).thenReturn("xxxxx@gmail.com");
        when(resourceB2.getEmailOfResource()).thenReturn("yyyyy@gmail.com");
        when(resourceA1.isActive()).thenReturn(true);
        when(resourceA2.isActive()).thenReturn(true);
        when(resourceB1.isActive()).thenReturn(true);
        when(resourceB2.isActive()).thenReturn(true);
        when(project2.createUserProjectsDTO(project2)).thenReturn(userProjectsDTO_B1);
        when(project2.getCode()).thenReturn(2);

        // create a new (real) controller
        ListUserProjectsController controller = new ListUserProjectsController(projectList);

        // act
        List<UserProjectsDTO> result = controller.listUserProjects(currentAccount);

        assertTrue(result.size() == 1);
    }

    @Test
    @DisplayName("ensure an empty project list throws an exception")
    public void ensureEmptyProjectListThrowsException(){
        // arrange
        ProjectList projectList = mock(ProjectList.class);

        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new ListUserProjectsController(projectList));
        String resultMessage = result.getMessage();

        // assert
        assertEquals("List is empty", resultMessage);
    }


    /***
     * This test will compare two userProjectsLists will compare 2 lists (one created by the userProjectsListController
     * and another by us) and conclude that they are not equal.
     */
    @Test
    public void confirmListOfProjectsCodesWorks() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");

        Account account1 = new Account ("Ricardo", "11111x@gmail.com", "33399988", profile);

        ResourceDTO resourceDTO1 = new ResourceDTO ("11111x@gmail.com", "DDD", 1, new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.JANUARY, 3),50, 80);
        ResourceDTO resourceDTO2 = new ResourceDTO ("11111x@gmail.com", "SXXX",2, new Date(2023, Calendar.JANUARY, 2), 40, 50);
        ResourceDTO resourceDTO3 = new ResourceDTO ("11111x@gmail.com", "fkkme",1,new Date(2023, Calendar.JANUARY, 4), 30, 80);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project1);
        projectList.addProject(project2);

        project1.addResource(account1,role1,resourceDTO1);
        project2.addResource(account1,role1,resourceDTO2);
        project1.addResource(account1,role2,resourceDTO3);

        ListUserProjectsController controller = new ListUserProjectsController(projectList);
        List<Integer> expected = controller.listUserProjects(account1);
        List<Integer> notExpected = Arrays.asList(1, 2, 1);

        assertNotEquals(expected,notExpected);
    }

    @Test
    @DisplayName("ensure that an empty list of user projects is returned")
    public void ensureThatEmptyListOfUserProjectsIsReturned() {
        // arrange
        // create all necessary mock objects
        Account currentAccount = mock(Account.class);
        ProjectList projectList = mock(ProjectList.class);
        Project project1 = mock(Project.class);
        Project project2 = mock(Project.class);
        ResourceList resourceList1 = mock(ResourceList.class);
        ResourceList resourceList2 = mock(ResourceList.class);

        Resource resourceA1 = mock(Resource.class);
        Resource resourceA2 = mock(Resource.class);
        Resource resourceB1 = mock(Resource.class);
        Resource resourceB2 = mock(Resource.class);

        // train the mock objects
        when(projectList.listSize()).thenReturn(2);
        when(currentAccount.getEmail()).thenReturn("xxxxx@gmail.com");
        when(projectList.getProjectByIndex(0)).thenReturn(project1);
        when(projectList.getProjectByIndex(1)).thenReturn(project2);
        when(project1.getList()).thenReturn(resourceList1);
        when(project2.getList()).thenReturn(resourceList2);
        when(project1.getList().listResourceSize()).thenReturn(2);
        when(project2.getList().listResourceSize()).thenReturn(2);
        when(project1.getList().getResourceIndex(0)).thenReturn(resourceA1);
        when(project1.getList().getResourceIndex(1)).thenReturn(resourceA2);
        when(project2.getList().getResourceIndex(0)).thenReturn(resourceB1);
        when(project2.getList().getResourceIndex(1)).thenReturn(resourceB2);
        when(resourceA1.getEmailOfResource()).thenReturn("yyyyy@gmail.com");
        when(resourceA2.getEmailOfResource()).thenReturn("zzzzz@gmail.com");
        when(resourceB1.getEmailOfResource()).thenReturn("zzzzz@gmail.com");
        when(resourceA2.getEmailOfResource()).thenReturn("yyyyy@gmail.com");
        when(resourceA1.isActive()).thenReturn(true);
        when(resourceA2.isActive()).thenReturn(true);
        when(resourceB1.isActive()).thenReturn(true);
        when(resourceB2.isActive()).thenReturn(true);

        // create a new (real) controller
        ListUserProjectsController controller = new ListUserProjectsController(projectList);

        // act
        List<UserProjectsDTO> result = controller.listUserProjects(currentAccount);

        assertTrue(result.size() == 0);
    }
}