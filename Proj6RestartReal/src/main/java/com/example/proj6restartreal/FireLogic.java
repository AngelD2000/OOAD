package com.example.proj6restartreal;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FireLogic {
    Map map;
    Building building;
    Game game;
    private final int[][] lastFireCoord = new int[1][];

    FireLogic(Map map, Building building, Game game){
        this.map = map;
        this.building = building;
        this.game = game;
    }

    /**
     * Stores the coordinate of where the last fire occured
     * @param x coordinate
     * @param y coordinate
     */
    public void storeFire(int x, int y){
        int[] temp = new int[2];
        temp[0] = x;
        temp[1] =y;
        lastFireCoord[0] = temp;
    }

    /**
     * Gets the last fire coordinate
     */
    public int[][] getLastFireCoord() {
        return lastFireCoord;
    }

    /**
     * Advances the fire on the map
     */
    public void advanceFire(){
        Square square = map.getRandomSquare();
        storeFire(square.getX(),square.getY());
        Util.print("Added fire: " + square.getX() + " " + square.getY() + "\n");
        building.killPoi(square);
        if(square.hasFire()){
            explosion(square);
        }
        else{
            map.updateSquare(square, Util.addFire);
        }
        while(map.hasNext()){
            square = map.next();
            if(square.hasSmoke() && map.fireAdjacent(square)){
                map.updateSquare(square, Util.addFire);
            }
            if((square.hasFire() || square.hasSmoke()) && (square.hasPoi() || square.hasVictim())){
                building.killPoi(square);
            }
        }
        map.resetIterator();
    }
    /**
     * Makes an indicated square fire
     */
    public void makeFire(Square square){
        building.killPoi(square);
        while(!square.hasFire() && !square.isOutside()){
            map.updateSquare(square, Util.addFire);
            square = map.getLoc(new int[]{square.getX(), square.getY()});
        }
    }

    /**
     * Handles an explosion at designated square
     */
    public void explosion(Square square){
        Util.print("Explosion: " + square.getX() + ", " + square.getY() + "\n");
        makeFire(square);
        for(int i = 0; i < 4; i++){
            translateExplosion(square, i);
        }
    }

    /**
     * Translates an explosion in a direction from given square
     * @param square Where explosion is moving from
     * @param direction Direction explosion is moving
     */
    public void translateExplosion(Square square, int direction){
        Square next = map.getSquareInDirection(square, direction);
        if(map.areAdjacent(square, next) == Util.wallBetween){
            Edge edge = map.getEdge(square, next);
            edge.doDamage(game);
        }
        else if(next.hasSmoke() || next.hasFire()){
            translateExplosion(next, direction);
        }
        else{
            makeFire(next);
        }
    }
}
