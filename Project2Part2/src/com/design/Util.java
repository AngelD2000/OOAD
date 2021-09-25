package com.design;

import java.util.ArrayList;
import java.util.Random;

public abstract class Util {
    //Initialization data for Store simulation
    //Constants
    public static final int simDays = 30;
    //Price that store buys cookies at, needs to be *2 when sold to customer
    public static final double cookiePricePerDozen = 12;
    public static final int dozen = 12;
    public static final int maxInventory = 3;
    public static final int noneConsumed = 0;
    public static final int consumed = 1;
    public static final int dissapointed = 2;
    public static final double cookieDesire = .5;
    public static final int maxCookiesDesired = 3;
    //Init helpers
    public static final String[] employeeNames = {"Burt", "Ernie", "Bart"};
    public static final Double[] vacSkill = {.1, .05, .02};
    public static StackShortToTall height = new StackShortToTall();
    public static StackWideToNarrow wide = new StackWideToNarrow();
    public static StackWidthAndCount odd = new StackWidthAndCount();
    public static final StackBehaviour[] stackPref = new StackBehaviour[]{wide, height, odd};
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
     * Pretty prints games in inventory and number sold
     */
    public static void printInventory(GameList inventory){
        print("Games in inventory and number sold: ");
//        inventory.printGameAmount();
        inventory.printGameAmountAndSold();
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
