package com.design;

public class Print {

    /**
     * Function to handle printing syntax
     */
    public void print(String content){
        System.out.println(content);
    }
    /**
     * Pretty prints games in inventory
     */
    public void printInventory(GameList inventory){
        print("Games in inventory: ");
        inventory.prettyPrintList();
    }
    /**
     * Pretty prints broken games
     */
    public void printBroken(GameList brokenGames){
        print("Games that were broken: ");
        brokenGames.prettyPrintList();
    }
}
