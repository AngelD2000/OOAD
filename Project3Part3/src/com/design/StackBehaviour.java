package com.design;

import java.util.ArrayList;
import java.util.Map;

abstract class StackBehaviour {
    /**
     * Strategy for cashier in stacking game
     * Will take cashier (for announcing) and inventory and re-sort inventory based on stack preference
     */
    void stack(Cashier cashier, GameList Inventory){

    }
}
class StackShortToTall extends StackBehaviour{
    /**
     * Stacks shortest games on bottom and tallest on top
     */
    void stack(Cashier cashier, GameList inventory){
        cashier.report("is stacking games on the shelf");
        String gameName = "";
        int curr_dim = 0;
        int index = 2;
        int pick_dim = Integer.MAX_VALUE;
        ArrayList<String> gameAssigned = new ArrayList<>();
        //Loop through width to restack from shortest to tallest
        for(int i = 0; i < inventory.size(); i++){
            int next = pick_dim;
            for (Map.Entry<String, Game> item:
                    inventory.entrySet()) {
                if(!gameAssigned.contains(item.getKey())){
                    curr_dim = item.getValue().getGameDimension().get(index);
                    if(curr_dim < next){
                        next = curr_dim;
                        gameName = item.getKey();
                    }
                }
            }
            Game game = inventory.get(gameName);
            game.setPosOnShelf(i+1);
            gameAssigned.add(game.getGameName());
            cashier.report("stacked the game " + game.getGameName() + " in position " + String.valueOf(i+1) + " because of its height of " + game.getGameDimension().get(index));
        }
    }
}

class StackWideToNarrow extends StackBehaviour{
    /**
     * Stacks widest games on bottom and narrowest on top
     */
    void stack(Cashier cashier, GameList inventory){
        cashier.report("is stacking games on the shelf");
        String gameName = "";
        int curr_dim;
        int index = 1;
        int pick_dim = Integer.MIN_VALUE;
        ArrayList<String> gameAssigned = new ArrayList<>();
        //Loop through width to restack from shortest to tallest
        for(int i = 0; i < inventory.size(); i++){
            int next = pick_dim;
            for (Map.Entry<String, Game> item:
                    inventory.entrySet()) {
                if(!gameAssigned.contains(item.getKey())){
                    curr_dim = item.getValue().getGameDimension().get(index);
                    if(curr_dim > next){
                        next = curr_dim;
                        gameName = item.getKey();
                    }
                }
            }
            Game game = inventory.get(gameName);
            game.setPosOnShelf(i+1);
            gameAssigned.add(game.getGameName());
            cashier.report("stacked the game " + game.getGameName() + " in position " + String.valueOf(i+1) + " because of its width of " + game.getGameDimension().get(index));
        }
    }
}

class StackWidthAndCount extends StackBehaviour {
    /**
     * Stacks widest games on bottom and narrowest on top
     * However, games with inventory of 1 will always go last
     */
    void stack(Cashier cashier, GameList inventory) {
        cashier.report("is stacking games on the shelf");
        int curr_dim;
        int index = 1;
        int pick_dim = Integer.MIN_VALUE;
        ArrayList<String> gameAssigned = new ArrayList<>();
        //Loop through width to restack from shortest to tallest
        //EXCEPT stack multi-inventory games before 1 inventory games
        for (int i = 0; i < inventory.size(); i++) {
            int next = pick_dim;
            String gameName = "";
            for (Map.Entry<String, Game> item :
                    inventory.entrySet()) {
                if (!gameAssigned.contains(item.getKey())) {
                    curr_dim = item.getValue().getGameDimension().get(index);
                    int curr_inv = item.getValue().getCount();
                    int pick_inv = 1;
                    if(inventory.get(gameName) != null){
                        pick_inv = inventory.get(gameName).getCount();
                    }
                    //Games dimension is bigger and not 1 inventory (unless next best is also 1)
                    if ((curr_dim > next && (curr_inv !=1 || pick_inv == 1)) || (curr_inv != 1) && (pick_inv == 1)) {
                        next = curr_dim;
                        gameName = item.getKey();
                    }
                }
            }
            Game game = inventory.get(gameName);
            game.setPosOnShelf(i + 1);
            gameAssigned.add(game.getGameName());
            cashier.report("stacked the game " + game.getGameName() + " in position " + String.valueOf(i + 1) + " because of its width of "
                    + game.getGameDimension().get(index) + " and it's inventory of " + game.getCount());
        }
    }
}