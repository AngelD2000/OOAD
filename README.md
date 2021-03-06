## Object Oriented Analysis and Design Projects ##
### Team Members: ### 
Elly Landrum, Angel Dong, Sam Feig

### Java Version: ###
11.0.12

### Project 1 - Project 4 ###
#### Description:
A store that sells different kinds of games with cookies using OO patterns and simulate the running of the store for 30 days. 
Print the number in inventory, the number sold, and the total sales for that game type.  Also list the contents of the 
Damaged Game container, the final count of money in the Cash Register, as well as how many times money was 
added to the register due to low funds in the Count the Money step.  Additionally, list the number of cookies 
sold each day and in total, as well as total cookies lost to the Cookie Monster; and the total amount of money 
paid to Gonger (which he puts in his pocket) for his cookies. Capture all output from a single simulation run in your repository in a text file called Output.txt

#### OO Pattern Used: 
- Decorator pattern implementation to support the following optional purchases and additional cost for 
these games. A three-level inheritance model for Games:  Game <- Family Game <- Monopoly (for instance).  
- Strategy pattern for the new cashier Bart. First, Bart stacks 
games based on their width, with the widest games in the first shelf position.  However, Bart will wait to 
stack games with an inventory count of one until all games with a greater inventory count have been 
stacked. Second, Bart will only damage games during the vacuum step with a 2% chance.
- Observer pattern for announcer Guy. Guy arrives each day just before the 
Cashier in the morning and is the last Employee to leave the store.  Guy must subscribe for events from 
all other Employees, each of which becomes an observable publisher. 
-  Use a Factory Pattern to create each type of arriving Customer 
object.  Customer types include Family Gamer, Kid Gamer, Card Gamer, Board Gamers, and Cookie 
Monster.  Decide on the chance of each type of Customer arriving (the Cookie Monster should only 
rarely arrive on 1% or 2% chance).  Customers, when created, should randomly assign base purchase 
chance bonuses to the 3 games in their category of 20%, 10%, and 0%. 
- The Announcer will become a Singleton Pattern object.  Create two versions of the Announcer, one with 
eager instantiation (EagerAnnouncer), one with lazy instantiation (LazyAnnouncer).  Clearly indicate how 
the version of the Announcer Singleton can be selected at compile or run time. 
- A new Employee, the Demonstrator, will be created.  The Demonstrator will arrive at the store just 
before Customers begin entering and will leave after all Customers are served.  Arriving Customer 
objects will make Command Requests to the active Cashier before purchasing Cookies or Games.  
- Customers will use a Command Pattern to issue Command Requests to the active Cashier.  The Cashier 
(as Invoker) will ask the Demonstrator to perform Demonstrate, Recommend, and Explain Commands.  
Remember that as an Employee, the Demonstrator must use the Announcer to say what it is doing. 

#### Other:
- JUnit tests to check proper initialization of objects and the correctness of functions
- UML diagram documenting the architecture of the whole program
- UML sequence diagram for Project 4 clearly indicating the communication between class


### Project 5 - Project 6 ###
#### Description: 
Our project is Flashpoint Lite, a implementation of a subset of the game Flashpoint. Our team is Angel Dong, Sam Feig, and Elly Landrum. Our completed project will be a playable version of the game Flashpoint with explosions, structural damage, firefighters, points of interest, fire, smoke, and an interface to play the game with all these elements. When we???re done, a user should be able to control 4 firefighters to play a fun game! This game shall be a standard Java application.

#### OO Pattern Used: 
- MVC: MVC describes the general interaction of our classes, and also how we divided the work. It was helpful for our organization to be able to assign Sam to the model (MapFactory, Map, Edge, and all the Squares), Angel to the view (MapView, Menu, setWindow, and ViewManager) and Elly to the controller (Game, Building, FireLogic, FirefighterLogic, Company, and Firefighters).
- Composite: Our design uses 2 different composites: The Map to store all the squares in the game, and the company to organize and interact with the firefighters. This Company was very helpful in the controller, because it allowed many of the actions taken in the game to just be passed to the company which can then route to the active Firefighter.
- Iterator: The Map composite is also an iterator pattern. It was key for both the UI and firelogic to be able to iterate over every square and display, analyze, or change it. By having Map implement Java???s Iterator interface, Map can support functions such as hasNext() and next() to iterate over all the squares. The other functions of the Iterator interface (remove() and forEachRemaining()) were left unimplemented throwing exceptions as they are unneeded for the use case. Map also has a resetIterator() function that resets the iterator when you are done using it.
- Factory: We created a map factory to handle the setup of the game board. Map factory creates the 8x6 map of squares adding outside squares and edges in the specified locations. It calls fire logic to place 4 explosions, calls building to place 3 POI, and then places the firefighters in their designated position. 
- Singleton: The map factory also uses the eager singleton pattern as there is no need for more than one factory when creating the static map. 
- Decorator: The squares on the board implement a decorator pattern to enable their different behaviors. The Square interface defines all the functions and behaviors a square can have. The BaseSqaure class is the representation of an empty square with nothing on it and implements all the Square functions. SquareDecorator is the decorator that acts as a passthrough, so all the functions of the square interface are passed through to the BaseSqaure that was used to create the SquareDecorator. From there, all the specialized squares (Fire, Smoke, Outside, POI, Victim) extend the SquareDecorator and override only the methods they need to change the behavior of the square letting all other implementations pass through to the BaseSquare???s versions. For instance, in a FireSquare, hasFire() will return true and removeFire() will create a new Smoke decorated square when the default behavior in BaseSquare is to return false and return a null square.

#### Other:
- UML diagram documenting the architecture of the whole program

