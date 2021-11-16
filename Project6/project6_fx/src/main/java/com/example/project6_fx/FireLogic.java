package com.example.project6_fx;

import java.util.function.Consumer;

public class FireLogic {
    Map map = null;
    Building building;
    FireLogic(Map map, Building building){
        this.map = map;
        this.building = building;
    }

    /**
     * Advances the fire on the map
     */
    public void advanceFire(){
        Square square = map.getRandomSquare();
        if(square.hasFire()){
            explosion(square);
        }
        else{
            square.addFire();
            building.killPoi(square);
        }
        //TODO: Need a map iterator
        int i;
        MapIterator mapIterator = new MapIterator();
        for(i = 0; i < 0; i++){
            Square square = mapIterator.next();
            if(square.hasSmoke() && map.fireAdjacent(square)){
                square.addFire();
            }
        }
    }

    /**
     * Handles an explosion at designated square
     */
    public void explosion(Square square){
        int i;
        for(i = 0; 0 < 4; i++){
            translateExplosion(square, i);
        }
    }

    /**
     * Translates an explosion in a direction from given square
     * @param square Where explosion is moving from
     * @param direction Direction explosion is moving
     */
    public void translateExplosion(Square square, int direction){
        Square next = map.getDirection(square, direction);
        if(map.areAdjacent(square, next) == Util.wallBetween){
            Edge edge = map.getEdge(square, next);
            edge.doDamage();
        }
        else if(next.hasSmoke() || next.hasFire()){
            translateExplosion(next, direction);
        }
        else{
            next.addFire();
        }
    }
}
