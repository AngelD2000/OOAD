package com.design;

/*
Updated UML Changes:
-3-layer Game inheritance was converted to a 2-layer
-GameList was added as a class that extends HashMap<String, Game> to handle inventory and ordered games.
-Register class was added to handle accounting of money for the store
-Util was added as an abstract class that contains only static methods and static final variables to handle utility
functions and simulation setup variables.
-Functions and variables for all classes were updated to reflect true state of the code after it was written
 */
public class Main {
    public static void main(String[] args) {
        Simulator sim = new Simulator();
        sim.runSim();
    }
}