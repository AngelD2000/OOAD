package com.design;

import java.text.NumberFormat;
import java.util.*;

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
    public static List<Double> cookieOdds = Arrays.asList(0.0, .2, -.1);
    public static final double cookieDesire = .5;
    public static final int maxCookiesDesired = 3;
    public static final double monsterChance = .01;
    public static final int maxMonsterBreaks = 6;
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
    public static final String announcerName = "Guy";
    public static final String announcerType = "Eager"; // Either "Eager" or "Lazy"

    public static final String[] names = {
            "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "William", "Sophia", "Elijah", "Isabella", "James",
            "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper", "Ethan", "Evelyn", "Adam", "Alex",
            "Aaron", "Ben", "Carl", "Dan", "David", "Edward", "Fred", "Frank", "George", "Hank", "Ike", "John",
            "Jack", "Joe", "Matthew", "Mark", "Nathan", "Otto", "Paul", "Peter", "Roger", "Steve", "Thomas", "Tim",
            "Ty", "Victor", "Ashley", "Sarah", "Alexis", "Samantha", "Jessica", "Elizabeth", "Taylor", "Lauren",
            "Alyssa", "Alexandra", "Savannah", "Chloe", "Rebecca" };

    //Order of odds: Family Gamer, Kid Gamer, Card Gamer, Board Gamers, Cookie Monster
    public static final String[] customerTypes = {"Family Gamer", "Kid Gamer", "Card Gamer", "Board Gamer", "Cookie Monster"};
    public static final Double[] customerChance = {.245, .49, .735, .99, 1.0};

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
        int length = rndFromRange(1, 12);
        int width = rndFromRange(1, 12);
        int height = rndFromRange(1, 12);
        dimensions.add(length);
        dimensions.add(width);
        dimensions.add(height);
        return dimensions;
    }
    /**
     * Picks random int from min to max inclusive
     * https://www.baeldung.com/java-generating-random-numbers-in-range via Bruce Montgomery solution to project 2
     */
    static int rndFromRange(int min, int max) {
        //returns a uniform inclusive random number from a given min and max range
        return (int) ((Math.random() * ((max+1) - min)) + min);
    }
    /**
     * Returns string as pretty mones
     * https://stackoverflow.com/questions/13791409/java-format-double-value-as-dollar-amount via Bruce Montgomery solution to project 2
     */
    static String asDollar(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }

    /**
     * Calculates if an event with odds has occurred
     * Odds 1=Always true, Odds 0=Never true
     */
    static boolean testOdds(double odds){
        //Description of Math.random: https://www.geeksforgeeks.org/java-math-random-method-examples/
        double randomValue = Math.random();
        if(randomValue < odds){
            return true;
        }
        else{
            return false;
        }
    }
    public static double uniform() {
        Random random = new Random();
        return random.nextDouble();
    }
    /**
     * Returns a random integer from a Poisson distribution with mean &lambda;.
     * Source: https://introcs.cs.princeton.edu/java/stdlib/StdRandom.java.html
     * @param  lambda the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution with mean {@code lambda}
     * @throws IllegalArgumentException unless {@code lambda > 0.0} and not infinite
     */
    public static int poisson(double lambda) {
        if (!(lambda > 0.0))
            throw new IllegalArgumentException("lambda must be positive: " + lambda);
        if (Double.isInfinite(lambda))
            throw new IllegalArgumentException("lambda must not be infinite: " + lambda);
        // using algorithm given by Knuth
        // see http://en.wikipedia.org/wiki/Poisson_distribution
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform();
        } while (p >= expLambda);
        return k-1;
    }

    /**
     * Picks a random name from the Util.names array for customers and demonstrators
     */
    public static String pickName(){
        int rand = Util.rndFromRange(1, names.length);
        return names[rand-1];
    }

    /**
     * Picks a customer type based on an array of probability ranges for different events
     * https://stackoverflow.com/questions/48121883/best-way-of-setting-up-probabilities-for-multiple-items-in-java
     */
    public static String getCustomerType() {
        int event = Arrays.binarySearch(customerChance, Math.random());
        if (event < 0) event = -event - 1;
        switch (event) {
            case 0:
                return customerTypes[0];
            case 1:
                return customerTypes[1];
            case 2:
                return customerTypes[2];
            case 3:
                return customerTypes[3];
            case 4:
                return customerTypes[4];
            default:
                return "";
        }
    }
}

