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
     * Go through every item in orderedGames list and order the games with orderGame function and spend money to get the games
     */
    public void orderGame(GameList inventory,Register register){
        for (Map.Entry<String, Game> order: entrySet()) {
            String gameName = order.getKey();
            float storeTotal = register.getStoreTotal();
            Game game = inventory.get(gameName);
            storeTotal -= (game.getCost()/2) * 3;
            register.setStoreTotal(storeTotal);
        }
    }
}