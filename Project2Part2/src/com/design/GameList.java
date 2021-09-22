package com.design;

import java.util.HashMap;

//Each game in GameList has its own unique Identity
public class GameList extends HashMap<String, Game> {
    //This function is Polymorphic as it is iterating over all the games irrespective of their individual type
    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    void printGameAmount(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> Util.print("    -" + key + " : " + (value.getCount())));
    }

    /**
     * Function to print the game position on the shelf
     */
    void printGamePosition(){
        forEach((key, value) -> Util.print("    -" + key + " : " + (value.getPosOnShelf()) + " " + value.getGameDimension()));
    }

    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    public boolean removeGame(String gameName){
        if (gameName != null){
            Game gameObject = get(gameName);
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
                Game temp;
                switch(game.getClass().getName()) {
                    case "BoardGame":
                        temp = new BoardGame();
                        break;
                    case "FamilyGame":
                        temp = new FamilyGame();
                        break;
                    case "KidsGame":
                        temp = new KidsGame();
                        break;
                    case "CardGame":
                        temp = new CardGame();
                        break;
                    default:
                       temp = new BoardGame();
                }
//                Game temp = new Game();
//                temp.setType(game.getType());
                temp.setCount(1);
                put(gameName, temp);
            }
        }
    }
}