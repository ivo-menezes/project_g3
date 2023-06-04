package org.switch2022.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.*;
import org.switch2022.project.mapper.sprintDTOs.SprintDTOController;
import org.switch2022.project.model.valueobject.*;
import org.switch2022.project.service.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Profile("!test")
@Component
public class DataLoader implements CommandLineRunner {
    /**
     * This class runs at the same time as application.java.
     * So is necessary to avoid problems to run tests add the annotation @ActiveProfiles("test") in each test class of Services injected.
     */
    final
    TypologyService typologyService;
    final
    CustomerService customerService;
    final
    BusinessSectorService businessSectorService;

    final
    ProjectService projectService;

    final
    SprintServiceDDD sprintService;

    final
    UserStoryService userStoryService;

    public DataLoader(TypologyService typologyService, CustomerService customerService,
                      BusinessSectorService businessSectorService,
                      ProjectService projectService, SprintServiceDDD sprintService,
                      UserStoryService userStoryService) {
        this.typologyService = typologyService;
        this.customerService = customerService;
        this.businessSectorService = businessSectorService;
        this.projectService = projectService;
        this.sprintService = sprintService;
        this.userStoryService = userStoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    /**
     * This method populates the database with data;
     */
    private void loadData() {

        //Load Customers
        CustomerNIF customerNIFOne = new CustomerNIF("306123987");
        CustomerDesignation customerDesignationOne = new CustomerDesignation("XPTO, SA");

        CustomerNIF customerNIFTwo = new CustomerNIF("306123988");
        CustomerDesignation customerDesignationTwo = new CustomerDesignation("XYZ, Lda");

        CustomerNIF customerNIFThree = new CustomerNIF("306123989");
        CustomerDesignation customerDesignationThree = new CustomerDesignation("Hell, LLC");

        CustomerDTO customerOne = new CustomerDTO();
        customerOne.customerNIF = customerNIFOne;
        customerOne.customerDesignation = customerDesignationOne;

        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.customerNIF = customerNIFTwo;
        customerTwo.customerDesignation = customerDesignationTwo;

        CustomerDTO customerThree = new CustomerDTO();
        customerThree.customerNIF = customerNIFThree;
        customerThree.customerDesignation = customerDesignationThree;

        CustomerID customerID_1 = new CustomerID(customerService.createCustomer(customerOne).customerID.getId());
        CustomerID customerID_2 = new CustomerID(customerService.createCustomer(customerTwo).customerID.getId());
        CustomerID customerID_3 = new CustomerID(customerService.createCustomer(customerThree).customerID.getId());

        //Load Business Sectors
        BusinessSectorDesignation businessSectorDesignationOne = new BusinessSectorDesignation("It doesn't matter");
        BusinessSectorDesignation businessSectorDesignationTwo = new BusinessSectorDesignation("Hospitality industry");

        BusinessSectorDTO businessSectorOne = new BusinessSectorDTO();
        businessSectorOne.businessSectorDesignation = businessSectorDesignationOne;

        BusinessSectorDTO businessSectorTwo = new BusinessSectorDTO();
        businessSectorTwo.businessSectorDesignation = businessSectorDesignationTwo;

        BusinessSectorID businessSectorID_1 = new BusinessSectorID(businessSectorService.createBusinessSector(businessSectorOne).businessSectorID.getId());
        BusinessSectorID businessSectorID_2 = new BusinessSectorID(businessSectorService.createBusinessSector(businessSectorTwo).businessSectorID.getId());

        //Load Typologies
        TypologyDesignation typologyDesignationOne = new TypologyDesignation("Fixed cost");
        TypologyDesignation typologyDesignationTwo = new TypologyDesignation("Time and materials");

        TypologyDTO typologyOne = new TypologyDTO();
        typologyOne.typologyDesignation = typologyDesignationOne;

        TypologyDTO typologyTwo = new TypologyDTO();
        typologyTwo.typologyDesignation = typologyDesignationTwo;

        TypologyID typologyID_1 = new TypologyID(typologyService.createTypology(typologyOne).typologyID.getId());
        TypologyID typologyID_2 = new TypologyID(typologyService.createTypology(typologyTwo).typologyID.getId());

        //Load Projects

        //Project 1
        ProjectCode projectCode_1 = new ProjectCode("A1");
        ProjectName projectName_1 = new ProjectName("Dummy01");
        Description description_1 = new Description("Just a dummy project");
        LocalDate startDate_project_1 = LocalDate.of(2022, 3, 1);
        Date newStardDate_project_1 = Date.from(startDate_project_1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDate_project_1 = LocalDate.of(2022, 7, 31);
        Date newEndDate_project_1 = Date.from(endDate_project_1.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_1 = new TimePeriod(newStardDate_project_1, newEndDate_project_1);
        //ProjectStatus projectStatus_1 = new ProjectStatus();
        ProjectSprintDuration projectSprintDuration_1 = new ProjectSprintDuration(2);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints_1 = new ProjectNumberOfPlannedSprints(8);
        ProjectBudget projectBudget_1 = new ProjectBudget(150000.00F);

        NewProjectDTO project_1 = createProjectDTO(customerID_1, businessSectorID_1, typologyID_1, projectCode_1, projectName_1,
                description_1, timePeriod_1, projectSprintDuration_1, projectNumberOfPlannedSprints_1, projectBudget_1);

        projectService.createProject(project_1);

        //Project 2
        ProjectCode projectCode_2 = new ProjectCode("A2");
        ProjectName projectName_2 = new ProjectName("Dummy02");
        Description description_2 = new Description("Just another dummy project");

        LocalDate startDate_project_2 = LocalDate.of(2022, 5, 31);
        Date newStardDate_project_2 = Date.from(startDate_project_2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDate_project_2 = LocalDate.of(2023, 4, 29);
        Date newEndDate_project_2 = Date.from(endDate_project_2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_2 = new TimePeriod(newStardDate_project_2, newEndDate_project_2);
        ProjectSprintDuration projectSprintDuration_2 = new ProjectSprintDuration(4);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints_2 = new ProjectNumberOfPlannedSprints(12);
        ProjectBudget projectBudget_2 = new ProjectBudget(350000.00F);

        NewProjectDTO project_2 = createProjectDTO(customerID_2, businessSectorID_1,
                typologyID_1, projectCode_2, projectName_2, description_2, timePeriod_2,
                projectSprintDuration_2, projectNumberOfPlannedSprints_2,
                projectBudget_2);

        projectService.createProject(project_2);

        //Project 3
        ProjectCode projectCode_3 = new ProjectCode("666");
        ProjectName projectName_3 = new ProjectName("Inevitable nightmare");
        Description description_3 = new Description("Doomed from the start");

        LocalDate startDate_project_3 = LocalDate.of(2023, 3, 10);
        Date newStardDate_project_3 = Date.from(startDate_project_3.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_3 = new TimePeriod(newStardDate_project_3, new Date());
        ProjectSprintDuration projectSprintDuration_3 = new ProjectSprintDuration(3);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints_3 = new ProjectNumberOfPlannedSprints(15);
        ProjectBudget projectBudget_3 = new ProjectBudget(750000.00F);

        NewProjectDTO project_3 = createProjectDTO(customerID_3, businessSectorID_2,
                typologyID_2, projectCode_3, projectName_3, description_3, timePeriod_3,
                projectSprintDuration_3, projectNumberOfPlannedSprints_3,
                projectBudget_3);

        projectService.createProject(project_3);

        //Load User Stories

        // User story 1
        UserStoryNumber userStoryNumber_1 = new UserStoryNumber("US01");
        UserStoryActor actor_1 = new UserStoryActor("Team member");
        Description usDescription_1 = new Description("Dummy 01");
        UserStoryAcceptanceCriteria acceptanceCriteria_1 = new UserStoryAcceptanceCriteria("To be defined");
        UserStoryPriority userStoryPriority_1 = new UserStoryPriority(3);

        NewUserStoryInfoDTO userStoryDTO_1 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_1,
        usDescription_1, userStoryPriority_1);

        userStoryService.createUserStory(userStoryDTO_1);

        // User story 2
        UserStoryNumber userStoryNumber_2 = new UserStoryNumber("US02");
        Description usDescription_2 = new Description("Dummy 02");
        UserStoryPriority userStoryPriority_2 = new UserStoryPriority(2);

        NewUserStoryInfoDTO userStoryInfoDTO_2 = createUserStoryDTO(projectCode_1, actor_1,
        acceptanceCriteria_1, userStoryNumber_2, usDescription_2, userStoryPriority_2);

        userStoryService.createUserStory(userStoryInfoDTO_2);

        // User story 3
        UserStoryNumber userStoryNumber_3 = new UserStoryNumber("US03");
        Description usDescription_3 = new Description("Dummy 03");
        UserStoryPriority userStoryPriority_3 = new UserStoryPriority(1);

        NewUserStoryInfoDTO userStoryInfoDTO_3 = createUserStoryDTO(projectCode_1, actor_1,
        acceptanceCriteria_1, userStoryNumber_3, usDescription_3, userStoryPriority_3);

        userStoryService.createUserStory(userStoryInfoDTO_3);

        // User story 4
        UserStoryNumber userStoryNumber_4 = new UserStoryNumber("US04");
        Description usDescription_4 = new Description("Dummy 04");
        UserStoryPriority userStoryPriority_4 = new UserStoryPriority(4);

        NewUserStoryInfoDTO userStoryInfoDTO_4 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_4, usDescription_4,
        userStoryPriority_4);

        userStoryService.createUserStory(userStoryInfoDTO_4);

        // User story 5
        UserStoryNumber userStoryNumber_5 = new UserStoryNumber("US05");
        Description usDescription_5 = new Description("Dummy 05");
        UserStoryPriority userStoryPriority_5 = new UserStoryPriority(5);

        NewUserStoryInfoDTO userStoryInfoDTO_5 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_5, usDescription_5,
        userStoryPriority_5);

        userStoryService.createUserStory(userStoryInfoDTO_5);

        // User story 6
        UserStoryNumber userStoryNumber_6 = new UserStoryNumber("US06");
        Description usDescription_6 = new Description("Dummy 06");
        UserStoryPriority userStoryPriority_6 = new UserStoryPriority(6);

        NewUserStoryInfoDTO userStoryInfoDTO_6 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_6, usDescription_6,
        userStoryPriority_6);

        userStoryService.createUserStory(userStoryInfoDTO_6);

        // User story 7
        UserStoryNumber userStoryNumber_7 = new UserStoryNumber("US07");
        Description usDescription_7 = new Description("Dummy 07");
        UserStoryPriority userStoryPriority_7 = new UserStoryPriority(7);

        NewUserStoryInfoDTO userStoryInfoDTO_7 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_7, usDescription_7,
        userStoryPriority_7);

        userStoryService.createUserStory(userStoryInfoDTO_7);

        // User story 8
        UserStoryNumber userStoryNumber_8 = new UserStoryNumber("US08");
        Description usDescription_8 = new Description("Dummy 08");
        UserStoryPriority userStoryPriority_8 = new UserStoryPriority(8);

        NewUserStoryInfoDTO userStoryInfoDTO_8 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_8, usDescription_8,
        userStoryPriority_8);

        userStoryService.createUserStory(userStoryInfoDTO_8);

        // User story 9
        UserStoryNumber userStoryNumber_9 = new UserStoryNumber("US09");
        Description usDescription_9 = new Description("Dummy 09");
        UserStoryPriority userStoryPriority_9 = new UserStoryPriority(9);

        NewUserStoryInfoDTO userStoryInfoDTO_9 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_9, usDescription_9,
        userStoryPriority_9);

