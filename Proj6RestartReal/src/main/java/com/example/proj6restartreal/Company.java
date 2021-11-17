package com.example.proj6restartreal;

public class Company {
    Firefighter firefighters[]= null;
    Integer activeFirefighter = 0;

    Company(Game game){
        firefighters= new Firefighter[Util.numFirefighters];
        for (int i = 0; i < Util.numFirefighters; i++) {
            Square location = game.getLoc(Util.firefighterLocations[i]);
            firefighters[i] = new Firefighter(location);
            firefighters[i].setImage(Util.firefighterImages[i]);
        }
    }

    /**
     * Gets the location of the active firefighter
     * @return The associated square
     */
    public Square getActiveLocation(){
        return firefighters[activeFirefighter].getLoc();
    }

    /**
     * Moves the firefighter
     * @return The associated square
     */
    public void move(Square square){
        firefighters[activeFirefighter].setLoc(square);
    }

    public void nextFirefighter(){
        activeFirefighter += 1;
        if (activeFirefighter >= Util.numFirefighters){
            activeFirefighter = 0;
        }
    }
}
