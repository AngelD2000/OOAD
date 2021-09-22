package com.design;

import java.util.ArrayList;

//This class has high Cohesion as it is designed with the single purpose of representing and storing information about each game
abstract class Game{
    //This private data is an example of encapsulation
    private String gameName;
    private ArrayList<Integer> gameDimension;
    private int cost;
    private int posOnShelf;
    private int count = 0;
//    private Game type;
    private int numSold = 0;

    Game() { }
    Game(String name, ArrayList<Integer> dim, int price){
        this.gameName = name;
        this.gameDimension = dim;
        this.cost = price;
    }

    public int getCount() {
        return count;
    }



    void incrementCount(int value){
        this.count += value;
        if(value == -1){
            this.numSold += 1;
        }
    }

//    public void newGame(String name, ArrayList<Integer> dim, int price, Game Type){
//        this.gameName = name;
//        this.gameDimension = dim;
//        this.cost = price;
////        this.type = Type;
//    }

    public void setCount(int count) {
        this.count = count;
    }

//    public void setType(Game type) {
//        this.type = type;
//    }

//    public Game getType() {
//        return type;
//    }

    public int getCost() {
        return cost;
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

    public ArrayList<Integer> getGameDimension() {
        return gameDimension;
    }


}

// Each of these classes is an example of Inheritance
class FamilyGame extends Game{
    FamilyGame() {}
    FamilyGame(String name, ArrayList<Integer> dim, int price){
        super(name, dim, price);
    }

}

class KidsGame extends Game{
    KidsGame() {}
    KidsGame(String name, ArrayList<Integer> dim, int price){
        super(name, dim, price);
    }

}

class BoardGame extends Game{
    BoardGame() {}
    BoardGame(String name, ArrayList<Integer> dim, int price){
        super(name, dim, price);
    }

}

class CardGame extends Game{
    CardGame() {}
    CardGame(String name, ArrayList<Integer> dim, int price){
        super(name, dim, price);
    }

}