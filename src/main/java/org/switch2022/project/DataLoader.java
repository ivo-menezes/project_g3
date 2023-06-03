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
