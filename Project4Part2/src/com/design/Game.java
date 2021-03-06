package com.design;

import java.util.ArrayList;

//This class has high Cohesion as it is designed with the single purpose of representing and storing information about each game
abstract class Game{
    //This private data is an example of encapsulation
    protected String gameName;
    protected ArrayList<Integer> gameDimension;
    //This is the price to sell, not to order
    protected double price;
    protected int posOnShelf;
    protected int count = 0;
    protected int numSold = 0;
    protected String type = null;

    Game() { }
    Game(String name, ArrayList<Integer> dim, double price){
        this.gameName = name;
        this.gameDimension = dim;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public int getNumSold() {
        return numSold;
    }

    public double getPrice() { return price; }

    public void setPrice() {};

    void incrementCount(int value){
        this.count += value;
    }

    void incrementNumSold(int value) {
        this.numSold += value;
    }

    public void setCount(int newCount) {
        count = newCount;
    }

    public void setPosOnShelf(int posOnShelf) {
        this.posOnShelf = posOnShelf;
    }

    public int getPosOnShelf() {
        return posOnShelf;
    }

    public String getGameName() {
        return gameName;
    }

    public String getSimpleGameName() {
        return gameName;
    }

    public ArrayList<Integer> getGameDimension() {
        return gameDimension;
    }

    public String getType(){return type;}

    protected void setType(String type){this.type = type;}
}

// Each of these classes is an example of Inheritance
class FamilyGame extends Game{
    FamilyGame() {}
    FamilyGame(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
        setType(Util.family);
    }
}
class Monopoly extends FamilyGame{
    Monopoly() {}
    Monopoly(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Clue extends FamilyGame{
    Clue() {}
    Clue(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Life extends FamilyGame{
    Life() {}
    Life(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}

class KidsGame extends Game{
    KidsGame() {}
    KidsGame(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
        setType(Util.kid);
    }
}
class Mousetrap extends KidsGame{
    Mousetrap() {}
    Mousetrap(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Candyland extends KidsGame{
    Candyland() {}
    Candyland(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class ConnectFour extends KidsGame{
    ConnectFour() {}
    ConnectFour(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}

class BoardGame extends Game{
    BoardGame() {}
    BoardGame(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);setType(Util.board);
    }
}
class Gloomhaven extends BoardGame{
    Gloomhaven() {}
    Gloomhaven(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Risk extends BoardGame{
    Risk() {}
    Risk(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Catan extends BoardGame{
    Catan() {}
    Catan(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}

class CardGame extends Game{
    CardGame() {}
    CardGame(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
        setType(Util.card);
    }
}
class Magic extends CardGame{
    Magic() {}
    Magic(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Pokemon extends CardGame{
    Pokemon() {}
    Pokemon(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}
class Netrunner extends CardGame{
    Netrunner() {}
    Netrunner(String name, ArrayList<Integer> dim, double price){
        super(name, dim, price);
    }
}

/**
 * AddOn is a decorator for a game
 * This is some product that a customer will optionally choose to buy with the game
 */
class AddOn extends Game{
    Game base;
    //Max number of the add-on that will be bought
    int num_possible_buy;
    //Odds the customer will buy any add-on
    double odds_buy;
    //Price for each add-on
    double each_price;
    int num_bought = 0;
    AddOn(Game base, String name, int num_buy, double odds_buy, double each_price){
        this.gameName = name;
        this.base = base;
        this.num_possible_buy = num_buy;
        this.odds_buy = odds_buy;
        this.each_price = each_price;
        this.gameDimension = base.getGameDimension();

    }
    /**
     * Calculates if (and how many) add-ons are bought
     */
    public void setPrice() {
        if (Util.testOdds(odds_buy)) {
            num_bought = Util.rndFromRange(1, num_possible_buy);
        }
        else{
            num_bought = 0;
        }
    };
    public double getPrice() {
        return base.getPrice() + num_bought*each_price;
    }
    public String getGameName() {
        return base.getGameName() + " with " + num_bought + " add-ons of " + this.gameName;
    }
    public String getType(){return base.getType();}
    public String getSimpleGameName() {
        return base.getSimpleGameName();
    }
}