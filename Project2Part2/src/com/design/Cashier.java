package com.design;
import java.util.*;


public class Cashier extends PublisherEmployee{
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
        if(randomValue < vacSkill && games.getTotalInventory() > 0){
            Random rand = new Random();
            //Random key of hashmap: https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java
            List<String> keysAsArray = new ArrayList<String>(games.keySet());
            broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            while (games.get(broken).getCount() == 0){
                broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            }
            if(games.get(broken).getCount() == 1){
                Util.print(name + " the " + type + " broke the last " + broken);
            }
            else{
                Util.print(name + " the " + type + " broke a " + broken);
            }
        }
        report("has finished vacuuming the store.");
        return broken;
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
            restock.setCount(3);
            Util.print("ORDER ARRIVED " + restock.getGameName() + " is now in stock");
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
    public void storeOpen(GameList inventory, Register register){
        report("is opening the store");

        double total = customersCome(inventory);
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
                double storeTotal = register.getStoreTotal();
                storeTotal -= (game.getCost() / 2) * 3;
                register.setStoreTotal(storeTotal);
                orderedGames.addGame(gameName, game);
            }
        }
    }

    /**
     * Checkout customers and add purchased games total to the register
     */
    private void checkout(Register register, double total) {
        register.setStoreTotal(register.getStoreTotal() + total);
    }

    //This is an example of Abstraction as this function is only needed within the Cashier class
    /**
     * Process each customer buying their game(s)
     */
    private double buyGames(GameList inventory, int customerNum, List<Integer> bought) {
        Random rand = new Random();
        double total = 0.0;
        String game_buy = "";
        for (int j = 0; j < bought.size(); j++) {
            Game current = inventory.getGameAtPos(bought.get(j));
            total += current.getCost();
            boolean flag_store = inventory.removeGame(current.getGameName(), true);
            if (flag_store) {
                Util.print(name + " sold the last " + current.getGameName() + " to customer " + (customerNum + 1) + " for $" + current.getPrice());
            } else {
                Util.print(name + " sold a " + current.getGameName() + " to customer " + (customerNum + 1) + " for $" + current.getPrice());
            }
        }
        return total;
    }

    /**
     * Function to handle customers entering the store and shopping
     */
    private double customersCome(GameList inventory) {
        double total = 0.0;
        Random customer_rand = new Random();
        String game_buy = "";
        int num_customers = customer_rand.nextInt(5);
        Util.print(num_customers + " customer(s) entered the store.");
        Random game_rand = new Random();
        int odds = game_rand.nextInt(100);
        for (int i = 0; i < num_customers; i++) {
            List<Integer> bought = new ArrayList<Integer>();
            //For every shelf position
            for (int j = 0; j < inventory.size(); j++) {
                //See if game picked if in stock
                if(inventory.getGameAtPos(j+1).getCount() > 0) {
                    odds = game_rand.nextInt(100);
                    if (odds < 20 - 2 * j && bought.size() < 2) {
                        bought.add(j + 1);
                    }
                }
            }
            if (bought.size() == 0) {
                Util.print("Customer " + (i + 1) + " didn't buy a game.");
            }
            total += buyGames(inventory, i, bought);
        }
        return total;
    }
}