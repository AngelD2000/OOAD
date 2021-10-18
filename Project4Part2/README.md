## Project 3 Part 2 ##
### Team Members: ### 
Elly Landrum, Angel Dong, Sam Feig

### Java Version: ###
11.0.12
### Assumptions: ###
- All customers have a 50% chance of buying cookies
- Announcer is an EagerAnnouncer (Set this in Util.announcerType)
- Customer types are given by the probabilities in Util.customerChance {Family gamer: 24.5%, Kid Gamer: 24.5%, Card Gamer: 24.5%, Board Gamer: 25.5%, Cookie Monster: 1%}
### Updated UML Changes: ###
- Announcer made abstract to support the Singleton pattern in Eager and Lazy Announcer
- Announcer has no instance variable, this is instead only in Eager and Lazy Announcer
- Util updated for new functions and simulation variables

### For Bonus Run Difference with/without test###
JUnit runs through IntelliJ: (Private post piazza, got confirmation this is ok)
- Running without tests: Configuration is set to Main and click the run button
- Running with tests: Configuration is set to test and click the run button