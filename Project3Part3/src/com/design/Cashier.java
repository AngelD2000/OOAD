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
            report("ORDER ARRIVED " + inventory.getKey(restock) + " is now in stock");
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
                register.incrementStoreTotal((game.getCost() / 2) * Util.maxInventory);
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
     */
    private double buyGames(GameList inventory, int customerNum, List<Integer> bought) {
        Random rand = new Random();
        double total = 0.0;
        for (int j = 0; j < bought.size(); j++) {
            Game current = inventory.getGameAtPos(bought.get(j));
            current.setPrice();
            total += current.getCost();
            boolean flag_store = inventory.removeGame(inventory.getKey(current), true);
            if (flag_store) {
                report(name + " sold the last " + current.getGameName() + " to customer " + (customerNum + 1) + " for $" + current.getPrice());
            } else {
                report(name + " sold a " + current.getGameName() + " to customer " + (customerNum + 1) + " for $" + current.getPrice());
            }
        }
        return total;
    }

    /**
     * Function to handle customers entering the store and shopping
     */
    private double customersCome(GameList inventory, Store store) {
        double total = 0.0;
        Random customer_rand = new Random();
        String game_buy = "";
        int num_customers = 1+Util.poisson(3);
        store.customers.add(num_customers);
        report(" welcomed " + num_customers +  " customer(s) into the store.");
        List<Customer> customers = new ArrayList<>();
        for(int i=0; i < num_customers; i++){
            Customer nextCustomer = new Customer((i+1));
            if (nextCustomer.isMonster() == true){
                store.rampage();
            }
            else {
                String result = nextCustomer.considerCookies(store);
                report("says that \"Customer " + nextCustomer.customerNum + " " + result + "\"");
                //TODO: Customer buys games with new cookie odds
            }
        }
        Random game_rand = new Random();
        for (int i = 0; i < num_customers; i++) {
            List<Integer> bought = new ArrayList<Integer>();
            //For every shelf position
            for (int j = 0; j < inventory.size(); j++) {
                //See if game picked in stock
                if(inventory.getGameAtPos(j+1).getCount() > 0) {
                    if (Util.testOdds(20 - 2 * j) && bought.size() < 2) {
                        bought.add(j + 1);
                    }
                }
            }
            if (bought.size() == 0) {
                report("Customer " + (i + 1) + " didn't buy a game.");
            }
            total += buyGames(inventory, i, bought);
        }
        return total;
    }
}