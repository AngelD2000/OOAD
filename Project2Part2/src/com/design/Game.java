package com.design;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Game{
    private String gameName;
    private ArrayList<Integer> gameDimension;
    private int cost;
    private int posOnShelf;
    private int count = 0;
    private Game type;
    private int numSold = 0;

    public int getCount() {
        return count;
    }



    void incrementCount(int value){
        this.count += value;
        if(value == -1){
            this.numSold += 1;
        }
    }

    public void newGame(String name, ArrayList<Integer> dim, int price, Game Type){
        this.gameName = name;
        this.gameDimension = dim;
        this.cost = price;
        this.type = Type;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setType(Game type) {
        this.type = type;
    }

    public Game getType() {
        return type;
    }

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

    /**
     * Assign the dimensions of a game randomly
     * Could be modified
     *      length: between 1 and 10
     *      width: between 10 and 20
     *      height: between 1 and 20
     */

    public ArrayList<Integer> assign_dim(){
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

class FamilyGames extends Game{

}

class KidsGames extends Game{

}

class BoardGames extends Game{

}

class CardGames extends Game{

}