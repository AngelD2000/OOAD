package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameList extends HashMap<String, Game> {
    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    void printGameAmount(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getCount())));
    }
    void printGamePosition(){
        forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getPosOnShelf()) + " " + value.getGameDimension()));
    }

    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    public boolean removeGame(String gameName, GameList orderedGames){
        if (gameName != null){
            Game gameObject = get(gameName);
            if(gameObject.getCount() > 0){
                gameObject.incrementCount(-1);
                if(gameObject.getCount() == 0){
                    Game game = get(gameName);
                    Game temp = new Game();
                    temp.setType(game.getType());
                    orderedGames.put(gameName, temp);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Function to handle adding a game of name from a game list of self.
     */
    public void addGame(String gameName, Game game){
        if(gameName != null){
            if(containsKey(gameName)){
                get(gameName).incrementCount(1);
            }
            else{
                Game temp = new Game();
                temp.setType(game.getType());
                temp.setCount(1);
                put(gameName, temp);
            }
        }
    }

    /**
     * Go through every item in orderedGames list grab the cost of each game and order them (decreases money in register)
     * Documentation said to order games it is half the price it's listed (so buying a game is double the amount we ordered it.... expensive)
     */
    public void orderGame(GameList inventory,Register register){
        //https://stackoverflow.com/questions/3973512/java-hashmap-how-to-get-a-key-and-value-by-index
        for (Map.Entry<String, Game> order: entrySet()) {
            String gameName = order.getKey();
            Game game = inventory.get(gameName);
            float storeTotal = register.getStoreTotal();
            storeTotal -= (game.getCost()/2) * 3;
            register.setStoreTotal(storeTotal);
        }
    }
}