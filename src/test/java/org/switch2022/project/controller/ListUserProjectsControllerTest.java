package org.switch2022.project.controller;

import org.junit.jupiter.api.Test;
import org.switch2022.project.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ListUserProjectsControllerTest {

    /***
     * Test if the Controller can create the UserProjectsDTO list.
     */
    @Test
    public void createListUserProjectsDTO() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");
        Role role3 = new Role( "Project Owner");

        Account account1 = new Account ("Ricardo", "11111x@gmail.com", "33399988", profile);
        Account account2 = new Account ("Filipe", "22222x@gmail.com", "33399987", profile);
        Account account3 = new Account ("Tavares", "33333x@gmail.com", "33399986", profile);

        ResourceDTO resourceDTO1 = new ResourceDTO ("11111x@gmail.com", "DDD", 1, new Date(2023, Calendar.JANUARY, 1), 50, 80);
        ResourceDTO resourceDTO2 = new ResourceDTO ("11111x@gmail.com", "SXXX",2, new Date(2023, Calendar.JANUARY, 2), 40, 50);
        ResourceDTO resourceDTO3 = new ResourceDTO ("22222x@gmail.com", "xssc", 1, new Date(2023, Calendar.JANUARY, 3), 40, 80);
        ResourceDTO resourceDTO4 = new ResourceDTO ("33333x@gmail.com", "dfgfd", 1, new Date(2023, Calendar.JANUARY,4), 40, 60);
        ResourceDTO resourceDTO5 = new ResourceDTO ("22222x@gmail.com", "devjv", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);
        ResourceDTO resourceDTO6 = new ResourceDTO ("33333x@gmail.com", "dkjr", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project1);
        projectList.addProject(project2);

        project1.addResource(account1,role1,resourceDTO1);
        project2.addResource(account1,role1,resourceDTO2);
        project1.addResource(account2,role2,resourceDTO3);
        project1.addResource(account3,role3,resourceDTO4);
        project2.addResource(account2,role3,resourceDTO5);
        project2.addResource(account3,role2,resourceDTO6);


        ListUserProjectsController controller = new ListUserProjectsController(projectList);

        controller.listUserProjectsDTO(account1);

        assertNotNull(controller);
    }

    /***
     * Entering an empty list will give a message of error.
     */
    @Test
    public void checkTheEmptyList(){
        ProjectList projectList = new ProjectList();
        AtomicReference<ListUserProjectsController> controller = null;
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
                () -> controller.set(new ListUserProjectsController(projectList)));
        String resultMessage = result.getMessage();

        // assert
        assertEquals("List is empty", resultMessage);
    }

    /***
     * This test will ensure the resulting list doesn't come out as empty as well.
     */
    @Test
    public void listUserProjectsNotEmpty() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");
        Role role3 = new Role( "Project Owner");

        Account account1 = new Account ("Ricardo", "11111x@gmail.com", "33399988", profile);
        Account account2 = new Account ("Filipe", "22222x@gmail.com", "33399987", profile);
        Account account3 = new Account ("Tavares", "33333x@gmail.com", "33399986", profile);

        ResourceDTO resourceDTO1 = new ResourceDTO ("11111x@gmail.com", "DDD", 1, new Date(2023, Calendar.JANUARY, 1), 50, 80);
        ResourceDTO resourceDTO2 = new ResourceDTO ("11111x@gmail.com", "SXXX",2, new Date(2023, Calendar.JANUARY, 2), 40, 50);
        ResourceDTO resourceDTO3 = new ResourceDTO ("22222x@gmail.com", "xssc", 1, new Date(2023, Calendar.JANUARY, 3), 40, 80);
        ResourceDTO resourceDTO4 = new ResourceDTO ("33333x@gmail.com", "dfgfd", 1, new Date(2023, Calendar.JANUARY,4), 40, 60);
        ResourceDTO resourceDTO5 = new ResourceDTO ("22222x@gmail.com", "devjv", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);
        ResourceDTO resourceDTO6 = new ResourceDTO ("33333x@gmail.com", "dkjr", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project1);
        projectList.addProject(project2);

        project1.addResource(account1,role1,resourceDTO1);
        project2.addResource(account1,role1,resourceDTO2);
        project1.addResource(account2,role2,resourceDTO3);
        project1.addResource(account3,role3,resourceDTO4);
        project2.addResource(account2,role3,resourceDTO5);
        project2.addResource(account3,role2,resourceDTO6);


        ListUserProjectsController controller = new ListUserProjectsController(projectList);
        List result = controller.listUserProjectsDTO(account1);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /***
     * The first assertTrue of the method below will check if the result is an instance of the List class
     * and not another type of objet.
     * The second assertTrue checks if the first element of the returned list is an instance of the UserProjectsDTO class.
     */
    @Test
    public void returnListOfUserProjectsDTO() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");
        Project project3 = new Project(3, "Test3", "For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");
        Role role3 = new Role( "Project Owner");

        Account account1 = new Account ("Ricardo", "11111x@gmail.com", "33399988", profile);
        Account account2 = new Account ("Filipe", "22222x@gmail.com", "33399987", profile);
        Account account3 = new Account ("Tavares", "33333x@gmail.com", "33399986", profile);

        ResourceDTO resourceDTO1 = new ResourceDTO ("11111x@gmail.com", "DDD", 1, new Date(2023, Calendar.JANUARY, 1), 50, 80);
        ResourceDTO resourceDTO2 = new ResourceDTO ("11111x@gmail.com", "SXXX",2, new Date(2023, Calendar.JANUARY, 2), 40, 50);
        ResourceDTO resourceDTO3 = new ResourceDTO ("22222x@gmail.com", "xssc", 1, new Date(2023, Calendar.JANUARY, 3), 40, 80);
        ResourceDTO resourceDTO4 = new ResourceDTO ("33333x@gmail.com", "dfgfd", 1, new Date(2023, Calendar.JANUARY,4), 40, 60);
        ResourceDTO resourceDTO5 = new ResourceDTO ("22222x@gmail.com", "devjv", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);
        ResourceDTO resourceDTO6 = new ResourceDTO ("33333x@gmail.com", "dkjr", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);
        ResourceDTO resourceDTO7 = new ResourceDTO ("33333x@gmail.com", "fkkme",3,new Date(2023, Calendar.JANUARY, 6), 30, 80);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project1);
        projectList.addProject(project2);
        projectList.addProject(project3);

        project1.addResource(account1,role1,resourceDTO1);
        project2.addResource(account1,role1,resourceDTO2);
        project1.addResource(account2,role2,resourceDTO3);
        project1.addResource(account3,role3,resourceDTO4);
        project2.addResource(account2,role3,resourceDTO5);
        project2.addResource(account3,role2,resourceDTO6);
        project3.addResource(account3,role3,resourceDTO7);

        ListUserProjectsController controller = new ListUserProjectsController(projectList);
        List<UserProjectsDTO> result = controller.listUserProjectsDTO(account1);
        assertTrue(result instanceof List);
        assertTrue(result.get(0) instanceof UserProjectsDTO);
    }

    @Test
    public void confirmListOfProjectsCodesWorks() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");
        Role role3 = new Role( "Project Owner");

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
        List<Integer> expected = controller.listUserProjectsDTO(account1);
        List<Integer> notExpected = Arrays.asList(1, 2, 1);

        assertNotEquals(expected,notExpected);
    }

    @Test
    public void listUserProjectsEmpty() {
        Project project1 = new Project(1,"Test1","For testing purposes");
        Project project2 = new Project(2,"Test2","For testing purposes");

        Profile profile = new Profile("User");

        Role role1 = new Role( "Team Member");
        Role role2 = new Role( "Scrum Master");
        Role role3 = new Role( "Project Owner");

        Account account1 = new Account ("Ricardo", "11111x@gmail.com", "33399988", profile);
        Account account2 = new Account ("Filipe", "22222x@gmail.com", "33399987", profile);
        Account account3 = new Account ("Tavares", "33333x@gmail.com", "33399986", profile);

        ResourceDTO resourceDTO3 = new ResourceDTO ("22222x@gmail.com", "xssc", 1, new Date(2023, Calendar.JANUARY, 3), 40, 80);
        ResourceDTO resourceDTO4 = new ResourceDTO ("33333x@gmail.com", "dfgfd", 1, new Date(2023, Calendar.JANUARY,4), 40, 60);
        ResourceDTO resourceDTO5 = new ResourceDTO ("22222x@gmail.com", "devjv", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);
        ResourceDTO resourceDTO6 = new ResourceDTO ("33333x@gmail.com", "dkjr", 2, new Date(2023, Calendar.JANUARY, 5), 40, 80);

        ProjectList projectList = new ProjectList();
        projectList.addProject(project1);
        projectList.addProject(project2);

        project1.addResource(account2,role2,resourceDTO3);
        project1.addResource(account3,role3,resourceDTO4);
        project2.addResource(account2,role3,resourceDTO5);
        project2.addResource(account3,role2,resourceDTO6);

        ListUserProjectsController controller = new ListUserProjectsController(projectList);
        List result = controller.listUserProjectsDTO(account1);
        List<Integer> notExpected = Arrays.asList(1);

        assertNotSame(notExpected,result);
        assertTrue(result.isEmpty());
    }
}