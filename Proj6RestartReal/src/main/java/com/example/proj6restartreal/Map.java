package com.example.proj6restartreal;

import java.util.*;
import java.util.function.Consumer;

/**
 * This is the model in the MVC
 * THis also is an iterator to enable iterating through all squares in the game map easily
 */
public class Map implements Iterator<Square> {
    private Square[][] map = new Square[Util.mapHeight][Util.mapWidth];

    private int rowIndex;
    private int columnIndex;

    Map(){
        rowIndex = 0;
        columnIndex = 0;
        for(int i = 0; i < Util.mapHeight; i++) {
            for(int j = 0; j < Util.mapWidth; j++) {
                map[i][j] = new BaseSquare(i, j);
            }
        }
    }

    /**
     * Update a square using the list of Square Update Parameters in Util
     * @param square Square to update
     * @param action action as defined in Util under Square Update
     */
    public void updateSquare(Square square, int action) {
        int[] loc = getPos(square);

        switch(action) {
            case Util.addFire:
                map[loc[0]][loc[1]] = square.addFire();
                break;
            case Util.removeFire:
                map[loc[0]][loc[1]] = square.removeFire();
                break;
            case Util.addPoi:
                map[loc[0]][loc[1]] = square.addPoi();
                break;
            case Util.removePoi:
                map[loc[0]][loc[1]] = square.removePoi();
                break;
            case Util.addVict:
                map[loc[0]][loc[1]] = square.addVictim();
                break;
            case Util.removeVict:
                map[loc[0]][loc[1]] = square.removeVictim();
                break;
            case Util.addOutside:
                map[loc[0]][loc[1]] = square.addOutside();
                break;
        }
    }

    /**
     * Gets the square associated with the coordinates
     * @param loc Array of [i, j] location on the map
     * @return The associated square object
     */
    public Square getLoc(int[] loc){
        if(loc[0] >= 0 && loc[1] >= 0 && loc[0] < Util.mapHeight && loc[1] < Util.mapWidth) {
            return map[loc[0]][loc[1]];
        }
        else {
            return null;
        }
    }

    /**
     * Gets the i,j position of a square
     * @param square Square object to get its location
     * @return The associated i, j coordinates
     */
    public int[] getPos(Square square){
        return new int[] {square.getX(), square.getY()};
    }

    /**
     * Get a list of the squares neighboring the current one
     * @param square Current square
     * @return ArrayList of neighboring squares. Will be of size 4 if all neighbors exist or smaller if on an edge without all 4 neighbors
     */
    public ArrayList<Square> getNeighbors(Square square) {
        int[] pos = getPos(square);

        ArrayList<Square> squares = new ArrayList<Square>();
        //Get right neighbor
        pos[0] += 1; //pos[0] + 1
        //pos[1] + 0
        squares.add(getLoc(pos));

        //Get bottom neighbor
        pos[0] -= 1; //pos[0] + 0
        pos[1] += 1; //pos[1] + 1
        squares.add(getLoc(pos));

        //Get left neighbor
        pos[0] -= 1; //pos[0] - 1
        pos[1] -= 1; //pos[1] + 0
        squares.add(getLoc(pos));

        //Get top neighbor
        pos[0] += 1; //pos[0] + 1
        pos[1] -= 1; //pos[1] - 1
        squares.add(getLoc(pos));

        // Strip null out of neighbors (will be null if on an edge where one or more neighbors doesn't exist
        squares.removeAll(Collections.singleton(null));
        return squares;
    }

    /**
     * Returns the adjacency of two squares as values from Util of notAdjacent, wallBetween, or adjacent.
     * @param firstSquare first square
     * @param secondSquare second square to check against
     * @return An integer representing the adjacency of two squares
     */
    public int areAdjacent(Square firstSquare, Square secondSquare) {
        ArrayList<Square> neighbors = getNeighbors(firstSquare);
        if(!neighbors.contains(secondSquare)) {
            return Util.notAdjacent;
        }
        else {
            if(hasEdge(firstSquare,secondSquare)) {
                return Util.wallBetween;
            }
            else {
                return Util.adjacent;
            }
        }
    }