        userStoryService.createUserStory(userStoryInfoDTO_9);

        // User story 10
        UserStoryNumber userStoryNumber_10 = new UserStoryNumber("US10");
        Description usDescription_10 = new Description("Dummy 10");
        UserStoryPriority userStoryPriority_10 = new UserStoryPriority(10);

        NewUserStoryInfoDTO userStoryInfoDTO_10 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_10, usDescription_10,
        userStoryPriority_10);

        userStoryService.createUserStory(userStoryInfoDTO_10);

        // User story 11
        UserStoryNumber userStoryNumber_11 = new UserStoryNumber("US11");
        Description usDescription_11 = new Description("Dummy 11");
        UserStoryPriority userStoryPriority_11 = new UserStoryPriority(11);

        NewUserStoryInfoDTO userStoryInfoDTO_11 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_11, usDescription_11,
        userStoryPriority_11);

        userStoryService.createUserStory(userStoryInfoDTO_11);

        // User story 12
        UserStoryNumber userStoryNumber_12 = new UserStoryNumber("US12");
        Description usDescription_12 = new Description("Dummy 12");
        UserStoryPriority userStoryPriority_12 = new UserStoryPriority(12);

        NewUserStoryInfoDTO userStoryInfoDTO_12 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_12, usDescription_12,
        userStoryPriority_12);

        userStoryService.createUserStory(userStoryInfoDTO_12);

        // User story 13
        UserStoryNumber userStoryNumber_13 = new UserStoryNumber("US13");
        Description usDescription_13 = new Description("Dummy 13");
        UserStoryPriority userStoryPriority_13 = new UserStoryPriority(13);

        NewUserStoryInfoDTO userStoryInfoDTO_13 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_13, usDescription_13,
        userStoryPriority_13);

        userStoryService.createUserStory(userStoryInfoDTO_13);

        // User story 14
        UserStoryNumber userStoryNumber_14 = new UserStoryNumber("US14");
        Description usDescription_14 = new Description("Dummy 14");
        UserStoryPriority userStoryPriority_14 = new UserStoryPriority(14);

        NewUserStoryInfoDTO userStoryInfoDTO_14 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_14, usDescription_14,
        userStoryPriority_14);

        userStoryService.createUserStory(userStoryInfoDTO_14);

        // User story 14
        UserStoryNumber userStoryNumber_15 = new UserStoryNumber("US15");
        Description usDescription_15 = new Description("Dummy 15");
        UserStoryPriority userStoryPriority_15 = new UserStoryPriority(15);

        NewUserStoryInfoDTO userStoryInfoDTO_15 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_15, usDescription_15,
        userStoryPriority_15);

        userStoryService.createUserStory(userStoryInfoDTO_15);

        // User story 16
        UserStoryNumber userStoryNumber_16 = new UserStoryNumber("US16");
        Description usDescription_16 = new Description("Dummy 16");
        UserStoryPriority userStoryPriority_16 = new UserStoryPriority(16);

        NewUserStoryInfoDTO userStoryInfoDTO_16 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_16, usDescription_16,
        userStoryPriority_16);

        userStoryService.createUserStory(userStoryInfoDTO_16);

        // User story 17
        UserStoryNumber userStoryNumber_17 = new UserStoryNumber("US17");
        Description usDescription_17 = new Description("Dummy 17");
        UserStoryPriority userStoryPriority_17 = new UserStoryPriority(17);

        NewUserStoryInfoDTO userStoryInfoDTO_17 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_17, usDescription_17,
        userStoryPriority_17);

        userStoryService.createUserStory(userStoryInfoDTO_17);

        // User story 18
        UserStoryNumber userStoryNumber_18 = new UserStoryNumber("US18");
        Description usDescription_18 = new Description("Dummy 18");
        UserStoryPriority userStoryPriority_18 = new UserStoryPriority(18);

        NewUserStoryInfoDTO userStoryInfoDTO_18 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_18, usDescription_18,
        userStoryPriority_18);

        userStoryService.createUserStory(userStoryInfoDTO_18);

        // User story 19
        UserStoryNumber userStoryNumber_19 = new UserStoryNumber("US19");
        Description usDescription_19 = new Description("Dummy 19");
        UserStoryPriority userStoryPriority_19 = new UserStoryPriority(19);

        NewUserStoryInfoDTO userStoryInfoDTO_19 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_19, usDescription_19,
        userStoryPriority_19);

        userStoryService.createUserStory(userStoryInfoDTO_19);

        // User story 20
        UserStoryNumber userStoryNumber_20 = new UserStoryNumber("US20");
        Description usDescription_20 = new Description("Dummy 20");
        UserStoryPriority userStoryPriority_20 = new UserStoryPriority(20);

        NewUserStoryInfoDTO userStoryInfoDTO_20 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_20, usDescription_20,
        userStoryPriority_20);

        userStoryService.createUserStory(userStoryInfoDTO_20);

        // User story 21
        UserStoryNumber userStoryNumber_21 = new UserStoryNumber("US21");
        Description usDescription_21 = new Description("Dummy 21");
        UserStoryPriority userStoryPriority_21 = new UserStoryPriority(21);

        NewUserStoryInfoDTO userStoryInfoDTO_21 = createUserStoryDTO(projectCode_1,
        actor_1, acceptanceCriteria_1, userStoryNumber_21, usDescription_21,
        userStoryPriority_21);

        userStoryService.createUserStory(userStoryInfoDTO_21);


        // User story 1 P2
        UserStoryNumber userStoryNumber_1P2 = new UserStoryNumber("US001");
        UserStoryActor actor_2 = new UserStoryActor("Project Manager");
        Description usDescription_1P2 = new Description("A2 dummy 01");
        UserStoryAcceptanceCriteria acceptanceCriteria_2 = new UserStoryAcceptanceCriteria("To be defined");
        UserStoryPriority userStoryPriority_1P2 = new UserStoryPriority(3);

        NewUserStoryInfoDTO userStoryDTO_1P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_1P2,
        usDescription_1P2, userStoryPriority_1P2);

        userStoryService.createUserStory(userStoryDTO_1P2);

        // User story 2 P2
        UserStoryNumber userStoryNumber_2P2 = new UserStoryNumber("US002");
        Description usDescription_2P2 = new Description("A2 dummy 02");
        UserStoryPriority userStoryPriority_2P2 = new UserStoryPriority(2);

        NewUserStoryInfoDTO userStoryInfoDTO_2P2 = createUserStoryDTO(projectCode_2, actor_2,
        acceptanceCriteria_2, userStoryNumber_2P2, usDescription_2P2, userStoryPriority_2P2);

        userStoryService.createUserStory(userStoryInfoDTO_2P2);

        // User story 3 P2
        UserStoryNumber userStoryNumber_3P2 = new UserStoryNumber("US003");
        Description usDescription_3P2 = new Description("A2 dummy 03");
        UserStoryPriority userStoryPriority_3P2 = new UserStoryPriority(1);

        NewUserStoryInfoDTO userStoryInfoDTO_3P2 = createUserStoryDTO(projectCode_2, actor_2,
        acceptanceCriteria_2, userStoryNumber_3P2, usDescription_3P2, userStoryPriority_3P2);

        userStoryService.createUserStory(userStoryInfoDTO_3P2);

        // User story 4 P2
        UserStoryNumber userStoryNumber_4P2 = new UserStoryNumber("US004");
        Description usDescription_4P2 = new Description("A2 dummy 04");
        UserStoryPriority userStoryPriority_4P2 = new UserStoryPriority(4);

        NewUserStoryInfoDTO userStoryInfoDTO_4P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_4P2, usDescription_4P2,
        userStoryPriority_4P2);

        userStoryService.createUserStory(userStoryInfoDTO_4P2);

        // User story 5 P2
        UserStoryNumber userStoryNumber_5P2 = new UserStoryNumber("US005");
        Description usDescription_5P2 = new Description("A2 dummy 05");
        UserStoryPriority userStoryPriority_5P2 = new UserStoryPriority(5);

        NewUserStoryInfoDTO userStoryInfoDTO_5P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_5P2, usDescription_5P2,
        userStoryPriority_5P2);

        userStoryService.createUserStory(userStoryInfoDTO_5P2);

        // User story 6 P2
        UserStoryNumber userStoryNumber_6P2 = new UserStoryNumber("US006");
        Description usDescription_6P2 = new Description("A2 dummy 06");
        UserStoryPriority userStoryPriority_6P2 = new UserStoryPriority(6);

        NewUserStoryInfoDTO userStoryInfoDTO_6P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_6P2, usDescription_6P2,
        userStoryPriority_6P2);

        userStoryService.createUserStory(userStoryInfoDTO_6P2);

        // User story 7 P2
        UserStoryNumber userStoryNumber_7P2 = new UserStoryNumber("US007");
        Description usDescription_7P2 = new Description("A2 dummy 07");
        UserStoryPriority userStoryPriority_7P2 = new UserStoryPriority(7);

        NewUserStoryInfoDTO userStoryInfoDTO_7P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_7P2, usDescription_7P2,
        userStoryPriority_7P2);

        userStoryService.createUserStory(userStoryInfoDTO_7P2);

        // User story 8 P2
        UserStoryNumber userStoryNumber_8P2 = new UserStoryNumber("US008");
        Description usDescription_8P2 = new Description("A2 dummy 08");
        UserStoryPriority userStoryPriority_8P2 = new UserStoryPriority(8);

        NewUserStoryInfoDTO userStoryInfoDTO_8P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_8P2, usDescription_8P2,
        userStoryPriority_8P2);

        userStoryService.createUserStory(userStoryInfoDTO_8P2);

        // User story 9 P2
        UserStoryNumber userStoryNumber_9P2 = new UserStoryNumber("US009");
        Description usDescription_9P2 = new Description("A2 dummy 09");
        UserStoryPriority userStoryPriority_9P2 = new UserStoryPriority(9);

        NewUserStoryInfoDTO userStoryInfoDTO_9P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_9P2, usDescription_9P2,
        userStoryPriority_9P2);

        userStoryService.createUserStory(userStoryInfoDTO_9P2);

        // User story 10 P2
        UserStoryNumber userStoryNumber_10P2 = new UserStoryNumber("US010");
        Description usDescription_10P2 = new Description("A2 dummy 10");
        UserStoryPriority userStoryPriority_10P2 = new UserStoryPriority(10);

        NewUserStoryInfoDTO userStoryInfoDTO_10P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_10P2, usDescription_10P2,
        userStoryPriority_10P2);

        userStoryService.createUserStory(userStoryInfoDTO_10P2);

        // User story 11 P2
        UserStoryNumber userStoryNumber_11P2 = new UserStoryNumber("US011");
        Description usDescription_11P2 = new Description("A2 dummy 11");
        UserStoryPriority userStoryPriority_11P2 = new UserStoryPriority(11);

        NewUserStoryInfoDTO userStoryInfoDTO_11P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_11P2, usDescription_11P2,
        userStoryPriority_11P2);

        userStoryService.createUserStory(userStoryInfoDTO_11P2);

        // User story 12 P2
        UserStoryNumber userStoryNumber_12P2 = new UserStoryNumber("US012");
        Description usDescription_12P2 = new Description("A2 dummy 12");
        UserStoryPriority userStoryPriority_12P2 = new UserStoryPriority(12);

        NewUserStoryInfoDTO userStoryInfoDTO_12P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_12P2, usDescription_12P2,
        userStoryPriority_12P2);

        userStoryService.createUserStory(userStoryInfoDTO_12P2);

        // User story 13 P2
        UserStoryNumber userStoryNumber_13P2 = new UserStoryNumber("US013");
        Description usDescription_13P2 = new Description("A2 dummy 13");
        UserStoryPriority userStoryPriority_13P2 = new UserStoryPriority(13);

        NewUserStoryInfoDTO userStoryInfoDTO_13P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_13P2, usDescription_13P2,
        userStoryPriority_13P2);

        userStoryService.createUserStory(userStoryInfoDTO_13P2);

        // User story 14 P2
        UserStoryNumber userStoryNumber_14P2 = new UserStoryNumber("US014");
        Description usDescription_14P2 = new Description("A2 dummy 14");
        UserStoryPriority userStoryPriority_14P2 = new UserStoryPriority(14);

        NewUserStoryInfoDTO userStoryInfoDTO_14P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_14P2, usDescription_14P2,
        userStoryPriority_14P2);

        userStoryService.createUserStory(userStoryInfoDTO_14P2);

        // User story 14 P2
        UserStoryNumber userStoryNumber_15P2 = new UserStoryNumber("US015");
        Description usDescription_15P2 = new Description("A2 dummy 15");
        UserStoryPriority userStoryPriority_15P2 = new UserStoryPriority(15);

        NewUserStoryInfoDTO userStoryInfoDTO_15P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_15P2, usDescription_15P2,
        userStoryPriority_15P2);

        userStoryService.createUserStory(userStoryInfoDTO_15P2);

        // User story 16 P2
        UserStoryNumber userStoryNumber_16P2 = new UserStoryNumber("US016");
        Description usDescription_16P2 = new Description("A2 dummy 16");
        UserStoryPriority userStoryPriority_16P2 = new UserStoryPriority(16);

        NewUserStoryInfoDTO userStoryInfoDTO_16P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_16P2, usDescription_16P2,
        userStoryPriority_16P2);

        userStoryService.createUserStory(userStoryInfoDTO_16P2);

        // User story 17 P2
        UserStoryNumber userStoryNumber_17P2 = new UserStoryNumber("US017");
        Description usDescription_17P2 = new Description("A2 dummy 17");
        UserStoryPriority userStoryPriority_17P2 = new UserStoryPriority(17);

        NewUserStoryInfoDTO userStoryInfoDTO_17P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_17P2, usDescription_17P2,
        userStoryPriority_17P2);

        userStoryService.createUserStory(userStoryInfoDTO_17P2);

        // User story 18 P2
        UserStoryNumber userStoryNumber_18P2 = new UserStoryNumber("US018");
        Description usDescription_18P2 = new Description("A2 dummy 18");
        UserStoryPriority userStoryPriority_18P2 = new UserStoryPriority(18);

        NewUserStoryInfoDTO userStoryInfoDTO_18P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_18P2, usDescription_18P2,
        userStoryPriority_18P2);

        userStoryService.createUserStory(userStoryInfoDTO_18P2);

        // User story 19 P2
        UserStoryNumber userStoryNumber_19P2 = new UserStoryNumber("US019");
        Description usDescription_19P2 = new Description("A2 dummy 19");
        UserStoryPriority userStoryPriority_19P2 = new UserStoryPriority(19);

        NewUserStoryInfoDTO userStoryInfoDTO_19P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_19P2, usDescription_19P2,
        userStoryPriority_19P2);

        userStoryService.createUserStory(userStoryInfoDTO_19P2);

        // User story 20 P2
        UserStoryNumber userStoryNumber_20P2 = new UserStoryNumber("US020");
        Description usDescription_20P2 = new Description("A2 dummy 20");
        UserStoryPriority userStoryPriority_20P2 = new UserStoryPriority(20);

        NewUserStoryInfoDTO userStoryInfoDTO_20P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_20P2, usDescription_20P2,
        userStoryPriority_20P2);

        userStoryService.createUserStory(userStoryInfoDTO_20P2);

        // User story 21 P2
        UserStoryNumber userStoryNumber_21P2 = new UserStoryNumber("US021");
        Description usDescription_21P2 = new Description("A2 dummy 21");
        UserStoryPriority userStoryPriority_21P2 = new UserStoryPriority(21);

        NewUserStoryInfoDTO userStoryInfoDTO_21P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_21P2, usDescription_21P2,
        userStoryPriority_21P2);

        userStoryService.createUserStory(userStoryInfoDTO_21P2);

        // User story 22 P2
        UserStoryNumber userStoryNumber_22P2 = new UserStoryNumber("US022");
        Description usDescription_22P2 = new Description("A2 dummy 22");
        UserStoryPriority userStoryPriority_22P2 = new UserStoryPriority(22);

        NewUserStoryInfoDTO userStoryInfoDTO_22P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_22P2, usDescription_22P2,
        userStoryPriority_22P2);

        userStoryService.createUserStory(userStoryInfoDTO_22P2);

        // User story 23 P2
        UserStoryNumber userStoryNumber_23P2 = new UserStoryNumber("US023");
        Description usDescription_23P2 = new Description("A2 dummy 23");
        UserStoryPriority userStoryPriority_23P2 = new UserStoryPriority(23);

        NewUserStoryInfoDTO userStoryInfoDTO_23P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_23P2, usDescription_23P2,
        userStoryPriority_23P2);

        userStoryService.createUserStory(userStoryInfoDTO_23P2);

        // User story 24 P2
        UserStoryNumber userStoryNumber_24P2 = new UserStoryNumber("US024");
        Description usDescription_24P2 = new Description("A2 dummy 24");
        UserStoryPriority userStoryPriority_24P2 = new UserStoryPriority(24);

        NewUserStoryInfoDTO userStoryInfoDTO_24P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_24P2, usDescription_24P2,
        userStoryPriority_24P2);

        userStoryService.createUserStory(userStoryInfoDTO_24P2);

        // User story 25 P2
        UserStoryNumber userStoryNumber_25P2 = new UserStoryNumber("US025");
        Description usDescription_25P2 = new Description("A2 dummy 25");
        UserStoryPriority userStoryPriority_25P2 = new UserStoryPriority(25);

        NewUserStoryInfoDTO userStoryInfoDTO_25P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_25P2, usDescription_25P2,
        userStoryPriority_25P2);

        userStoryService.createUserStory(userStoryInfoDTO_25P2);

        // User story 26 P2
        UserStoryNumber userStoryNumber_26P2 = new UserStoryNumber("US026");
        Description usDescription_26P2 = new Description("A2 dummy 26");
        UserStoryPriority userStoryPriority_26P2 = new UserStoryPriority(26);

        NewUserStoryInfoDTO userStoryInfoDTO_26P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_26P2, usDescription_26P2,
        userStoryPriority_26P2);

        userStoryService.createUserStory(userStoryInfoDTO_26P2);

        // User story 27 P2
        UserStoryNumber userStoryNumber_27P2 = new UserStoryNumber("US027");
        Description usDescription_27P2 = new Description("A2 dummy 27");
        UserStoryPriority userStoryPriority_27P2 = new UserStoryPriority(27);

        NewUserStoryInfoDTO userStoryInfoDTO_27P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_27P2, usDescription_27P2,
        userStoryPriority_27P2);

        userStoryService.createUserStory(userStoryInfoDTO_27P2);

        // User story 28 P2
        UserStoryNumber userStoryNumber_28P2 = new UserStoryNumber("US028");
        Description usDescription_28P2 = new Description("A2 dummy 28");
        UserStoryPriority userStoryPriority_28P2 = new UserStoryPriority(28);

        NewUserStoryInfoDTO userStoryInfoDTO_28P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_28P2, usDescription_28P2,
        userStoryPriority_28P2);

        userStoryService.createUserStory(userStoryInfoDTO_28P2);

        // User story 29 P2
        UserStoryNumber userStoryNumber_29P2 = new UserStoryNumber("US029");
        Description usDescription_29P2 = new Description("A2 dummy 29");
        UserStoryPriority userStoryPriority_29P2 = new UserStoryPriority(29);

        NewUserStoryInfoDTO userStoryInfoDTO_29P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_29P2, usDescription_29P2,
        userStoryPriority_29P2);

        userStoryService.createUserStory(userStoryInfoDTO_29P2);

        // User story 30 P2
        UserStoryNumber userStoryNumber_30P2 = new UserStoryNumber("US030");
        Description usDescription_30P2 = new Description("A2 dummy 30");
        UserStoryPriority userStoryPriority_30P2 = new UserStoryPriority(30);

        NewUserStoryInfoDTO userStoryInfoDTO_30P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_30P2, usDescription_30P2,
        userStoryPriority_30P2);

        userStoryService.createUserStory(userStoryInfoDTO_30P2);

        // User story 31 P2
        UserStoryNumber userStoryNumber_31P2 = new UserStoryNumber("US031");
        Description usDescription_31P2 = new Description("A2 dummy 31");
        UserStoryPriority userStoryPriority_31P2 = new UserStoryPriority(31);

        NewUserStoryInfoDTO userStoryInfoDTO_31P2 = createUserStoryDTO(projectCode_2,
        actor_2, acceptanceCriteria_2, userStoryNumber_31P2, usDescription_31P2,
        userStoryPriority_31P2);

        userStoryService.createUserStory(userStoryInfoDTO_31P2);

        //Load Sprints

        // Sprint A1-1

        int sprint_1 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_1 = new SprintNumber(sprint_1);

        LocalDate startDate_sprint_1 = LocalDate.of(2022, 3, 22);
        Date newStardDate_sprint_1 = Date.from(startDate_sprint_1.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_1 = new TimePeriod(newStardDate_sprint_1, new Date());

        SprintDTOController sprintDTO_1 = new SprintDTOController();

        sprintDTO_1.projectCode = projectCode_1;
        sprintDTO_1.sprintNumber = sprintNumber_1;
        sprintDTO_1.timePeriod = timePeriod_sprint_1;

        sprintService.createSprint(sprintDTO_1);

        // Sprint A1-2

        int sprint_2 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_2 = new SprintNumber(sprint_2);

        LocalDate startDate_sprint_2 = LocalDate.of(2022, 4, 5);
        Date newStardDate_sprint_2 = Date.from(startDate_sprint_2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_2 = new TimePeriod(newStardDate_sprint_2, new Date());

        SprintDTOController sprintDTO_2 = new SprintDTOController();

        sprintDTO_2.projectCode = projectCode_1;
        sprintDTO_2.sprintNumber = sprintNumber_2;
        sprintDTO_2.timePeriod = timePeriod_sprint_2;

        sprintService.createSprint(sprintDTO_2);

        // Sprint A1-3

        int sprint_3 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_3 = new SprintNumber(sprint_3);

        LocalDate startDate_sprint_3 = LocalDate.of(2022, 4, 26);
        Date newStardDate_sprint_3 = Date.from(startDate_sprint_3.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_3 = new TimePeriod(newStardDate_sprint_3, new Date());

        SprintDTOController sprintDTO_3 = new SprintDTOController();

        sprintDTO_3.projectCode = projectCode_1;
        sprintDTO_3.sprintNumber = sprintNumber_3;
        sprintDTO_3.timePeriod = timePeriod_sprint_3;

        sprintService.createSprint(sprintDTO_3);

        // Sprint A1-4

        int sprint_4 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_4 = new SprintNumber(sprint_4);

        LocalDate startDate_sprint_4 = LocalDate.of(2022, 5, 10);
        Date newStardDate_sprint_4 = Date.from(startDate_sprint_4.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_4 = new TimePeriod(newStardDate_sprint_4, new Date());

        SprintDTOController sprintDTO_4 = new SprintDTOController();

        sprintDTO_4.projectCode = projectCode_1;
        sprintDTO_4.sprintNumber = sprintNumber_4;
        sprintDTO_4.timePeriod = timePeriod_sprint_4;

        sprintService.createSprint(sprintDTO_4);

        // Sprint A1-5

        int sprint_5 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_5 = new SprintNumber(sprint_5);

        LocalDate startDate_sprint_5 = LocalDate.of(2022, 5, 24);
        Date newStardDate_sprint_5 = Date.from(startDate_sprint_5.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_5 = new TimePeriod(newStardDate_sprint_5, new Date());

        SprintDTOController sprintDTO_5 = new SprintDTOController();

        sprintDTO_5.projectCode = projectCode_1;
        sprintDTO_5.sprintNumber = sprintNumber_5;
        sprintDTO_5.timePeriod = timePeriod_sprint_5;

        sprintService.createSprint(sprintDTO_5);

        // Sprint A1-6

        int sprint_6 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_6 = new SprintNumber(sprint_6);

        LocalDate startDate_sprint_6 = LocalDate.of(2022, 6, 7);
        Date newStardDate_sprint_6 = Date.from(startDate_sprint_6.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_6 = new TimePeriod(newStardDate_sprint_6, new Date());

        SprintDTOController sprintDTO_6 = new SprintDTOController();

        sprintDTO_6.projectCode = projectCode_1;
        sprintDTO_6.sprintNumber = sprintNumber_6;
        sprintDTO_6.timePeriod = timePeriod_sprint_6;

        sprintService.createSprint(sprintDTO_6);

        // Sprint A1-7

        int sprint_7 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_7 = new SprintNumber(sprint_7);

        LocalDate startDate_sprint_7 = LocalDate.of(2022, 6, 21);
        Date newStardDate_sprint_7 = Date.from(startDate_sprint_7.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_7 = new TimePeriod(newStardDate_sprint_7, new Date());

        SprintDTOController sprintDTO_7 = new SprintDTOController();

        sprintDTO_7.projectCode = projectCode_1;
        sprintDTO_7.sprintNumber = sprintNumber_7;
        sprintDTO_7.timePeriod = timePeriod_sprint_7;

        sprintService.createSprint(sprintDTO_7);

        // Sprint A1-8

        int sprint_8 = sprintService.generateSprintNumber(projectCode_1);
        SprintNumber sprintNumber_8 = new SprintNumber(sprint_8);

        LocalDate startDate_sprint_8 = LocalDate.of(2022, 7, 19);
        Date newStardDate_sprint_8 = Date.from(startDate_sprint_8.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_8 = new TimePeriod(newStardDate_sprint_8, new Date());

        SprintDTOController sprintDTO_8 = new SprintDTOController();

        sprintDTO_8.projectCode = projectCode_1;
        sprintDTO_8.sprintNumber = sprintNumber_8;
        sprintDTO_8.timePeriod = timePeriod_sprint_8;

        sprintService.createSprint(sprintDTO_8);

        // Sprint A2-1

        int sprint_1A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_1A2 = new SprintNumber(sprint_1A2);

        LocalDate startDate_sprint_1A2 = LocalDate.of(2022, 6, 7);
        Date newStardDate_sprint_1A2 = Date.from(startDate_sprint_1A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_1A2 = new TimePeriod(newStardDate_sprint_1A2, new Date());

        SprintDTOController sprintDTO_1A2 = new SprintDTOController();

        sprintDTO_1A2.projectCode = projectCode_2;
        sprintDTO_1A2.sprintNumber = sprintNumber_1A2;
        sprintDTO_1A2.timePeriod = timePeriod_sprint_1A2;

        sprintService.createSprint(sprintDTO_1A2);

        // Sprint A2-2

        int sprint_2A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_2A2 = new SprintNumber(sprint_2A2);

        LocalDate startDate_sprint_2A2 = LocalDate.of(2022, 7, 5);
        Date newStardDate_sprint_2A2 = Date.from(startDate_sprint_2A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_2A2 = new TimePeriod(newStardDate_sprint_2A2, new Date());

        SprintDTOController sprintDTO_2A2 = new SprintDTOController();

        sprintDTO_2A2.projectCode = projectCode_2;
        sprintDTO_2A2.sprintNumber = sprintNumber_2A2;
        sprintDTO_2A2.timePeriod = timePeriod_sprint_2A2;

        sprintService.createSprint(sprintDTO_2A2);

        // Sprint A2-3

        int sprint_3A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_3A2 = new SprintNumber(sprint_3A2);

        LocalDate startDate_sprint_3A2 = LocalDate.of(2022, 8, 2);
        Date newStardDate_sprint_3A2 = Date.from(startDate_sprint_3A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_3A2 = new TimePeriod(newStardDate_sprint_3A2, new Date());

        SprintDTOController sprintDTO_3A2 = new SprintDTOController();

        sprintDTO_3A2.projectCode = projectCode_2;
        sprintDTO_3A2.sprintNumber = sprintNumber_3A2;
        sprintDTO_3A2.timePeriod = timePeriod_sprint_3A2;

        sprintService.createSprint(sprintDTO_3A2);

        // Sprint A2-4

        int sprint_4A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_4A2 = new SprintNumber(sprint_4A2);

        LocalDate startDate_sprint_4A2 = LocalDate.of(2022, 8, 30);
        Date newStardDate_sprint_4A2 = Date.from(startDate_sprint_4A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_4A2 = new TimePeriod(newStardDate_sprint_4A2, new Date());

        SprintDTOController sprintDTO_4A2 = new SprintDTOController();

        sprintDTO_4A2.projectCode = projectCode_2;
        sprintDTO_4A2.sprintNumber = sprintNumber_4A2;
        sprintDTO_4A2.timePeriod = timePeriod_sprint_4A2;

        sprintService.createSprint(sprintDTO_4A2);

        // Sprint A2-5

        int sprint_5A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_5A2 = new SprintNumber(sprint_5A2);

        LocalDate startDate_sprint_5A2 = LocalDate.of(2022, 9, 27);
        Date newStardDate_sprint_5A2 = Date.from(startDate_sprint_5A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_5A2 = new TimePeriod(newStardDate_sprint_5A2, new Date());

        SprintDTOController sprintDTO_5A2 = new SprintDTOController();

        sprintDTO_5A2.projectCode = projectCode_2;
        sprintDTO_5A2.sprintNumber = sprintNumber_5A2;
        sprintDTO_5A2.timePeriod = timePeriod_sprint_5A2;

        sprintService.createSprint(sprintDTO_5A2);

        // Sprint A2-6

        int sprint_6A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_6A2 = new SprintNumber(sprint_6A2);

        LocalDate startDate_sprint_6A2 = LocalDate.of(2022, 10, 25);
        Date newStardDate_sprint_6A2 = Date.from(startDate_sprint_6A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_6A2 = new TimePeriod(newStardDate_sprint_6A2, new Date());

        SprintDTOController sprintDTO_6A2 = new SprintDTOController();

        sprintDTO_6A2.projectCode = projectCode_2;
        sprintDTO_6A2.sprintNumber = sprintNumber_6A2;
        sprintDTO_6A2.timePeriod = timePeriod_sprint_6A2;

        sprintService.createSprint(sprintDTO_6A2);

        // Sprint A2-7

        int sprint_7A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_7A2 = new SprintNumber(sprint_7A2);

        LocalDate startDate_sprint_7A2 = LocalDate.of(2022, 11, 22);
        Date newStardDate_sprint_7A2 = Date.from(startDate_sprint_7A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_7A2 = new TimePeriod(newStardDate_sprint_7A2, new Date());

        SprintDTOController sprintDTO_7A2 = new SprintDTOController();

        sprintDTO_7A2.projectCode = projectCode_2;
        sprintDTO_7A2.sprintNumber = sprintNumber_7A2;
        sprintDTO_7A2.timePeriod = timePeriod_sprint_7A2;

        sprintService.createSprint(sprintDTO_7A2);

        // Sprint A2-8

        int sprint_8A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_8A2 = new SprintNumber(sprint_8A2);

        LocalDate startDate_sprint_8A2 = LocalDate.of(2022, 12, 13);
        Date newStardDate_sprint_8A2 = Date.from(startDate_sprint_8A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_8A2 = new TimePeriod(newStardDate_sprint_8A2, new Date());

        SprintDTOController sprintDTO_8A2 = new SprintDTOController();

        sprintDTO_8A2.projectCode = projectCode_2;
        sprintDTO_8A2.sprintNumber = sprintNumber_8A2;
        sprintDTO_8A2.timePeriod = timePeriod_sprint_8A2;

        sprintService.createSprint(sprintDTO_8A2);

        // Sprint A2-9

        int sprint_9A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_9A2 = new SprintNumber(sprint_9A2);

        LocalDate startDate_sprint_9A2 = LocalDate.of(2023, 1, 10);
        Date newStardDate_sprint_9A2 = Date.from(startDate_sprint_9A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_9A2 = new TimePeriod(newStardDate_sprint_9A2, new Date());

        SprintDTOController sprintDTO_9A2 = new SprintDTOController();

        sprintDTO_9A2.projectCode = projectCode_2;
        sprintDTO_9A2.sprintNumber = sprintNumber_9A2;
        sprintDTO_9A2.timePeriod = timePeriod_sprint_9A2;

        sprintService.createSprint(sprintDTO_9A2);

        // Sprint A2-10

        int sprint_10A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_10A2 = new SprintNumber(sprint_10A2);

        LocalDate startDate_sprint_10A2 = LocalDate.of(2023, 2, 7);
        Date newStardDate_sprint_10A2 = Date.from(startDate_sprint_10A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_10A2 = new TimePeriod(newStardDate_sprint_10A2, new Date());

        SprintDTOController sprintDTO_10A2 = new SprintDTOController();

        sprintDTO_10A2.projectCode = projectCode_2;
        sprintDTO_10A2.sprintNumber = sprintNumber_10A2;
        sprintDTO_10A2.timePeriod = timePeriod_sprint_10A2;

        sprintService.createSprint(sprintDTO_10A2);

        // Sprint A2-11

        int sprint_11A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_11A2 = new SprintNumber(sprint_11A2);

        LocalDate startDate_sprint_11A2 = LocalDate.of(2023, 3, 7);
        Date newStardDate_sprint_11A2 = Date.from(startDate_sprint_11A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_11A2 = new TimePeriod(newStardDate_sprint_11A2, new Date());

        SprintDTOController sprintDTO_11A2 = new SprintDTOController();

        sprintDTO_11A2.projectCode = projectCode_2;
        sprintDTO_11A2.sprintNumber = sprintNumber_11A2;
        sprintDTO_11A2.timePeriod = timePeriod_sprint_11A2;

        sprintService.createSprint(sprintDTO_11A2);

        // Sprint A2-12

        int sprint_12A2 = sprintService.generateSprintNumber(projectCode_2);
        SprintNumber sprintNumber_12A2 = new SprintNumber(sprint_12A2);

        LocalDate startDate_sprint_12A2 = LocalDate.of(2023, 4, 4);
        Date newStardDate_sprint_12A2 = Date.from(startDate_sprint_12A2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_sprint_12A2 = new TimePeriod(newStardDate_sprint_12A2, new Date());

        SprintDTOController sprintDTO_12A2 = new SprintDTOController();

        sprintDTO_12A2.projectCode = projectCode_2;
        sprintDTO_12A2.sprintNumber = sprintNumber_12A2;
        sprintDTO_12A2.timePeriod = timePeriod_sprint_12A2;

        sprintService.createSprint(sprintDTO_12A2);
    }

    /**
     * Creates a NewProjectDTO to pass on to projectService, to create a new project.
     *
     * @param customerID customer ID
     * @param businessSectorID business sector ID
     * @param typologyID typology ID
     * @param projectCode project code
     * @param projectName project name
     * @param description decsription
     * @param timePeriod timePeriod
     * @param projectSprintDuration sprint duration
     * @param projectNumberOfPlannedSprints number of planned sprints
     * @param projectBudget project budget
     * @return NewProjectDTO
     */
    private NewProjectDTO createProjectDTO(CustomerID customerID, BusinessSectorID businessSectorID,
                                           TypologyID typologyID, ProjectCode projectCode,
                                           ProjectName projectName, Description description,
                                           TimePeriod timePeriod,
                                           ProjectSprintDuration projectSprintDuration,
                                           ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints,
                                           ProjectBudget projectBudget) {

        NewProjectDTO project = new NewProjectDTO();

        project.projectCode = projectCode;
        project.projectName = projectName;
        project.description = description;
        project.timePeriod = timePeriod;
        project.projectSprintDuration = projectSprintDuration;
        project.projectNumberOfPlannedSprints = projectNumberOfPlannedSprints;
        project.customerID = customerID;
        project.businessSectorID = businessSectorID;
        project.typologyID = typologyID;
        project.projectBudget = projectBudget;

        return project;
    }

    /** Creates a NewUserStoryInfoDTO to pass on to UserStoryService, to create a new
     * user story.
     *
     * @param projectCode project code
     * @param  actor actor
     * @param acceptanceCriteria acceptance criteria
     * @param userStoryNumber user story number
     * @param usDescription user story description
     * @param userStoryPriority user story priority
     * @return NewUserStoryInfoDTO
     */
    private NewUserStoryInfoDTO createUserStoryDTO(ProjectCode projectCode, UserStoryActor actor,
                                                   UserStoryAcceptanceCriteria acceptanceCriteria,
                                                   UserStoryNumber userStoryNumber, Description usDescription,
                                                   UserStoryPriority userStoryPriority) {
        NewUserStoryInfoDTO userStoryDTO = new NewUserStoryInfoDTO();

        userStoryDTO.userStoryNumber = userStoryNumber;
        userStoryDTO.projectCode = projectCode;
        userStoryDTO.actor = actor;
        userStoryDTO.description = usDescription;
        userStoryDTO.acceptanceCriteria = acceptanceCriteria;
        //userStoryDTO.status = UserStoryStatus.TO_DO;
        userStoryDTO.priority = userStoryPriority;

        return userStoryDTO;
    }

}
