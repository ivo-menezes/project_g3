## **US026 - As Product Owner/Scrum Master/Team Member, I want to consult the product backlog in the web UI.**

### **1.1 – User Story Description**

As Product Owner/Scrum Master/Team Member, I want to consult the product backlog in the web UI.

### **1.2 – Customer Specifications and Clarifications**

**From the specifications document:**

- "In scrum, the requirements of the project are expressed as user stories and the set of all user
stories (US) is the “product backlog”, i.e., the ordered set (by priority) of all user stories still to be implemented in the project."
- "The user interface to the system should be a web application developed using ReactJS."

**From the client clarifications:**

- *Question:* What kind of information should the Product Backlog contain?
- *Answer:*  The Product Backlog is the list of User Stories to be implemented (not concluded nor deleted). Probably it should be a list of strings.


- *Question:* How to define the priority of User Stories? Is it possible for two User Stories to have the same priority?
- *Answer:* The product Backlog is the list of User Stories yet to be implemented, by descendent order of priority. Naturally, two User Stories can not have the same priority since a list can not have two elements in the same position [...] the priority/position in the list can be set by the Product Owner. New User Stories can be added to the Product Backlog. The relative User Story positioning can be defined by the Product Owner.

### **1.3 – Acceptance criteria**

- Awaiting further information regarding acceptance criteria.

### **1.4 – Found out dependencies**

* There is a dependency to US010. A project must have been previously created.
* There is a dependency to US011. A Team Member must have been previously associated to a project.
* There is a dependency to US012. A Product Owner must have been previously associated to a project.
* There is a dependency to US013. A Scrum Master must have been previously associated to a project.
* There is a dependency to US017. A project must have a Product Backlog.
* There is a dependency to US018.

### **1.5 – Input and output data**

Input Data:
- Project code

Output Data:

- Product Backlog of the specified project