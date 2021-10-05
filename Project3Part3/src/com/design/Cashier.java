package com.design;
import java.util.*;


public class Cashier extends Employee {
    private final double vacSkill;
    private StackBehaviour stackPref;
    Cashier(String setName, double vacBreakage, StackBehaviour stackPreference){
        setType("Cashier");
        setName(setName);
        vacSkill = vacBreakage;
        stackPref = stackPreference;
    }

    /**
     * Cashier announces who they are and that they have arrived at the store.
     */
    public void arrive(int day){
        report("has arrived at the store on day ",day);
    }
    /**
     * Cashier does their best to vacuum
     */
    public boolean vacuum(){
        String broken = null;
        report("has started vacuuming the store.");
        return Util.testOdds(vacSkill);
    }

    /**
     * Unpack games ordered the previous day and add them to inventory
     */
    public void unpackOrders(GameList inventory, GameList orderedGames) {
        report("is restocking the games");
        for (Map.Entry<String, Game> order:
                orderedGames.entrySet()) {
            String ordered_game = order.getKey();
            Game restock = inventory.get(ordered_game);
            restock.setCount(Util.maxInventory);
            report("unpacked the order of " + inventory.getKey(restock));
        }
        orderedGames.clear();
    }

    /**
     * Function to stack the games on shelves according to cashier preferences
     */
    private void stackGames(GameList inventory) {
        stackPref.stack(this, inventory);
    }

    /**
     * Cashier stacks the shelf based on their preferences
     * If there are any ordered games from the previous night, cashier will restock the games in their original positions
     * */
    public void stackShelf(GameList inventory, GameList orderedGames){
        if(orderedGames.size() > 0){
            unpackOrders(inventory, orderedGames);
        }
        else{
            stackGames(inventory);
        }
    }

    /**
     * Cashier announce they are opening the store
     * Generate the number of customers that goes into the store
     * Generate the number of games a customer will buy and prints it out
     * Customer buys games
     */
    public void storeOpen(GameList inventory, Register register, Store store){
        report("is opening the store");
        double total = customersCome(inventory, store);
        checkout(register, total);
    }
    /**
     * Cashier announces who they are and that they are leaving the store.
     */
    public void close(){
        report("has left the store.");
    }

    /**
     * Go through every item in inventory and order them if 0 in stock (decreases money in register)
     * Documentation said to order games it is half the price it's listed (so buying a game is double the amount we ordered it.... expensive)
     */
    public void orderGame(GameList orderedGames, GameList inventory, Register register){
        for (String gameName : inventory.keySet()) {
            Game game = inventory.get(gameName);
            if(game.getCount() == 0) {
                report("ordered 3 more " + gameName);
                register.incrementStoreTotal(((game.getPrice() / 2) * Util.maxInventory) * -1);
                orderedGames.addGame(gameName, game);
            }
        }
    }
    /**
     * Cashier orders cookies for next day
     * Changes the dozens of cookies the cashier will bring
     */
    public void orderCookies(Baker baker, Store store){
        int cookies = store.getCookieInventory();
        if (cookies > 0){
            baker.incrementDozenPerDay(-1);
        }
        else{
            baker.incrementDozenPerDay(1);
        }
        store.countMissingCookies();
    }

    /**
     * Checkout customers and add purchased games total to the register
     */
    private void checkout(Register register, double total) {
        register.incrementStoreTotal(total);
    }

    //This is an example of Abstraction as this function is only needed within the Cashier class
    /**
     * Process each customer buying their game(s)
     * Handles changing the money in regsiter and games in stock
     * Will also announce sale
     */
    private double sellGames(GameList inventory, int customerNum, List<Integer> bought) {
        double total = 0.0;
        for (int j = 0; j < bought.size(); j++) {
            Game current = inventory.getGameAtPos(bought.get(j));
            current.setPrice();
            total += current.getPrice();
            boolean flag_store = inventory.removeGame(inventory.getKey(current), true);
            if (flag_store) {
                report(name + " sold the last " + current.getGameName() + " to Customer " + (customerNum + 1) + " for $" + current.getPrice());
            } else {
                report(name + " sold a " + current.getGameName() + " to Customer " + (customerNum + 1) + " for $" + current.getPrice());
            }
        }
        return total;
    }

    /**
     * Function to handle customers entering the store and shopping for games and cookies
     * The customers first considers whether they buy cookies or not
     * Then decides what games they buy
     */
    private double customersCome(GameList inventory, Store store) {
        double total = 0.0;
        String choice;
        int num_customers = 1+Util.poisson(3);
        store.customers.add(num_customers);
        report(" welcomed " + num_customers +  " customer(s) into the store.");
        for(int i=0; i < num_customers; i++){
            Customer nextCustomer = new Customer((i+1));
            if (nextCustomer.isMonster() == true){
                store.rampage();
            }
            else {
                choice = nextCustomer.considerCookies(store);
                report("sold customer " + (i + 1) + " " + choice);
                List<Integer> bought = nextCustomer.considerGames(inventory);
                if (bought.size() == 0) {
                    report("Customer " + (i + 1) + " didn't buy a game.");
                }
                total += sellGames(inventory, i, bought);
            }
        }
        return total;
    }
}