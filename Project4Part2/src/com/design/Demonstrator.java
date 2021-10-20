package com.design;

public class Demonstrator extends Employee {
    Demonstrator(){
        setType("Demonstrator");
        setName(Util.pickName());
    }
    Demonstrator(Announcer guy){
        setType("Demonstrator");
        setName(Util.pickName());
        this.subscribe(guy);
        Util.print(guy.getName() + " Subscribed to " + this.getName());
    }

    /**
     * Pick a game for a customer
     * Will return name of game
     */
    public String pickGame(GameList inventory, Customer customer){
        String gameType = Util.customerTypeToGameType(customer.getType());
        return inventory.pickRandomType(gameType);
    }
    /**
     * Demonstrate a game for a customer
     * Will return how much more likely customer is to buy now
     */
    public double demonstrate(String gameName, String customerName){
        this.report("demonstrates " + gameName + " for customer " + customerName);
        return .1;
    }
    /**
     * Explain a game for a customer
     * Will return how much more likely customer is to buy now
     */
    public double explain(String gameName, String customerName){
        this.report("explains " + gameName + " for customer " + customerName);
        return .1;
    }
    /**
     * Recommend a game for a customer
     * Will return the name of the game recommended
     */
    public double recommend(String gameName, String customerName){
        this.report("recommends " + gameName + " for customer " + customerName);
        return .1;
    }
}