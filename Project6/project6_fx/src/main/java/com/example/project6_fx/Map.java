package com.example.project6_fx;

import java.util.*;
import java.util.function.Consumer;

public class Map implements Iterator<Square> {
    private Square[][] map = new Square[Util.mapWidth][Util.mapHeight];

    private int rowIndex;
    private int columnIndex;

    Map(){
        rowIndex = 0;
        columnIndex = 0;

        for(int i = 0; i < Util.mapWidth; i++) {
            for(int j = 0; j < Util.mapHeight; j++) {
                map[i][j] = new Square();
            }
        }
    }

    /**
     * Gets the square associated with the coordinates
     * @param loc Array of [x, y] location on the map
     * @return The associated square
     */
    public Square getLoc(int[] loc){
        if(loc[0] >= 0 && loc[1] >= 0 && loc[0] <= Util.mapWidth && loc[1] <= Util.mapHeight) {
            return map[loc[0]][loc[1]];
        }
        else {
            return null;
        }
    }

    /**
     * Gets the position of a square
     * @param square Square object to get its location
     * @return The associated  i, j coordinates
     */
    public int[] getPos(Square square){
        for(int i = 0; i < Util.mapWidth; i++) {
            for(int j = 0; j < Util.mapHeight; j++) {
                if(map[i][j].equals(square)) {
                    return new int[] {i, j};
                }
            }
        }
        throw new NoSuchElementException("That Square doesnt exist");
    }

    public ArrayList<Square> getNeighbors(Square square) {
        int[] pos = getPos(square);

        ArrayList<Square> squares = new ArrayList<Square>();
        pos[0] += 1;
        squares.add(getLoc(pos));
        pos[1] += 1;
        squares.add(getLoc(pos));
        pos[0] -= 2;
        squares.add(getLoc(pos));
        pos[1] -= 2;
        squares.add(getLoc(pos));

        // Strip null out of neighbors (will be null if on an edge where one or more neighbors doesnt exist
        squares.removeAll(Collections.singleton(null));
        return squares;
    }

    /**
     * Returns the adjacency of notAdjacent, wallBetween, or adjacent from Util
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
            if (sq instanceof FireSquare) {
                return true;
            }
        }
        return false;
    }

    private int getDirection(Square firstSquare, Square secondSquare) {
        ArrayList<Square> neighbors = getNeighbors(firstSquare);
        if (!neighbors.contains(secondSquare) || firstSquare.equals(secondSquare)) {
            return -1; // No direction
        } else {
            int[] pos1 = getPos(firstSquare);
            int[] pos2 = getPos(secondSquare);
            int rowdiff = pos1[0] - pos2[0];
            int coldiff = pos1[1] - pos2[1];

            if (rowdiff == -1) {
                return Util.south;
            } else if (rowdiff == 1) {
                return Util.north;
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
     */
    public boolean hasEdge(Square firstSquare, Square secondSquare){
        return getEdge(firstSquare, secondSquare) != null;
    }

    /**
     * Returns edge between two squares
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
     * Get a random square on the map that is inside
     */
    public Square getRandomSquare(){
        //Bounded 1 to Util.mapHeight and 1 to Util.mapWidth
        Random rand = new Random();
        int loci = rand.nextInt(Util.mapHeight-1) + 1;
        int locj = rand.nextInt(Util.mapWidth-1) + 1;
        return map[loci][locj];
    }

    /**
     * Gets the square in the direction relative to given square
     */
    public Square getSquareInDirection(Square square, Integer direction){
        int[] pos = getPos(square);

        // Can't go that direction as it is an edge square
        if(pos[0] <= 0 || pos[0] >= Util.mapWidth || pos[1] <= 0 || pos[1] >= Util.mapHeight) {
            return square;
        }

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
        return getLoc(pos);
    }

    public void resetIterator() {
        rowIndex = 0;
        columnIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return rowIndex < map.length && columnIndex < map[rowIndex].length;
    }

    @Override
    public Square next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // Move on to next row
        if (columnIndex >= map[rowIndex].length) {
            rowIndex++;
            columnIndex = 0;
        }
        return map[rowIndex][columnIndex++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