    /**
     * Returns if a square is adjacent to any fire (not through a wall)
     * @param square The square to check if fire is adjacent
     * @return A boolean
     */
    public boolean fireAdjacent(Square square){
        ArrayList<Square> neighbors = getNeighbors(square);

        for(Square sq : neighbors){
            if (sq.hasFire()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an integer representation of the direction the second square is in relation to the first based on the directions in Util
     * @param firstSquare first square
     * @param secondSquare second square to check against
     * @return Int representing the direction the second square is in relation to the first from Util (north, south, east, west, -1)
     */
    private int getDirection(Square firstSquare, Square secondSquare) {
        ArrayList<Square> neighbors = getNeighbors(firstSquare);
        if (!neighbors.contains(secondSquare) || firstSquare.equals(secondSquare)) {
            return -1; // No direction
        } else {
            int[] pos1 = getPos(firstSquare);
            int[] pos2 = getPos(secondSquare);
            int rowdiff = pos2[0] - pos1[0];
            int coldiff = pos2[1] - pos1[1];

            if (rowdiff == -1) {
                return Util.north;
            } else if (rowdiff == 1) {
                return Util.south;
            } else if (coldiff == -1) {
                return Util.west;
            } else if (coldiff == 1) {
                return Util.east;
            }
        }
        return -1;
    }

    /**
     * Returns true if there is an edge between two squares
     * @param firstSquare first square
     * @param secondSquare second square to compare against
     * @return Boolean
     */
    public boolean hasEdge(Square firstSquare, Square secondSquare){
        return getEdge(firstSquare, secondSquare) != null;
    }

    /**
     * Returns edge between two squares
     * @param firstSquare first square
     * @param secondSquare second square to compare against
     * @return Edge object between the two squares or null if no edge
     */
    public Edge getEdge(Square firstSquare, Square secondSquare){
        int direction = getDirection(firstSquare, secondSquare);

        // No edge between the two
        if(direction == -1) {
            return null;
        }
        return firstSquare.getEdge(direction);
    }

    /**
     * Get a random square on the map that is inside (Bounded 1 to Util.mapHeight-1 and 1 to Util.mapWidth-1 (exclude outside squares))
     * @return Square randomly selected from inside the map
     * @throws IllegalStateException if it tries to return an outside square (should never happen)
     */
    public Square getRandomSquare(){
        //Bounded 1 to Util.mapHeight-1 and 1 to Util.mapWidth-1 (exclude outside squares)
        Random rand = new Random();
        int loci = rand.nextInt(Util.mapHeight-2) + 1;
        int locj = rand.nextInt(Util.mapWidth-2) + 1;

        if(map[loci][locj].isOutside()) {
                    throw new IllegalStateException("Cant explode a outside square");
        }
        return map[loci][locj];
    }

    /**
     * Gets the square in the direction relative to given square
     * @param square square to start at
     * @param direction Integer direction to look in based on directions in Util
     * @return Square in the given direction or the input square if no square in that direction
     */
    public Square getSquareInDirection(Square square, int direction){
        int[] pos = getPos(square);
        // Determine new square coordinates
        switch(direction) {
            case Util.north:
                pos[1] += 1;
                break;
            case Util.south:
                pos[1] -= 1;
                break;
            case Util.east:
                pos[0] += 1;
                break;
            case Util.west:
                pos[0] -= 1;
                break;
        }
        // Can't go that direction as it is an edge square
        if(pos[0] < 0 || pos[0] >= Util.mapHeight || pos[1] < 0 || pos[1] >= Util.mapWidth) {
            return square;
        }
        return getLoc(pos);
    }

    /**
     * Resets the Iterator indexes to 0. Must be called after done iterating through the map
     */
    public void resetIterator() {
        rowIndex = 0;
        columnIndex = 0;
    }

    /**
     * Checks if there are more Squares to iterate over in the map
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return rowIndex < map.length && columnIndex <= map[rowIndex].length;
    }

    /**
     * Returns the next square in the map
     * @return Square
     * @throws NoSuchElementException if no more squares to iterate over are available
     */
    @Override
    public Square next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Square answer = map[rowIndex][columnIndex++];
        // Move on to next row
        if (columnIndex >= map[rowIndex].length) {
            rowIndex++;
            columnIndex = 0;
        }
        return answer;
    }

    /**
     * Not implemented
     * @throws UnsupportedOperationException Not implemented
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Not implemented
     * @throws UnsupportedOperationException Not implemented
     */
    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Testing function to print the map to CLI as a set of xy coordinates
     */
    public void mapBasicPrint(){
        Square square;
        while(this.hasNext()){
            if(columnIndex == 0){
                Util.print("\n");
            }
            square = this.next();
            Util.print(" " + square.getX() + square.getY());
        }
        this.resetIterator();
        Util.print("\n");
    }
}
