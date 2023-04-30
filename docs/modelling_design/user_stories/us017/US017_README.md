# US017 - As Product Owner, I want to create a user story and add it to the Product Backlog

## Implementation clarifications

- UserStoryID is a VO that contains 2 other VOs: UserStoryNumber and ProjectCode. This is because, to be uniquely identified, a US needs both a number (an alphanumeric reference given by the user) and the code of the project to which it belongs.

## TODO

- DTO from UI to Controller (steps 2 & 3 from SD) should contain all data for User Story creation, i.e. projectCode and priority should not be separate parameters \[prof. Baltarejo, Sprint Review 4\].
  - didn't change this yet because what happens between UI and Controller will change with implementation of REST API; DTO will be a json object that will probably be unpacked by Service or between Controller and Service.

- Service should verify contents of DTO and, if necessary, form another DTO to pass into the domain (for security reasons) \[prof. Baltarejo, Sprint Review 4\]. Verifications could be checking string sizes, check strings for certain command keywords, etc.
