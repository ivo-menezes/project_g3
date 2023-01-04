# Supplementary Specifications

## 1. **Introduction**
### 1.1. **Purpose**

The purpose of this document is to define all and any data pertaining to functionalities that are not expressed in the use-case diagrams present in the software, as well as any nun-functional requirements and design constraints of the same, for the user's knowledge.

### 1.2. **Scope**

The supplementary specifications cover all functional requirements not described in the use-case diagrams and all non-functional requirements.

### 1.3. **Definitions, Acronyms and Abbreviations**

- PM - Project Manager: A role for the users with User Profile. Responsible for the management of the project.
- PO - Product Owner: A role for the users with User Profile. Acts as connection between client and developing team, manages Product Backlog(1).
- SM - Scrum master: A role for the users with User Profile. Functions as a coach for the team to help enhance and streamline the processes used to achieve the objectives and goals(2).
- KPI - Key Performance Indicator: Metric used to evaluate the performance of the users and the process towards specific goals or targets(3).
- EVM - Earned Value Management: Project management technique which combines the measurement of progress with measurement of cost to assess the performance of a project. Used to determine whether a project is on track to be completed on time, within budget, and to the required quality (4).
- SPI - Schedule Performance Index: measures the progress made on a project, compared to the amount of progress that was planned(5).
- CPI - Cost Performance Index: measures the completed work of a project in terms of cost(5).

### 1.4. **References**

* (1) https://www.scrum.org/resources/what-is-a-product-owner
* (2) https://www.atlassian.com/agile/scrum/scrum-master
* (3) https://www.kpi.org/kpi-basics/
* (4) https://www.pinnaclemanagement.com/blog/earned-value-management-an-introduction
* (5) https://www.pmi.org/learning/library/practical-calculation-schedule-variance-7028#:~:text=The%20schedule%20performance%20index%20(SPI,project%20performance%20is%20on%20target.


### 1.5. **Overview**

The supplementary specifications serve as a guiding document for the user of the software. It describes the functional features not present in use-case diagrams, as well as non-functional features of the software. It will be developed alongside the project.


## 2. **FUNCTIONALITIES**

* *Legacy Data Input:* Users are able to import information regarding projects from other systems, as long as the mentioned information fulfils certain parameters or is imported in a file of a certain format. Files that are not correctly formatted or do not fulfil the current business rules will not be imported to the system.
  - Information on the type of information or business rules mentioned in the paragraph above are yet to be described.
* *Login:* The process of entering the user’s credentials of e-mail and password in order to create a session where the user will be able to access the system and its data.
* *Logout:* The process of closing the user’s session on the system.
* *Password reset:* A process that is present on the login page, in order to allow the user to change the password safely. The link present should open a form, where the user enters the e-mail and a button to send a message to the mailbox. The message will have a link where the user will enter to change the password.
* *Reporting:* The system has options for reporting, or the display of information pertaining certain topics. This information can be generated on screen or exported as an XML file. The information available for reporting is:
  - Allocations report:  This report shows the information about the resources and the respective projects they are associated to, by showing the distribution of time per associated projects, depending on the filtered start and end date. The Manager is able to access the information of all users associated to the projects, while PM, PO and SM are able to see the information corresponding to the projects they are associated to and all the users allocated to them. The TM will only be able to search information regarding itself and the projects they are allocated to.
  - KPI report: This report is only available to the Manager, PO and PM. It displays information regarding performance on the project, according to some EVM parameters (such as SPI and CPI). These reports consider the budget of the project, as well as the hourly cost of the resources who carry the activities within the project.
* *Scrum Recordkeeping:* All minutiae produced during the scrum ceremonies will be stored in the system, available for visualisation by the users participating in the projects. This recordkeeping is done during the different parts of the ceremonies (sprint planning, sprint review and spring retrospective, as examples), which will then be stored as history of what has been done, discussed and decided during these events, for later assessment by the users.
* *Task update:* Users associated to projects are able to update the tasks assigned to them in each project, in order to ensure a smooth process of development. This is done alongside time recording. The updates originate records, where a comment can be added to it, as well as an option to attach files deemed important to the update recordkeeping.

## 3. **USABILITY:**
- _SESSION TIMEOUT_: The system detects the activity of the user, closing the session of the user when not activity is detected in more than 30 minutes.
- _SAVED PASSWORDS_: The passwords should not be saved to the user’s database, in order not to allow others to access the account unduly.
- _LANGUAGE AND LOCATION_: Options will allow for the formatting of text, dates and decimals to be configured according to user preferences and language.

## 4. **RELIABILITY:**
- _LOGGING_: Generating reports on all user operations, system messages (warnings or errors), saved in log files and identified with the following levels: debug, info, warn, error.

## 5. **PERFORMANCE:**
- _RESPONSIVE LAYOUT_: The layout design developed for the user interface must be adaptable to different devices automatically. The layout will be able to adapt correctly to the screens of either a mobile phone or a laptop, display the information with no loss of resolution and/or data, to maximise the usability and user experience of the application.

## 6. **SUPPORTABILITY:**
- This point will be developed once there is more information pertaining the subject.

## 7. **OTHERS**
- This point will be developed once there is more information pertaining the subject.
