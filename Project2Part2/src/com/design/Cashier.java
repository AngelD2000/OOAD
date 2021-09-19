package com.design;
import java.util.*;

public class Cashier extends Employee{
    private double vacSkill;
    private String stackPref;
    Cashier(String setName, double vacBreakage, String stackPreference){
        setType("Cashier");
        setName(setName);
        vacSkill = vacBreakage;
        stackPref = stackPreference;
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
    public void stackShelf(GameList inventory){
        report("is stacking games on the shelf");
        String gameName = "";
        int shelfSize = inventory.size();
        int curr_dim = 0;
        int index = 1;
        int pick_dim = Integer.MIN_VALUE;
        if (stackPref == "height"){
            index = 2;
            pick_dim = Integer.MAX_VALUE;
        }
        ArrayList<String> gameAssigned = new ArrayList<>();
        //Loop through width to restack from widest to narrowest
        for(int i = 0; i < shelfSize; i++){
            int next = pick_dim;
            for (Map.Entry<String, Game> item:
                    inventory.entrySet()) {
                if(!gameAssigned.contains(item.getKey())){
                    curr_dim = item.getValue().getGameDimension().get(index);
                    if((stackPref == "width" && curr_dim > next) || (stackPref == "height" && curr_dim < next)){
                        next = curr_dim;
                        gameName = item.getKey();
                    }
                }
            }
            Game game = inventory.get(gameName);
            game.setPosOnShelf(i+1);
            gameAssigned.add(game.getGameName());
        }
        inventory.printGamePosition();
    }
}