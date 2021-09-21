package com.design;

public class Util {
    public static final String[] employeeNames = {"Burt", "Ernie"};
    //TODO: Set these values to be right once tested better
    public static final Double[] vacSkill = {.5, .5};
    public static final String[] stackPref = {"width", "height"};

    public static final String[] boardGames = {"Catan", "Gloomhaven", "Risk"};
    public static final String[] familyGames = {"Monopoly", "Clue", "Life"};
    public static final String[] kidsGames = {"Mousetrap", "Candyland","Connect Four"};
    public static final String[] cardGames = {"Magic", "Pokemon", "Netrunner"};

    /**
     * Function to handle printing syntax
     */
    public static void print(String content){
        System.out.println(content);
    }
    /**
     * Pretty prints games in inventory
     */
    public static void printInventory(GameList inventory){
        print("Games in inventory: ");
        inventory.printGameAmount();
    }
    /**
     * Pretty prints broken games
     */
    public static void printBroken(GameList brokenGames){
        print("Games that were broken: ");
        brokenGames.printGameAmount();
    }
}
