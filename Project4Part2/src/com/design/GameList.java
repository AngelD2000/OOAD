package com.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//Each game in GameList has its own unique Identity
public class GameList extends HashMap<String, Game> {
    //This function is Polymorphic as it is iterating over all the games irrespective of their individual type
    //Some will be board games, some will be card games etc.
    /**
     * Prints all the game in the gamelist and their count
     */
    void printGameAmount(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> Util.print("    -" + key + " : " + (value.getCount())));
    }

    /**
     * Prints all the game in the gamelist and their count, number sold, and total value of sold
     */
    void printGameAmountAndSold(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getCount()) +" are in inventory and "
                + value.getNumSold() + " were sold in total, " +
                "and the total value of the games sold was " + Util.asDollar(value.getNumSold()*value.getPrice())));
    }

    /**
     * Function to print the game position on the shelf
     */
    void printGamePosition(){
        forEach((key, value) -> Util.print("    -" + key + " : " + (value.getPosOnShelf()) + " " + value.getGameDimension()));
    }
    /**
     * Function to print the total inventory of games
     */
    int getTotalInventory(){
        int total = 0;
        for (String key : this.keySet()) {
            total+=get(key).getCount();
        }
        return total;
    }
    /**
     * Get the game at a certain position
     */
    Game getGameAtPos(int i){
        for (String key : this.keySet()) {
            if (get(key).getPosOnShelf() == i){
                return get(key);
            }
        }
        return null;
    }

    /**
     * Function to handle removing a game of name from this game list.
     * Used for breaking games and selling games
     */
    public boolean removeGame(String gameName, boolean sold){
        if (gameName != null){
            Game gameObject = get(gameName);
            if(gameObject.getCount() > 0){
                gameObject.incrementCount(-1);
                if(sold) {
                    gameObject.incrementNumSold(1);
                }
                if(gameObject.getCount() == 0){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Function to handle adding a game of name from a game list of self.
     * NOTE: The passed game is not put in inventory, just a copy of it
     */
    public void addGame(String gameName, Game game){
        if(gameName != null){
            if(containsKey(gameName)){
                get(gameName).incrementCount(1);
            }
            else{
                Game temp;
                switch(game.getClass().getName()) {
                    case "BoardGame":
                    case "com.design.BoardGame":
                        temp = new BoardGame(game.getGameName(), game.getGameDimension(), game.getPrice());
                        break;
                    case "FamilyGame":
                    case "com.design.FamilyGame":
                        temp = new FamilyGame(game.getGameName(), game.getGameDimension(), game.getPrice());
                        break;
                    case "KidsGame":
                    case "com.design.KidsGame":
                        temp = new KidsGame(game.getGameName(), game.getGameDimension(), game.getPrice());
                        break;
                    case "CardGame":
                    case "com.design.CardGame":
                        temp = new CardGame(game.getGameName(), game.getGameDimension(), game.getPrice());
                        break;
                    default:
                       temp = new BoardGame(game.getGameName(), game.getGameDimension(), game.getPrice());
                }
                temp.setCount(Util.maxInventory);
                put(gameName, temp);
            }
        }
    }
    /**
     * Get key value of a game in the gamelist
     * https://www.baeldung.com/java-map-key-from-value
     */
    public String getKey(Game value) {
        for (Entry<String, Game> entry : this.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
    /**
     * Gets a random game of the designated type
     */
    public String pickRandomType(String type) {
        GameList games = new GameList();
        for (String key : this.keySet()) {
            Game hold = get(key);
            if (hold.getType().equals(type)){
                games.addGame(hold.getSimpleGameName(), hold);
            }
        }
        if(games.size() > 0){
            Random rand = new Random();
            List<String> keysAsArray = new ArrayList<String>(games.keySet());
            String pick = keysAsArray.get(rand.nextInt(games.size()));
            return pick;
        }
        return null;
    }
}