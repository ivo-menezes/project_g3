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
