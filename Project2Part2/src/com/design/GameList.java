package com.design;

import java.util.ArrayList;
import java.util.HashMap;

public class GameList extends HashMap<String, Game> {
    void prettyPrintList(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getCount())));
    }

    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    public boolean removeGame(String game){
        if (game != null){
            Game gameObject = get(game);
            if(gameObject.getCount() > 0){
                gameObject.incrementCount(-1);
                if(gameObject.getCount() == 0){
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

    public float orderGame(String gameName, GameList inventory, float storeTotal){
        Game game = inventory.get(gameName);
        Game temp = new Game();
        temp.setType(game.getType());
        put(gameName, temp);
        storeTotal -= (game.getCost()/2) * 3;
        return storeTotal;
    }
}