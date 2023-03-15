## **US017 - To create a User Story**

### **1.1 – User Story Description**

As Product Owner, I want to create a user story and add it to the Product Backlog

### **1.2 – Customer Specifications and Clarifications**

**From the specifications document:**
* "the set of all user stories (US) is the “product backlog”, i.e., the ordered set (by priority) of all user stories still to be implemented in the project."

* "A US must have at least the following information:
  - US number;
  - Actor;
  - US text;
  - List of acceptance criteria."

**From the client clarifications:**
- *Question:* Is the US priority defined at creation or after it is already added to the Product Backlog? I.e. would it be added to the list in the last position and then reordered?

- *Answer:* US don't have a "priority" property. Its priority is given by its position in the list. The PO could define the position when adding a US. If nothing is indicated, it could be added at the beginning or end.


- *Question:* When creating a US, is its number given automatically? When creating a US, are there criteria to ensure it is not repeated?

- *Answer:* The US ID is given by the author. It is not automatic. Obviously, there can't be two US with the same ID. This includes concluded US. Note that for example US03 and US03v2 are not the same.

### **1.3 – Acceptance criteria**


### **1.4 – Found out dependencies**

- There is a dependency to US010: The project has to already exist.
- There is a dependency to US012: The project has to already have a PO.
- There is a dependency to US016: The PO has to be able to list all projects he is assigned to.

### **1.5 – Input and output data**

**Input Data:**

- Project code
- User Story ID, Actor, Text and Acceptance Criteria
- User Story priority, i.e. position that it will take in the list

**Output data:**
- User Story successfully created and added to Product Backlog.