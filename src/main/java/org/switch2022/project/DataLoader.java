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

    AccountService accountService;

    ResourceService resourceService;

    public DataLoader(TypologyService typologyService, CustomerService customerService,
                      BusinessSectorService businessSectorService,
                      ProjectService projectService, SprintServiceDDD sprintService,
                      UserStoryService userStoryService, AccountService accountService,
                      ResourceService resourceService) {
        this.typologyService = typologyService;
        this.customerService = customerService;
        this.businessSectorService = businessSectorService;
        this.projectService = projectService;
        this.sprintService = sprintService;
        this.userStoryService = userStoryService;
        this.accountService = accountService;
        this.resourceService = resourceService;
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

        //LOAD PROJECTS

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

        //LOAD USERSTORIES

        // User story A1_1
        UserStoryNumber userStoryNumberA1_1 = new UserStoryNumber("US01");
        UserStoryActor actorA1_1 = new UserStoryActor("Team member");
        Description usDescriptionA1_1 = new Description("Dummy 01");
        UserStoryAcceptanceCriteria acceptanceCriteriaA1_1 = new UserStoryAcceptanceCriteria("To be defined");
        UserStoryPriority userStoryPriorityA1_1 = new UserStoryPriority(3);

        NewUserStoryInfoDTO userStoryDTOA1_1 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumberA1_1,
                usDescriptionA1_1, userStoryPriorityA1_1);

        userStoryService.createUserStory(userStoryDTOA1_1);

        // User story A1_2
        UserStoryNumber userStoryNumberA1_2 = new UserStoryNumber("US02");
        Description usDescriptionA1_2 = new Description("Dummy 02");
        UserStoryPriority userStoryPriorityA1_2 = new UserStoryPriority(2);

        NewUserStoryInfoDTO userStoryInfoDTOA1_2 = createUserStoryDTO(projectCode_1, actorA1_1,
                acceptanceCriteriaA1_1, userStoryNumberA1_2, usDescriptionA1_2, userStoryPriorityA1_2);

        userStoryService.createUserStory(userStoryInfoDTOA1_2);

        // User story A1_3
        UserStoryNumber userStoryNumberA1_3 = new UserStoryNumber("US03");
        Description usDescriptionA1_3 = new Description("Dummy 03");
        UserStoryPriority userStoryPriorityA1_3 = new UserStoryPriority(1);

        NewUserStoryInfoDTO userStoryInfoDTOA1_3 = createUserStoryDTO(projectCode_1, actorA1_1,
                acceptanceCriteriaA1_1, userStoryNumberA1_3, usDescriptionA1_3, userStoryPriorityA1_3);

        userStoryService.createUserStory(userStoryInfoDTOA1_3);

        // User story A1_4
        UserStoryNumber userStoryNumberA1_4 = new UserStoryNumber("US04");
        Description usDescriptionA1_4 = new Description("Dummy 04");
        UserStoryPriority userStoryPriorityA1_4 = new UserStoryPriority(4);

        NewUserStoryInfoDTO userStoryInfoDTOA1_4 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumberA1_4, usDescriptionA1_4,
                userStoryPriorityA1_4);

        userStoryService.createUserStory(userStoryInfoDTOA1_4);

        // User story A1_5
        UserStoryNumber userStoryNumberA1_5 = new UserStoryNumber("US05");
        Description usDescriptionA1_5 = new Description("Dummy 05");
        UserStoryPriority userStoryPriorityA1_5 = new UserStoryPriority(5);

        NewUserStoryInfoDTO userStoryInfoDTOA1_5 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumberA1_5, usDescriptionA1_5,
                userStoryPriorityA1_5);

        userStoryService.createUserStory(userStoryInfoDTOA1_5);

        // User story 6
        UserStoryNumber userStoryNumber_6 = new UserStoryNumber("US06");
        Description usDescription_6 = new Description("Dummy 06");
        UserStoryPriority userStoryPriority_6 = new UserStoryPriority(6);

        NewUserStoryInfoDTO userStoryInfoDTO_6 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_6, usDescription_6,
        userStoryPriority_6);

        userStoryService.createUserStory(userStoryInfoDTO_6);

        // User story 7
        UserStoryNumber userStoryNumber_7 = new UserStoryNumber("US07");
        Description usDescription_7 = new Description("Dummy 07");
        UserStoryPriority userStoryPriority_7 = new UserStoryPriority(7);

        NewUserStoryInfoDTO userStoryInfoDTO_7 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_7, usDescription_7,
        userStoryPriority_7);

        userStoryService.createUserStory(userStoryInfoDTO_7);

        // User story 8
        UserStoryNumber userStoryNumber_8 = new UserStoryNumber("US08");
        Description usDescription_8 = new Description("Dummy 08");
        UserStoryPriority userStoryPriority_8 = new UserStoryPriority(8);

        NewUserStoryInfoDTO userStoryInfoDTO_8 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_8, usDescription_8,
        userStoryPriority_8);

        userStoryService.createUserStory(userStoryInfoDTO_8);

        // User story 9
        UserStoryNumber userStoryNumber_9 = new UserStoryNumber("US09");
        Description usDescription_9 = new Description("Dummy 09");
        UserStoryPriority userStoryPriority_9 = new UserStoryPriority(9);

        NewUserStoryInfoDTO userStoryInfoDTO_9 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_9, usDescription_9,
        userStoryPriority_9);

        userStoryService.createUserStory(userStoryInfoDTO_9);

        // User story 10
        UserStoryNumber userStoryNumber_10 = new UserStoryNumber("US10");
        Description usDescription_10 = new Description("Dummy 10");
        UserStoryPriority userStoryPriority_10 = new UserStoryPriority(10);

        NewUserStoryInfoDTO userStoryInfoDTO_10 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_10, usDescription_10,
        userStoryPriority_10);

        userStoryService.createUserStory(userStoryInfoDTO_10);

        // User story 11
        UserStoryNumber userStoryNumber_11 = new UserStoryNumber("US11");
        Description usDescription_11 = new Description("Dummy 11");
        UserStoryPriority userStoryPriority_11 = new UserStoryPriority(11);

        NewUserStoryInfoDTO userStoryInfoDTO_11 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_11, usDescription_11,
        userStoryPriority_11);

        userStoryService.createUserStory(userStoryInfoDTO_11);

        // User story 12
        UserStoryNumber userStoryNumber_12 = new UserStoryNumber("US12");
        Description usDescription_12 = new Description("Dummy 12");
        UserStoryPriority userStoryPriority_12 = new UserStoryPriority(12);

        NewUserStoryInfoDTO userStoryInfoDTO_12 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_12, usDescription_12,
        userStoryPriority_12);

        userStoryService.createUserStory(userStoryInfoDTO_12);

        // User story 13
        UserStoryNumber userStoryNumber_13 = new UserStoryNumber("US13");
        Description usDescription_13 = new Description("Dummy 13");
        UserStoryPriority userStoryPriority_13 = new UserStoryPriority(13);

        NewUserStoryInfoDTO userStoryInfoDTO_13 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_13, usDescription_13,
        userStoryPriority_13);

        userStoryService.createUserStory(userStoryInfoDTO_13);

        // User story 14
        UserStoryNumber userStoryNumber_14 = new UserStoryNumber("US14");
        Description usDescription_14 = new Description("Dummy 14");
        UserStoryPriority userStoryPriority_14 = new UserStoryPriority(14);

        NewUserStoryInfoDTO userStoryInfoDTO_14 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_14, usDescription_14,
        userStoryPriority_14);

        userStoryService.createUserStory(userStoryInfoDTO_14);

        // User story 14
        UserStoryNumber userStoryNumber_15 = new UserStoryNumber("US15");
        Description usDescription_15 = new Description("Dummy 15");
        UserStoryPriority userStoryPriority_15 = new UserStoryPriority(15);

        NewUserStoryInfoDTO userStoryInfoDTO_15 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_15, usDescription_15,
        userStoryPriority_15);

        userStoryService.createUserStory(userStoryInfoDTO_15);

        // User story 16
        UserStoryNumber userStoryNumber_16 = new UserStoryNumber("US16");
        Description usDescription_16 = new Description("Dummy 16");
        UserStoryPriority userStoryPriority_16 = new UserStoryPriority(16);

        NewUserStoryInfoDTO userStoryInfoDTO_16 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_16, usDescription_16,
        userStoryPriority_16);

        userStoryService.createUserStory(userStoryInfoDTO_16);

        // User story 17
        UserStoryNumber userStoryNumber_17 = new UserStoryNumber("US17");
        Description usDescription_17 = new Description("Dummy 17");
        UserStoryPriority userStoryPriority_17 = new UserStoryPriority(17);

        NewUserStoryInfoDTO userStoryInfoDTO_17 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_17, usDescription_17,
        userStoryPriority_17);

        userStoryService.createUserStory(userStoryInfoDTO_17);

        // User story 18
        UserStoryNumber userStoryNumber_18 = new UserStoryNumber("US18");
        Description usDescription_18 = new Description("Dummy 18");
        UserStoryPriority userStoryPriority_18 = new UserStoryPriority(18);

        NewUserStoryInfoDTO userStoryInfoDTO_18 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_18, usDescription_18,
        userStoryPriority_18);

        userStoryService.createUserStory(userStoryInfoDTO_18);

        // User story 19
        UserStoryNumber userStoryNumber_19 = new UserStoryNumber("US19");
        Description usDescription_19 = new Description("Dummy 19");
        UserStoryPriority userStoryPriority_19 = new UserStoryPriority(19);

        NewUserStoryInfoDTO userStoryInfoDTO_19 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_19, usDescription_19,
        userStoryPriority_19);

        userStoryService.createUserStory(userStoryInfoDTO_19);

        // User story 20
        UserStoryNumber userStoryNumber_20 = new UserStoryNumber("US20");
        Description usDescription_20 = new Description("Dummy 20");
        UserStoryPriority userStoryPriority_20 = new UserStoryPriority(20);

        NewUserStoryInfoDTO userStoryInfoDTO_20 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_20, usDescription_20,
        userStoryPriority_20);

        userStoryService.createUserStory(userStoryInfoDTO_20);

        // User story 21
        UserStoryNumber userStoryNumber_21 = new UserStoryNumber("US21");
        Description usDescription_21 = new Description("Dummy 21");
        UserStoryPriority userStoryPriority_21 = new UserStoryPriority(21);

        NewUserStoryInfoDTO userStoryInfoDTO_21 = createUserStoryDTO(projectCode_1,
                actorA1_1, acceptanceCriteriaA1_1, userStoryNumber_21, usDescription_21,
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

        //LOAD SPRINTS

        // Sprint A1-1

        SprintNumber sprintNumber_A1_1 = getNextSprintNumber(projectCode_1);

        LocalDate startDate_sprint_A1_1 = LocalDate.of(2022, 3, 22);
        Date newStardDate_sprint_A1_1 = createDate(startDate_sprint_A1_1);
        LocalDate endDate_sprint_A1_1 = LocalDate.of(2022, 4, 4);
        Date newEndDate_sprint_A1_1 = createDate(endDate_sprint_A1_1);

        TimePeriod timePeriod_sprint_A1_1 = new TimePeriod(newStardDate_sprint_A1_1,
                newEndDate_sprint_A1_1);

        SprintDTOController sprintDTO_A1_1 = createSprintDTOController(projectCode_1,
                sprintNumber_A1_1, timePeriod_sprint_A1_1);

        sprintService.createSprint(sprintDTO_A1_1);

        // Sprint A1-2
        // This line is duplicated because it needs to. Each time a sprint is created, a
        // different sprintNumber will be generated here, depending on how many sprints
        // already exist (autonumber feature)
        SprintNumber sprintNumber_A1_2 = getNextSprintNumber(projectCode_1);

        LocalDate startDate_sprint_A1_2 = LocalDate.of(2022, 4, 5);
        Date newStardDate_sprint_A1_2 = createDate(startDate_sprint_A1_2);
        LocalDate endDate_sprint_A1_2 = LocalDate.of(2022, 4, 25);
        Date newEndDate_sprint_A1_2 = createDate(endDate_sprint_A1_2);

        TimePeriod timePeriod_sprint_A1_2 = new TimePeriod(newStardDate_sprint_A1_2,
                newEndDate_sprint_A1_2);

        SprintDTOController sprintDTO_A1_2 = createSprintDTOController(projectCode_1,
                sprintNumber_A1_2, timePeriod_sprint_A1_2);

        sprintService.createSprint(sprintDTO_A1_2);

        // Sprint A1-3

        SprintNumber sprintNumber_A1_3 = getNextSprintNumber(projectCode_1);

        LocalDate startDate_sprint_A1_3 = LocalDate.of(2022, 4, 26);
        Date newStardDate_sprint_A1_3 = createDate(startDate_sprint_A1_3);
        LocalDate endDate_sprint_A1_3 = LocalDate.of(2022, 5, 9);
        Date newEndDate_sprint_A1_3 = createDate(endDate_sprint_A1_3);

        TimePeriod timePeriod_sprint_A1_3 = new TimePeriod(newStardDate_sprint_A1_3,
                newEndDate_sprint_A1_3);

        SprintDTOController sprintDTO_A1_3 = createSprintDTOController(projectCode_1,
                sprintNumber_A1_3, timePeriod_sprint_A1_3);

        sprintService.createSprint(sprintDTO_A1_3);

        // Sprint A2-1

        SprintNumber sprintNumber_A2_1 = getNextSprintNumber(projectCode_2);

        LocalDate startDate_sprint_A2_1 = LocalDate.of(2022, 6, 7);
        Date newStardDate_sprint_A2_1 = createDate(startDate_sprint_A2_1);
        LocalDate endDate_sprint_A2_1 = LocalDate.of(2022, 7,4);
        Date newEndDate_sprint_A2_1 = createDate(endDate_sprint_A2_1);

        TimePeriod timePeriod_sprint_A2_1 = new TimePeriod(newStardDate_sprint_A2_1,
                newEndDate_sprint_A2_1);

        SprintDTOController sprintDTO_A2_1 = createSprintDTOController(projectCode_2,
                sprintNumber_A2_1, timePeriod_sprint_A2_1);

        sprintService.createSprint(sprintDTO_A2_1);

        // Sprint A2-2

        SprintNumber sprintNumber_A2_2 = getNextSprintNumber(projectCode_2);

        LocalDate startDate_sprint_A2_2 = LocalDate.of(2022, 7, 5);
        Date newStardDate_sprint_A2_2 = createDate(startDate_sprint_A2_2);
        LocalDate endDate_sprint_A2_2 = LocalDate.of(2022, 8, 1);
        Date newEndDate_sprint_A2_2 = createDate(endDate_sprint_A2_2);

        TimePeriod timePeriod_sprint_A2_2 = new TimePeriod(newStardDate_sprint_A2_2,
                newEndDate_sprint_A2_2);

        SprintDTOController sprintDTO_A2_2 = createSprintDTOController(projectCode_2,
                sprintNumber_A2_2, timePeriod_sprint_A2_2);

        sprintService.createSprint(sprintDTO_A2_2);

        // Sprint A2-3

        SprintNumber sprintNumber_A2_3 = getNextSprintNumber(projectCode_2);

        LocalDate startDate_sprint_A2_3 = LocalDate.of(2022, 8, 2);
        Date newStardDate_sprint_A2_3 = createDate(startDate_sprint_A2_3);
        LocalDate endDate_sprint_A2_3 = LocalDate.of(2022, 8, 29);
        Date newEndDate_sprint_A2_3 = createDate(endDate_sprint_A2_3);

        TimePeriod timePeriod_sprint_A2_3 = new TimePeriod(newStardDate_sprint_A2_3,
                newEndDate_sprint_A2_3);

        SprintDTOController sprintDTO_A2_3 = createSprintDTOController(projectCode_2,
                sprintNumber_A2_3, timePeriod_sprint_A2_3);

        sprintService.createSprint(sprintDTO_A2_3);

        //LOAD ACCOUNTS

        // Account 1
        Email email_account_1 = new Email("js@mymail.com");
        Name name_account_1 = new Name("João Silva");
        PhoneNumber phone_account_1 = new PhoneNumber("+351915879652");
        Photo photo_account = new Photo("");
        ProfileName profile_account = new ProfileName("User");

        NewAccountDTO account_1 = new NewAccountDTO();
        account_1.email = email_account_1;
        account_1.name = name_account_1;
        account_1.phoneNumber = phone_account_1;
        account_1.photo = photo_account;
        account_1.profile = profile_account;

        accountService.createAccount(account_1);

        // Account 2
        Email email_account_2 = new Email("ms@mymail.com");
        Name name_account_2 = new Name("Manel Costa");
        PhoneNumber phone_account_2 = new PhoneNumber("+351263650520");

        NewAccountDTO account_2 = new NewAccountDTO();
        account_2.email = email_account_2;
        account_2.name = name_account_2;
        account_2.phoneNumber = phone_account_2;
        account_2.photo = photo_account;
        account_2.profile = profile_account;

        accountService.createAccount(account_2);

        // Account 3
        Email email_account_3 = new Email("xf@mymail.com");
        Name name_account_3 = new Name("Xico Ferreira");
        PhoneNumber phone_account_3 = new PhoneNumber("+351263650532");

        NewAccountDTO account_3 = new NewAccountDTO();
        account_3.email = email_account_3;
        account_3.name = name_account_3;
        account_3.phoneNumber = phone_account_3;
        account_3.photo = photo_account;
        account_3.profile = profile_account;

        accountService.createAccount(account_3);

        // Account 4
        Email email_account_4 = new Email("qd@mymail.com");
        Name name_account_4 = new Name("Quim Barreiros");
        PhoneNumber phone_account_4 = new PhoneNumber("+351921458803");

        NewAccountDTO account_4 = new NewAccountDTO();
        account_4.email = email_account_4;
        account_4.name = name_account_4;
        account_4.phoneNumber = phone_account_4;
        account_4.photo = photo_account;
        account_4.profile = profile_account;

        accountService.createAccount(account_4);

        // Account 5
        Email email_account_5 = new Email("tc@mymail.com");
        Name name_account_5 = new Name("Tiago Cancado");
        PhoneNumber phone_account_5 = new PhoneNumber("+351263650345");

        NewAccountDTO account_5 = new NewAccountDTO();
        account_5.email = email_account_5;
        account_5.name = name_account_5;
        account_5.phoneNumber = phone_account_5;
        account_5.photo = photo_account;
        account_5.profile = profile_account;

        accountService.createAccount(account_5);

        // Account 6
        Email email_account_6 = new Email("nel.m@mymail.com");
        Name name_account_6 = new Name("Nel Moleiro");
        PhoneNumber phone_account_6 = new PhoneNumber("+351930123456");

        NewAccountDTO account_6 = new NewAccountDTO();
        account_6.email = email_account_6;
        account_6.name = name_account_6;
        account_6.phoneNumber = phone_account_6;
        account_6.photo = photo_account;
        account_6.profile = profile_account;

        accountService.createAccount(account_6);

        // Account 7
        Email email_account_7 = new Email("ze@mymail.com");
        Name name_account_7 = new Name("Ze da Esquina");
        PhoneNumber phone_account_7 = new PhoneNumber("+351212349016");
        ProfileName profile_account_2 = new ProfileName("Manager");

        NewAccountDTO account_7 = new NewAccountDTO();
        account_7.email = email_account_7;
        account_7.name = name_account_7;
        account_7.phoneNumber = phone_account_7;
        account_7.photo = photo_account;
        account_7.profile = profile_account_2;

        accountService.createAccount(account_7);

        // Account 8
        Email email_account_8 = new Email("zb@mymail.com");
        Name name_account_8 = new Name("Zé do Bento");
        PhoneNumber phone_account_8 = new PhoneNumber("+351921458791");

        NewAccountDTO account_8 = new NewAccountDTO();
        account_8.email = email_account_8;
        account_8.name = name_account_8;
        account_8.phoneNumber = phone_account_8;
        account_8.photo = photo_account;
        account_8.profile = profile_account;

        accountService.createAccount(account_8);

        // Account 9
        Email email_account_9 = new Email("to.f@mymail.com");
        Name name_account_9 = new Name("Tó Farrulo");
        PhoneNumber phone_account_9 = new PhoneNumber("+351921458795");

        NewAccountDTO account_9 = new NewAccountDTO();
        account_9.email = email_account_9;
        account_9.name = name_account_9;
        account_9.phoneNumber = phone_account_9;
        account_9.photo = photo_account;
        account_9.profile = profile_account;

        accountService.createAccount(account_9);

        // Account 10
        Email email_account_10 = new Email("tdc@mymail.com");
        Name name_account_10 = new Name("Tino das Cruzes");
        PhoneNumber phone_account_10 = new PhoneNumber("+351921458799");

        NewAccountDTO account_10 = new NewAccountDTO();
        account_10.email = email_account_10;
        account_10.name = name_account_10;
        account_10.phoneNumber = phone_account_10;
        account_10.photo = photo_account;
        account_10.profile = profile_account;

        accountService.createAccount(account_10);

        // Account 11
        Email email_account_11 = new Email("zm@mymail.com");
        Name name_account_11 = new Name("Zé Manel");
        PhoneNumber phone_account_11 = new PhoneNumber("+351921458811");

        NewAccountDTO account_11 = new NewAccountDTO();
        account_11.email = email_account_11;
        account_11.name = name_account_11;
        account_11.phoneNumber = phone_account_11;
        account_11.photo = photo_account;
        account_11.profile = profile_account;

        accountService.createAccount(account_11);

        // Account 12
        Email email_account_12 = new Email("as@mymail.com");
        Name name_account_12 = new Name("Antonio Silva");
        PhoneNumber phone_account_12 = new PhoneNumber("+351921458815");

        NewAccountDTO account_12 = new NewAccountDTO();
        account_12.email = email_account_12;
        account_12.name = name_account_12;
        account_12.phoneNumber = phone_account_12;
        account_12.photo = photo_account;
        account_12.profile = profile_account;

        accountService.createAccount(account_12);

        // Account 13
        Email email_account_13 = new Email("tg@mymail.com");
        Name name_account_13 = new Name("Tiago Geringonca");
        PhoneNumber phone_account_13 = new PhoneNumber("+351921458807");

        NewAccountDTO account_13 = new NewAccountDTO();
        account_13.email = email_account_13;
        account_13.name = name_account_13;
        account_13.phoneNumber = phone_account_13;
        account_13.photo = photo_account;
        account_13.profile = profile_account_2;

        accountService.createAccount(account_13);

        // Account 14
        Email email_account_14 = new Email("udu@mymail.com");
        Name name_account_14 = new Name("Urbino das Urzes");
        PhoneNumber phone_account_14 = new PhoneNumber("+351962547891");
        ProfileName profile_account_3 = new ProfileName("Administrator");

        NewAccountDTO account_14 = new NewAccountDTO();
        account_14.email = email_account_14;
        account_14.name = name_account_14;
        account_14.phoneNumber = phone_account_14;
        account_14.photo = photo_account;
        account_14.profile = profile_account_3;

        accountService.createAccount(account_14);


        //LOAD RESOURCES

        // Resource A1_1

        NewResourceDTO resource_A1_1 = new NewResourceDTO();
        resource_A1_1.email = email_account_1;
        resource_A1_1.costPerHour = new CostPerHour(25.00);
        resource_A1_1.role = new Role("Product Owner");
        resource_A1_1.percentageOfAllocation = new PercentageOfAllocation(0.2);
        resource_A1_1.projectCode = projectCode_1;
        resource_A1_1.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 3, 31)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resource_A1_1);









    }

    /**
     * Generates the sprint number for the sprint instance to be created,
     * based in the existing sprints already in the database.
     * @param projectCode project code
     * @return a SprintNumber, which is the VO to be used.
     */
    private SprintNumber getNextSprintNumber(ProjectCode projectCode) {
        int number = sprintService.generateSprintNumber(projectCode);

        return new SprintNumber(number);
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

    /**
     * Converts a date in LocalDate format into Date format, which is used in the application.
     * @param someLocalDate has the input date to convert;
     * @return converted date in Date format.
     */
    private Date createDate(LocalDate someLocalDate){
        return Date.from(someLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Creates a SprintDTOController to use in the sprintService.createSprint method.
     * @param projectCode project code
     * @param sprintNumber sprint number
     * @param timePeriod_sprint time period of the sprint
     * @return a SprintDTOController with the input data.
     */
    private static SprintDTOController createSprintDTOController(ProjectCode projectCode, SprintNumber sprintNumber, TimePeriod timePeriod_sprint) {
        SprintDTOController sprintDTO_A1_1 = new SprintDTOController();

        sprintDTO_A1_1.projectCode = projectCode;
        sprintDTO_A1_1.sprintNumber = sprintNumber;
        sprintDTO_A1_1.timePeriod = timePeriod_sprint;
        return sprintDTO_A1_1;
    }

}
