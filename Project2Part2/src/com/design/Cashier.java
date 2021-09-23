package com.design;
import java.util.*;


public class Cashier extends Employee{
    private final double vacSkill;
    private final String stackPref;
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
        if(randomValue < vacSkill && games.getTotalInventory() > 0){
            Random rand = new Random();
            //Random key of hashmap: https://stackoverflow.com/questions/12385284/how-to-select-a-random-key-from-a-hashmap-in-java
            List<String> keysAsArray = new ArrayList<String>(games.keySet());
            broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            while (games.get(broken).getCount() == 0){
                broken = keysAsArray.get(rand.nextInt(keysAsArray.size()));
            }
            Util.print(name + " the " + type + " broke a " + broken);
        }
        report("has finished vacuuming the store.");
        return broken;
    }

    private void unpackOrders(GameList inventory, GameList orderedGames) {
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

    private void stackGames(GameList inventory) {
        report("is stacking games on the shelf");
        String gameName = "";
//        int shelfSize = inventory.size();
        int curr_dim = 0;
        int index = 1;
        int pick_dim = Integer.MIN_VALUE;
        if (stackPref.equals("height")){
            index = 2;
            pick_dim = Integer.MAX_VALUE;
        }
        ArrayList<String> gameAssigned = new ArrayList<>();
        //Loop through width to restack from widest to narrowest
        for(int i = 0; i < inventory.size(); i++){
            int next = pick_dim;
            for (Map.Entry<String, Game> item:
                    inventory.entrySet()) {
                if(!gameAssigned.contains(item.getKey())){
                    curr_dim = item.getValue().getGameDimension().get(index);
                    if((stackPref.equals("width") && curr_dim > next) || (stackPref.equals("height") && curr_dim < next)){
                        next = curr_dim;
                        gameName = item.getKey();
                    }
                }
            }
            Game game = inventory.get(gameName);
            game.setPosOnShelf(i+1);
            gameAssigned.add(game.getGameName());
            report("stacked the game " + game.getGameName() + " in position " + String.valueOf(i+1));
        }
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
     * Go through every item in orderedGames list grab the cost of each game and order them (decreases money in register)
     * Documentation said to order games it is half the price it's listed (so buying a game is double the amount we ordered it.... expensive)
     */
    public void orderGame(GameList orderedGames, GameList inventory, Register register){
        //https://stackoverflow.com/questions/3973512/java-hashmap-how-to-get-a-key-and-value-by-index
        for (Map.Entry<String, Game> order: inventory.entrySet()) {
            String gameName = order.getKey();
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

    private void checkout(Register register, double total) {
        register.setStoreTotal(register.getStoreTotal() + total);
    }

    //This is an example of Abstraction as this function is only needed within the Cashier class
    /**
     * Process each customer buying their game
     */
    private double buyGames(GameList inventory, int customerNum, int num_games) {
        Random rand = new Random();
        double total = 0.0;
        String game_buy = "";
        for (int j = 0; j < num_games; j++) {
            List<String> keysAsArray = new ArrayList<String>(inventory.keySet());
            game_buy = keysAsArray.get(rand.nextInt(keysAsArray.size()));
//            while (inventory.get(game_buy).getCount() == 0) {
//                Util.print("Customer tries to buy " + game_buy + " but it was out of stock.");
//                game_buy = keysAsArray.get(rand.nextInt(keysAsArray.size()));
//            }
            int cost = inventory.get(game_buy).getCost();
            total += cost;
            boolean flag_store = inventory.removeGame(game_buy);
            if (flag_store) {
//                orderedGames.put(game_buy, inventory.get(game_buy));
                Util.print("Customer " + (customerNum + 1) + " bought the last " + game_buy);
            } else {
                Util.print("Customer " + (customerNum + 1) + " bought " + game_buy);
            }
        }
        return total;
    }

    private double customersCome(GameList inventory) {
        double total = 0.0;
        Random customer_rand = new Random();
        String game_buy = "";
        int num_customers = customer_rand.nextInt(5);
        Util.print(num_customers + " customer(s) entered the store.");
        for (int i = 0; i < num_customers; i++) {
            Random game_rand = new Random();
            int shelf = game_rand.nextInt(100);

            //20% from shelf 1 18% from shelf 2 ...

//            if(self <= 20) //5% chance
//            {
//                Util.print("You found 10 gold!");
//            }
//            else if(rand <= 25) //20% chance
//            {
//                Util.print("You found a sword!");
//            }
//            else if(rand <= 70) //45% chance
//            {
//                Util.print("You found a shield!");
//            }
//            else if(rand <= 90) //20% chance
//            {
//                Util.print("You found armor!");
//            }
//            else //80% chance
//            {
//                //Do Nothing
//            }



            int num_games = game_rand.nextInt(3);
            if (num_games == 0) {
                Util.print("Customer " + (i + 1) + " didn't buy a game.");
            }
            total += buyGames(inventory, i, num_games);
        }
        return total;
    }
}