package com.example.project6_fx;

import java.util.ArrayList;

public class Game {
    Building building = null;
    FirefighterLogic firefighterLogic = null;
    FireLogic fireLogic = null;
    Game(){
        building = new Building();
        firefighterLogic = new FirefighterLogic(this);
        fireLogic = new FireLogic(building.getMap(), building);
    }
    Map getMap(){
        return building.getMap();
    }

    /**
     * Gets the square associated with the coordinates
     * @param loc Array of [x, y] location on the map
     * @ return The associated square
     */
    Square getLoc(Integer[] loc){
        return building.getMap().getLoc(loc);
    }

    /**
     * Gets the array of legal actions given the targeted location
     * @param loc Array of [x, y] location on the map
     * @ return An arraylist of legal actions
     */
    ArrayList<Integer> getActions(Integer[] loc){
        return firefighterLogic.getActions(loc);
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
            firefighterLogic.chop(square);
        }
        if (action == Util.chop){
            firefighterLogic.hose(square);
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
        }
        else if(building.getPerished() >= 4 || building.getDamage() < 0){
            Util.print("The game ended in a loss...");
        }
        //Add poi
        building.placePoi();
        //Start next turn
        firefighterLogic.nextTurn();
    }
}
