#Association Table

| Concept A       | Association     | Concept B         |
|-----------------|-----------------|-------------------|
| Account         | has             | Profile           |
| Profile         | created by      | Profile (Admin)   |
| Profile         | controlled by   | Account (Admin)   |
| Project         | created by      | Account (Manager) |
| Role            | performed by    | Account (User)    |
| Role            | participates in | Project           |
| Role            | works on        | Task              |
| Project         | includes        | Product Backlog   |
| Project         | entails         | Sprint            |
| Product Backlog | collects        | User Story        |
| Sprint          | implements      | Task              |
| Sprint          | defines         | Sprint BackLog    |
| User Story      | picked into     | Sprint Backlog    |
