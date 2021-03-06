package com.example.proj6restartreal;

import java.util.ArrayList;

public class Game {
    Building building = null;
    FirefighterLogic firefighterLogic = null;
    FireLogic fireLogic = null;
    int damage = 24;
    Map map = null;
    ViewManager viewManager;
    Game(ViewManager viewManager){
        this.viewManager = viewManager;
        MapFactory mapFactory = MapFactory.getInstance();
        map = mapFactory.makeMap();
        building = new Building(map);
        firefighterLogic = new FirefighterLogic(this);
        fireLogic = new FireLogic(map, building, this);

        mapFactory.setup(map, fireLogic, building);
    }
    Map getMap(){
        return map;
    }
    Building getBuilding(){return building;}
    FireLogic getFireLogic(){return fireLogic;}

    int getDamage(){
        return damage;
    }
    void incrementDamage(){
        damage -= 1;
    }

    /**
     * Gets the square associated with the coordinates
     * @param loc Array of [x, y] location on the map
     * @ return The associated square
     */
    Square getLoc(int[] loc){
        return getMap().getLoc(loc);
    }

    /**
     * Gets the array of legal actions given the targeted location
     * @param loc Array of [x, y] location on the map
     * @ return An arraylist of legal actions
     */
    ArrayList<Integer> getActions(int[] loc){
        Square target = getMap().getLoc(loc);
        return firefighterLogic.getActions(target);
    }

    /**
     * Game handles performing the action
     * @param action Integer from Util representing action to take
     * @ return An arraylist of legal actions
     */
    void takeAction(Integer action, Square square){
        if (action == Util.move){
            firefighterLogic.move(square);
            flipPoi(square);
        }
        if (action == Util.drag){
            firefighterLogic.drag(square);
            if(square.isOutside()){
                building.rescue(square);
            }
        }
        if (action == Util.hose){
            firefighterLogic.hose(square);
        }
        if (action == Util.chop){
            firefighterLogic.chop(square);
        }
        if (firefighterLogic.getActions() == 0){
            endTurn();
        }
    }

    /**
     * Game handles flipping the POI if applicable
     */
    void flipPoi(Square square){
        if(square.hasPoi()){
            building.flipPoi(square);
        }
    }

    /**
     * Game handles ending the turn
     * Spread fire
     */
    void endTurn(){
        //Spread fire
        fireLogic.advanceFire();
        //Check end conditions
        if(building.getSaved() >= 7){
            Util.print("The game ended in a victory!");
            viewManager.endUI("The firefighters were victorious!");
        }
        else if(building.getPerished() >= 4){
            Util.print("The game ended in a loss...");
            viewManager.endUI("Too many people were allowed to die...");
        }
        else if(damage < 0){
            Util.print("The game ended in a loss...");
            viewManager.endUI("The building collapsed before everyone was saved!");
        }
        //Add poi
        building.placePoi();
        //Start next turn
        firefighterLogic.nextTurn();
    }
}
