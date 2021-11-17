package com.example.project6_fx;

public class MapFactory {
    //TODO: All of this
    Map map = null;
    MapFactory(){

    }

    //Just guessing parameters this will need
    public Map makeMap(Game game){
        //Make the map
        setup(game.getFireLogic(), game.getBuilding());
    }

    public void setup(FireLogic fireLogic, Building building){
        for(int i = 0; i < 3; i++){
            fireLogic.explosion(map.getRandomSquare());
        }
        building.placePoi();
    }
}