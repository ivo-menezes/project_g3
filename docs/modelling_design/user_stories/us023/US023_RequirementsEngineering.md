## **US023 - I want to create a new project in the web UI**

### **1.1 – User Story Description**

As Manager, I want to create a new project in the web UI.

### **1.2 – Customer Specifications and Clarifications**

- **From the specifications document:**

The system should allow the registration of projects, the base entity that will serve for the association of activities and resources (users). Each project must have at least the following information: “Code (unique alphanumerical identifier for each project)”, Project manager (user who will have the project manager profile for that record; may change over time)”, “Name”, “Description”, “Start Date”, “Sprint duration (initial estimate)”, “Number of planned sprints”, “End Date (when available, date it was closed)”, “Customer”, “Business Sector”, “Typology (Fixed Cost / Time and materials)”, “Product Owner (may change over time)”, “Scrum Master (may change over time)”, “Project team (may change over time)”, “Project Status (Planned / Inception / Elaboration / Construction / Transition / Warranty/ Closed)”, “Budget (monetary amount available for resource spending)”.

The process of creating information relating to a project should be available exclusively for the Manager profile. However, the Project Manager should be able to edit part of the information.

"The user interface to the system should be a web application developed using ReactJS."


- **From the client clarifications:**

*Question: What is essential/minimum to create a project? Registering includes saving?

*Answer:


### **1.3 – Acceptance criteria**

- Awaiting further information regarding Acceptance criteria;

### **1.4 – Found out dependencies**

### **1.5 – Input and output data**

**Input Data:**

- Code (unique alphanumerical identifier for each project);
- Name;
- Description;
- Start Date;
- End Date (when available, date it was closed);
- Customer;
- Sprint duration (initial estimate);
- Number of planned sprints;
- Project Status (Planned / Inception / Elaboration / Construction / Transition / Warranty/ Closed);
- Budget (monetary amount available for resource spending).

**Output Data:**

- Project successfully created.