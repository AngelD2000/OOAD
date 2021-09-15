import java.util.*;

@SuppressWarnings("serial")
public class GameList extends HashMap<String, Game> {
    /**
     * Pretty prints games in list
     */
    void prettyPrintList(){
        //https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/
        forEach((key, value) -> System.out.println("    -" + key + " : " + (value.getCount())));
    }
    /**
     * Function to handle removing a game of name from a game list of self.
     * Used for breaking games and selling games
     */
    void removeGame(String game){
        if (game != null){
            Game gameObject =get(game);
            gameObject.incrementCount(-1);
            if (gameObject.getCount() == 0){
               remove(game);
            }
        }
    }
    /**
     * Function to handle adding a game of name from a game list of self.
     */
    void addGame(String game){
        if (game != null){
            if (containsKey(game)){
                get(game).incrementCount(1);
            }
            else{
                int[] dimensions = {10, 10, 10};
                BoardGame x = new BoardGame();
                x.init(game, dimensions);
                x.incrementCount(1);
                put(game, x);
            }
        }
    }
}