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
        return map[loc[0]][loc[1]];
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
        return new int[] {-1, -1};
    }

    /**
     * Returns the adjacency of notAdjacent, wallBetween, or adjacent from Util
     * @return An integer representing the adjacency of two squares
     */
    public int areAdjacent(Square firstSquare, Square secondSquare) {
        int[] pos1 = getPos(firstSquare);
        int[] pos2 = getPos(secondSquare);
        int rowdiff = Math.abs(pos1[0] - pos2[0]);
        int coldiff = Math.abs(pos1[1] - pos2[1]);

        // Same row
        if (rowdiff == 0) {
            // same row, adj col (east/west)
            if (Arrays.asList(-1, 1).contains(coldiff)) {
                //Check if wall between
                if (getEdge(firstSquare, secondSquare) == null) {
                    return Util.adjacent;
                } else {
                    return Util.wallBetween;
                }
            }
        }
        //Different adj row
        else if (Arrays.asList(-1, 1).contains(rowdiff)) {
            // adj row, same col (north/south)
            if (coldiff == 0) {
                //Check if wall between
                if (getEdge(firstSquare, secondSquare) == null) {
                    return Util.adjacent;
                } else {
                    return Util.wallBetween;

                }
            }
        }
        return Util.notAdjacent;
    }

    /**
     * Returns if a square is adjacent to any fire (not through a wall)
     * @param square The square to check if fire is adjacent
     * @return A boolean
     */
    public boolean fireAdjacent(Square square){
        return false;
    }

    /**
     * Returns edge between two squares
     */
    public Edge getEdge(Square firstSquare, Square secondSquare){
        return new Edge();
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
    public Square getDirection(Square square, Integer direction){
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
