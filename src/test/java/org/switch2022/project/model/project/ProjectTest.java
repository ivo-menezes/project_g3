package org.switch2022.project.model.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.switch2022.project.mapper.ResourceDTO;
import org.switch2022.project.mapper.SprintDTO;
import org.switch2022.project.mapper.UserProjectsDTO;
import org.switch2022.project.model.ProductBacklog;
import org.switch2022.project.model.role.Role;
import org.switch2022.project.model.SprintList;
import org.switch2022.project.model.account.Account;
import org.switch2022.project.model.profile.Profile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProjectTest {

    @Test
    @DisplayName("Equal is true when comparing the same project")
    void EqualsIsTrueWhenComparingSameProject() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);

        assertTrue(project.equals(project));
    }

    @Test
    @DisplayName("Equal is true when comparing different projects")
    void EqualsIsTrueWhenComparingDifferentProjects() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project project2 = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        assertTrue(project.equals(project2) == project2.equals(project));
    }

    @Test
    @DisplayName("Equal is true when comparing more than three projects")
    void EqualsIsTrueWhenComparingMoreThanThreeProjects() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project project2 = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project project3 = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        assertTrue(project.equals(project2) && project2.equals(project) == project.equals(project3));
    }

    @Test
    @DisplayName("When project are called")
    void HashCodeWhenProjectAreCalledSomeTimes() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        int hash1 = project.hashCode();
        int hash2 = project.hashCode();
        int hash3 = project.hashCode();
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    @DisplayName("Equals is false when comparing different objects")
    void EqualsIsFalseWhenComparingDifferentProjects() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project projectTest = new Project(25, "Testing", "For Nothing", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Equals is false when comparing with null project")
    void EqualsIsFalseWhenComparingWithNullProject() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project projectTest = null;

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Equals equals and same")
    void testEqualsAndSame() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project projectTest = project;


        assertSame(projectTest, project);
        assertNotEquals(false, project.equals(projectTest));
    }

    @Test
    @DisplayName("test hash code")
    void testHashCode() {
        Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        Project projectTest = project;

        assertEquals(projectTest.hashCode(), project.hashCode());
        assertNotEquals(0, project.hashCode());
    }

    @Test
    @DisplayName("Check if the code is null when is zero")
    void checkIfCodeIsNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(0, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the code is null when is negative")
    void checkIfCodeIsNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(-2, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the name is null")
    void checkIfNameIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, null, "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the description is null")
    void checkIfDescriptionIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", null, new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check exception when start date is after the end date")
    void checkIfStartDateIsAfterEndDate() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the start date is null")
    void checkIfStartDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", null, new Date(2023, Calendar.FEBRUARY, 2), 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if the end date is null")
    void checkIfEndDateIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), null, 2, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if sprint duration is null when is zero")
    void checkIfSprintDurationIsNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 0, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if sprint duration is null when is negative")
    void checkIfSprintDurationIsNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), -15, 5, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if number of planned sprints are null when is zero")
    void checkIfNumberOfPlannedSprintsAreNullWhenIsZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 0, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if number of planned sprints are null when is negative")
    void checkIfNumberOfPlannedSprintsAreNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, -15, "Planned", 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if project status is null")
    void checkIfProjectStatusIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 2, 5, null, 1000);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Check if budget is null when is negative")
    void checkIfBudgetNullWhenIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", "For testing purposes", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), 5, 5, "Planned", -230);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("Equal is true when comparing the same project on second constructor")
    void EqualsIsTrueWhenComparingSameProjectSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");

        assertTrue(project.equals(project));
    }

    @Test
    @DisplayName("Equal is true when comparing different projects on second constructor")
    void EqualsIsTrueWhenComparingDifferentProjectsSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project project2 = new Project(26, "Test", "For testing purposes");
        assertTrue(project.equals(project2) == project2.equals(project));
    }

    @Test
    @DisplayName("Equal is true when comparing more than three projects on second constructor")
    void EqualsIsTrueWhenComparingMoreThanThreeProjectsSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project project2 = new Project(26, "Test", "For testing purposes");
        Project project3 = new Project(26, "Test", "For testing purposes");
        assertTrue(project.equals(project2) && project2.equals(project) == project.equals(project3));
    }

    @Test
    @DisplayName("When project are called on second constructor")
    void HashCodeWhenProjectAreCalledSomeTimesSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        int hash1 = project.hashCode();
        int hash2 = project.hashCode();
        int hash3 = project.hashCode();
        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
    }

    @Test
    void EqualsIsFalseWhenComparingDifferentProjectsSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project projectTest = new Project(25, "Testing", "For Nothing");

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void EqualsIsFalseWhenComparingWithNullProjectSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project projectTest = null;

        boolean result = project.equals(projectTest);

        Assertions.assertFalse(result);
    }

    @Test
    void testEqualsAndSameSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project projectTest = project;

        assertSame(projectTest, project);
        assertNotEquals(false, project.equals(projectTest));
    }

    @Test
    void testHashCodeSecondConstructor() {
        Project project = new Project(26, "Test", "For testing purposes");
        Project projectTest = project;

        assertEquals(projectTest.hashCode(), project.hashCode());
        assertNotEquals(0, project.hashCode());
    }

    @Test
    void checkIfCodeIsNullWhenIsZeroSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(0, "Test", "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfCodeIsNullWhenIsNegativeSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(-2, "Test", "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNameIsNullSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, null, "For testing purposes");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfDescriptionIsNullSecondConstructor() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", null);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    @DisplayName("ensure the resource has been added.")
    void ensureAddResourceTrue() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role("Team Member");

        //act
        boolean result = project.addResource(account, role, resourceDTO);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("ensure the resource has not been added.")
    void ensureAddResourceFalse() {
        //arrange
        Project project = new Project(1, "test", "test");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Role role = new Role("Team Member");

        //act
        boolean added = project.addResource(account, role, resourceDTO);
        boolean result = project.addResource(account, role, resourceDTO);

        //assert
        assertFalse(result);
    }

    @Test
    void checkIfCodeIsNullWhenIsZeroInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(0, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfCodeIsNullWhenIsNegativeInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(-2, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfNameIsNullInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, null, new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsAfterEndDateInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfStartDateIsNullInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", null, new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfEndDateIsNullInListProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), null, "client1", "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfCostumerIsNullInLIstProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), null, "Planned");
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void checkIfProjectStatusIsNullInLIstProj() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {

            Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", null);
        });
        Assertions.assertEquals("Missing mandatory details.", exception.getMessage());
    }

    @Test
    void testIfGetsCode() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        int expected = 26;
        int code = project.getCode();
        assertEquals(expected, code);
    }

    @Test
    void testIfGetsName() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        String expected = "Test";
        String name = project.getName();
        assertEquals(expected, name);
    }

    @Test
    void testIfGetsStartDate() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        Date expected = new Date(2023, Calendar.JANUARY, 1);
        Date startDate = project.getStartDate();
        assertEquals(expected, startDate);
    }

    @Test
    void testIfGetsEndDate() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        Date expected = new Date(2023, Calendar.FEBRUARY, 2);
        Date endDate = project.getEndDate();
        assertEquals(expected, endDate);
    }

    @Test
    void testIfGetsCustomer() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        String expected = "client1";
        String customer = project.getCustomer();
        assertEquals(expected, customer);
    }

    @Test
    void testIfGetProjectStatus() {
        Project project = new Project(26, "Test", new Date(2023, Calendar.JANUARY, 1), new Date(2023, Calendar.FEBRUARY, 2), "client1", "Planned");
        String expected = "Planned";
        String projectStatus = project.getProjectStatus();
        assertEquals(expected, projectStatus);
    }

    @Test
    @DisplayName("get a list of resources as a list of DTOs")
    void getAListOfResources() {
        // arrange
        Project project = new Project(1, "test", "test");
        Account account = new Account("Deborah", "deborah@hotmail.com", "938966782", new Profile("User"));
        Account account1 = new Account("Pedro", "pedro@example.com", "9666666", new Profile("User"));
        Role role = new Role("Team Member");
        ResourceDTO resourceDTO = new ResourceDTO("deborah@hotmail.com", "Team Member", 1, new Date(), 25, 100);
        ResourceDTO resourceDTO1 = new ResourceDTO("pedro@example.com", "Team Member", 1, new Date(), 50, 95);
        project.addResource(account, role, resourceDTO);
        project.addResource(account1, role, resourceDTO1);

        List<ResourceDTO> listExpected = new ArrayList<>();
        ResourceDTO resourceDTO_Output = new ResourceDTO("deborah@hotmail.com", "Team Member");
        ResourceDTO resourceDTO1_Output = new ResourceDTO("pedro@example.com", "Team Member");
        listExpected.add(resourceDTO_Output);
        listExpected.add(resourceDTO1_Output);
        // act
        List<ResourceDTO> listResult = project.listResources();

        // assert
        assertTrue(listExpected.containsAll(listResult) && listResult.containsAll(listExpected));
    }

    @Test
    @DisplayName("ensure the userProjectsDTO is not null.")
    void ensureUserProjectsDTOIsNotNull() {

        Project project = new Project(1, "Test1", "ddd");

        UserProjectsDTO DTO1 = project.createUserProjectsDTO(project);
        UserProjectsDTO DTO2 = DTO1;

        assertNotNull(DTO1);
        assertSame(DTO1, DTO2);
    }

    @Test
    @DisplayName("addSprintProject")
    void addSprintProjectSuccessfully() {
        //arrange
        Project project = new Project(1, "test", "test");
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(1 / 2 / 2023);
        sprintDTO.endDate = new Date(31 / 2 / 2023);
        //act
        boolean result = project.addSprint(sprintDTO);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("addSprintProject")
    void addTwoDifferentSprintProject() {
        //arrange
        Project project = new Project(1, "test", "test");
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(1 / 2 / 2023);
        sprintDTO.endDate = new Date(31 / 2 / 2023);

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 2;
        sprintDTOTwo.startDate = new Date(1 / 3 / 2023);
        sprintDTOTwo.endDate = new Date(31 / 3 / 2023);
        //act
        boolean result = project.addSprint(sprintDTO);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("addSprintProject")
    void addSameSprintProject() {
        //arrange
        Project project = new Project(1, "test", "test");
        SprintDTO sprintDTO = new SprintDTO();
        sprintDTO.sprintNumber = 1;
        sprintDTO.startDate = new Date(1 / 2 / 2023);
        sprintDTO.endDate = new Date(31 / 2 / 2023);

        SprintDTO sprintDTOTwo = new SprintDTO();
        sprintDTOTwo.sprintNumber = 1;
        sprintDTOTwo.startDate = new Date(1 / 2 / 2023);
        sprintDTOTwo.endDate = new Date(31 / 2 / 2023);
        //act
        project.addSprint(sprintDTO);
        boolean result = project.addSprint(sprintDTOTwo);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Get product backlog")
    void getProductBacklog() {
        //arrange
        Project project = mock(Project.class);
        ProductBacklog productBacklog = mock(ProductBacklog.class);

        Mockito.when(project.getProductBacklog()).thenReturn(productBacklog);

        //act
        ProductBacklog result = project.getProductBacklog();

         // assert
        assertEquals(productBacklog, result);
    }

    @Test
    @DisplayName("Get sprint list")
    void getSprintList() {
        //arrange
        Project project = mock(Project.class);
        SprintList sprintList = mock(SprintList.class);

        Mockito.when(project.getSprintList()).thenReturn(sprintList);

        //act
        SprintList result = project.getSprintList();

        // assert
        assertEquals(sprintList, result);
    }

}
