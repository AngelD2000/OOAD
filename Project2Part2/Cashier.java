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
    public void arrive(){
        report("has arrived at the store.");
    }
    /**
     * Cashier announces who they are and that they have arrived at the store.
     */
    public Game vacuum(List<Game> games){
        Game broken = null;
        report("has started vacuuming the store.");
        //Description of Math.random: https://www.geeksforgeeks.org/java-math-random-method-examples/
        double randomValue = Math.random();
        if(randomValue < vacSkill){
            Random rand = new Random();
            broken = games.get(rand.nextInt(games.size()));
            System.out.println(name + " the " + type + " broke a " + broken.name);
        }
        report("has finished vacuuming the store.");
        return broken;
    }
}