package org.switch2022.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.switch2022.project.mapper.*;
import org.switch2022.project.mapper.sprintDTOs.NewSprintDTO;
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

        CustomerID customerID1 = new CustomerID(customerService.createCustomer(customerOne).customerID.getId());
        CustomerID customerID2 = new CustomerID(customerService.createCustomer(customerTwo).customerID.getId());
        CustomerID customerID3 = new CustomerID(customerService.createCustomer(customerThree).customerID.getId());

        //Load Business Sectors
        BusinessSectorDesignation businessSectorDesignationOne = new BusinessSectorDesignation("It doesn't matter");
        BusinessSectorDesignation businessSectorDesignationTwo = new BusinessSectorDesignation("Hospitality industry");

        BusinessSectorDTO businessSectorOne = new BusinessSectorDTO();
        businessSectorOne.businessSectorDesignation = businessSectorDesignationOne;

        BusinessSectorDTO businessSectorTwo = new BusinessSectorDTO();
        businessSectorTwo.businessSectorDesignation = businessSectorDesignationTwo;

        BusinessSectorID businessSectorID1 = new BusinessSectorID
                (businessSectorService.createBusinessSector(businessSectorOne).businessSectorID.getId());
        BusinessSectorID businessSectorID2 = new BusinessSectorID
                (businessSectorService.createBusinessSector(businessSectorTwo).businessSectorID.getId());

        //Load Typologies
        TypologyDesignation typologyDesignationOne = new TypologyDesignation("Fixed cost");
        TypologyDesignation typologyDesignationTwo = new TypologyDesignation("Time and materials");

        TypologyDTO typologyOne = new TypologyDTO();
        typologyOne.typologyDesignation = typologyDesignationOne;

        TypologyDTO typologyTwo = new TypologyDTO();
        typologyTwo.typologyDesignation = typologyDesignationTwo;

        TypologyID typologyID1 = new TypologyID(typologyService.createTypology(typologyOne).typologyID.getId());
        TypologyID typologyID2 = new TypologyID(typologyService.createTypology(typologyTwo).typologyID.getId());

        //LOAD PROJECTS

        //Project 1
        ProjectCode projectCode1 = new ProjectCode("A1");
        ProjectName projectName1 = new ProjectName("Dummy01");
        Description description1 = new Description("Just a dummy project");
        LocalDate startDateProject1 = LocalDate.of(2022, 3, 1);
        Date newStardDateProject1 = Date.from(startDateProject1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDateProject1 = LocalDate.of(2022, 7, 31);
        Date newEndDateProject1 = Date.from(endDateProject1.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod_1 = new TimePeriod(newStardDateProject1, newEndDateProject1);
        //ProjectStatus projectStatus1 = new ProjectStatus();
        int sprintTime1 = 2;
        ProjectSprintDuration projectSprintDuration1 = new ProjectSprintDuration(sprintTime1);
        int totalSprints1 = 8;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints1 =
                new ProjectNumberOfPlannedSprints(totalSprints1);
        float totalBudget1 = 150000F;
        ProjectBudget projectBudget1 = new ProjectBudget(totalBudget1);

        NewProjectDTO project1 = createProjectDTO(customerID1, businessSectorID1,
                typologyID1, projectCode1, projectName1, description1, timePeriod_1,
                projectSprintDuration1, projectNumberOfPlannedSprints1, projectBudget1);

        projectService.createProject(project1);

        //Project 2
        ProjectCode projectCode2 = new ProjectCode("A2");
        ProjectName projectName2 = new ProjectName("Dummy02");
        Description description2 = new Description("Just another dummy project");

        LocalDate startDateProject2 = LocalDate.of(2022, 5, 31);
        Date newStardDateProject2 = Date.from(startDateProject2.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDateProject2 = LocalDate.of(2023, 4, 29);
        Date newEndDateProject2 = Date.from(endDateProject2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod2 = new TimePeriod(newStardDateProject2, newEndDateProject2);
        int sprintTime2 = 4;
        ProjectSprintDuration projectSprintDuration2 = new ProjectSprintDuration(sprintTime2);
        int totalSprints2 = 12;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints2 =
                new ProjectNumberOfPlannedSprints(totalSprints2);
        float totalBudget2 = 350000F;
        ProjectBudget projectBudget2 = new ProjectBudget(totalBudget2);

        NewProjectDTO project2 = createProjectDTO(customerID2, businessSectorID1,
                typologyID1, projectCode2, projectName2, description2, timePeriod2,
                projectSprintDuration2, projectNumberOfPlannedSprints2,
                projectBudget2);

        projectService.createProject(project2);

        //Project 3
        ProjectCode projectCode3 = new ProjectCode("666");
        ProjectName projectName3 = new ProjectName("Inevitable nightmare");
        Description description3 = new Description("Doomed from the start");

        LocalDate startDateProject3 = LocalDate.of(2023, 3, 10);
        Date newStardDateProject3 = Date.from(startDateProject3.atStartOfDay(ZoneId.systemDefault()).toInstant());

        TimePeriod timePeriod3 = new TimePeriod(newStardDateProject3, new Date());
        int sprintTime3 = 3;
        ProjectSprintDuration projectSprintDuration3 = new ProjectSprintDuration(sprintTime3);
        int totalSprints3 = 15;
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints3 =
                new ProjectNumberOfPlannedSprints(totalSprints3);
        float totalBudget3 = 750000F;
        ProjectBudget projectBudget3 = new ProjectBudget(totalBudget3);

        NewProjectDTO project3 = createProjectDTO(customerID3, businessSectorID2,
                typologyID2, projectCode3, projectName3, description3, timePeriod3,
                projectSprintDuration3, projectNumberOfPlannedSprints3,
                projectBudget3);

        projectService.createProject(project3);

        //LOAD USERSTORIES

        // User story A1_1 (named A1N1)
        UserStoryNumber userStoryNumberA1N1 = new UserStoryNumber("US01");
        UserStoryActor actorA1N1 = new UserStoryActor("Team member");
        Description usDescriptionA1N1 = new Description("Dummy 01");
        UserStoryAcceptanceCriteria acceptanceCriteriaA1N1 = new UserStoryAcceptanceCriteria("To be defined");
        UserStoryPriority userStoryPriorityA1N1 = new UserStoryPriority(3);

        NewUserStoryInfoDTO userStoryDTOA1N1 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N1,
                usDescriptionA1N1, userStoryPriorityA1N1);

        userStoryService.createUserStory(userStoryDTOA1N1);

        // User story A1_2 (named A1N2)
        UserStoryNumber userStoryNumberA1N2 = new UserStoryNumber("US02");
        Description usDescriptionA1N2 = new Description("Dummy 02");
        UserStoryPriority userStoryPriorityA1N2 = new UserStoryPriority(2);

        NewUserStoryInfoDTO userStoryInfoDTOA1N2 = createUserStoryDTO(projectCode1, actorA1N1,
                acceptanceCriteriaA1N1, userStoryNumberA1N2, usDescriptionA1N2, userStoryPriorityA1N2);

        userStoryService.createUserStory(userStoryInfoDTOA1N2);

        // User story A1_3 (named A1N3)
        UserStoryNumber userStoryNumberA1N3 = new UserStoryNumber("US03");
        Description usDescriptionA1N3 = new Description("Dummy 03");
        UserStoryPriority userStoryPriorityA1N3 = new UserStoryPriority(1);

        NewUserStoryInfoDTO userStoryInfoDTOA1N3 = createUserStoryDTO(projectCode1, actorA1N1,
                acceptanceCriteriaA1N1, userStoryNumberA1N3, usDescriptionA1N3, userStoryPriorityA1N3);

        userStoryService.createUserStory(userStoryInfoDTOA1N3);

        // User story A1_4 (named A1N4)
        UserStoryNumber userStoryNumberA1N4 = new UserStoryNumber("US04");
        Description usDescriptionA1N4 = new Description("Dummy 04");
        UserStoryPriority userStoryPriorityA1N4 = new UserStoryPriority(4);

        NewUserStoryInfoDTO userStoryInfoDTOA1N4 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N4, usDescriptionA1N4,
                userStoryPriorityA1N4);

        userStoryService.createUserStory(userStoryInfoDTOA1N4);

        // User story A1_5 (named A1N5)
        UserStoryNumber userStoryNumberA1N5 = new UserStoryNumber("US05");
        Description usDescriptionA1N5 = new Description("Dummy 05");
        UserStoryPriority userStoryPriorityA1N5 = new UserStoryPriority(5);

        NewUserStoryInfoDTO userStoryInfoDTOA1N5 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N5, usDescriptionA1N5,
                userStoryPriorityA1N5);

        userStoryService.createUserStory(userStoryInfoDTOA1N5);

        // User story A1_6 (named A1N6)
        UserStoryNumber userStoryNumberA1N6 = new UserStoryNumber("US06");
        Description usDescriptionA1N6 = new Description("Dummy 06");
        UserStoryPriority userStoryPriorityA1N6 = new UserStoryPriority(6);

        NewUserStoryInfoDTO userStoryInfoDTOA1N6 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N6, usDescriptionA1N6,
        userStoryPriorityA1N6);

        userStoryService.createUserStory(userStoryInfoDTOA1N6);

        // User story A1_7 (named A1N7)
        UserStoryNumber userStoryNumberA1N7 = new UserStoryNumber("US07");
        Description usDescriptionA1N7 = new Description("Dummy 07");
        UserStoryPriority userStoryPriorityA1N7 = new UserStoryPriority(7);

        NewUserStoryInfoDTO userStoryInfoDTOA1N7 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N7, usDescriptionA1N7,
        userStoryPriorityA1N7);

        userStoryService.createUserStory(userStoryInfoDTOA1N7);

        // User story A1_8 (named A1N8)
        UserStoryNumber userStoryNumberA1N8 = new UserStoryNumber("US08");
        Description usDescriptionA1N8 = new Description("Dummy 08");
        UserStoryPriority userStoryPriorityA1N8 = new UserStoryPriority(8);

        NewUserStoryInfoDTO userStoryInfoDTOA1N8 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N8, usDescriptionA1N8,
        userStoryPriorityA1N8);

        userStoryService.createUserStory(userStoryInfoDTOA1N8);

        // User story A1_9 (named A1N9)
        UserStoryNumber userStoryNumberA1N9 = new UserStoryNumber("US09");
        Description usDescriptionA1N9 = new Description("Dummy 09");
        UserStoryPriority userStoryPriorityA1N9 = new UserStoryPriority(9);

        NewUserStoryInfoDTO userStoryInfoDTOA1N9 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N9, usDescriptionA1N9,
        userStoryPriorityA1N9);

        userStoryService.createUserStory(userStoryInfoDTOA1N9);

        // User story A1_10 (named A1N10)
        UserStoryNumber userStoryNumberA1N10 = new UserStoryNumber("US10");
        Description usDescriptionA1N10 = new Description("Dummy 10");
        UserStoryPriority userStoryPriorityA1N10 = new UserStoryPriority(10);

        NewUserStoryInfoDTO userStoryInfoDTOA1N10 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N10, usDescriptionA1N10,
        userStoryPriorityA1N10);

        userStoryService.createUserStory(userStoryInfoDTOA1N10);

        // User story A1_11 (named A1N11)
        UserStoryNumber userStoryNumberA1N11 = new UserStoryNumber("US11");
        Description usDescriptionA1N11 = new Description("Dummy 11");
        UserStoryPriority userStoryPriorityA1N11 = new UserStoryPriority(11);

        NewUserStoryInfoDTO userStoryInfoDTOA1N11 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N11, usDescriptionA1N11,
        userStoryPriorityA1N11);

        userStoryService.createUserStory(userStoryInfoDTOA1N11);

        // User story A1_12 (named A1N12)
        UserStoryNumber userStoryNumberA1N12 = new UserStoryNumber("US12");
        Description usDescriptionA1N12 = new Description("Dummy 12");
        UserStoryPriority userStoryPriorityA1N12 = new UserStoryPriority(12);

        NewUserStoryInfoDTO userStoryInfoDTOA1N12 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N12, usDescriptionA1N12,
        userStoryPriorityA1N12);

        userStoryService.createUserStory(userStoryInfoDTOA1N12);

        // User story A1_13 (named A1N13)
        UserStoryNumber userStoryNumberA1N13 = new UserStoryNumber("US13");
        Description usDescriptionA1N13 = new Description("Dummy 13");
        UserStoryPriority userStoryPriorityA1N13 = new UserStoryPriority(13);

        NewUserStoryInfoDTO userStoryInfoDTOA1N13 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N13, usDescriptionA1N13,
        userStoryPriorityA1N13);

        userStoryService.createUserStory(userStoryInfoDTOA1N13);

        // User story A1_14 (named A1N14)
        UserStoryNumber userStoryNumberA1N14 = new UserStoryNumber("US14");
        Description usDescriptionA1N14 = new Description("Dummy 14");
        UserStoryPriority userStoryPriorityA1N14 = new UserStoryPriority(14);

        NewUserStoryInfoDTO userStoryInfoDTOA1N14 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N14, usDescriptionA1N14,
        userStoryPriorityA1N14);

        userStoryService.createUserStory(userStoryInfoDTOA1N14);

        // User story 15 (named A1N15)
        UserStoryNumber userStoryNumberA1N15 = new UserStoryNumber("US15");
        Description usDescriptionA1N15 = new Description("Dummy 15");
        UserStoryPriority userStoryPriorityA1N15 = new UserStoryPriority(15);

        NewUserStoryInfoDTO userStoryInfoDTOA1N15 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N15, usDescriptionA1N15,
        userStoryPriorityA1N15);

        userStoryService.createUserStory(userStoryInfoDTOA1N15);

        // User story A1_16 (named A1N16)
        UserStoryNumber userStoryNumberA1N16 = new UserStoryNumber("US16");
        Description usDescriptionA1N16 = new Description("Dummy 16");
        UserStoryPriority userStoryPriorityA1N16 = new UserStoryPriority(16);

        NewUserStoryInfoDTO userStoryInfoDTOA1N16 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N16, usDescriptionA1N16,
        userStoryPriorityA1N16);

        userStoryService.createUserStory(userStoryInfoDTOA1N16);

        // User story A1_17 (named A1N17)
        UserStoryNumber userStoryNumberA1N17 = new UserStoryNumber("US17");
        Description usDescriptionA1N17 = new Description("Dummy 17");
        UserStoryPriority userStoryPriorityA1N17 = new UserStoryPriority(17);

        NewUserStoryInfoDTO userStoryInfoDTOA1N17 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N17, usDescriptionA1N17,
        userStoryPriorityA1N17);

        userStoryService.createUserStory(userStoryInfoDTOA1N17);

        // User story A1_18 (named A1N18)
        UserStoryNumber userStoryNumberA1N18 = new UserStoryNumber("US18");
        Description usDescriptionA1N18 = new Description("Dummy 18");
        UserStoryPriority userStoryPriorityA1N18 = new UserStoryPriority(18);

        NewUserStoryInfoDTO userStoryInfoDTOA1N18 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N18, usDescriptionA1N18,
        userStoryPriorityA1N18);

        userStoryService.createUserStory(userStoryInfoDTOA1N18);

        // User story A1_19 (named A1N19)
        UserStoryNumber userStoryNumberA1N19 = new UserStoryNumber("US19");
        Description usDescriptionA1N19 = new Description("Dummy 19");
        UserStoryPriority userStoryPriorityA1N19 = new UserStoryPriority(19);

        NewUserStoryInfoDTO userStoryInfoDTOA1N19 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N19, usDescriptionA1N19,
        userStoryPriorityA1N19);

        userStoryService.createUserStory(userStoryInfoDTOA1N19);

        // User story A1_20 (named A1N20)
        UserStoryNumber userStoryNumberA1N20 = new UserStoryNumber("US20");
        Description usDescriptionA1N20 = new Description("Dummy 20");
        UserStoryPriority userStoryPriorityA1N20 = new UserStoryPriority(20);

        NewUserStoryInfoDTO userStoryInfoDTOA1N20 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N20, usDescriptionA1N20,
        userStoryPriorityA1N20);

        userStoryService.createUserStory(userStoryInfoDTOA1N20);

        // User story A1_21 (named A1N21)
        UserStoryNumber userStoryNumberA1N21 = new UserStoryNumber("US21");
        Description usDescriptionA1N21 = new Description("Dummy 21");
        UserStoryPriority userStoryPriorityA1N21 = new UserStoryPriority(21);

        NewUserStoryInfoDTO userStoryInfoDTOA1N21 = createUserStoryDTO(projectCode1,
                actorA1N1, acceptanceCriteriaA1N1, userStoryNumberA1N21, usDescriptionA1N21,
        userStoryPriorityA1N21);

        userStoryService.createUserStory(userStoryInfoDTOA1N21);


        // User story 1 P2
        UserStoryNumber userStoryNumber1P2 = new UserStoryNumber("US001");
        UserStoryActor actor2 = new UserStoryActor("Project Manager");
        Description usDescription1P2 = new Description("A2 dummy 01");
        UserStoryAcceptanceCriteria acceptanceCriteria1P2 = new UserStoryAcceptanceCriteria("To be defined");
        UserStoryPriority userStoryPriority1P2 = new UserStoryPriority(3);

        NewUserStoryInfoDTO userStoryDTO1P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber1P2,
        usDescription1P2, userStoryPriority1P2);

        userStoryService.createUserStory(userStoryDTO1P2);

        // User story 2 P2
        UserStoryNumber userStoryNumber2P2 = new UserStoryNumber("US002");
        Description usDescription2P2 = new Description("A2 dummy 02");
        UserStoryPriority userStoryPriority2P2 = new UserStoryPriority(2);

        NewUserStoryInfoDTO userStoryInfoDTO2P2 = createUserStoryDTO(projectCode2, actor2,
        acceptanceCriteria1P2, userStoryNumber2P2, usDescription2P2, userStoryPriority2P2);

        userStoryService.createUserStory(userStoryInfoDTO2P2);

        // User story 3 P2
        UserStoryNumber userStoryNumber3P2 = new UserStoryNumber("US003");
        Description usDescription3P2 = new Description("A2 dummy 03");
        UserStoryPriority userStoryPriority3P2 = new UserStoryPriority(1);

        NewUserStoryInfoDTO userStoryInfoDTO3P2 = createUserStoryDTO(projectCode2, actor2,
        acceptanceCriteria1P2, userStoryNumber3P2, usDescription3P2, userStoryPriority3P2);

        userStoryService.createUserStory(userStoryInfoDTO3P2);

        // User story 4 P2
        UserStoryNumber userStoryNumber4P2 = new UserStoryNumber("US004");
        Description usDescription4P2 = new Description("A2 dummy 04");
        UserStoryPriority userStoryPriority4P2 = new UserStoryPriority(4);

        NewUserStoryInfoDTO userStoryInfoDTO4P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber4P2, usDescription4P2,
        userStoryPriority4P2);

        userStoryService.createUserStory(userStoryInfoDTO4P2);

        // User story 5 P2
        UserStoryNumber userStoryNumber5P2 = new UserStoryNumber("US005");
        Description usDescription5P2 = new Description("A2 dummy 05");
        UserStoryPriority userStoryPriority5P2 = new UserStoryPriority(5);

        NewUserStoryInfoDTO userStoryInfoDTO5P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber5P2, usDescription5P2,
        userStoryPriority5P2);

        userStoryService.createUserStory(userStoryInfoDTO5P2);

        // User story 6 P2
        UserStoryNumber userStoryNumber6P2 = new UserStoryNumber("US006");
        Description usDescription6P2 = new Description("A2 dummy 06");
        UserStoryPriority userStoryPriority6P2 = new UserStoryPriority(6);

        NewUserStoryInfoDTO userStoryInfoDTO6P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber6P2, usDescription6P2,
        userStoryPriority6P2);

        userStoryService.createUserStory(userStoryInfoDTO6P2);

        // User story 7 P2
        UserStoryNumber userStoryNumber7P2 = new UserStoryNumber("US007");
        Description usDescription7P2 = new Description("A2 dummy 07");
        UserStoryPriority userStoryPriority7P2 = new UserStoryPriority(7);

        NewUserStoryInfoDTO userStoryInfoDTO7P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber7P2, usDescription7P2,
        userStoryPriority7P2);

        userStoryService.createUserStory(userStoryInfoDTO7P2);

        // User story 8 P2
        UserStoryNumber userStoryNumber8P2 = new UserStoryNumber("US008");
        Description usDescription8P2 = new Description("A2 dummy 08");
        UserStoryPriority userStoryPriority8P2 = new UserStoryPriority(8);

        NewUserStoryInfoDTO userStoryInfoDTO8P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber8P2, usDescription8P2,
        userStoryPriority8P2);

        userStoryService.createUserStory(userStoryInfoDTO8P2);

        // User story 9 P2
        UserStoryNumber userStoryNumber9P2 = new UserStoryNumber("US009");
        Description usDescription9P2 = new Description("A2 dummy 09");
        UserStoryPriority userStoryPriority9P2 = new UserStoryPriority(9);

        NewUserStoryInfoDTO userStoryInfoDTO9P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber9P2, usDescription9P2,
        userStoryPriority9P2);

        userStoryService.createUserStory(userStoryInfoDTO9P2);

        // User story 10 P2
        UserStoryNumber userStoryNumber10P2 = new UserStoryNumber("US010");
        Description usDescription10P2 = new Description("A2 dummy 10");
        UserStoryPriority userStoryPriority10P2 = new UserStoryPriority(10);

        NewUserStoryInfoDTO userStoryInfoDTO10P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber10P2, usDescription10P2,
        userStoryPriority10P2);

        userStoryService.createUserStory(userStoryInfoDTO10P2);

        // User story 11 P2
        UserStoryNumber userStoryNumber11P2 = new UserStoryNumber("US011");
        Description usDescription11P2 = new Description("A2 dummy 11");
        UserStoryPriority userStoryPriority11P2 = new UserStoryPriority(11);

        NewUserStoryInfoDTO userStoryInfoDTO11P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber11P2, usDescription11P2,
        userStoryPriority11P2);

        userStoryService.createUserStory(userStoryInfoDTO11P2);

        // User story 12 P2
        UserStoryNumber userStoryNumber12P2 = new UserStoryNumber("US012");
        Description usDescription12P2 = new Description("A2 dummy 12");
        UserStoryPriority userStoryPriority12P2 = new UserStoryPriority(12);

        NewUserStoryInfoDTO userStoryInfoDTO12P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber12P2, usDescription12P2,
        userStoryPriority12P2);

        userStoryService.createUserStory(userStoryInfoDTO12P2);

        // User story 13 P2
        UserStoryNumber userStoryNumber13P2 = new UserStoryNumber("US013");
        Description usDescription13P2 = new Description("A2 dummy 13");
        UserStoryPriority userStoryPriority13P2 = new UserStoryPriority(13);

        NewUserStoryInfoDTO userStoryInfoDTO13P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber13P2, usDescription13P2,
        userStoryPriority13P2);

        userStoryService.createUserStory(userStoryInfoDTO13P2);

        // User story 14 P2
        UserStoryNumber userStoryNumber14P2 = new UserStoryNumber("US014");
        Description usDescription14P2 = new Description("A2 dummy 14");
        UserStoryPriority userStoryPriority14P2 = new UserStoryPriority(14);

        NewUserStoryInfoDTO userStoryInfoDTO14P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber14P2, usDescription14P2,
        userStoryPriority14P2);

        userStoryService.createUserStory(userStoryInfoDTO14P2);

        // User story 15 P2
        UserStoryNumber userStoryNumber15P2 = new UserStoryNumber("US015");
        Description usDescription15P2 = new Description("A2 dummy 15");
        UserStoryPriority userStoryPriority15P2 = new UserStoryPriority(15);

        NewUserStoryInfoDTO userStoryInfoDTO15P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber15P2, usDescription15P2,
        userStoryPriority15P2);

        userStoryService.createUserStory(userStoryInfoDTO15P2);

        // User story 16 P2
        UserStoryNumber userStoryNumber16P2 = new UserStoryNumber("US016");
        Description usDescription16P2 = new Description("A2 dummy 16");
        UserStoryPriority userStoryPriority16P2 = new UserStoryPriority(16);

        NewUserStoryInfoDTO userStoryInfoDTO16P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber16P2, usDescription16P2,
        userStoryPriority16P2);

        userStoryService.createUserStory(userStoryInfoDTO16P2);

        // User story 17 P2
        UserStoryNumber userStoryNumber17P2 = new UserStoryNumber("US017");
        Description usDescription17P2 = new Description("A2 dummy 17");
        UserStoryPriority userStoryPriority17P2 = new UserStoryPriority(17);

        NewUserStoryInfoDTO userStoryInfoDTO17P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber17P2, usDescription17P2,
        userStoryPriority17P2);

        userStoryService.createUserStory(userStoryInfoDTO17P2);

        // User story 18 P2
        UserStoryNumber userStoryNumber18P2 = new UserStoryNumber("US018");
        Description usDescription18P2 = new Description("A2 dummy 18");
        UserStoryPriority userStoryPriority18P2 = new UserStoryPriority(18);

        NewUserStoryInfoDTO userStoryInfoDTO18P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber18P2, usDescription18P2,
        userStoryPriority18P2);

        userStoryService.createUserStory(userStoryInfoDTO18P2);

        // User story 19 P2
        UserStoryNumber userStoryNumber19P2 = new UserStoryNumber("US019");
        Description usDescription19P2 = new Description("A2 dummy 19");
        UserStoryPriority userStoryPriority19P2 = new UserStoryPriority(19);

        NewUserStoryInfoDTO userStoryInfoDTO19P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber19P2, usDescription19P2,
        userStoryPriority19P2);

        userStoryService.createUserStory(userStoryInfoDTO19P2);

        // User story 20 P2
        UserStoryNumber userStoryNumber20P2 = new UserStoryNumber("US020");
        Description usDescription20P2 = new Description("A2 dummy 20");
        UserStoryPriority userStoryPriority20P2 = new UserStoryPriority(20);

        NewUserStoryInfoDTO userStoryInfoDTO20P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber20P2, usDescription20P2,
        userStoryPriority20P2);

        userStoryService.createUserStory(userStoryInfoDTO20P2);

        // User story 21 P2
        UserStoryNumber userStoryNumber21P2 = new UserStoryNumber("US021");
        Description usDescription21P2 = new Description("A2 dummy 21");
        UserStoryPriority userStoryPriority21P2 = new UserStoryPriority(21);

        NewUserStoryInfoDTO userStoryInfoDTO21P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber21P2, usDescription21P2,
        userStoryPriority21P2);

        userStoryService.createUserStory(userStoryInfoDTO21P2);

        // User story 22 P2
        UserStoryNumber userStoryNumber22P2 = new UserStoryNumber("US022");
        Description usDescription22P2 = new Description("A2 dummy 22");
        UserStoryPriority userStoryPriority22P2 = new UserStoryPriority(22);

        NewUserStoryInfoDTO userStoryInfoDTO22P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber22P2, usDescription22P2,
        userStoryPriority22P2);

        userStoryService.createUserStory(userStoryInfoDTO22P2);

        // User story 23 P2
        UserStoryNumber userStoryNumber23P2 = new UserStoryNumber("US023");
        Description usDescription23P2 = new Description("A2 dummy 23");
        UserStoryPriority userStoryPriority23P2 = new UserStoryPriority(23);

        NewUserStoryInfoDTO userStoryInfoDTO23P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber23P2, usDescription23P2,
        userStoryPriority23P2);

        userStoryService.createUserStory(userStoryInfoDTO23P2);

        // User story 24 P2
        UserStoryNumber userStoryNumber24P2 = new UserStoryNumber("US024");
        Description usDescription24P2 = new Description("A2 dummy 24");
        UserStoryPriority userStoryPriority24P2 = new UserStoryPriority(24);

        NewUserStoryInfoDTO userStoryInfoDTO24P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber24P2, usDescription24P2,
        userStoryPriority24P2);

        userStoryService.createUserStory(userStoryInfoDTO24P2);

        // User story 25 P2
        UserStoryNumber userStoryNumber25P2 = new UserStoryNumber("US025");
        Description usDescription25P2 = new Description("A2 dummy 25");
        UserStoryPriority userStoryPriority25P2 = new UserStoryPriority(25);

        NewUserStoryInfoDTO userStoryInfoDTO25P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber25P2, usDescription25P2,
        userStoryPriority25P2);

        userStoryService.createUserStory(userStoryInfoDTO25P2);

        // User story 26 P2
        UserStoryNumber userStoryNumber26P2 = new UserStoryNumber("US026");
        Description usDescription26P2 = new Description("A2 dummy 26");
        UserStoryPriority userStoryPriority26P2 = new UserStoryPriority(26);

        NewUserStoryInfoDTO userStoryInfoDTO26P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber26P2, usDescription26P2,
        userStoryPriority26P2);

        userStoryService.createUserStory(userStoryInfoDTO26P2);

        // User story 27 P2
        UserStoryNumber userStoryNumber27P2 = new UserStoryNumber("US027");
        Description usDescription27P2 = new Description("A2 dummy 27");
        UserStoryPriority userStoryPriority27P2 = new UserStoryPriority(27);

        NewUserStoryInfoDTO userStoryInfoDTO27P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber27P2, usDescription27P2,
        userStoryPriority27P2);

        userStoryService.createUserStory(userStoryInfoDTO27P2);

        // User story 28 P2
        UserStoryNumber userStoryNumber28P2 = new UserStoryNumber("US028");
        Description usDescription28P2 = new Description("A2 dummy 28");
        UserStoryPriority userStoryPriority28P2 = new UserStoryPriority(28);

        NewUserStoryInfoDTO userStoryInfoDTO28P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber28P2, usDescription28P2,
        userStoryPriority28P2);

        userStoryService.createUserStory(userStoryInfoDTO28P2);

        // User story 29 P2
        UserStoryNumber userStoryNumber29P2 = new UserStoryNumber("US029");
        Description usDescription29P2 = new Description("A2 dummy 29");
        UserStoryPriority userStoryPriority29P2 = new UserStoryPriority(29);

        NewUserStoryInfoDTO userStoryInfoDTO29P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber29P2, usDescription29P2,
        userStoryPriority29P2);

        userStoryService.createUserStory(userStoryInfoDTO29P2);

        // User story 30 P2
        UserStoryNumber userStoryNumber30P2 = new UserStoryNumber("US030");
        Description usDescription30P2 = new Description("A2 dummy 30");
        UserStoryPriority userStoryPriority30P2 = new UserStoryPriority(30);

        NewUserStoryInfoDTO userStoryInfoDTO30P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber30P2, usDescription30P2,
        userStoryPriority30P2);

        userStoryService.createUserStory(userStoryInfoDTO30P2);

        // User story 31 P2
        UserStoryNumber userStoryNumber31P2 = new UserStoryNumber("US031");
        Description usDescription31P2 = new Description("A2 dummy 31");
        UserStoryPriority userStoryPriority31P2 = new UserStoryPriority(31);

        NewUserStoryInfoDTO userStoryInfoDTO31P2 = createUserStoryDTO(projectCode2,
        actor2, acceptanceCriteria1P2, userStoryNumber31P2, usDescription31P2,
        userStoryPriority31P2);

        userStoryService.createUserStory(userStoryInfoDTO31P2);

        //LOAD SPRINTS

       // Sprint A1-1 (named A1N1)
        SprintNumber sprintNumberA1N1 = getNextSprintNumber(projectCode1);

        LocalDate startDatesprintA1N1 = LocalDate.of(2022, 3, 22);
        Date newStardDateSprintA1N1 = createDate(startDatesprintA1N1);
        LocalDate endDateSprintA1N1 = LocalDate.of(2022, 4, 4);
        Date newEndDateSprintA1N1 = createDate(endDateSprintA1N1);

        TimePeriod timePeriodSprintA1N1 = new TimePeriod(newStardDateSprintA1N1,
                newEndDateSprintA1N1);
        SprintStatus status = SprintStatus.Closed;

        NewSprintDTO sprintDTOA1N1 = createNewSprintDTO(projectCode1,
                sprintNumberA1N1, timePeriodSprintA1N1, status);

        sprintService.createSprint(sprintDTOA1N1);

        // Sprint A1-2
        // This line is duplicated because it needs to. Each time a sprint is created, a
        // different sprintNumber will be generated here, depending on how many sprints
        // already exist (autonumber feature)
        SprintNumber sprintNumberA1N2 = getNextSprintNumber(projectCode1);

        LocalDate startDateSprintA1N2 = LocalDate.of(2022, 4, 5);
        Date newStardDateSprintA1N2 = createDate(startDateSprintA1N2);
        LocalDate endDateSprintA1N2 = LocalDate.of(2022, 4, 25);
        Date newEndDateSprintA1N2 = createDate(endDateSprintA1N2);

        TimePeriod timePeriodSprintA1N2 = new TimePeriod(newStardDateSprintA1N2,
                newEndDateSprintA1N2);

        NewSprintDTO sprintDTOA1N2 = createNewSprintDTO(projectCode1,
                sprintNumberA1N2, timePeriodSprintA1N2, status);

        sprintService.createSprint(sprintDTOA1N2);

        // Sprint A1-3
        SprintNumber sprintNumberA1N3 = getNextSprintNumber(projectCode1);

        LocalDate startDateSprintA1N3 = LocalDate.of(2022, 4, 26);
        Date newStardDateSprintA1N3 = createDate(startDateSprintA1N3);
        LocalDate endDateSprintA1N3 = LocalDate.of(2022, 5, 9);
        Date newEndDateSprintA1N3 = createDate(endDateSprintA1N3);

        SprintStatus status2 = SprintStatus.Open;

        TimePeriod timePeriodSprintA1N3 = new TimePeriod(newStardDateSprintA1N3,
                newEndDateSprintA1N3);

        NewSprintDTO sprintDTOA1N3 = createNewSprintDTO(projectCode1,
                sprintNumberA1N3, timePeriodSprintA1N3, status2);

        sprintService.createSprint(sprintDTOA1N3);

        // Sprint A2-1
        SprintNumber sprintNumberA2N1 = getNextSprintNumber(projectCode2);

        LocalDate startDateSprintA2N1 = LocalDate.of(2022, 6, 7);
        Date newStardDateSprintA2N1 = createDate(startDateSprintA2N1);
        LocalDate endDateSprintA2N1 = LocalDate.of(2022, 7,4);
        Date newEndDateSprintA2N1 = createDate(endDateSprintA2N1);

        TimePeriod timePeriodSprintA2N1 = new TimePeriod(newStardDateSprintA2N1,
                newEndDateSprintA2N1);

        NewSprintDTO sprintDTOA2N1 = createNewSprintDTO(projectCode2,
                sprintNumberA2N1, timePeriodSprintA2N1, status);

        sprintService.createSprint(sprintDTOA2N1);

        // Sprint A2-2

        SprintNumber sprintNumberA2N2 = getNextSprintNumber(projectCode2);

        LocalDate startDateSprintA2N2 = LocalDate.of(2022, 7, 5);
        Date newStardDateSprintA2N2 = createDate(startDateSprintA2N2);
        LocalDate endDateSprintA2N2 = LocalDate.of(2022, 8, 1);
        Date newEndDateSprintA2N2 = createDate(endDateSprintA2N2);

        TimePeriod timePeriodSprintA2N2 = new TimePeriod(newStardDateSprintA2N2,
                newEndDateSprintA2N2);

        NewSprintDTO sprintDTOA2N2 = createNewSprintDTO(projectCode2,
                sprintNumberA2N2, timePeriodSprintA2N2, status);

        sprintService.createSprint(sprintDTOA2N2);

        // Sprint A2-3

        SprintNumber sprintNumberA2N3 = getNextSprintNumber(projectCode2);

        LocalDate startDateSprintA2N3 = LocalDate.of(2022, 8, 2);
        Date newStardDateSprintA2N3 = createDate(startDateSprintA2N3);
        LocalDate endDateSprintA2N3 = LocalDate.of(2022, 8, 29);
        Date newEndDateSprintA2N3 = createDate(endDateSprintA2N3);

        TimePeriod timePeriodSprintA2N3 = new TimePeriod(newStardDateSprintA2N3,
                newEndDateSprintA2N3);

        NewSprintDTO sprintDTOA2N3 = createNewSprintDTO(projectCode2,
                sprintNumberA2N3, timePeriodSprintA2N3, status2);

        sprintService.createSprint(sprintDTOA2N3);

        // LOAD USERSTORYINSPRINT

        UserStoryEffortEstimate userStoryEffortEstimate = new UserStoryEffortEstimate(4.0);
        NewAddUsToSprintBacklogDTO sprintBacklogDTOSB_A1_N3_N1 = createSprintBacklogDto(projectCode1,
                sprintNumberA1N3,userStoryNumberA1N1,userStoryEffortEstimate);

        sprintService.addUsToSprintBacklog(sprintBacklogDTOSB_A1_N3_N1);








        // LOAD ACCOUNTS

        // Account 1
        Email emailAccount1 = new Email("js@mymail.com");
        Name nameAccount1 = new Name("João Silva");
        PhoneNumber phoneAccount1 = new PhoneNumber("+351915879652");
        Photo photoAccount = new Photo("");
        ProfileName profileAccount = new ProfileName("User");

        NewAccountDTO account1 = new NewAccountDTO();
        account1.email = emailAccount1;
        account1.name = nameAccount1;
        account1.phoneNumber = phoneAccount1;
        account1.photo = photoAccount;
        account1.profile = profileAccount;

        accountService.createAccount(account1);

        // Account 2
        Email emailAccount2 = new Email("ms@mymail.com");
        Name nameAccount2 = new Name("Manel Costa");
        PhoneNumber phoneAccount2 = new PhoneNumber("+351263650520");

        NewAccountDTO account2 = new NewAccountDTO();
        account2.email = emailAccount2;
        account2.name = nameAccount2;
        account2.phoneNumber = phoneAccount2;
        account2.photo = photoAccount;
        account2.profile = profileAccount;

        accountService.createAccount(account2);

        // Account 3
        Email emailAccount3 = new Email("xf@mymail.com");
        Name nameAccount3 = new Name("Xico Ferreira");
        PhoneNumber phoneAccount3 = new PhoneNumber("+351263650532");

        NewAccountDTO account3 = new NewAccountDTO();
        account3.email = emailAccount3;
        account3.name = nameAccount3;
        account3.phoneNumber = phoneAccount3;
        account3.photo = photoAccount;
        account3.profile = profileAccount;

        accountService.createAccount(account3);

        // Account 4
        Email emailAccount4 = new Email("qd@mymail.com");
        Name nameAccount4 = new Name("Quim Barreiros");
        PhoneNumber phoneAccount4 = new PhoneNumber("+351921458803");

        NewAccountDTO account4 = new NewAccountDTO();
        account4.email = emailAccount4;
        account4.name = nameAccount4;
        account4.phoneNumber = phoneAccount4;
        account4.photo = photoAccount;
        account4.profile = profileAccount;

        accountService.createAccount(account4);

        // Account 5
        Email emailAccount5 = new Email("tc@mymail.com");
        Name nameAccount5 = new Name("Tiago Cancado");
        PhoneNumber phoneAccount5 = new PhoneNumber("+351263650345");

        NewAccountDTO account5 = new NewAccountDTO();
        account5.email = emailAccount5;
        account5.name = nameAccount5;
        account5.phoneNumber = phoneAccount5;
        account5.photo = photoAccount;
        account5.profile = profileAccount;

        accountService.createAccount(account5);

        // Account 6
        Email emailAccount6 = new Email("nel.m@mymail.com");
        Name nameAccount6 = new Name("Nel Moleiro");
        PhoneNumber phoneAccount6 = new PhoneNumber("+351930123456");

        NewAccountDTO account6 = new NewAccountDTO();
        account6.email = emailAccount6;
        account6.name = nameAccount6;
        account6.phoneNumber = phoneAccount6;
        account6.photo = photoAccount;
        account6.profile = profileAccount;

        accountService.createAccount(account6);

        // Account 7
        Email emailAccount7 = new Email("ze@mymail.com");
        Name nameAccount7 = new Name("Ze da Esquina");
        PhoneNumber phoneAccount7 = new PhoneNumber("+351212349016");
        ProfileName profileAccount2 = new ProfileName("Manager");

        NewAccountDTO account7 = new NewAccountDTO();
        account7.email = emailAccount7;
        account7.name = nameAccount7;
        account7.phoneNumber = phoneAccount7;
        account7.photo = photoAccount;
        account7.profile = profileAccount2;

        accountService.createAccount(account7);

        // Account 8
        Email emailAccount8 = new Email("zb@mymail.com");
        Name nameAccount8 = new Name("Zé do Bento");
        PhoneNumber phoneAccount8 = new PhoneNumber("+351921458791");

        NewAccountDTO account8 = new NewAccountDTO();
        account8.email = emailAccount8;
        account8.name = nameAccount8;
        account8.phoneNumber = phoneAccount8;
        account8.photo = photoAccount;
        account8.profile = profileAccount;

        accountService.createAccount(account8);

        // Account 9
        Email emailAccount9 = new Email("to.f@mymail.com");
        Name nameAccount9 = new Name("Tó Farrulo");
        PhoneNumber phoneAccount9 = new PhoneNumber("+351921458795");

        NewAccountDTO account9 = new NewAccountDTO();
        account9.email = emailAccount9;
        account9.name = nameAccount9;
        account9.phoneNumber = phoneAccount9;
        account9.photo = photoAccount;
        account9.profile = profileAccount;

        accountService.createAccount(account9);

        // Account 10
        Email emailAccount10 = new Email("tdc@mymail.com");
        Name nameAccount10 = new Name("Tino das Cruzes");
        PhoneNumber phoneAccount10 = new PhoneNumber("+351921458799");

        NewAccountDTO account10 = new NewAccountDTO();
        account10.email = emailAccount10;
        account10.name = nameAccount10;
        account10.phoneNumber = phoneAccount10;
        account10.photo = photoAccount;
        account10.profile = profileAccount;

        accountService.createAccount(account10);

        // Account 11
        Email emailAccount11 = new Email("zm@mymail.com");
        Name nameAccount11 = new Name("Zé Manel");
        PhoneNumber phoneAccount11 = new PhoneNumber("+351921458811");

        NewAccountDTO account11 = new NewAccountDTO();
        account11.email = emailAccount11;
        account11.name = nameAccount11;
        account11.phoneNumber = phoneAccount11;
        account11.photo = photoAccount;
        account11.profile = profileAccount;

        accountService.createAccount(account11);

        // Account 12
        Email emailAccount12 = new Email("as@mymail.com");
        Name nameAccount12 = new Name("Antonio Silva");
        PhoneNumber phoneAccount12 = new PhoneNumber("+351921458815");

        NewAccountDTO account12 = new NewAccountDTO();
        account12.email = emailAccount12;
        account12.name = nameAccount12;
        account12.phoneNumber = phoneAccount12;
        account12.photo = photoAccount;
        account12.profile = profileAccount;

        accountService.createAccount(account12);

        // Account 13
        Email emailAccount13 = new Email("tg@mymail.com");
        Name nameAccount13 = new Name("Tiago Geringonca");
        PhoneNumber phoneAccount13 = new PhoneNumber("+351921458807");

        NewAccountDTO account13 = new NewAccountDTO();
        account13.email = emailAccount13;
        account13.name = nameAccount13;
        account13.phoneNumber = phoneAccount13;
        account13.photo = photoAccount;
        account13.profile = profileAccount2;

        accountService.createAccount(account13);

        // Account 14
        Email emailAccount14 = new Email("udu@mymail.com");
        Name nameAccount14 = new Name("Urbino das Urzes");
        PhoneNumber phoneAccount14 = new PhoneNumber("+351962547891");
        ProfileName profileAccount3 = new ProfileName("Administrator");

        NewAccountDTO account14 = new NewAccountDTO();
        account14.email = emailAccount14;
        account14.name = nameAccount14;
        account14.phoneNumber = phoneAccount14;
        account14.photo = photoAccount;
        account14.profile = profileAccount3;

        accountService.createAccount(account14);


        //LOAD RESOURCES

        // Resource A1_1

        NewResourceDTO resourceA1N1 = new NewResourceDTO();
        resourceA1N1.accountID = new AccountID(1); //João Silva, USER
        resourceA1N1.costPerHour = new CostPerHour(25.00);
        resourceA1N1.role = new Role("Product Owner");
        resourceA1N1.percentageOfAllocation = new PercentageOfAllocation(0.2);
        resourceA1N1.projectCode = projectCode1;
        resourceA1N1.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 3, 31)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resourceA1N1);

        // Resource A1_2

        NewResourceDTO resourceA1N2 = new NewResourceDTO();
        resourceA1N2.accountID = new AccountID(2); //"Manel Costa" USER
        resourceA1N2.costPerHour = new CostPerHour(25.00);
        resourceA1N2.role = new Role("Scrum Master");
        resourceA1N2.percentageOfAllocation = new PercentageOfAllocation(0.3);
        resourceA1N2.projectCode = projectCode1;
        resourceA1N2.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 4)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resourceA1N2);

        // Resource A1_3

        NewResourceDTO resourceA1N3 = new NewResourceDTO();
        resourceA1N3.accountID = new AccountID(3); //"Xico Ferreira" USER
        resourceA1N3.costPerHour = new CostPerHour(20.00);
        resourceA1N3.role = new Role("Team Member");
        resourceA1N3.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA1N3.projectCode = projectCode1;
        resourceA1N3.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 5)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resourceA1N3);

        // Resource A1_4

        NewResourceDTO resourceA1N4 = new NewResourceDTO();
        resourceA1N4.accountID = new AccountID(5); //"Tiago Cancado" USER
        resourceA1N4.costPerHour = new CostPerHour(35.00);
        resourceA1N4.role = new Role("Project Manager");
        resourceA1N4.percentageOfAllocation = new PercentageOfAllocation(0.2);
        resourceA1N4.projectCode = projectCode1;
        resourceA1N4.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 2)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resourceA1N4);

        // Resource A1_5

        NewResourceDTO resourceA1N5 = new NewResourceDTO();
        resourceA1N5.accountID = new AccountID(6); //"Nel Moleiro" USER
        resourceA1N5.costPerHour = new CostPerHour(20.00);
        resourceA1N5.role = new Role("Team Member");
        resourceA1N5.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA1N5.projectCode = projectCode1;
        resourceA1N5.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 7)),
                createDate(LocalDate.of(2022, 7, 31)));

        resourceService.createResource(resourceA1N5);


        // Resource A1_6

        NewResourceDTO resourceA1N6 = new NewResourceDTO();
        resourceA1N6.accountID = new AccountID(8); //"Zé do Bento" USER
        resourceA1N6.costPerHour = new CostPerHour(20.00);
        resourceA1N6.role = new Role("Team Member");
        resourceA1N6.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA1N6.projectCode = projectCode1;
        resourceA1N6.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 8)),
                createDate(LocalDate.of(2022, 6, 20)));

        resourceService.createResource(resourceA1N6);

        // Resource A1_7

        NewResourceDTO resourceA1N7 = new NewResourceDTO();
        resourceA1N7.accountID = new AccountID(9); //"Tó Farrulo" USER
        resourceA1N7.costPerHour = new CostPerHour(20.00);
        resourceA1N7.role = new Role("Team Member");
        resourceA1N7.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA1N7.projectCode = projectCode1;
        resourceA1N7.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 8)),
                createDate(LocalDate.of(2022, 6, 20)));

        resourceService.createResource(resourceA1N7);

        // Resource A1_8

        NewResourceDTO resourceA1N8 = new NewResourceDTO();
        resourceA1N8.accountID = new AccountID(10); //"Tino das Cruzes" USER
        resourceA1N8.costPerHour = new CostPerHour(20.00);
        resourceA1N8.role = new Role("Team Member");
        resourceA1N8.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA1N8.projectCode = projectCode1;
        resourceA1N8.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 1, 10)),
                createDate(LocalDate.of(2022, 6, 20)));

        resourceService.createResource(resourceA1N8);

        // Resource A2_1

        NewResourceDTO resourceA2N1 = new NewResourceDTO();
        resourceA2N1.accountID = new AccountID(4); //"Quim Barreiros" USER
        resourceA2N1.costPerHour = new CostPerHour(42.00);
        resourceA2N1.role = new Role("Project Manager");
        resourceA2N1.percentageOfAllocation = new PercentageOfAllocation(0.2);
        resourceA2N1.projectCode = projectCode2;
        resourceA2N1.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 5, 15)),
                createDate(LocalDate.of(2023, 4, 30)));

        resourceService.createResource(resourceA2N1);

        /*// Resource A2_2

        NewResourceDTO resourceA2N2 = new NewResourceDTO();
        resourceA2N2.email = email_account_13; //"Tiago Geringonca" MANAGER (Bate nas validações)
        resourceA2N2.costPerHour = new CostPerHour(30.00);
        resourceA2N2.role = new Role("Product Owner");
        resourceA2N2.percentageOfAllocation = new PercentageOfAllocation(0.2);
        resourceA2N2.projectCode = projectCode2;
        resourceA2N2.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 5, 15)),
                createDate(LocalDate.of(2023, 4, 30)));

        resourceService.createResource(resourceA2N2);*/

        // Resource A2_3

        NewResourceDTO resourceA2N3 = new NewResourceDTO();
        resourceA2N3.accountID = new AccountID(11);
        resourceA2N3.costPerHour = new CostPerHour(20.00);
        resourceA2N3.role = new Role("Team Member");
        resourceA2N3.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA2N3.projectCode = projectCode2;
        resourceA2N3.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 5, 31)),
                createDate(LocalDate.of(2023, 4, 30)));

        resourceService.createResource(resourceA2N3);

        // Resource A2_4

        NewResourceDTO resourceA2N4 = new NewResourceDTO();
        resourceA2N4.accountID = new AccountID(12);
        resourceA2N4.costPerHour = new CostPerHour(18.00);
        resourceA2N4.role = new Role("Team Member");
        resourceA2N4.percentageOfAllocation = new PercentageOfAllocation(1.0);
        resourceA2N4.projectCode = projectCode2;
        resourceA2N4.timePeriod = new TimePeriod(createDate(LocalDate.of(2022, 5, 31)),
                createDate(LocalDate.of(2023, 4, 30)));

        resourceService.createResource(resourceA2N4);



    }

    private static NewAddUsToSprintBacklogDTO createSprintBacklogDto(ProjectCode projectCode,
                                                                     SprintNumber sprintNumber,
                                                                     UserStoryNumber userStoryNumber,
                                                                     UserStoryEffortEstimate userStoryEffortEstimate){
        NewAddUsToSprintBacklogDTO sprintBacklogDTO = new NewAddUsToSprintBacklogDTO();

        sprintBacklogDTO.projectCode = projectCode;
        sprintBacklogDTO.sprintNumber=sprintNumber;
        sprintBacklogDTO.userStoryNumber=userStoryNumber;
        sprintBacklogDTO.userStoryEffortEstimate=userStoryEffortEstimate;

        return sprintBacklogDTO;
    }



    /**
     * Generates the sprint number for the sprint instance to be created,
     * based in the existing sprints already in the database.
     * @param projectCode project code
     * @return a SprintNumber, which is the VO to be used.
     */
    private SprintNumber getNextSprintNumber(ProjectCode projectCode) {
        int number = sprintService.getNewSprintNumber(projectCode);

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
    private static NewProjectDTO createProjectDTO(CustomerID customerID, BusinessSectorID businessSectorID,
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
    private static NewUserStoryInfoDTO createUserStoryDTO(ProjectCode projectCode, UserStoryActor actor,
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
     * Creates a NewSprintDTO to use in the sprintService.createSprint method.
     * @param projectCode project code
     * @param sprintNumber sprint number
     * @param timePeriod_sprint time period of the sprint
     * @param status the status of each Sprint
     * @return a NewSprintDTO with the input data.
     */
    private static NewSprintDTO createNewSprintDTO(ProjectCode projectCode, SprintNumber sprintNumber, TimePeriod timePeriod_sprint, SprintStatus status) {
        NewSprintDTO sprintDTO = new NewSprintDTO();

        sprintDTO.projectCode = projectCode;
        sprintDTO.sprintNumber = sprintNumber;
        sprintDTO.timePeriod = timePeriod_sprint;
        sprintDTO.status = status;
        return sprintDTO;
    }

}
