package com.design;
import java.util.*;

public class Cashier extends Employee{
    private double vacSkill;
    Cashier(String setName, double vacBreakage){
        setType("Cashier");
        setName(setName);
        vacSkill = vacBreakage;
    }

    /**
     * Cashier announces who they are and that they have arrived at the store.
     */
    public void arrive(int day){
        report("has arrived at the store Day ",day);
    }
    /**
     * Cashier does their best to vacuum
     */
    public String vacuum(GameList games){
        String broken = null;
        report("has started vacuuming the store.");
        //Description of Math.random: https://www.geeksforgeeks.org/java-math-random-method-examples/
        double randomValue = Math.random();
        if(randomValue < vacSkill){
            Random rand = new Random();
            //Random key of hashmap: https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java
            //TODO: I'm not positive this is correctly picking a random game in inventory
            List<String> keysAsArray = new ArrayList<String>(games.keySet());
            broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            System.out.println(name + " the " + type + " broke a " + broken);
        }
        report("has finished vacuuming the store.");
        return broken;
    }
    /**
     * Cashier announces who they are and that they are leaving the store.
     */
    public void open(){report("is opening the store");}
    public void close(){
        report("has left the store.");
    }
}