package com.example.proj6restartreal;

public class FireLogic {
    Map map;
    Building building;
    Game game;
    FireLogic(Map map, Building building, Game game){
        this.map = map;
        this.building = building;
        this.game = game;
    }

    /**
     * Advances the fire on the map
     */
    public void advanceFire(){
        Square square = map.getRandomSquare();
        Util.print("Added fire: " + square.getX() + " " + square.getY() + "\n");
        if(square.hasFire()){
            explosion(square);
        }
        else{
            map.updateSquare(square, Util.addFire);
            building.killPoi(square);
        }
        while(map.hasNext()){
            square = map.next();
            if(square.hasSmoke() && map.fireAdjacent(square)){
                map.updateSquare(square, Util.addFire);
            }
        }
        map.resetIterator();
    }

    /**
     * Handles an explosion at designated square
     */
    public void explosion(Square square){
        Util.print("Explosion: " + square.getX() + ", " + square.getY() + "\n");
        map.updateSquare(square, Util.addFire);
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
//        Util.print(next.getX() + ", " + next.getY() + " compared to " + square.getX() + ", " + square.getY() + "\n");
//        Util.print(map.areAdjacent(square, next) + "\n");
        if(map.areAdjacent(square, next) == Util.wallBetween){
            Util.print("Wall hit\n");
            Edge edge = map.getEdge(square, next);
            edge.doDamage(game);
        }
        else if(next.hasSmoke() || next.hasFire()){
            translateExplosion(next, direction);
        }
        else{
            map.updateSquare(next, Util.addFire);
        }
    }
}
