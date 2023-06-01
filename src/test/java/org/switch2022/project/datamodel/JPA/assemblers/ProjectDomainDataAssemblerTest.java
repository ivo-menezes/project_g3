
package org.switch2022.project.datamodel.JPA.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.switch2022.project.datamodel.JPA.ProjectJpa;
import org.switch2022.project.model.project.ProjectDDD;
import org.switch2022.project.model.valueobject.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectDomainDataAssemblerTest {

    @Test
    @DisplayName("Ensure Project is correctly converted to ProjectJpa")
    void testProjectToProjectJpaConversion(){
        //Arrange
        ProjectDDD projectDouble = mock(ProjectDDD.class);

        ProjectCode projectCodeDouble = mock(ProjectCode.class);
        when(projectDouble.identity()).thenReturn(projectCodeDouble);
        when(projectCodeDouble.toString()).thenReturn("P001");

        ProjectName projectNameDouble = mock(ProjectName.class);
        when(projectDouble.getProjectName()).thenReturn(projectNameDouble);
        when(projectNameDouble.toString()).thenReturn("Awesome Project");

        Description descriptionDouble = mock(Description.class);
        when(projectDouble.getDescription()).thenReturn(descriptionDouble);
        when(projectNameDouble.toString()).thenReturn("This is a description");

        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        when(projectDouble.getProjectStatus()).thenReturn(projectStatusDouble);
        when(projectStatusDouble.toString()).thenReturn("Planned");
        //dealing with timePeriod which has a startDate and an endDate
        TimePeriod timePeriodDouble = mock(TimePeriod.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date startDate = calendar.getTime();

        calendar.set(2023, Calendar.MAY, 1);
        Date endDate = calendar.getTime();
        when(projectDouble.getTimePeriod()).thenReturn(timePeriodDouble);
        when(timePeriodDouble.getStartDate()).thenReturn(startDate);
        when(timePeriodDouble.getEndDate()).thenReturn(endDate);

        ProjectSprintDuration sprintDurationDouble = mock(ProjectSprintDuration.class);
        when(projectDouble.getProjectSprintDuration()).thenReturn(sprintDurationDouble);
        when(sprintDurationDouble.getValue()).thenReturn(3);

        ProjectNumberOfPlannedSprints numberOfPlannedSprintsDouble = mock(ProjectNumberOfPlannedSprints.class);
        when(projectDouble.getProjectNumberOfPlannedSprints()).thenReturn(numberOfPlannedSprintsDouble);
        when(numberOfPlannedSprintsDouble.getValue()).thenReturn(5);

        CustomerID customerIdDouble = mock(CustomerID.class);
        when(projectDouble.getCustomerID()).thenReturn(customerIdDouble);
        when(customerIdDouble.getId()).thenReturn(10L);

        BusinessSectorID businessSectorIDDouble = mock(BusinessSectorID.class);
        when(projectDouble.getBusinessSectorID()).thenReturn(businessSectorIDDouble);
        when(businessSectorIDDouble.getId()).thenReturn(11L);

        TypologyID typologyIDDouble = mock(TypologyID.class);
        when(projectDouble.getTypologyID()).thenReturn(typologyIDDouble);
        when(typologyIDDouble.getId()).thenReturn(12L);

        ProjectBudget projectBudgetDouble = mock(ProjectBudget.class);
        when(projectDouble.getProjectBudget()).thenReturn(projectBudgetDouble);
        when(projectBudgetDouble.getValue()).thenReturn(1000.0f);
        //dealing with productBacklog
        List<UserStoryID> productBacklog = new ArrayList<>();
        UserStoryID userStory1 = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble1 = mock(UserStoryNumber.class);
        when(userStory1.getUserStoryNumber()).thenReturn(userStoryNumberDouble1);
        when(userStory1.getUserStoryNumber().toString()).thenReturn("US001");
        productBacklog.add(userStory1);

        UserStoryID userStory2 = mock(UserStoryID.class);
        UserStoryNumber userStoryNumberDouble2 = mock(UserStoryNumber.class);
        when(userStory2.getUserStoryNumber()).thenReturn(userStoryNumberDouble2);
        when(userStory2.getUserStoryNumber().toString()).thenReturn("US002");
        productBacklog.add(userStory2);

        List<String> productBacklogJpa = new ArrayList<>();
        for (UserStoryID userStoryID : productBacklog) {
            String userStoryNumber = userStoryID.getUserStoryNumber().toString();
            productBacklogJpa.add(userStoryNumber);
        }

        ProjectDomainDataAssembler assembler = new ProjectDomainDataAssembler();

        ProjectJpa expectedProjectJpa = new ProjectJpa(
                "P001",
                "Awesome Project",
                "This is a description",
                "Planned",
                startDate,
                endDate,
                3,
                5,
                10L,
                11L,
                12L,
                1000.0f,
                productBacklogJpa
        );

        //Act
        ProjectJpa returnedProjectJpa = assembler.toData(projectDouble);

        //Assert
        assertInstanceOf(ProjectJpa.class, returnedProjectJpa);
        assertEquals(expectedProjectJpa, returnedProjectJpa);
    }

    @Test
    @DisplayName("Ensure ProjectJpa is correctly converted to Project")
    void testProjectJpaToProjectConversion(){
        ProjectJpa projectJpa = mock(ProjectJpa.class);
        when(projectJpa.getProjectCode()).thenReturn("P001");
        when(projectJpa.getProjectName()).thenReturn("Awesome Project");
        when(projectJpa.getDescription()).thenReturn("This is a description");
        when(projectJpa.getProjectStatus()).thenReturn("Planned");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);
        Date startDate = calendar.getTime();
        when(projectJpa.getStartDate()).thenReturn(startDate);
        calendar.set(2023, Calendar.MAY, 1);
        Date endDate = calendar.getTime();
        when(projectJpa.getEndDate()).thenReturn(endDate);

        when(projectJpa.getSprintDuration()).thenReturn(3);
        when(projectJpa.getProjectNumberOfPlannedSprints()).thenReturn(5);
        when(projectJpa.getCustomerID()).thenReturn(10L);
        when(projectJpa.getBusinessSectorID()).thenReturn(11L);
        when(projectJpa.getTypologyID()).thenReturn(12L);
        when(projectJpa.getProjectBudget()).thenReturn(1000.0f);

        List<UserStoryID> productBacklog = new ArrayList<>();
        UserStoryID userStory1 = mock(UserStoryID.class);
        UserStoryNumber userStoryNumber1 = mock(UserStoryNumber.class);
        when(userStory1.getUserStoryNumber()).thenReturn(userStoryNumber1);
        when(userStoryNumber1.toString()).thenReturn("US001");
        productBacklog.add(userStory1);

        UserStoryID userStory2 = mock(UserStoryID.class);
        UserStoryNumber userStoryNumber2 = mock(UserStoryNumber.class);
        when(userStory2.getUserStoryNumber()).thenReturn(userStoryNumber2);
        when(userStoryNumber2.toString()).thenReturn("US002");
        productBacklog.add(userStory2);

        List<String> productBacklogJpa = new ArrayList<>();
        for (UserStoryID userStoryID : productBacklog) {
            productBacklogJpa.add(userStoryID.getUserStoryNumber().toString());
        }
        when(projectJpa.getProductBacklog()).thenReturn(productBacklogJpa);

        ProjectDDD expectedProject = new ProjectDDD(
                new ProjectCode("P001"),
                new ProjectName("Awesome Project"),
                new Description("This is a description"),
                ProjectStatus.Planned,
                new TimePeriod(startDate, endDate),
                new ProjectSprintDuration(3),
                new ProjectNumberOfPlannedSprints(5),
                new CustomerID(10L),
                new BusinessSectorID(11L),
                new TypologyID(12L),
                new ProjectBudget(1000.0f),
                productBacklog

        );

        ProjectDomainDataAssembler assembler = new ProjectDomainDataAssembler();

        // Act
        ProjectDDD returnedProject = assembler.toDomain(projectJpa);

        //Assert
        assertInstanceOf(ProjectDDD.class, returnedProject);
        assertEquals(expectedProject, returnedProject);
    }
}
