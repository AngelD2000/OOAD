## Project 3 Part 2 ##
### Team Members: ### 
Elly Landrum, Angel Dong, Sam Feig

### Java Version: ###
11.0.12
### Assumptions: ###
- All customers have a 50% chance of buying cookies

### Updated UML Changes: ###
- 2-layer Game inheritance was converted to a 3-layer
- Add-On class details were added to make Decorator pattern clearer
- Simulator Class was added
- Specific subclasses of StackBehavior were added using the Strategy pattern
- Announcer methods and variables were added
- Baker methods and variables were added
- Interface implementation was added for Announcer from Flow.Subscriber
- Employee inheritance was added for SubmissionPublisher which is defined in java.util.concurrent that implements Flow.Publisher
- The above two changes enable the Observer Pattern and are both using the implementations of the pattern from java.util.concurrent.Flow
- Store methods and variables were updated to add customers and associations to new employee types