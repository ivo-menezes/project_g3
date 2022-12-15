## **US005 - To inactivate a user account.**

### **1.1 – User Story Description**

As Administrator, I want to inactivate a user account.

### **1.2 – Customer Specifications and Clarifications**

**From the specifications document:**
* "The system should have an administration area, which allows the Administrator to […] Activate and disable user accounts […]."

**From the client clarifications:**
- *Question:* Which “privileges” are withdrawn from an inactive user account?
- *Answer:*


- *Question:* In case of more than one administrator, can an administrator inactivate another administrator?
- *Answer:*

### **1.3 – Acceptance criteria**

- **AC1:** A user account must be active.
- **AC2:** The user account must become inactive.

### **1.4 – Found out dependencies**

There is a dependency to US002. A user must be previously registered with a user account.

There is a dependency to US004. The admin must be able to list user accounts and check their status.

### **1.5 – Input and output data**

**Input Data:**

- E-mail and profile (for searching purposes of a specific profile).

**Output data:**

- Inactivation of user account.