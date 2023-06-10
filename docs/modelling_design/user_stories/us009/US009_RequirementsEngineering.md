## **US009 - I want to add a costumer**

### **1.1 – User Story Description**

As Administrator, I want to add a costumer.

### **1.2 – Customer Specifications and Clarifications**

- **From the specifications document:**

Each project must have at least the following information: […] Customer;

- **From the client clarifications:**

1) Question: Attributes of customer will be, for example, id, name, address, etc.?

Answer: Customer designation will be enough for now.

2) Question: For US009, we are using a Tax Identification Number as an attribute of Customer. Should we assume that this Tax Identification Number is a Portuguese TIN and check if it is a valid number (according to the TIN rules in Portugal)?

Answer: The NIF should be validated according to the rules of the country of origin. For example, they should support Portugal and Spain. The rest can only be validated in length (9 digits). NIF rules in Spain: https://es.wikipedia.org/wiki/N%C3%BAmero_de_identificaci%C3%B3n_fiscal

3) Is it intended that a customer be created and added to a project? Or is it assumed that the customer already exists, is he just added to a project?

Answer: Sorry, neither of those. The user story doesn't talk about a project, does it? It just means that you want to add a customer to the system.

### **1.3 – Acceptance criteria**

- Awaiting further information regarding Acceptance criteria;

### **1.4 – Found out dependencies**

### **1.5 – Input and output data**

**Input Data:**

- CustomerNIF
- CustomerDesignation

**Output Data:**

- Confirms customer creation