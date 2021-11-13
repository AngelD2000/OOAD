package com.example.project6_fx;

public class Map {
    Map(){

    }
    public Square getLoc(Integer[] loc){
        //TODO: Make this actually get the square
        return new Square();
    }

    /**
     * Returns the adjacency of notAdjacent, wallBetween, or adjacent from Util
     * @return An integer representing the adjacency of two squares
     */
    public int areAdjacent(Square firstSquare, Square secondSquare){

    }

    /**
     * Returns if a square is adjacent to any fire (not through a wall)
     * @param square The square to check if fire is adjacent
     * @return A boolean
     */
    public boolean fireAdjacent(Square square){

    }

    /**
     * Returns edge between two squares
     */
    public Edge getEdge(Square firstSquare, Square secondSquare){

    }
    /**
     * Get a random square on the map that is inside
     */
    public Square getRandomSquare(){

    }

    /**
     * Gets the square in the direction relative to given square
     */
    public Square getDirection(Square square, Integer direction){

    }
}
