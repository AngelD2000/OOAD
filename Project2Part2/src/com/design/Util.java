package com.design;

import java.util.ArrayList;
import java.util.Random;

public abstract class Util {
    //Initialization data for Store simulation
    public static final int simDays = 11;
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

    /**
     * Assign the dimensions of a game randomly
     * Could be modified
     *      length: between 1 and 10
     *      width: between 10 and 20
     *      height: between 1 and 20
     */
    public static ArrayList<Integer> assign_dim(){
        ArrayList<Integer> dimensions = new ArrayList<>();
        Random dim = new Random();
        int length = dim.nextInt(11) + 1;
        int width = dim.nextInt((20 - 10) + 1) + 10;
        int height = dim.nextInt(21) + 1;
        dimensions.add(length);
        dimensions.add(width);
        dimensions.add(height);
        return dimensions;
    }
}
